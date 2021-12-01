package com.nexlogica.form.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload; 
import org.apache.commons.io.FilenameUtils;

import com.nexlogica.form.client.validator.FileExtensionValidator;
import com.nexlogica.form.server.configreader.propertyFileReader;
import com.nexlogica.form.servlet.bean.FormBean;


@SuppressWarnings({ "serial", "unused" })

public class SubmitForm extends HttpServlet{

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		final FileExtensionValidator fev = new FileExtensionValidator();
		ArrayList<String> list = new ArrayList<String>();
		
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// Configure a repository (to ensure a secure temp location is used)
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		FormBean form = new FormBean();

		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			// Process the uploaded items
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();


				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					
					System.out.println("Name............" + name);
					System.out.println("Value............" + value);
					form.setField(name, value);
				} 
//				else if(){}
				else {
					if(fev.validate(item.getName())){
						
						String name = item.getFieldName();

						FileItemHeaders itemHeaders = item.getHeaders();
						Iterator<String> headers = itemHeaders.getHeaderNames();

						while(headers.hasNext()){
							String header = headers.next();
							String headerValue = itemHeaders.getHeader(header);

							System.out.println("Header Name............" + header);
							System.out.println("Header Value..........." + headerValue);
						}

						System.out.println("FieldName............" + name);
						System.out.println("Name............" + item.getName());
						System.out.println("contenttype............" + item.getContentType());

						InputStream stream = item.getInputStream();

						//////////////////////////////////////
						//// PUT PATH IN PROPERTIES FILE
						///////////////////////////////////////


						// replace characters to fix issue with attaching file location to file name
						String itemName = item.getName();
						if (itemName != null) {
							itemName = FilenameUtils.getName(itemName);
						}
						
						String fileName = "";
						
						// check if the file name list already contains the itemName
						// this is to check if there are identical attachments being submitted
						if(list.contains(itemName))
							fileName  = String.valueOf(form.getFormRandomNumber()) + "_" + list.size() + itemName;
						else
							fileName = String.valueOf(form.getFormRandomNumber()) + "_" + itemName;
						
						list.add(itemName);

						//String fileName = FilenameUtils.removeExtension(item.getName())+ "_" + String.valueOf(form.getFormRandomNumber()) + "." + FilenameUtils.getExtension(item.getName());

						String filePathPrefix = new propertyFileReader().getLocationProperty();

						String fullPath = filePathPrefix + fileName;
						OutputStream os = new FileOutputStream(fullPath);

						byte[] buffer = new byte[1024];
						int bytesRead;
						//read from is to buffer
						while((bytesRead = stream.read(buffer)) !=-1){
							os.write(buffer, 0, bytesRead);
						}


						stream.close();
						//flush OutputStream to write any buffered data to file
						os.flush();
						os.close();

						//////////////////////////////////////
						//// FILE CONTENT CHECKING
						//// currently unused, this method requires writing to file system before checking file
						///////////////////////////////////////
//						if(fev.identifyFileTypeUsingDefaultTikaForFile(fullPath) != item.getContentType()){
//							
//							// if the above check fails, start typing out the error message
//							response.setContentType("text/html");
//
//							PrintWriter out = response.getWriter();
//
//							// reponse number 4 means that there was a bad file type
//							out.print("4");
//
//							try {
//								Files.delete(FileSystems.getDefault().getPath(fullPath));
//							} catch (NoSuchFileException x) {
//								System.err.format("%s: no such" + " file or directory%n", fullPath);
//							} catch (DirectoryNotEmptyException x) {
//								System.err.format("%s not empty%n", fullPath);
//							} catch (IOException x) {
//								// File permission problems are caught here.
//								System.err.println(x);
//							}
//
//
//							throw new Exception("File content type invalid.");
//
//						}

						form.setField(name, fullPath);
					} else {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();

						// reponse number 1 means that there was a bad file type
						out.print("1");
						System.out.println("validation failed: " + FilenameUtils.getExtension(item.getName()).toLowerCase());
						throw new Exception("file extension invalid.");
					} 
				} 

			}

			XMLBuilder xmlBuilder = new XMLBuilder(form);
			String xmlFileName = String.valueOf(form.getFormRandomNumber()) + ".xml";

			String filePathPrefix = new propertyFileReader().getLocationProperty();

			xmlFileName = form.getAppellantLasttName() + form.getAppellantFirstName() + form.getAppealType() + xmlFileName;

			String xmlFilePath = filePathPrefix + xmlFileName;

			xmlBuilder.createXMLFile(xmlFilePath);

			response.setContentType("text/html");

			PrintWriter out = response.getWriter();

			// response number 0 will indicate to client side that everything was successful
			out.print("0");


		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws ServletException, IOException
	{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// response number 0 will indicate to client side that everything was successful
		out.println("0");
		System.out.println("Sending back 0");

	}
}