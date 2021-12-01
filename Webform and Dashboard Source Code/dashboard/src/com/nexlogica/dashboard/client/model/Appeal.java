package com.nexlogica.dashboard.client.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONValue;
import com.sencha.gxt.widget.core.client.info.Info;

@SuppressWarnings("serial")
public class Appeal implements Serializable{

	private Integer id;
	private Integer caseNumber;
	private String appealType;
	private String examNumber;
	private String dateSubmitted;
	private String caseStatus;
	private String firstName;
	private String lastName;
	private String last4SSN;
	private String fileUploadRequired;
	private String currentStep;
	private String examName;
	private String dateReceived;
	
	private static int COUNTER = 0;
	
	public Appeal(){
		this.id = Integer.valueOf(COUNTER++);
	}
	
	public static List<Appeal> createListFromJSONObject(JSONObject jsonMultiCase){
		
		String n = jsonMultiCase.get("numberOfCases").toString().replace("\"", "");
		
		int numberOfResults = Integer.parseInt(n);
		
		JSONValue jv = jsonMultiCase.get("cases");
		JSONArray ja = new JSONArray();
		
		// should be nonnull - if not, just return the table
		if(jv.isArray() != null){
			ja = (JSONArray) jv;
		} else {
			Info.display("Array Check", "jsonMultiCase.get(cases) was NOT an array");
			return null;
		}
		
		List<Appeal> appealList = new ArrayList<Appeal>();
		
		for(int i = 0; i < numberOfResults; i++){

			JSONValue tempjv = ja.get(i);
			JSONObject tempjo = new JSONObject();

			// should be nonnul
			if (tempjv.isObject() != null){
				tempjo = (JSONObject) ja.get(i);
			} else {
				Info.display("ObjectCheck", "ja.get(i) was NOT a JSONObject");
				return null;
			}

			Appeal a = new Appeal();
			a.setCaseNumber(tempjo.get("caseNumber").toString().replace("\"", ""));
			a.setAppealType(tempjo.get("appealType").toString().replace("\"", ""));
			a.setExamNumber(tempjo.get("examNumber").toString().replace("\"", ""));
			a.setDateSubmitted(tempjo.get("dateSubmitted").toString().replace("\"", ""));
			a.setCaseStatus(tempjo.get("caseStatus").toString().replace("\"", ""));
			appealList.add(a);
		}
		
		return appealList;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String string) {
		this.caseNumber = Integer.parseInt(string);
	}
	public String getAppealType() {
		return appealType;
	}
	public void setAppealType(String appealType) {
		this.appealType = appealType.replace("Appeal Type","").trim();
	}
	public String getExamNumber() {
		return examNumber;
	}
	public void setExamNumber(String string) {
		this.examNumber = string;
	}
	public String getDateSubmitted() {
		return dateSubmitted;
	}
	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted.replace("Date Submitted", "").trim();
		this.dateSubmitted = this.dateSubmitted.substring(0, 10);
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLast4SSN() {
		return last4SSN;
	}
	public void setLast4SSN(String last4ssn) {
		last4SSN = last4ssn;
	}
	public String getFileUploadRequired() {
		return fileUploadRequired;
	}
	public void setFileUploadRequired(String fileUploadRequired) {
		this.fileUploadRequired = fileUploadRequired;
	}
	public String getCurrentStep() {
		return currentStep;
	}
	public void setCurrentStep(String currentStep) {
		this.currentStep = currentStep;
	}
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	public String getDateReceived() {
		return dateReceived;
	}
	public void setDateReceived(String dateReceived) {
		this.dateReceived = dateReceived.substring(0,10);
	}
}
