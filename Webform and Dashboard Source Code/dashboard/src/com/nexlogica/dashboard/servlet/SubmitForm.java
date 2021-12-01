package com.nexlogica.dashboard.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemHeaders;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.documentum.fc.common.DfException;
import com.documentum.fc.impl.util.RegistryPasswordUtils;
import com.emc.documentum.rest.client.sample.client.DCTMRestClient;
import com.emc.documentum.rest.client.sample.client.DCTMRestClientBinding;
import com.emc.documentum.rest.client.sample.model.Entry;
import com.emc.documentum.rest.client.sample.model.Feed;
import com.emc.documentum.rest.client.sample.model.RestObject;
import com.nexlogica.dashboard.servlet.bean.FormBean;
import com.nexlogica.dashboard.validators.FileExtensionValidator;

import static com.emc.documentum.rest.client.sample.client.DCTMRestClientBuilder.buildSilently;

@SuppressWarnings("serial")
public class SubmitForm extends HttpServlet {

	private static DCTMRestClient client;

	FormBean form;

	// when the form posts, this is what is going to happen
	public void doPost(HttpServletRequest request, HttpServletResponse response){

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletContext servletContext = this.getServletConfig().getServletContext();
		File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
		factory.setRepository(repository);
		ServletFileUpload upload = new ServletFileUpload(factory);

		boolean wasFileUpload = false;

		form = new FormBean();

		// validator object to check if the file type falls into the allowed types
		FileExtensionValidator fileExtensionValidator = new FileExtensionValidator();	

		// Parse the request
		try {
			List<FileItem> items = upload.parseRequest(request);
			
			// Process the submitted fields and uploaded documents
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				// grab the next thing
				FileItem item = iter.next();

				// identify the thing
				// if it's a form field...
				if (item.isFormField()) {
					
					// get field name and field value
					String name = item.getFieldName();
					String value = item.getString();
					System.out.println("Name............" + name);
					System.out.println("Value (unclean) ............" + value);
					
					// check to make sure that the field value does not contain any invalid characters
					if(value != cleanValue(name, value)){
						
						// if there is invalid data, return a failed submission
						System.out.println("Value (clean) ............" + cleanValue(name,value));
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print("BAD_DATA");
						throw new Exception("Data entered was corrupt or invalid.");
					}
					
					form.setField(name, value);
					
					if("commentBox".equals(name)){
						wasFileUpload = true;
					}
				} else {
					if(fileExtensionValidator.validate(item.getName())){
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

						// replace characters to fix issue with attaching file location to file name
						String itemName = item.getName();
						if (itemName != null) {
							itemName = FilenameUtils.getName(itemName);
							form.setField("fileName", itemName);
						}

						String fileName = form.getRandomNumber() + "_" + itemName;

						// path to upload in properties file
						String filePathPrefix = new PropertyFileReader().getLocationProperty();

						String fullPath = filePathPrefix + fileName;
						OutputStream os = new FileOutputStream(fullPath);

						byte[] buffer = new byte[1024];
						int bytesRead;
						//read from is to buffer
						while((bytesRead = stream.read(buffer)) !=-1){
							os.write(buffer, 0, bytesRead);
						}
						stream.close();
						os.flush();
						os.close();

						wasFileUpload = true;

						form.setField(name, fullPath);
					} else {
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();

						// reponse number 1 means that there was a bad file type
						out.print("INVALID_FILE_TYPE");

						throw new Exception("file extension invalid.");
					}
				} 
			}


			// perform action based on what kind of fields were submitted

			if (!wasFileUpload)	// here is where the REST request should take place
			{
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();

				// Do the REST api call and generate a JSON string 
				String numberOfCases = form.getNumberOfCases().replace("\"","");
				String JSONStringResults = "";

				System.out.println("number of cases: " + numberOfCases );

//				//TEST ONLY REMOVE LATER
//				if(("CHRISTO").equals(form.getFirstName()) && ("BAL").equals(form.getLastName())){
//					JSONStringResults = "{\"numberOfCases\":\"2\", "
//							+ "\"cases\":["
//							+ "{ \"caseNumber\":\"99881\", \"appealType\":\"Written Tests\", \"examNumber\":\"A0998D\", \"dateSubmitted\":\"12/18/2017\", "
//							+ "\"firstName\":\"Carol\", \"lastName\":\"Medina\", \"last4SSN\":\"4181\", \"examName\":\"MANAGEMENT FELLOW\", "
//							+ "\"currentStep\":\"Your Response Needed\", \"caseStatus\":\"Your Response Needed\", \"dateActionRequested\":\"12/16/1991\", "
//							+ "\"fileUploadRequired\":\"true\", "
//							//+ "\"instructions\":\"none\", "
//							//+ "\"instructions\":\"very short instructions\", "
//							+ "\"instructions\":\"really really really really really really really really really really really reallyreally really really really really really really really really really really reallyreally really really really really really really really really really really reallyreally really really really really really really really really really really reallyreally really really really really really really really really really really reallyreally really really really really really really really really really really really long instructions\", "
//							+ "\"dateReceived\":\"12/18/2017\"},"
//							+ "{ \"caseNumber\":\"100000\", \"appealType\":\"Interview\", \"examNumber\":\"A0998D\", \"dateSubmitted\":\"12/26/2017\", \"firstName\":\"Carol\", \"lastName\":\"Medina\", \"last4SSN\":\"4181\", \"examName\":\"MANAGEMENT FELLOW\", \"currentStep\":\"Response Issued / Case Closed\", \"caseStatus\":\"Response Issued / Case Closed\", \"fileUploadRequired\":\"false\", \"instructions\":\"none\", \"dateReceived\":\"12/18/2017\"}]}";
//					System.out.println("CHRISTOOOO");
//				} else 
				
				if(("0").equals(numberOfCases))
					JSONStringResults = doNoCaseQuery(form);
				else if(("1").equals(numberOfCases))
					JSONStringResults = doSingleCaseQuery(form);
				else
					JSONStringResults = doMultiCaseQuery(form);
				
				out.print(JSONStringResults);
			}
			else				// here is where the file upload should take place
			{
				//create xml file with info
				XMLBuilder xmlBuilder = new XMLBuilder(form);
				String xmlFileName = form.getRandomNumber() + ".xml";
				String filePathPrefix = new PropertyFileReader().getLocationProperty();
				String xmlFilePath = filePathPrefix + xmlFileName;
				xmlBuilder.createXMLFile(xmlFilePath);

				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				out.print("SUBMIT_GOOD");
			}


		} catch (FileUploadException e) {
			e.printStackTrace();
			response.setContentType("text/html");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("SUBMIT_FAIL File Upload Fail");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setContentType("text/html");
			PrintWriter out;
			try {
				out = response.getWriter();
				out.print("SUBMIT_FAIL General Failure");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	// function to run the same front end validation changes on the values on the back end
	private String cleanValue(String name, String value) {
		
		if("caseNumbers".equals(name))
			return cleanCaseNumbers(value);
		else if("firstName".equals(name))
			return cleanFirstName(value);
		else if("lastName".equals(name))
			return cleanLastName(value);
		else if("last4SSN".equals(name))
			return cleanLast4SSN(value);
		
		return value;
	}

	

	private String cleanCaseNumbers(String value) {
		// replace special characters
		return value.replace("-", "").replace("\"", "").replace("'", "").replace("=", "").replace("+", "").replace(";", "");
	}
	
	private String cleanFirstName(String value) {
		// replace special characters
		return value.replace("\"", "").replace("=", "").replace("+", "").replace(";", "");

	}

	private String cleanLastName(String value) {
		// replace special characters
		return value.replace("\"", "").replace("=", "").replace("+", "").replace(";", "");

	}
	
	private String cleanLast4SSN(String value) {
		// regex to replace all non-numeric characters with blanks
		String value2 = value.replaceAll("[^0-9]", "");
		
		// replace special characters
		return value2.replace("\"", "").replace("'", "").replace("=", "").replace("+", "").replace(";", "");

	}
	
	
	// when the form gets, this is what is going to happen
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		
	}


	/*  NOTE: Query
	 *  select * from dhrea_appeal
	 *  attributes in DA for appeals are the following:
	 *	appellant_first_name	
	 *	appellant_last_name
	 *	last_4_ssn
	 *  submission_date			// date submitted
	 *  received_date			// date received		
	 *  exam_					// exam number
	 *  exam_name				// exam name
	 *  appeals_case_			// case number
	 * 	case_status				// currently just "Open", or "Closed"
	 *  appeal_type
	 * 
	 * select appellant_first_name, appellant_last_name, last_4_ssn, submission_date, received_date, exam_, exam_name, appeals_case_, case_status, appeal_type 
	 * from dhrea_appeal 
	 * where UPPER(appellant_first_name)=UPPER('tgert') and UPPER(appellant_last_name)=UPPER('tret') and last_4_ssn='5344'
	 * 
	 * 	The above query returns 2 results in the test environment
	 * 
	 *  UPPER function makes query case insensitive
	 *  
	 *  add the below line to the end of the query if the case number is specified, replace 555555 with whatever the case number is
	 *  and appeals_case_='555555' 
	 * 
	 *  rest services are installed locally on ckatnic-pc:8086/dctm-rest/
	 *  
	 *  
	 *  We’ll use the following query:
	 * select  distinct q.task_name, q.name
	 *	from     dmi_workitem w,
	 *	                dmi_package p,
	 *	                dhrea_appeal d,
	 *	                dmi_queue_item q
	 *	where    w.r_workflow_id = p.r_workflow_id
	 *	                and any p.r_component_id = d.r_object_id
	 *	                and w.r_queue_item_id = q.r_object_id
	 *	                and q.delete_flag = 0 
	 *	                and d.appeals_case_ = '<case number>'
	 *	
	 *	And the following logic ( we can discuss this further tomorrow also if there are any questions)
	 *	if dhrea_appeal.action_required = true (will be a new attribute)
	 *	                Status = ‘Your response needed’
	 *	if q.task_name = ‘SS1’
	 *	                Status = Appeal Received
	 *	If q.task_name = ‘SS3’
	 *	                Status = ‘Closing Stage’
	 *	if q.task_name != ‘SS1’ and != ‘SS3’ and row returned >=1
	 *	                Status = In Review
	 *	If row returned = 0
	 *	                Get  the value from dhrea_appeal.case_status
	 *
	 *  
	 *  
	 *  
	 * 
	 */


	private String doNoCaseQuery(FormBean form){
		String query = generateSingleQueryFromBean(form);

		System.out.println("Generating query with no case input...");
		System.out.println(query);

		System.out.println("\nATTEMPTING QUERY and GENERATING JSON STRING......");
		String jsonString = runDQLQuery(query);
		System.out.println(jsonString);

		return(jsonString);
	}

	private String doSingleCaseQuery(FormBean form){
		form.setCaseNumber(form.getCaseNumbers().replace("\"",""));

		String query = generateSingleQueryFromBean(form);

		System.out.println("Generating query with single case input...");
		System.out.println(query);


		System.out.println("\nATTEMPTING QUERY and GENERATING JSON STRING......");
		String jsonString = runDQLQuery(query);
		System.out.println(jsonString);

		return(jsonString);
	}

	private String doMultiCaseQuery(FormBean form){
		String query = generateMultipleQueryFromBean(form);

		System.out.println("Generating query with multiple cases input...");
		System.out.println(query);


		System.out.println("\nATTEMPTING QUERY and GENERATING JSON STRING......");
		String jsonString = runDQLQuery(query);
		System.out.println(jsonString);

		return(jsonString);
	}


	// TODO: get current step and case status value 
	private String generateSingleQueryFromBean(FormBean form){

		// TODO: use action_required when the value becomes available. in the meantime, it will be something different (csc)
		// String prefix = "select appellant_first_name, appellant_last_name, last_4_ssn, submission_date, received_date, exam_, exam_name, appeals_case_, case_status, appeal_type, action_required from dhrea_appeal ";

		String prefix = "select appellant_first_name, appellant_last_name, last_4_ssn, submission_date, received_date, exam_, exam_name, appeals_case_, case_status, appeal_type, action_requested, dashboard_comment, date_action_requested_from_ from dhrea_appeal ";

		String whereClause = "";
		whereClause += "where UPPER(appellant_first_name)=UPPER('"; whereClause += form.getFirstName().replace("\"", "").replace("'", "''"); 
		whereClause += "') and UPPER(appellant_last_name)=UPPER('"; whereClause += form.getLastName().replace("\"", "").replace("'", "''");
		whereClause += "') and last_4_ssn='"; whereClause += form.getLast4SSN().replace("\"", "");
		whereClause += "'";

		if(Integer.parseInt(form.getNumberOfCases()) != 0){
			whereClause += " and appeals_case_='"; whereClause += form.getCaseNumber(); whereClause += "'";
		}
		
		return prefix + whereClause;
	}

	// TODO: get current step and case status value 
	private String generateMultipleQueryFromBean(FormBean form){

		// TODO: use action_required when the value becomes available. in the meantime, it will be something different (csc)
		// String prefix = "select appellant_first_name, appellant_last_name, last_4_ssn, submission_date, received_date, exam_, exam_name, appeals_case_, case_status, appeal_type, action_required from dhrea_appeal ";

		String prefix = "select appellant_first_name, appellant_last_name, last_4_ssn, submission_date, received_date, exam_, exam_name, appeals_case_, case_status, appeal_type, action_requested, dashboard_comment, date_action_requested_from_ from dhrea_appeal ";

		String whereClause = "";
		whereClause += "where UPPER(appellant_first_name)=UPPER('"; whereClause += form.getFirstName().replace("\"", "").replace("'", "''"); 
		whereClause += "') and UPPER(appellant_last_name)=UPPER('"; whereClause += form.getLastName().replace("\"", "").replace("'", "''");
		whereClause += "') and last_4_ssn='"; whereClause += form.getLast4SSN().replace("\"", "");
		whereClause += "'";

		if(Integer.parseInt(form.getNumberOfCases()) != 0){
			List<String> caseList = form.getCaseList();

			whereClause +=" and (";

			for(int i = 0; i < caseList.size(); i++){
				whereClause+= "appeals_case_='"; whereClause += caseList.get(i).trim(); whereClause +="'";

				if(caseList.size() - 1 != i)
					whereClause += " or ";
			}
			whereClause +=")";

		}

		return prefix + whereClause;
	}



	// method used in testing
	@SuppressWarnings("unused")
	private String generateJSONString(FormBean form){		
		System.out.println("Number of cases " + form.getNumberOfCases());

		int numberOfCases = Integer.parseInt(form.getNumberOfCases());

		if ( numberOfCases == 0 )
		{
			System.out.println("Number of cases value was 0, defaulting to 5 for testing");

			numberOfCases = 5;

			ArrayList<String> temp = new ArrayList<String>();

			for(int i = 0; i < numberOfCases; i++)
				temp.add(String.valueOf(i));

			form.setCaseList(temp);
		}

		String s = "{\"numberOfCases\":\"" +numberOfCases+ "\", \"cases\":[";



		for(int i = 0; i < numberOfCases; i++){

			// multi result table values
			String temp = "{ \"caseNumber\":\"" + form.getCaseList().get(i) + "\",";
			temp 		+= " \"appealType\":\"Appeal Type " + i + "\",";
			temp 		+= " \"examNumber\":\"" + i + "\",";
			temp 		+= " \"dateSubmitted\":\"Date Submitted " + i + "\",";
			temp 		+= " \"caseStatus\":\"In Review" + "\",";
			// basic info values
			temp 		+= " \"firstName\":\"" + form.getFirstName() + "\",";
			temp 		+= " \"lastName\":\"" + form.getLastName() + "\",";
			temp 		+= " \"last4SSN\":\"" + form.getLast4SSN()+ "\",";
			temp 		+= " \"fileUploadRequired\":\"true\",";
			// single result table values
			temp 		+= " \"currentStep\":\"Current Step " + i + "\",";		
			temp 		+= " \"examName\":\"Exam Name " + i + "\",";
			temp 		+= " \"dateReceived\":\"Date " + i + "\"}";
			s += temp;

			if((numberOfCases - 1) != i){
				s+=",";
			}
		}

		s += "]}";

		System.out.println("JSONObject generated: " + s);

		return s;
	}

	private String getJSONStringFromResults(List<Entry<RestObject>> resultsList){

		String s = "{\"numberOfCases\":\"" +resultsList.size()+ "\", \"cases\":[";

		for(int i = 0; i < resultsList.size(); i++)
		{
			Entry<RestObject> e = resultsList.get(i);
			Map<String, Object> properties = e.getContentObject().getProperties();

			if(checkForMissingProperties(properties) == false){
				System.out.println("Missing properties!");
				return null;}
			
			String dateSubmittedFormattedMMDDYYYY = "";
			if(properties.get("submission_date") != null){
				String dateSubmittedFormatted = ((String) properties.get("submission_date")).replace("Date ","").substring(0, 20).replace("T", " ").replace(".", "") + "\",";
				dateSubmittedFormatted = dateSubmittedFormatted.substring(0, 10);
				String YYYY = dateSubmittedFormatted.substring(0, 4);
				String MM = dateSubmittedFormatted.substring(5, 7);
				String DD = dateSubmittedFormatted.substring(8, 10);
				dateSubmittedFormattedMMDDYYYY = MM + "/" + DD + "/" + YYYY;}

			String dateReceivedFormattedMMDDYYYY = "";
			if(properties.get("received_date") != null){
				String dateReceivedFormatted = ((String) properties.get("received_date")).replace("Date ","").substring(0, 20).replace("T", " ").replace(".", "") + "\",";
				dateReceivedFormatted = dateReceivedFormatted.substring(0, 10);
				String YYYY = dateReceivedFormatted.substring(0, 4);
				String MM = dateReceivedFormatted.substring(5, 7);
				String DD = dateReceivedFormatted.substring(8, 10);
				dateReceivedFormattedMMDDYYYY = MM + "/" + DD + "/" + YYYY;}

			// multi result table values
			String temp = "{ \"caseNumber\":\"" + properties.get("appeals_case_").toString() + "\",";
			temp 		+= " \"appealType\":\"" + ((String) properties.get("appeal_type")).replace("Appeal Type ","") + "\",";
			temp 		+= " \"examNumber\":\"" + properties.get("exam_").toString() + "\",";
			//temp 		+= " \"dateSubmitted\":\"" + ((String) properties.get("submission_date")).replace("Date ","").substring(0, 20).replace("T", " ").replace(".", "") + "\",";
			temp 		+= " \"dateSubmitted\":\"" + dateSubmittedFormattedMMDDYYYY + "\",";

			// basic info values
			temp 		+= " \"firstName\":\"" + properties.get("appellant_first_name").toString() + "\",";
			temp 		+= " \"lastName\":\"" + properties.get("appellant_last_name").toString() + "\",";
			temp 		+= " \"last4SSN\":\"" + properties.get("last_4_ssn").toString() + "\",";
			// single result table values		
			temp 		+= " \"examName\":\"" + properties.get("exam_name").toString() + "\",";


			
			// case current step
			if(appellantInfoRequired(properties)){
				temp 	+= " \"currentStep\":\"Your Response Needed" + "\",";
				temp 		+= " \"caseStatus\":\"Your Response Needed" + "\",";


				String dateActionRequestedFormatted = ((String) properties.get("date_action_requested_from_")).replace("Date ","").substring(0, 20).replace("T", " ").replace(".", "") + "\",";
				dateActionRequestedFormatted = dateActionRequestedFormatted.substring(0, 10);
				String YYYY = dateActionRequestedFormatted.substring(0, 4);
				String MM = dateActionRequestedFormatted.substring(5, 7);
				String DD = dateActionRequestedFormatted.substring(8, 10);
				String dateActionRequestedFormattedMMDDYYYY = MM + "/" + DD + "/" + YYYY;
				temp 		+= " \"dateActionRequested\":\"" + dateActionRequestedFormattedMMDDYYYY + "\",";


				temp 		+= " \"fileUploadRequired\":\"true\",";
				temp 		+= " \"instructions\":\"" + properties.get("dashboard_comment").toString().replace("\n", "<br />").replace("\"", "'").replace("{", "--").replace("}", "--").replace("[", "-").replace("]", "-").replace("(", "-").replace(")", "-") + "\",";
				
				System.out.println("TEMP: " + temp);
			}
			
			
			else if(properties.get("case_status").toString().equals("Closed") || properties.get("case_status").toString().equals("Inactive"))
			{
				temp	+= " \"currentStep\":\"" + "Response Issued / Case Closed" + "\",";
				temp 		+= " \"caseStatus\":\"Response Issued / Case Closed" + "\",";
				temp 		+= " \"fileUploadRequired\":\"false\",";
				temp 		+= " \"instructions\":\"" + "none" + "\",";
			}
			// run second query, return result as string
			else {
				
				String step = runSecondQuery(properties);

				System.out.println("Current step: "+ step);
				
				if("SS1".equals(step) || "SS3".equals(step)){
					temp	+= " \"currentStep\":\"" + "Appeal Received" + "\",";
					temp 		+= " \"caseStatus\":\"Appeal Received" + "\",";}
				else if("SS2".equals(step)){
					temp	+= " \"currentStep\":\"" + "Closing Stage" + "\","; 
					temp 		+= " \"caseStatus\":\"Closing Stage" + "\",";}
				else{
					temp	+= " \"currentStep\":\"" + "In Review" + "\","; 
					temp 		+= " \"caseStatus\":\"In Review" + "\",";}

				
				temp 		+= " \"fileUploadRequired\":\"false\",";
				temp 		+= " \"instructions\":\"" + "none" + "\",";
			}

			//temp 		+= " \"dateReceived\":\"" + ((String) properties.get("received_date")).replace("Date ","").substring(0, 20).replace("T", " ").replace(".", "") + "\"}";
			temp 		+= " \"dateReceived\":\"" + dateReceivedFormattedMMDDYYYY + "\"}";

			s += temp;

			if((resultsList.size() - 1) != i){
				s+=",";
			}
		}
		s += "]}";

		return s;
	}

	// For local testing only
	private String getJSONStringFromNoResults(){

		String s = "{\"numberOfCases\":\"3\", \"cases\":[";

		for(int i = 0; i < 3; i++)
		{
			// TODO: return the current step and case status values instead of the static ones
			// multi result table values
			String temp = "{ \"caseNumber\":\"" + (i+1) * 111111 + "\",";
			temp 		+= " \"appealType\":\"Test appealType\",";
			temp 		+= " \"examNumber\":\"a89844\",";
			temp 		+= " \"dateSubmitted\":\"1/1/2017\",";

			if(i == 0){
				temp 		+= " \"caseStatus\":\"Your Response Needed." + "\",";
				temp 		+= " \"dateActionRequested\":\"12/16/1991" + "\",";
			}
			else
				temp 		+= " \"caseStatus\":\"In Review." + "\",";

			if(i == 0)
				temp 		+= " \"instructions\":\"really really really really really really really really really really really reallyreally really really really really really really really really really really reallyreally really really really really really really really really really really reallyreally really really really really really really really really really really reallyreally really really really really really really really really really really reallyreally really really really really really really really really really really really long instructions" + "\",";
			else
				temp 		+= " \"instructions\":\"none" + "\",";

			// basic info values
			temp 		+= " \"firstName\":\"Test firstName\",";
			temp 		+= " \"lastName\":\"Test lastName\",";
			temp 		+= " \"last4SSN\":\"1234\",";
			// single result table values		
			temp 		+= " \"examName\":\"Test examName\",";

			// current step
			if(i == 0)
				temp 		+= " \"currentStep\":\"Your Response Needed" + "\",";
			else
				temp 		+= " \"currentStep\":\"In Review" + "\",";

			if(i == 0)
				temp 		+= " \"fileUploadRequired\":\"true\",";
			else
				temp 		+= " \"fileUploadRequired\":\"false\",";

			temp 		+= " \"dateReceived\":\"1/2/2017\"}";

			s += temp;

			if(i != 2){
				s+=",";
			}
		}
		s += "]}";

		return s;
	}


	private boolean checkForMissingProperties(Map<String, Object> properties){

		if(		properties.get("appeals_case_") == null)
			//			|| 
			//				properties.get("appeal_type") == null ||
			//				properties.get("exam_") == null ||
			//				properties.get("submission_date") == null ||
			//				properties.get("appellant_first_name") == null ||
			//				properties.get("appellant_last_name") == null ||
			//				properties.get("last_4_ssn") == null ||
			//				properties.get("exam_name") == null ||
			//				properties.get("received_date") == null)

		{ System.out.println("Missing properties, returning false"); return false; }
		else
		{ System.out.println("No missing properties"); return true; }
	}

	private String runDQLQuery(String dql){

		String returnedJSONObject = "";
		PropertyFileReader p = new PropertyFileReader();

		try {
			client = buildSilently(DCTMRestClientBinding.JSON, p.getDCTMRestLocation() , p.getDCTMRepoName(), p.getDCTMUsername(), RegistryPasswordUtils.decrypt(p.getDCTMPassword()));
		} catch (IOException | DfException e) {
			System.out.println("Could not decrypt password");
			e.printStackTrace();
		}

		try {
			
			Feed<RestObject> feed = client.dql(dql);
			
			if(feed != null){
				List<Entry<RestObject>> list = feed.getEntries();

				if(list != null){
					System.out.println("Returned " + list.size() + " entries..");
					returnedJSONObject = getJSONStringFromResults(list);
				}
				else{
					System.out.println("ERROR, ENTRIES EMPTY");
					returnedJSONObject = "{numberOfCases:'0'}";
				}

			}
		} catch (java.lang.NullPointerException e) {
			returnedJSONObject = getJSONStringFromNoResults();
			System.out.println("ERROR, FEED EMPTY");
			e.printStackTrace();
		} catch (com.emc.documentum.rest.client.sample.client.DCTMRestErrorException e) {
			returnedJSONObject = getJSONStringFromNoResults();
			System.out.println("ERROR, CANNOT CONNECT TO DOCBROKER");
			e.printStackTrace();
		}


		return returnedJSONObject;
	}

	private boolean appellantInfoRequired(Map<String, Object> properties){

		if(properties.containsKey("action_requested")){

			System.out.println("Found action_requested value");
			System.out.println(properties.get("action_requested").toString());

			if(	"1".equals(properties.get("action_requested").toString()))
				return true;
			else
				return false;
		}

		System.out.println("ERROR: properties did not contain action_required, nor csc");

		return false;

	}

	private String runSecondQuery(Map<String, Object> properties){

		String prefix = "select distinct q.task_name, q.name from dmi_workitem w, dmi_package p, dhrea_appeal d, dmi_queue_item q ";
		String whereclause = "where w.r_workflow_id = p.r_workflow_id "
				+ "and any p.r_component_id = d.r_object_id "
				+ "and w.r_queue_item_id = q.r_object_id "
				+ "and q.delete_flag = 0 "
				+ "and d.appeals_case_ = '" + properties.get("appeals_case_") + "'";

		String dql = prefix + whereclause;

		PropertyFileReader p = new PropertyFileReader();

		if(client == null){
			try {
				client = buildSilently(DCTMRestClientBinding.JSON, p.getDCTMRestLocation() , p.getDCTMRepoName(), p.getDCTMUsername(), RegistryPasswordUtils.decrypt(p.getDCTMPassword()));
			} catch (IOException | DfException e) {
				e.printStackTrace();
				return "Unable to retrieve";
			}
		}

		System.out.println("\nATTEMPTING SECONDARY QUERY TO CHECK WORKFLOW STEP.....");
		System.out.println(dql);

		Feed<RestObject> feed = client.dql(dql);

		if(feed != null){
			List<Entry<RestObject>> list = feed.getEntries();

			if(list != null && list.size() > 0){
				System.out.println("Returned " + list.size() + " entries from secondary query");
				Entry<RestObject> e = list.get(0);
				Map<String, Object> properties2 = e.getContentObject().getProperties();

				System.out.println("Properties returned from secondary query:");
				System.out.println(properties2.toString());


				if(properties2.containsKey("task_name"))
					return properties2.get("task_name").toString();
				else
					return "See Case Status";

			}
			else{
				System.out.println("ERROR, ENTRIES EMPTY");
				return "Unable to retrieve";
			}

		}

		return "No Case Step Returned";
	}
}
