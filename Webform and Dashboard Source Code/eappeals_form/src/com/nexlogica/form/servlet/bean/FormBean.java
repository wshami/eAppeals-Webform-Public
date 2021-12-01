package com.nexlogica.form.servlet.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class FormBean {

	private String appealType;
	private String otherAppealTypeText;
	private String examNumber;
	private String jobTitle;
	private boolean isRepresentative;
	private String appellantFirstName;
	private String appellantMiddleName;
	private String appellantLasttName;
	private String aka;
	private String last4SSN;
	private String mailingAddress;
	private String city;
	private String state;
	private String zip;
	private String appellantPhone;
	private String appellantPhoneExt;
	private String appellantEmailAddress;
	private String appellantEmailAddressConfirm;
	private String appellantEmployeeNumber;
	private String appellantEmployingDept;
	private String appellantPayrollTitle;
	private String repCompanyName;
	private String repContactNameFirst;
	private String repContactNameLast;
	private String repContactAddress;
	private String repContactCity;
	private String repContactState;
	private String repContactZip;
	private String repContactPhone;
	private String repContactPhoneExt;
	private String repContactEmailAddress;
	private String repContactEmailAddressConfirm;
	private String appealIssue;
	private List<String> fileDesc;
	private List<String> filePath;
	private String appealRemedy;
	private double formRandomNumber;
	private String responseType;
	private String receivedDate;
	
	public FormBean() {
		    Random generator = new Random(System.currentTimeMillis());
		    formRandomNumber = generator.nextDouble() * (0.5);
		    fileDesc = new ArrayList<String>();
		    filePath = new ArrayList<String>();
		    appealType="";
		    otherAppealTypeText="";
		    examNumber="";
		    jobTitle="";
		    isRepresentative=false;
		    appellantFirstName="";
		    appellantMiddleName="";
		    appellantLasttName="";
		    aka="";
		    last4SSN="";
		    mailingAddress="";
		    city="";
		    state="";
		    zip="";
		    appellantPhone="";
		    appellantPhoneExt="";
		    appellantEmailAddress="";
		    appellantEmailAddressConfirm="";
		    appellantEmployeeNumber="";
		    appellantEmployingDept="";
		    appellantPayrollTitle="";
		    repCompanyName="";
		    repContactNameFirst="";
		    repContactNameLast="";
		    repContactAddress="";
		    repContactCity="";
		    repContactState="";
		    repContactZip="";
		    repContactPhone="";
		    repContactPhoneExt="";
		    repContactEmailAddress="";
		    repContactEmailAddressConfirm="";
		    appealIssue="";
		    appealRemedy="";
		    responseType="Electronic";
		    
		    SimpleDateFormat s = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		    s.setTimeZone(TimeZone.getTimeZone("PST"));
		    Date now = new Date();
		    receivedDate = s.format(now);
	}
	
	public String getReceivedDate() {
		return receivedDate;
	}
	
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	public double getFormRandomNumber() {
		return formRandomNumber;
	}
	
	public String getAppealType() {
		return appealType;
	}
	public void setAppealType(String appealType) {
		this.appealType = appealType;
	}

	public String getOtherAppealTypeText() {
		return otherAppealTypeText;
	}

	public void setOtherAppealTypeText(String otherAppealTypeText) {
		this.otherAppealTypeText = otherAppealTypeText.toUpperCase();
	}
	public String getExamNumber() {
		return examNumber;
	}
	public void setExamNumber(String examNumber) {
		this.examNumber = examNumber;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle.toUpperCase();
	}
	public boolean getIsRepresentative() {
		return isRepresentative;
	}
	public void setIsRepresentative(boolean isRepresentative) {
		this.isRepresentative = isRepresentative;
	}
	public String getAppellantFirstName() {
		return appellantFirstName;
	}
	public void setAppellantFirstName(String appellantFirstName) {
		this.appellantFirstName = appellantFirstName.toUpperCase();
	}
	public String getAppellantMiddleName() {
		return appellantMiddleName;
	}
	public void setAppellantMiddleName(String appellantMiddleName) {
		this.appellantMiddleName = appellantMiddleName.toUpperCase();
	}
	public String getAppellantLasttName() {
		return appellantLasttName;
	}
	public void setAppellantLasttName(String appellantLasttName) {
		this.appellantLasttName = appellantLasttName.toUpperCase();
	}
	public String getAka() {
		return aka;
	}
	public void setAka(String aka) {
		this.aka = aka.toUpperCase();
	}
	public String getLast4SSN() {
		return last4SSN;
	}
	public void setLast4SSN(String last4ssn) {
		last4SSN = last4ssn;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress.toUpperCase();
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city.toUpperCase();
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state.toUpperCase();
	}
	public String getZip(){
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAppellantPhone() {
		return appellantPhone;
	}
	public void setAppellantPhone(String appellantPhone) {
		this.appellantPhone = appellantPhone;
	}
	public String getAppellantPhoneExt(){
		return appellantPhoneExt;
	}
	public void setAppellantPhoneExt(String appellantPhoneExt) {
		this.appellantPhoneExt = appellantPhoneExt;
	}
	public String getAppellantEmailAddress() {
		return appellantEmailAddress;
	}
	public void setAppellantEmailAddress(String appellantEmailAddress) {
		this.appellantEmailAddress = appellantEmailAddress.toUpperCase();
	}
	public String getAppellantEmailAddressConfirm() {
		return appellantEmailAddressConfirm;
	}
	public void setAppellantEmailAddressConfirm(String appellantEmailAddressConfirm) {
		this.appellantEmailAddressConfirm = appellantEmailAddressConfirm.toUpperCase();
	}
	public String getAppellantEmployeeNumber() {
		return appellantEmployeeNumber;
	}
	public void setAppellantEmployeeNumber(String appellantEmployeeNumber) {
		this.appellantEmployeeNumber = appellantEmployeeNumber;
	}
	public String getAppellantEmployingDept() {
		return appellantEmployingDept;
	}
	public void setAppellantEmployingDept(String appellantEmployingDept) {
		this.appellantEmployingDept = appellantEmployingDept.toUpperCase();
	}
	public String getAppellantPayrollTitle() {
		return appellantPayrollTitle;
	}
	public void setAppellantPayrollTitle(String appellantPayrollTitle) {
		this.appellantPayrollTitle = appellantPayrollTitle.toUpperCase();
	}
	public String getRepCompanyName() {
		return repCompanyName;
	}
	public void setRepCompanyName(String repCompanyName) {
		this.repCompanyName = repCompanyName.toUpperCase();
	}

	public String getRepContactAddress() {
		return repContactAddress;
	}
	public void setRepContactAddress(String repContactAddress) {
		this.repContactAddress = repContactAddress.toUpperCase();
	}
	public String getRepContactCity() {
		return repContactCity;
	}
	public void setRepContactCity(String repContactCity) {
		this.repContactCity = repContactCity.toUpperCase();
	}
	public String getRepContactState() {
		return repContactState;
	}
	public void setRepContactState(String repContactState) {
		this.repContactState = repContactState.toUpperCase();
	}
	public String getRepContactZip() {
		return repContactZip;
	}
	public void setRepContactZip(String repContactZip) {
		this.repContactZip = repContactZip;
	}
	public String getRepContactPhone() {
		return repContactPhone;
	}
	public void setRepContactPhone(String repContactPhone) {
		this.repContactPhone = repContactPhone;
	}
	public String getRepContactPhoneExt(){
		return repContactPhoneExt;
	}
	public void setRepContactPhoneExt(String repContactPhoneExt) {
		this.repContactPhoneExt = repContactPhoneExt;
	}
	public String getRepContactEmailAddress() {
		return repContactEmailAddress;
	}
	public void setRepContactEmailAddress(String repContactEmailAddress) {
		this.repContactEmailAddress = repContactEmailAddress.toUpperCase();
	}
	public String getRepContactEmailAddressConfirm() {
		return repContactEmailAddressConfirm;
	}
	public void setRepContactEmailAddressConfirm(
			String repContactEmailAddressConfirm) {
		this.repContactEmailAddressConfirm = repContactEmailAddressConfirm.toUpperCase();
	}
	public String getAppealIssue() {
		return appealIssue;
	}
	public void setAppealIssue(String appealIssue) {
		this.appealIssue = appealIssue;
	}

	public List<String> getFilePath() {
		return filePath;
	}

	public void setFilePath(List<String> filePath) {
		this.filePath = filePath;
	}
	public List<String> getFileDesc() {
		return fileDesc;
	}
	public void setFileDesc(List<String> filDesc) {
		this.fileDesc = filDesc;
	}
	public String getAppealRemedy() {
		return appealRemedy;
	}
	public void setAppealRemedy(String appealRemedy) {
		this.appealRemedy = appealRemedy;
	}
	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	
	public void setField(String fieldName, String fieldValue) {
		if (fieldValue == null) {
			fieldValue = "";
		}
		else {
			fieldValue = fieldValue.trim();
		}
		
		if(("examNumber").equals(fieldName)) {
			this.setExamNumber(fieldValue);
		}
		else if(("jobTitle").equals(fieldName)) {
			this.setJobTitle(fieldValue);
		}
		else if(("radioYourself").equals(fieldName) && "on".equals(fieldValue)) {
			this.setIsRepresentative(false);
		}
		else if(("radioRep").equals(fieldName) && "on".equals(fieldValue)) {
			this.setIsRepresentative(true);
		}
		else if(("appellantFirstName").equals(fieldName)) {
			this.setAppellantFirstName(fieldValue);
		}
		else if(("appellantMiddleName").equals(fieldName)) {
			this.setAppellantMiddleName(fieldValue);
		}
		else if(("appellantLasttName").equals(fieldName)) {
			this.setAppellantLasttName(fieldValue);
		}
		else if(("aka").equals(fieldName)) {
			this.setAka(fieldValue);
		}
		else if(("last4SSN").equals(fieldName)) {
			this.setLast4SSN(fieldValue);
		}
		else if(("mailingAddress").equals(fieldName)) {
			this.setMailingAddress(fieldValue);
		}
		else if(("city").equals(fieldName)) {
			this.setCity(fieldValue);
		}
		else if(("state").equals(fieldName)) {
			this.setState(fieldValue);
		}
		else if(("zip").equals(fieldName)){
			this.setZip(fieldValue);
		}
		else if(("appellantPhone").equals(fieldName)) {
			this.setAppellantPhone(fieldValue);
		}
		else if(("appellantPhoneExt").equals(fieldName)) {
			this.setAppellantPhoneExt(fieldValue);
		}
		else if(("appellantEmailAddress").equals(fieldName)) {
			this.setAppellantEmailAddress(fieldValue);
		}
		else if(("appellantEmployeeNumber").equals(fieldName)) {
			this.setAppellantEmployeeNumber(fieldValue);
		}
		else if(("appellantEmployingDept").equals(fieldName)) {
			this.setAppellantEmployingDept(fieldValue);
		}
		else if(("appellantPayrollTitle").equals(fieldName)) {
			this.setAppellantPayrollTitle(fieldValue);
		}
		else if(("repCompanyName").equals(fieldName)) {
			this.setRepCompanyName(fieldValue);
		}
		else if(("repContactNameFirst").equals(fieldName)) {
			this.setRepContactNameFirst(fieldValue);
		}
		else if(("repContactNameLast").equals(fieldName)) {
			this.setRepContactNameLast(fieldValue);
		}
		else if(("repContactAddress").equals(fieldName)) {
			this.setRepContactAddress(fieldValue);
		}
		else if(("repContactCity").equals(fieldName)) {
			this.setRepContactCity(fieldValue);
		}
		else if(("repContactState").equals(fieldName)) {
			this.setRepContactState(fieldValue);
		}
		else if(("repContactZip").equals(fieldName)) {
			this.setRepContactZip(fieldValue);
		}
		else if(("repContactPhone").equals(fieldName)) {
			this.setRepContactPhone(fieldValue);
		}
		else if(("repContactPhoneExt").equals(fieldName)) {
			this.setRepContactPhoneExt(fieldValue);
		}
		else if(("repContactEmailAddress").equals(fieldName)) {
			this.setRepContactEmailAddress(fieldValue);
		}
		else if(("appealIssue").equals(fieldName)) {
			this.setAppealIssue(fieldValue);
		}
		else if(("appealRemedy").equals(fieldName)) {
			this.setAppealRemedy(fieldValue);
		}
		else if(("optOutCheckBox").equals(fieldName) && "on".equals(fieldValue)) {
			this.setResponseType("Mail");
		}
		
		else if(("oneFiveDatSuspension").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Suspension");
		}
		else if(("layoff").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Lay-Off");
		}
		else if(("probDischarge").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Probationary Discharge");
		}
		else if(("probReduction").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Probationary Reduction");
		}
		else if(("reductionLayOff").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Reduction due to Lay-Off");
		}
		else if(("relTempEmployment").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Release from Temporary Employment");
		}
		else if(("transfer").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Transfer");
		}
		else if(("classificationStudy").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Classification Study");
		}
		else if(("nonAppointment").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Non-Appointment");
		}
		else if(("resignation").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Resignation");
		}
		else if(("background").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Background");
		}
		else if(("inaccurateDiscOHP").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("OHP");
		}
		else if(("appRejection").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Application Rejection");
		}
		else if(("appraisalPromotibility").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("AP");
		}
		else if(("evalTrainExp").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Evaluation of Training and Experience");
		}
		else if(("interview").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Interview");
		}
		else if(("perfTest").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Performance Test");
		}
		else if(("vetCredit").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Veterans Credit");
		}
		else if(("writterTest").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Written Tests");
		}
		else if(("workstyleAssessment").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("WSA");
		}
		else if(("other").equals(fieldName) && "on".equals(fieldValue)) {
			this.setAppealType("Other");
		}
		else if(("otherTextBox").equals(fieldName)) {
			this.setOtherAppealTypeText(fieldValue);
		}
		else if(fieldName.startsWith("fileDesc")) {
			this.fileDesc.add(fieldValue);
		}
		else if(fieldName.startsWith("uploadedfile")) {
			this.filePath.add(fieldValue);
		}
	}

	public String getRepContactNameFirst() {
		return repContactNameFirst;
	}

	public void setRepContactNameFirst(String repContactNameFirst) {
		this.repContactNameFirst = repContactNameFirst.toUpperCase();
	}

	public String getRepContactNameLast() {
		return repContactNameLast;
	}

	public void setRepContactNameLast(String repContactNameLast) {
		this.repContactNameLast = repContactNameLast.toUpperCase();
	}
}
