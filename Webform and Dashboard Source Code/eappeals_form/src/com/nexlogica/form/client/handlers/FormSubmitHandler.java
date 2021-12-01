package com.nexlogica.form.client.handlers;

import gwtquery.plugins.gwtcaptcha.client.Captcha;

import java.util.List;

import ucar.nc2.grib.grib2.Grib2Pds.SatelliteBand;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.nexlogica.form.client.combobox.DropDown;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent;
import com.sencha.gxt.widget.core.client.event.BeforeShowEvent.BeforeShowHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.event.SubmitEvent;
import com.sencha.gxt.widget.core.client.event.SubmitEvent.SubmitHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.IsField;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FormSubmitHandler implements SelectHandler {

	final AutoProgressMessageBox messageBox = new AutoProgressMessageBox("Message Box - Wait", "Saving your data, please wait...");
	private FormPanel form = null;
	private Captcha captcha = null;
	Dialog success = null;
	Dialog simple = null;
	private int newWindowPositionTop = 1700;
	private int newWindowPositionLeft = 500;
	private static int newWindowOffset = 800;


	public FormSubmitHandler (FormPanel form, Captcha captcha) {
		this.form = form;
		this.captcha = captcha;

	}


	// this is used in the case that we don't have a captcha - when the application is in test
	public FormSubmitHandler (FormPanel form) { 
		this.form = form;

	}

	@Override
	public void onSelect(SelectEvent event) {        
		setNewWindowPositions();
		// create a confirmation message window
		if(simple == null) 
			createSimple();
		//		simple.setPosition(newWindowPositionLeft, newWindowPositionTop);
		//		simple.center();

		simple.show();
		simple.center();
		//		simple.getElement().center(true);

		form.addSubmitHandler(new SubmitHandler() {
			public void onSubmit(SubmitEvent event) {

			}
		});

		form.addSubmitCompleteHandler(new SubmitCompleteHandler() {
			public void onSubmitComplete(SubmitCompleteEvent event) {

				messageBox.hide();

				String rfs = event.getResults();

				if(rfs != "0") {
					MessageBox box = createResponseMessageBox(rfs);
					box.setIcon(MessageBox.ICONS.info());
					box.show();
				} else {
					if(success == null) 
						createSuccess();
					success.setPosition(newWindowPositionLeft, newWindowPositionTop);
					success.show();
					success.center();

				}
			}
		});

	}
	private void setNewWindowPositions() {
		VerticalLayoutContainer vlc = (VerticalLayoutContainer) this.form.getWidget(0);
		if(vlc.getWidget(4).getClass() == Widget.class){
			Widget vlc2 = vlc.getWidget(4);
			newWindowPositionTop = vlc2.getAbsoluteTop();
			newWindowPositionLeft = vlc2.getAbsoluteLeft() + newWindowOffset;
		}
	}


	private void createWait() {
		messageBox.setProgressText("Saving...");
		messageBox.auto();
		messageBox.show();
		messageBox.center();
	}

	// this is where all of the validation happens
	private void createSimple() {

		simple = new Dialog();
		simple.setPredefinedButtons();
		simple.setHeadingText("Certify and Submit");
		simple.setWidth(440);
		simple.setResizable(false);
		simple.setHideOnButtonClick(true);
		simple.setModal(true);
		simple.setBodyStyleName("pad-text");
		simple.setBodyStyle("padding: 10px 7px 10px 7px");

		simple.add(new Label("By clicking on the 'Accept and Submit' button, I certify that all the information and statements made in this appeal and any attachments pertaining thereto are true and complete to the best of my knowledge. I understand and agree that any false information and/or statement(s) of material facts or omissions may result in the denial of my appeal or subject me to discipline."));

		// accept button that starts most validation
		simple.addButton(new TextButton("Accept and Submit", new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				checkFormValidityAndSubmit();
			}
		}));

		// decline button
		simple.addButton(new TextButton("Review Appeal", new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				simple.hide();
			}
		}));                          
	}

	/***
	 * The method that fires when the submit button is pressed and the acknowledgement is approved
	 */
	protected void checkFormValidityAndSubmit() {

		final boolean WE_NEED_CAPTCHA = true;

		boolean requiredNumberOfDocuments = requiredNumberOfDocuments();

		if(this.captcha == null){
			// getwidget0 getwidget5 id="issueAndRemedy"
			Container base = (Container) form.getWidget(0);
			Container issueAndRemedy = (Container) base.getWidget(5);
			for(int i = 0; i < issueAndRemedy.getWidgetCount(); i++)
				if(issueAndRemedy.getWidget(i).getClass() == Captcha.class)
					this.captcha = (Captcha) issueAndRemedy.getWidget(i);

		}

		if(WE_NEED_CAPTCHA){
			// else if everything checks out, and we have a captcha and it's good submit
			if(form.isValid() && captcha.validate() && requiredNumberOfDocuments && !issueIsBlank()){
				createWait();
				form.submit();

			}
			else {
				MessageBox errorBox = null;
				if(!captcha.validate()){
					errorBox = new MessageBox("Error", "Captcha guess incorrect.");
					errorBox.setPosition(newWindowPositionLeft,  newWindowPositionTop);
					errorBox.setStylePrimaryName("dialogBoxText");
					errorBox.show();
					errorBox.center();
				} else if(!requiredNumberOfDocuments){
					errorBox = new MessageBox("Error", "You have not uploaded the required document types.");
					errorBox.setPosition(newWindowPositionLeft,  newWindowPositionTop);
					errorBox.setStylePrimaryName("dialogBoxText");
					errorBox.show();
					errorBox.center();
				} else if(issueIsBlank()){
					errorBox = new MessageBox("Error", "Issue description is blank.");
					errorBox.setPosition(newWindowPositionLeft,  newWindowPositionTop);
					errorBox.setStylePrimaryName("dialogBoxText");
					errorBox.show();
					errorBox.center();
				}
				else{
					errorBox = new MessageBox("Error", "Please fix invalid fields marked with red '!' mark.");
					errorBox.setPosition(newWindowPositionLeft,  newWindowPositionTop);
					errorBox.setStylePrimaryName("dialogBoxText");
					errorBox.show();
					errorBox.center();
				}

			}
		}else{
			if(form.isValid() && requiredNumberOfDocuments&& !issueIsBlank()){
				createWait();
				form.submit();

			}
			else {
				MessageBox errorBox = null;
				if(!requiredNumberOfDocuments){
					errorBox = new MessageBox("Error", "You have not uploaded the required document types.");
					errorBox.setPosition(newWindowPositionLeft,  newWindowPositionTop);
					errorBox.show();
					errorBox.center();
				}else if(issueIsBlank()){
					errorBox = new MessageBox("Error", "Issue description is blank.");
					errorBox.setPosition(newWindowPositionLeft,  newWindowPositionTop);
					errorBox.show();
					errorBox.center();
				}
				else{
					errorBox = new MessageBox("Error", "Please fix invalid fields marked with red '!' mark.");
					errorBox.setPosition(newWindowPositionLeft,  newWindowPositionTop);
					errorBox.show();
					errorBox.center();
				}

			}
		}

		// if not, create error message containing the error


		simple.hide();

	}


	private boolean issueIsBlank() {
		// getwidget0 getwidget5 id="issueAndRemedy"
		Container base = (Container) form.getWidget(0);
		Container issueAndRemedy = (Container) base.getWidget(5);
		for(int i = 0; i < issueAndRemedy.getWidgetCount(); i++)
			if(issueAndRemedy.getWidget(i).getClass() == RichTextArea.class)
				return ((RichTextArea)issueAndRemedy.getWidget(i)).getText().equals("") || ((RichTextArea)issueAndRemedy.getWidget(i)).getText().equals("\n") ;

		return false;

	}


	private void createSuccess() {
		success = new Dialog();
		success.setPredefinedButtons();
		success.setHeadingText("Submission Successful");
		success.setWidth(540);
		success.setResizable(false);
		success.setBodyStyle("padding: 10px 7px 10px 7px");
		success.setHideOnButtonClick(true);
		success.setBodyStyleName("pad-text");
		success.setModal(true);
		success.add(new HTML("Thank you for submitting your appeal.<br /><br />You will receive a confirmation email from eAppeals_DONOTREPLY@hr.lacounty.gov with the case number within an hour; unless you elected to receive the response by U.S. Mail. "
				+ "<br /> <br />If you do not receive this confirmation email, please check your spam/junk folder before contacting our office at (213) 738-3934 between 8:00 a.m. to 5:00 p.m., Monday through Friday, except on County holidays."
				+ "<br /><br />Would you like to submit another appeal?"));
		success.addButton(new TextButton("Yes", new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				success.hide();
				resetFormKeepUserData();
			}
		}));
		success.addButton(new TextButton("No", new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				form.reset();
				success.hide();
				//Window.Location.reload();
				// NOTE: Comment below out and uncomment above to reload instead of navigate elsewhere
				Window.open("post_submission_landing.html", "_self", "");
			}
		}));
	}

	// function to create the response message box after submission based on the server's reply
	private MessageBox createResponseMessageBox(String responseFromServer) {
		MessageBox response;

		switch(responseFromServer){
		case "1":
			response = new MessageBox("Error", "Incorrect file type submitted. Please see FAQ for approved file types.");
			break;
		case "3":
			response = new MessageBox("Error", "Duplicate submission receieved. Please see FAQ about duplicate submissions.");
			break;
		case "4":
			response = new MessageBox("Error", "File content type mismatch with file extension.");
			break;
		case "0":
			response = new MessageBox("Success", "Your case has been successfully submitted");
			form.reset();
			break;
		default:
			response = new MessageBox("Error", "Server did not recognize response code:" + responseFromServer);
			break;
		}              
		return response;

	}

	// function to reset the form when the user wants to submit another form after the first one
	private void resetFormKeepUserData(){

		// initialize an array for field info
		String Info[] = {};

		ListStore<DropDown> stateStore;
		ListStore<DropDown> deptStore;
		// get all fields from form
		List<IsField<?>> fields = form.getFields();

		// iterate through fields (first loop - save values)
		for(IsField<?> field : fields) {

			// if the field is a radio button toggle group, save the index of the toggled radio button


			// if the field is a text field, it has a value we may want to save
			if(field.getClass() == TextField.class){

				// cast to text field
				TextField tf = (TextField) field;

				// check if name is in the list of text fields we want
				if(tf.getValue() == "")
					continue;
				else if(tf.getName() == "appellantFirstName")
					Info[0] = tf.getValue();
				else if(tf.getName() == "appellantMiddleName")
					Info[1] = tf.getValue();
				else if(tf.getName() == "appellantLasttName")
					Info[2] = tf.getValue();
				else if(tf.getName() == "aka")
					Info[3] = tf.getValue();
				else if(tf.getName() == "last4SSN")
					Info[4] = tf.getValue();
				else if(tf.getName() == "mailingAddress")
					Info[5] = tf.getValue();
				else if(tf.getName() == "city")
					Info[6] = tf.getValue();
				else if(tf.getName() == "appellantPhone")
					Info[7] = tf.getValue();
				else if(tf.getName() == "appellantPhoneExt")
					Info[8] = tf.getValue();
				else if(tf.getName() == "zip")
					Info[9] = tf.getValue();
				else if(tf.getName() == "appellantEmailAddress")
					Info[10] = tf.getValue();
				else if(tf.getName() == "appellantEmailAddressConfirm")
					Info[11] = tf.getValue();
				else if(tf.getName() == "appellantEmployeeNumber")
					Info[12] = tf.getValue();
				else if(tf.getName() == "appellantPayrollTitle")
					Info[13] = tf.getValue();
				else if(tf.getName() == "repCompanyName")
					Info[14] = tf.getValue();
				else if(tf.getName() == "repContactNameFirst")
					Info[15] = tf.getValue();
				else if(tf.getName() == "repContactNameLast")
					Info[26] = tf.getValue();
				else if(tf.getName() == "repContactAddress")
					Info[16] = tf.getValue();
				else if(tf.getName() == "repContactCity")
					Info[17] = tf.getValue();
				else if(tf.getName() == "repContactPhone")
					Info[18] = tf.getValue();
				else if(tf.getName() == "repContactPhoneExt")
					Info[19] = tf.getValue();
				else if(tf.getName() == "repContactZip")
					Info[20] = tf.getValue();
				else if(tf.getName() == "repContactEmailAddress")
					Info[21] = tf.getValue();
				else if(tf.getName() == "repContactEmailAddressConfirm")
					Info[22] = tf.getValue();
			} else if (field.getClass() == ComboBox.class) {
				ComboBox<?> cb = (ComboBox<?>) field;
				if (cb.getName() == "state"){
					Info[23] = cb.getText();
				} else if(cb.getName() == "appellantEmployingDept"){
					Info[24] = cb.getText();
				}else if(cb.getName() == "repContactState"){
					Info[25] = cb.getText();
				}
			}

		}

		// reset form after saving relevant values
		form.reset();

		// collapse the content panels
		VerticalLayoutContainer base = (VerticalLayoutContainer) form.getWidget(0);
		if(base.getWidget(1).getClass() == AccordionLayoutContainer.class) {
			AccordionLayoutContainer a = (AccordionLayoutContainer) base.getWidget(1);
			for(int widget = 0; widget<a.getWidgetCount(); widget++){
				if(a.getWidget(widget).getClass() == ContentPanel.class){
					ContentPanel cp = (ContentPanel) a.getWidget(widget);
					cp.setExpanded(false);
				}

			}
		}
		// exam information
		if(base.getWidget(2).getClass() == VerticalLayoutContainer.class) {
			VerticalLayoutContainer a = (VerticalLayoutContainer) base.getWidget(2);
			a.hide();
		}

		//repquestiuon
		if(base.getWidget(3).getClass() == HBoxLayoutContainer.class) {
			HBoxLayoutContainer a = (HBoxLayoutContainer) base.getWidget(3);
			a.hide();
		}

		//appellantinfo
		if(base.getWidget(4).getClass() == FieldSet.class) {
			FieldSet a = (FieldSet) base.getWidget(4);
			a.hide();
		}

		// appeal issues
		if(base.getWidget(5).getClass() == VerticalLayoutContainer.class) {
			VerticalLayoutContainer a = (VerticalLayoutContainer) base.getWidget(5);

			if(a.getWidget(1).getClass() == RichTextArea.class){
				RichTextArea rta = (RichTextArea) a.getWidget(1);
				rta.setHTML("");
			}

			a.hide();
		}

		// fill form with saved relevant values
		for(IsField<?> field : fields) {

			// if the field is a text field, it may be one we wish to load a value into
			if(field.getClass() == TextField.class){

				// cast to text field
				TextField tf = (TextField) field;

				// check if name is in the list of text fields we want
				if(tf.getName() == "appellantFirstName")
					tf.setValue(Info[0]);
				else if(tf.getName() == "appellantMiddleName")
					tf.setValue(Info[1]);
				else if(tf.getName() == "appellantLasttName")
					tf.setValue(Info[2]);
				else if(tf.getName() == "aka")
					tf.setValue(Info[3]);
				else if(tf.getName() == "last4SSN")
					tf.setValue(Info[4]);
				else if(tf.getName() == "mailingAddress")
					tf.setValue(Info[5]);
				else if(tf.getName() == "city")
					tf.setValue(Info[6]);
				else if(tf.getName() == "appellantPhone")
					tf.setValue(Info[7]);
				else if(tf.getName() == "appellantPhoneExt")
					tf.setValue(Info[8]);
				else if(tf.getName() == "zip")
					tf.setValue(Info[9]);
				else if(tf.getName() == "appellantEmailAddress")
					tf.setValue(Info[10]);
				else if(tf.getName() == "appellantEmailAddressConfirm")
					tf.setValue(Info[11]);
				else if(tf.getName() == "appellantEmployeeNumber")
					tf.setValue(Info[12]);
				else if(tf.getName() == "appellantPayrollTitle")
					tf.setValue(Info[13]);
				else if(tf.getName() == "repCompanyName")
					tf.setValue(Info[14]);
				else if(tf.getName() == "repContactNameFirst")
					tf.setValue(Info[15]);
				else if(tf.getName() == "repContactNameLast")
					tf.setValue(Info[26]);
				else if(tf.getName() == "repContactAddress")
					tf.setValue(Info[16]);
				else if(tf.getName() == "repContactCity")
					tf.setValue(Info[17]);
				else if(tf.getName() == "repContactPhone")
					tf.setValue(Info[18]);
				else if(tf.getName() == "repContactPhoneExt")
					tf.setValue(Info[19]);
				else if(tf.getName() == "repContactZip")
					tf.setValue(Info[20]);
				else if(tf.getName() == "repContactEmailAddress")
					tf.setValue(Info[21]);
				else if(tf.getName() == "repContactEmailAddressConfirm")
					tf.setValue(Info[22]);
			} else if (field.getClass() == ComboBox.class) {
				ComboBox<DropDown> cb = (ComboBox<DropDown>) field;
				if (cb.getName() == "state"){
					cb.setValue(new DropDown(Info[23]), true, true);
				} else if(cb.getName() == "appellantEmployingDept"){
					cb.setValue(new DropDown(Info[24]), true, true);
				} else if(cb.getName() == "repContactState"){
					cb.setValue(new DropDown(Info[25]), true, true);
				}
			}
			//			else if(field.getClass() == Radio.class)
			//			{
			//				Radio t = (Radio) field;
			//				if(t.getName().equals("radioRep") || t.getName().equals("radioYourself"))
			//					t.setValue(false);
			//					
			//			}

		}

		// reset captcha
		resetCaptcha();

	}

	// function to find the captcha inside of the buttons widget and reset it
	private void resetCaptcha(){

		if(this.captcha == null){
			// getwidget0 getwidget5 id="issueAndRemedy"
			Container base = (Container) form.getWidget(0);
			Container issueAndRemedy = (Container) base.getWidget(5);
			for(int i = 0; i < issueAndRemedy.getWidgetCount(); i++)
				if(issueAndRemedy.getWidget(i).getClass() == Captcha.class)
					this.captcha = (Captcha) issueAndRemedy.getWidget(i);			
		}

		this.captcha.reset();
		this.captcha.getTextBox().setText("");
	}

	// fucntion to check if the required number and type of documents have been submitted by the user
	private boolean requiredNumberOfDocuments(){
		String selectedRadio="";

		// get base
		VerticalLayoutContainer w = (VerticalLayoutContainer) form.getWidget(0);			

		// get accordion with radio buttons to compare against attachments
		AccordionLayoutContainer accordion = (AccordionLayoutContainer) w.getWidget(1);

		// get info
		VerticalLayoutContainer w2 = (VerticalLayoutContainer) w.getWidget(5);	

		// get attachmentsVLC
		VerticalLayoutContainer attachmentsVLC = (VerticalLayoutContainer) w2.getWidget(3);

		// get number of attachments submitted
		int numDocsSubmitted = 0;
		numDocsSubmitted = numDocsSubmitted + attachmentsVLC.getWidgetCount();

		// find out which radio button is selected
		for(int i = 0; i < accordion.getWidgetCount(); i++){

			// if the radio button has been found, exit loop
			if(selectedRadio != "")
				break;

			// grab each content panel
			ContentPanel cp = (ContentPanel) accordion.getWidget(i);

			if(cp.getWidget(0).getClass() == VerticalLayoutContainer.class) {

				// get the vlc that contains the radio buttons within the content panel
				VerticalLayoutContainer radioGroup = (VerticalLayoutContainer) cp.getWidget(0);

				// get exact number of radio buttons
				int wc = radioGroup.getWidgetCount();

				// step through each radio
				for(int j = 0; j < wc; j++){

					if(radioGroup.getWidget(j).getClass() == Radio.class){
						Radio r = (Radio) radioGroup.getWidget(j);

						// if the radio button is selected, get the name
						if(r.getValue() == true){
							selectedRadio = r.getName();
							break;
						}
					}
				}
			}

		}
		// if 'other' was selected, no docs required, return true
		if (selectedRadio == "other"){ 
			return true;}
		// if no radio selected, or no docs submitted, throw error
		else if(selectedRadio == "" || numDocsSubmitted == 0){
			return false;
		}
		// otherwise continue
		else {

			boolean notificationLetterUploaded = false;
			boolean ohpSpecificLetterUploaded = false;

			for(int i = 0; i < numDocsSubmitted; i++){
				// get the hp that contains the dropdown
				HorizontalPanel hp = (HorizontalPanel) attachmentsVLC.getWidget(i);

				// get the file description fieldlabel at index 1
				FieldLabel fileDescLabel = (FieldLabel) hp.getWidget(1);

				// get the widget
				@SuppressWarnings("rawtypes")
				ComboBox cb = (ComboBox) fileDescLabel.getWidget();

				// get the selected description
				String selectedDescription = cb.getText();

				if(selectedDescription == "Notification Letter/Email")
					notificationLetterUploaded = true;
				if(selectedDescription == "Authorization for Release Confidential Information")
					ohpSpecificLetterUploaded = true;

			}

			// if both were uploaded, will return true, otherwise false
			if(selectedRadio == "inaccurateDiscOHP"){
				return notificationLetterUploaded && ohpSpecificLetterUploaded;
			}
			// if notification letter uploaded, will return true
			else{
				return notificationLetterUploaded;
			}
		}



	}

}
