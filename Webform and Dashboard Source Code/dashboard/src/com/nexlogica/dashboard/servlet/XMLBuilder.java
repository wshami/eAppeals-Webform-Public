package com.nexlogica.dashboard.servlet;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nexlogica.dashboard.servlet.bean.FormBean;

public class XMLBuilder {
	private FormBean form;
	public XMLBuilder(FormBean form) {
		this.form = form;
	}

	// XML data that's going to be stored
	// case number
	// file path upload
	public void createXMLFile(String xmlFilePath) throws Exception
	{

		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement("form");
			document.appendChild(rootElement);
			
			Element form_number = document.createElement("caseNumber");
			form_number.appendChild(document.createTextNode(form.getCaseNumber()));
			rootElement.appendChild(form_number);		
			
			
			Element submitDate = document.createElement("submitDate");
			submitDate.appendChild(document.createTextNode(form.getSubmitDate()));
			rootElement.appendChild(submitDate);	
			
			Element form_comment = document.createElement("comment");
			form_comment.appendChild(document.createTextNode(form.getCommentBox()));
			rootElement.appendChild(form_comment);		
			

			List<String> filePathList = form.getFilePath();
			List<String> fileDescList = form.getFileDesc();
			List<String> fileNameList = form.getFileName();

			
			for(int i=0; i < filePathList.size(); i++) {
				
				Element attachments = document.createElement("attachments");
				rootElement.appendChild(attachments);
				
				Element filePath = document.createElement("filePath");
				filePath.appendChild(document.createTextNode(filePathList.get(i)));
				
				Element fileDesc = document.createElement("fileDesc");
				fileDesc.appendChild(document.createTextNode(fileDescList.get(i)));		
				
				Element fileName = document.createElement("fileName");
				fileName.appendChild(document.createTextNode(fileNameList.get(i)+ "***"));		
				
				attachments.appendChild(fileDesc);			
				attachments.appendChild(fileName);
				attachments.appendChild(filePath);
				
			}

			
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);


			StreamResult result = new StreamResult(xmlFilePath);

			transformer.transform(source, result);	
		}
		catch(Exception e)
		{
			throw e;
		}
	}	

}
