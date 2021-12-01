package com.nexlogica.dashboard.servlet.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class FormBean {

	private String firstName;
	private String lastName;
	private String last4SSN;
	private String caseNumbers;
	private String caseNumber;
	private List<String> fileDesc;
	private List<String> filePath;
	private List<String> fileName;
	private List<String> caseList;
	private String numberOfCases;
	private String commentBox;
	private double randomNumber;
	private String submitDate;
	
	public FormBean() {
		firstName = "";
		lastName = "";
		last4SSN = "";
		caseNumbers = "";

		caseNumber = "";
		fileDesc = new ArrayList<String>();
		filePath = new ArrayList<String>();
		setFileName(new ArrayList<String>());
		
		caseList = new ArrayList<String>();
		numberOfCases = "";

		commentBox = "";

		setRandomNumber(Math.random());

		SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		Date now = new Date();
		setSubmitDate(s.format(now));


	}
	// getters
	public String getFirstName(){
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getLast4SSN(){
		return last4SSN;
	}

	public String getCaseNumbers(){
		return caseNumbers;
	}

	public String getNumberOfCases(){
		return numberOfCases;
	}

	public String getCaseNumber(){
		return caseNumber;
	}

	public List<String> getFilePath() {
		return filePath;
	}

	public List<String> getFileDesc() {
		return fileDesc;
	}

	public List<String> getCaseList() {
		return caseList;
	}
	public String getCommentBox() {
		return commentBox;
	}


	// setters
	public void setCommentBox(String commentBox) {
		this.commentBox = commentBox;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setLast4SSN(String last4SSN){
		this.last4SSN = last4SSN;
	}

	public void setCaseNumbers(String caseNumbers){
		this.caseNumbers = caseNumbers;
	}

	public void setCaseNumber(String caseNumber){
		this.caseNumber = caseNumber;
	}

	public void setNumberOfCases(int i){
		this.numberOfCases = String.valueOf(i);
	}

	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}

	public void setFileDesc(List<String> filDesc) {
		this.fileDesc = filDesc;
	}

	public void setCaseList(List<String> caseList) {
		this.caseList = caseList;
	}

	public void setField(String fieldName, String fieldValue) {
		if (fieldValue == null){
			fieldValue = "";
		}
		else {
			fieldValue = fieldValue.trim();
		}

		if(("firstName").equals(fieldName)){
			this.setFirstName(fieldValue);
		} else{
			if(("lastName").equals(fieldName)){
				this.setLastName(fieldValue);
			} else
				if(("last4SSN").equals(fieldName)){
					this.setLast4SSN(fieldValue);
				} else
					if(("caseNumbers").equals(fieldName)){
						this.setCaseNumbers(fieldValue.trim());
						if(this.getCaseNumbers() == "" || fieldValue.isEmpty() || fieldValue == null)
							this.setNumberOfCases(0);
						else{
							// set number of cases
							// this.setNumberOfCases(StringUtils.countMatches(this.getCaseNumbers(), ",") + 1);
							int n = this.getCaseNumbers().length() - this.getCaseNumbers().replace(",", "").length() + 1;
							this.setNumberOfCases(n);
							// split comma delimited values into array
							String[] caseArray = fieldValue.trim().split(",");
							for(int i = 0; i < caseArray.length; i++)
								this.caseList.add(caseArray[i]);
						}

					} else
						if(("caseNumber").equals(fieldName)){
							this.setCaseNumber(fieldValue);
						} else
							if(("commentBox").equals(fieldName)){
								this.setCommentBox(fieldValue);
							} else
								if(fieldName.startsWith("fileDesc")) {
									this.fileDesc.add(fieldValue);
								} else 
									if(fieldName.startsWith("uploadedfile")) {
										this.filePath.add(fieldValue);
									} else 
										if(fieldName.startsWith("fileName")) {
											this.fileName.add(fieldValue);
										} 
		}
	}
	public double getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(double randomNumber) {
		this.randomNumber = randomNumber;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public List<String> getFileName() {
		return fileName;
	}
	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

}