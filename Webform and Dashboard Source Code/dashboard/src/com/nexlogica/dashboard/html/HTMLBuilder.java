package com.nexlogica.dashboard.html;

//import org.json.simple.JSONObject;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.info.Info;

public class HTMLBuilder {

	public static final String DOC_TYPE_ALLOWABLE= "<br />Click 'Browse' to select the file you wish to upload. To provide upload additional documents, click the 'Attach Additional Documents' button and repeat these steps. To remove a document, click on the red '-' icon." + 
			"<br /><br />This page only accepts the following document types: PDF, Word document (doc or docx), Email (msg), Pictures (jpg, jpeg, png, tif, tiff, gif) and Audio files (m4a, wav, mp3, mp4, mpeg4)." +
			"<br /><br />If you encounter any issues attaching your document(s), please click on 'Technical Support' (top right of the screen) and submit a Help Desk ticket for assistance.";
	
	public static final String FOOTER_HTML = "<p id=\"multipleAppealFooter\"><b><span style='font-size:20px'>Please note...</span></b><br /><br />We make every effort to respond to you in writing within 60 days; however, it may take longer based on the level of complexity of this appeal.<p>";
	public static final String FOOTER_HTML_NO_MARGIN = "<p id=\"multipleAppealFooter2\"><b>Please note...</b><br /><br />We make every effort to respond to you in writing within 60 days; however, it may take longer based on the level of complexity of this appeal.<p>";

	public static final String TEST_HTML = "<h1 class=\"t1\">Test header</h1>";

	public static final String INTRO2 =
			"<div>" +
					"<img id=\"psl_header_image_2\" height=\"150\" width=\"450\" " +
					"src=\"HR.LOGOred3.jpg\" />" +
					"<a style=\"margin-left:349px; margin-right:1%;\" href=\"landing.html\">Home</a> <a style=\"margin-right:1%;\" href=\"FAQ.html\" target=\"_blank\">FAQ</a> <a href=\"https://apps.hr.lacounty.gov/eAppeal/\">Technical Support</a></div>" +
					"<div id=\"psl_header\">" + 
					"<p id=\"psl_header_text\">Appeals Tracking Dashboard</p>" +
					"</div>" +
					"<p style=\"margin-left:180px;\">Enter the following information to search for your appeal(s):</p>";

	public static final String ON_UPLOAD_SUCCESS = "<p id=\"psl_body\">Thank you for your submission. We have received it, and you will be notified shortly</p>";

	public static final String APPEALS_QUESTION = "QUESTION";
	public static final String APPEALS_ANSWER 	= "ANSWER";

	public static final String RESULTSINTRO =
			"<div>" +
					"<img id=\"psl_header_image_2\" height=\"150\" width=\"450\" " +
					"src=\"HR.LOGOred3.jpg\" />" +
					"<a style=\"margin-left:349px; margin-right:1%;\" href=\"landing.html\">Home</a> <a style=\"margin-right:1%;\" href=\"FAQ.html\" target=\"_blank\">FAQ</a> <a href=\"https://apps.hr.lacounty.gov/eAppeal/\">Technical Support</a></div>";




	// overloaded function 
	// use case: the general case; when the user searches for a single case
	public static HTML getCaseStatusTable(JSONObject jsonSingleCase) {
		HTML h = new HTML();

		JSONArray ja = (JSONArray) jsonSingleCase.get("cases");

		JSONObject tempjo = (JSONObject) ja.get(0);

		String resultsTable = "<p style='font-size:20px; margin-left:5px;'>Below is the information returned for Appeal #"+ tempjo.get("caseNumber").toString().replace("\"", "") + "</p><br /><table id=\"information\">"
				+ "<tr>"
				+ "<td id=\"information-title\">Case Received on:"
				+ "<td id=\"information-description\">" + tempjo.get("dateReceived").toString().replace("\"", "").replace("Date ","").substring(0, 10)
				+ "</tr><tr>"
				+ "<td id=\"information-title\">Appeal Type:"
				+ "<td id=\"information-description\">" + tempjo.get("appealType").toString().replace("\"", "").replace("Appeal Type ", "")
				+ "</tr><tr>"
				+ "<td id=\"information-title\">Exam:"
				+ "<td id=\"information-description\">" + tempjo.get("examNumber").toString().replace("\"", "") + " - " + tempjo.get("examName").toString().replace("\"", "")
				+ "</tr><tr>"
				+ "<td id=\"information-title\">Current Status:"
				+ "<td id=\"information-description\">" + getDetailedCaseStatusMessageFor(tempjo.get("currentStep").toString().replace("\"", ""))	
				+ "</tr>";

		if(!("none").equals(tempjo.get("instructions").toString().replace("\"", "")))		
			resultsTable += "<tr><td id=\"information-title\">Instructions:"
					+ "<td id=\"information-description\">" + "Please click on the Action Required tab and provide the information requested."
					+ "</tr></table><br />";

		else
			resultsTable += "</table><br />";

		h.setHTML(resultsTable);

		return h;
	}

	// overloaded function 
	// use case: when the user searches for multiple cases, and then selects
	// the desired case from the result table
	public static HTML getCaseStatusTable(JSONObject jsonSingleCase, int i) {
		HTML h = new HTML();

		JSONArray ja = (JSONArray) jsonSingleCase.get("cases");

		JSONObject tempjo = (JSONObject) ja.get(i);

		String resultsTable = "<div><p style='font-size:20px; margin-left:5px;'>Below is the information returned for Appeal #"+ tempjo.get("caseNumber").toString().replace("\"", "") + "</p><br /><table id=\"information\">"
				+ "<tr>"
				+ "<td id=\"information-title\">Case Received on:"
				+ "<td id=\"information-description\">" + tempjo.get("dateReceived").toString().replace("\"", "").replace("Date ","").substring(0, 10)
				+ "</tr><tr>"
				+ "<td id=\"information-title\">Appeal Type:"
				+ "<td id=\"information-description\">" + tempjo.get("appealType").toString().replace("\"", "").replace("Appeal Type ", "")
				+ "</tr><tr>"
				+ "<td id=\"information-title\">Exam:"
				+ "<td id=\"information-description\">" + tempjo.get("examNumber").toString().replace("\"", "") + " - " + tempjo.get("examName").toString().replace("\"", "")
				+ "</tr><tr>"
				+ "<td id=\"information-title\">Current Status:"
				+ "<td id=\"information-description\">" + getDetailedCaseStatusMessageFor(tempjo.get("currentStep").toString().replace("\"", ""))	
				+ "</tr>";

		if(!("none").equals(tempjo.get("instructions").toString().replace("\"", "")))		
			resultsTable += "<tr><td id=\"information-title\">Instructions:"
					+ "<td id=\"information-description\">" + "Please select the 'Action Required' tab above and follow the instructions provided."
					+ "</tr></table><br />";

		else
			resultsTable += "</table></div><br />";

		h.setHTML(resultsTable);

		return h;
	}

	public static HTML getTabViewTableFor(JSONObject jsonSingleCase, int i) {
		HTML h = new HTML();

		JSONArray ja = (JSONArray) jsonSingleCase.get("cases");
		JSONObject tempjo = (JSONObject) ja.get(i);

//		String resultsTable = "<table id=\"information\">"
//				+ "<tr>"
//				+ "<td id=\"information-title\">Your Response Needed"
//				+ "</tr><tr>"
//				+ "<td id=\"information-title\">Date Requested: " + tempjo.get("dateActionRequested").toString().replace("\"", "")	
//				+ "</tr><tr>"
//				+ "<td id=\"information-description\">" + tempjo.get("instructions").toString().replace("\"", "")
//				+ "</tr><tr>"
//				+ "<td id=\"information-description\">You have 10 business days from the date requested to submit the information as instructed"
//				+ "</tr></table><br />";

		String resultsTable2 = "<b>Your Action Requested</b><span style=\"margin-left:25px;\"><br /> <br /><b>Date Requested:</b> "+ tempjo.get("dateActionRequested").toString().replace("\"", "")	
				+"</span><br /><br />"
				+ "<b>" + tempjo.get("instructions").toString().replace("\"", "") + "</b>"
				+"<p>You have 10 business days from the date requested to submit the information as instructed.</p>";


		h.setHTML(resultsTable2);
		h.setStyleName("actionRequiredUpper");

		// resize case numbers
		// search button below instead of next to
		return h;
	}

	@SuppressWarnings("unused")
	private static String getDetailedCaseStatusMessageFor2(String step){
		if(("Appeal Received").equals(step)){
			return "Your appeal has been received through the online web form. Our analysts are assigned appeals in the order that they are received.";
		}
		else if(("In Review").equals(step)){
			return "Your appeal has been received through the online web form and has been assigned to an analyst.";
		}
		else if(("Your Response Needed").equals(step)){
			return "Your appeal has been flagged by an analyst to request more information. Please reference the instructions below.";
		}
		else if(("Closing Stage").equals(step)){
			return "Your appeal has been received through the online web form. Our analysts are assigned appeals in the order that they are received.";
		}
		else if(("Response Issued").equals(step)){
			return "Your appeal has been received through the online web form. Our analysts are assigned appeals in the order that they are received.";
		}
		else
			return null;

	}

	private static String getDetailedCaseStatusMessageFor(String step){
		
		if(("Your Response Needed").equals(step))
			return "Your Action Requested";
		
		return step;

	}

	// sent JSONObject with the following structure
	/* 
	 * { "numberOfCases":<value>, "cases":[<cases>] array of cases
	 * 
	 * case
	 * { 	
	 * 		"caseNumber" : <value>,
	 * 		"appealType" : <value>,
	 * 		"examNumber" : <value>,
	 * 		"dateSubmitted" : <value>,
	 * 		"caseStatus" : <value>,
	 * 		"firstName" : <value>,
	 * 		"lastName" : <value>,
	 * 		"last4SSN" : <value>,
	 * 		"currentStep" : <value>,
	 * 		"examName" : <value>,
	 * 		"dateReceived" : <value> 
	 * }
	 * */
	public static HTML getMultipleResultsTable(JSONObject jsonMultiCase) {
		HTML h = new HTML("test");

		String n = jsonMultiCase.get("numberOfCases").toString().replace("\"", "");

		int numberOfResults = Integer.parseInt(n);

		String tableHeaders = "<table id=\"results_table\"><tr><th>Case Number</th><th>Appeal Type</th><th>Exam Number</th><th>Date Submitted</th><th>Status</th></tr>";
		String tableEnd = "</table>";

		JSONValue jv = jsonMultiCase.get("cases");
		JSONArray ja = new JSONArray();

		// should be nonnull - if not, just return the table
		if(jv.isArray() != null){
			ja = (JSONArray) jv;
		} else {
			Info.display("Array Check", "jsonMultiCase.get(cases) was NOT an array");
			return h;
		}


		for(int i = 0; i < numberOfResults; i++){

			JSONValue tempjv = ja.get(i);
			JSONObject tempjo = new JSONObject();

			// should be nonnul
			if (tempjv.isObject() != null){
				tempjo = (JSONObject) ja.get(i);
			} else {
				Info.display("ObjectCheck", "ja.get(i) was NOT a JSONObject");
				return h;
			}

			String temp = "<tr>";	

			temp += "<td>"; temp += tempjo.get("caseNumber").toString().replace("\"", "");	temp +="</td>";
			temp += "<td>"; temp += tempjo.get("appealType").toString().replace("\"", "");	temp +="</td>";
			temp += "<td>"; temp += tempjo.get("examNumber").toString().replace("\"", "");	temp +="</td>";
			temp += "<td>"; temp += tempjo.get("dateSubmitted").toString().replace("\"", "");	temp +="</td>";
			temp += "<td>"; temp += tempjo.get("caseStatus").toString().replace("\"", "");	temp +="</td>";

			temp += "</tr>";

			tableHeaders += temp;
		}

		String fullTable = tableHeaders + tableEnd;

		h.setHTML(fullTable);

		return h;
	}

	public static HTML generateMultipleCasesFoundBody(JSONObject results) {

		HTML h = new HTML();

		JSONValue jv = results.get("cases");
		JSONArray ja = new JSONArray();

		// should be nonnull - if not, just return the table
		if(jv.isArray() != null){
			ja = (JSONArray) jv;
		} else {
			Info.display("Array Check", "jsonMultiCase.get(cases) was NOT an array");
			return h;
		}

		JSONValue tempjv = ja.get(0);
		JSONObject tempjo = new JSONObject();

		// should be nonnul
		if (tempjv.isObject() != null){
			tempjo = (JSONObject) ja.get(0);
		} else {
			Info.display("ObjectCheck", "ja.get(i) was NOT a JSONObject");
			return h;
		}

		h.setHTML("<p id=\"psl_body\">Appeal(s) found for "+ tempjo.get("firstName").toString().replace("\"", "")+ " " + tempjo.get("lastName").toString().replace("\"", "")+ "</p>");


		return h;
	}
	public static HTML generateMultipleCasesFoundHeader(JSONObject results) {

		HTML h = new HTML();

		JSONValue jv = results.get("cases");
		JSONArray ja = new JSONArray();

		// should be nonnull - if not, just return the table
		if(jv.isArray() != null){
			ja = (JSONArray) jv;
		} else {
			Info.display("Array Check", "jsonMultiCase.get(cases) was NOT an array");
			return h;
		}

		JSONValue tempjv = ja.get(0);
		JSONObject tempjo = new JSONObject();

		// should be nonnul
		if (tempjv.isObject() != null){
			tempjo = (JSONObject) ja.get(0);
		} else {
			Info.display("ObjectCheck", "ja.get(i) was NOT a JSONObject");
			return h;
		}

		h.setHTML("<div><img id=\"psl_header_image_2\" height=\"150\" width=\"450\"src=\"HR.LOGOred3.jpg\" />" +
				"<a style=\"margin-left:349px; margin-right:1%;\" href=\"landing.html\">Home</a> <a style=\"margin-right:1%;\" href=\"FAQ.html\" target=\"_blank\">FAQ</a><a href=\"https://apps.hr.lacounty.gov/eAppeal/\"> Technical Support</a></div>"
				+ "<div id=\"psl_header\"><p id=\"psl_header_text\">Results Found for "+ tempjo.get("firstName").toString().replace("\"", "")+ " " + tempjo.get("lastName").toString().replace("\"", "")+ "</p></div><br>");


		return h;
	}

















}
