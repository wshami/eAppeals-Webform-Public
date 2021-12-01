package com.nexlogica.form.servlet;

import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nexlogica.form.servlet.bean.FormBean;

public class XMLBuilder {
	private FormBean form;
	public XMLBuilder(FormBean form) {
		this.form = form;
	}

	public void createXMLFile(String xmlFilePath) throws Exception
	{

		try
		{
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
			Element rootElement = document.createElement("form");
			document.appendChild(rootElement);
			
			Element form_number = document.createElement("form_number");
			form_number.appendChild(document.createTextNode(String.valueOf(form.getFormRandomNumber())));
			rootElement.appendChild(form_number);

			Element appeal_type = document.createElement("appeal_type");
			appeal_type.appendChild(document.createTextNode(form.getAppealType()));
			rootElement.appendChild(appeal_type);
			
			Element otherAppealTypeText = document.createElement("otherAppealTypeText");
			otherAppealTypeText.appendChild(document.createTextNode(form.getOtherAppealTypeText()));
			rootElement.appendChild(otherAppealTypeText);

			Element examNumber = document.createElement("examNumber");
			examNumber.appendChild(document.createTextNode(form.getExamNumber()));
			rootElement.appendChild(examNumber);

			Element jobTitle = document.createElement("jobTitle");
			jobTitle.appendChild(document.createTextNode(form.getJobTitle()));
			rootElement.appendChild(jobTitle);

			Element isRepresentative = document.createElement("isRepresentative");
			isRepresentative.appendChild(document.createTextNode(Boolean.toString(form.getIsRepresentative())));
			rootElement.appendChild(isRepresentative);

			Element appellantFirstName = document.createElement("appellantFirstName");
			appellantFirstName.appendChild(document.createTextNode(form.getAppellantFirstName()));
			rootElement.appendChild(appellantFirstName);

			Element appellantMiddleName = document.createElement("appellantMiddleName");
			appellantMiddleName.appendChild(document.createTextNode(form.getAppellantMiddleName()));
			rootElement.appendChild(appellantMiddleName);

			Element appellantLasttName = document.createElement("appellantLasttName");
			appellantLasttName.appendChild(document.createTextNode(form.getAppellantLasttName()));
			rootElement.appendChild(appellantLasttName);

			Element aka = document.createElement("aka");
			aka.appendChild(document.createTextNode(form.getAka()));
			rootElement.appendChild(aka);

			Element last4SSN = document.createElement("last4SSN");
			last4SSN.appendChild(document.createTextNode(form.getLast4SSN()));
			rootElement.appendChild(last4SSN);

			Element mailingAddress = document.createElement("mailingAddress");
			mailingAddress.appendChild(document.createTextNode(form.getMailingAddress()));
			rootElement.appendChild(mailingAddress);

			Element city = document.createElement("city");
			city.appendChild(document.createTextNode(form.getCity()));
			rootElement.appendChild(city);

			Element state = document.createElement("state");
			state.appendChild(document.createTextNode(form.getState()));
			rootElement.appendChild(state);

			Element zip = document.createElement("zip");
			zip.appendChild(document.createTextNode(form.getZip()));
			rootElement.appendChild(zip);
			
			Element appellantPhone = document.createElement("appellantPhone");
			appellantPhone.appendChild(document.createTextNode(form.getAppellantPhone()));
			rootElement.appendChild(appellantPhone);
			
			Element appellantPhoneExt = document.createElement("appellantPhoneExt");
			appellantPhoneExt.appendChild(document.createTextNode(form.getAppellantPhoneExt()));
			rootElement.appendChild(appellantPhoneExt);

			Element appellantEmailAddress = document.createElement("appellantEmailAddress");
			appellantEmailAddress.appendChild(document.createTextNode(form.getAppellantEmailAddress()));
			rootElement.appendChild(appellantEmailAddress);

			Element appellantEmployeeNumber = document.createElement("appellantEmployeeNumber");
			appellantEmployeeNumber.appendChild(document.createTextNode(form.getAppellantEmployeeNumber()));
			rootElement.appendChild(appellantEmployeeNumber);

			Element appellantEmployingDept = document.createElement("appellantEmployingDept");
			appellantEmployingDept.appendChild(document.createTextNode(form.getAppellantEmployingDept()));
			rootElement.appendChild(appellantEmployingDept);

			Element appellantPayrollTitle = document.createElement("appellantPayrollTitle");
			appellantPayrollTitle.appendChild(document.createTextNode(form.getAppellantPayrollTitle()));
			rootElement.appendChild(appellantPayrollTitle);

			Element repCompanyName = document.createElement("repCompanyName");
			repCompanyName.appendChild(document.createTextNode(form.getRepCompanyName()));
			rootElement.appendChild(repCompanyName);

			Element repContactNameFirst = document.createElement("repContactNameFirst");
			repContactNameFirst.appendChild(document.createTextNode(form.getRepContactNameFirst()));
			rootElement.appendChild(repContactNameFirst);

			Element repContactNameLast = document.createElement("repContactNameLast");
			repContactNameLast.appendChild(document.createTextNode(form.getRepContactNameLast()));
			rootElement.appendChild(repContactNameLast);
			
			Element repContactAddress = document.createElement("repContactAddress");
			repContactAddress.appendChild(document.createTextNode(form.getRepContactAddress()));
			rootElement.appendChild(repContactAddress);

			Element repContactCity = document.createElement("repContactCity");
			repContactCity.appendChild(document.createTextNode(form.getRepContactCity()));
			rootElement.appendChild(repContactCity);

			Element repContactState = document.createElement("repContactState");
			repContactState.appendChild(document.createTextNode(form.getRepContactState()));
			rootElement.appendChild(repContactState);

			Element repContactZip = document.createElement("repContactZip");
			repContactZip.appendChild(document.createTextNode(form.getRepContactZip()));
			rootElement.appendChild(repContactZip);

			Element repContactPhone = document.createElement("repContactPhone");
			repContactPhone.appendChild(document.createTextNode(form.getRepContactPhone()));
			rootElement.appendChild(repContactPhone);

			Element repContactPhoneExt = document.createElement("repContactPhoneExt");
			repContactPhoneExt.appendChild(document.createTextNode(form.getRepContactPhoneExt()));
			rootElement.appendChild(repContactPhoneExt);
			
			Element repContactEmailAddress = document.createElement("repContactEmailAddress");
			repContactEmailAddress.appendChild(document.createTextNode(form.getRepContactEmailAddress()));
			rootElement.appendChild(repContactEmailAddress);

			Element appealIssue = document.createElement("appealIssue");
			appealIssue.appendChild(document.createTextNode(form.getAppealIssue()));
			rootElement.appendChild(appealIssue);

			Element appealRemedy = document.createElement("appealRemedy");
			appealRemedy.appendChild(document.createTextNode(form.getAppealRemedy()));
			rootElement.appendChild(appealRemedy);

			Element optOutEmail = document.createElement("responseType");
			optOutEmail.appendChild(document.createTextNode(form.getResponseType()));
			rootElement.appendChild(optOutEmail);
			
			Element receivedDate = document.createElement("receivedDate");
			receivedDate.appendChild(document.createTextNode(form.getReceivedDate()));
			rootElement.appendChild(receivedDate);
			
			List<String> filePathList = form.getFilePath();
			List<String> fileDescList = form.getFileDesc();
			for(int i=0; i < filePathList.size(); i++) {
				Element attachments = document.createElement("attachments");
				rootElement.appendChild(attachments);
				Element filePath = document.createElement("filePath");
				filePath.appendChild(document.createTextNode(filePathList.get(i)));
				
				Element fileDesc = document.createElement("fileDesc");
				fileDesc.appendChild(document.createTextNode(fileDescList.get(i)));		
				attachments.appendChild(filePath);
				attachments.appendChild(fileDesc);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF8");
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
