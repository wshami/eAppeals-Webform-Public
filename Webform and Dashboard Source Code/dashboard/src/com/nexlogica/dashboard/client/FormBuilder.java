package com.nexlogica.dashboard.client;

import gwtquery.plugins.gwtcaptcha.client.Captcha;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.nexlogica.dashboard.client.combobox.DropDown;
import com.nexlogica.dashboard.client.combobox.DropDownProperties;
import com.nexlogica.dashboard.client.handlers.FileUploadHandler;
import com.nexlogica.dashboard.client.handlers.FormSubmitHandler;
import com.nexlogica.dashboard.html.HTMLBuilder;
import com.nexlogica.dashboard.validators.CaseNumberValidator;
import com.nexlogica.dashboard.validators.FileExtensionFrontEndValidator;
import com.nexlogica.dashboard.validators.FirstNameValidator;
import com.nexlogica.dashboard.validators.Last4SSNValidator;
import com.nexlogica.dashboard.validators.LastNameValidator;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer.HorizontalLayoutData;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer.VBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class FormBuilder {

	private static int fieldCounter = 0;
	private static VerticalLayoutContainer attachments;
	private static TextButton attachMoreButton;
	private static TextButton submitButton;
	private static HTML fileTypeLabel = new HTML(HTMLBuilder.DOC_TYPE_ALLOWABLE);
	private static int instructionLength = 0;
	
	public static FormPanel getInputForm(){
		FormPanel form = new FormPanel();

		TextField inputValue1 = new TextField();
		TextField inputValue2 = new TextField(); 
		TextField inputValue3 = new TextField();
		TextArea inputValue4 = new TextArea();    
		//TextField inputValue4 = new TextField();

		// item ID is for search
		inputValue1.setItemId("inputValue1"); // First Name
		inputValue2.setItemId("inputValue2"); // Last Name
		inputValue3.setItemId("inputValue3"); // Last 4 SSN 
		inputValue4.setItemId("inputValue4"); // Case Number

		// name is set so that the field values can be grabbed during form submission
		inputValue1.setName("firstName"); 
		inputValue2.setName("lastName"); 
		inputValue3.setName("last4SSN"); 
		inputValue4.setName("caseNumbers"); 

		// add validation
		inputValue1.addValidator(new FirstNameValidator());
		inputValue2.addValidator(new LastNameValidator());
		inputValue3.addValidator(new Last4SSNValidator());
		inputValue4.addValidator(new CaseNumberValidator());

		// empty text
//		inputValue1.setEmptyText("First Name*");
//		inputValue2.setEmptyText("Last Name*");
//		inputValue3.setEmptyText("Last 4 digits of SSN*");
//		inputValue4.setEmptyText("Type one or multiple Appeal Case numbers, separated by commas (Optional)");
		
		inputValue3.setEmptyText("####");
		inputValue4.setEmptyText("Type one or multiple Appeal Case numbers, separated by commas");

		// default values
		inputValue1.setAllowBlank(false);
		inputValue2.setAllowBlank(false);
		inputValue3.setAllowBlank(false);
		inputValue4.setAllowBlank(true);

		inputValue1.setWidth(400);
		inputValue2.setWidth(400);
		inputValue3.setWidth(250);
		inputValue4.setWidth(500);

		inputValue1.setHeight(40);
		inputValue2.setHeight(40);
		inputValue3.setHeight(40);
		inputValue4.setHeight(120);		
		
		FieldLabel iv1Label = new FieldLabel(inputValue1, "First Name");
		FieldLabel iv2Label = new FieldLabel(inputValue2, "Last Name");
		FieldLabel iv3Label = new FieldLabel(inputValue3, "Last 4 digits of SSN");
		FieldLabel iv4Label = new FieldLabel(inputValue4, "Case Number(s)(optional)");
		
		iv1Label.setLabelAlign(LabelAlign.TOP);
		iv2Label.setLabelAlign(LabelAlign.TOP);
		iv3Label.setLabelAlign(LabelAlign.TOP);
		iv4Label.setLabelAlign(LabelAlign.TOP);


		iv1Label.setStylePrimaryName("captchaLabel");
		iv2Label.setStylePrimaryName("captchaLabel");
		iv3Label.setStylePrimaryName("captchaLabel");
		iv4Label.setStylePrimaryName("captchaLabel");
		
		inputValue4.setToolTip("For multiple appeals, please enter case numbers separated by commas");

		BoxLayoutData thirtyMarginLeft = new BoxLayoutData(new Margins(60, 0, 0, 30));
		BoxLayoutData thirtyMarginLeft2 = new BoxLayoutData(new Margins(60, 0, 7, 30));

		// container to hold left 3 field labels
		VBoxLayoutContainer vlc = new VBoxLayoutContainer(VBoxLayoutAlign.LEFT);
		vlc.add(iv1Label, thirtyMarginLeft);
		vlc.add(iv2Label, thirtyMarginLeft);
		vlc.add(iv3Label, thirtyMarginLeft);

		// container to hold right optional field label
		VBoxLayoutContainer vlc2 = new VBoxLayoutContainer(VBoxLayoutAlign.LEFT);								  // replace the below commented line with the preceeding HTML if the tooltip is unsatisfactory
		// vlc2.add(new HTML("<p style='font-size:29px; border-bottom: 2px solid black; !important'>Optional</p>"), new BoxLayoutData(new Margins(0, 0, 0, 20))); //vlc2.add(new HTML("<p style='font-size:29px; border-bottom: 2px solid black; !important'>Optional<br />For multiple appeals, please enter case numbers separated by commas</p>"));
		vlc2.add(iv4Label, thirtyMarginLeft2);


		Captcha c = new Captcha();
		HTML cLabel = new HTML("<b>SECURITY CHECK</b><br /><br />Please enter the text below.");
		cLabel.setStyleName("captchaLabel");
		HTML cLabel_lower = new HTML("<br />Can't read the text above?<br />Try another set by refreshing the captcha with the <img src=\"recycleicon.png\" height=\"25px\" width=\"25px\"> button.");
		cLabel_lower.setStyleName("captchaLabel");
		VBoxLayoutContainer captchaContainer = new VBoxLayoutContainer();
		captchaContainer.add(cLabel);
		captchaContainer.add(c);
		captchaContainer.add(cLabel_lower);
		captchaContainer.setHeight(380);

		TextButton trackButton = new TextButton("Search");
		trackButton.setWidth(200); trackButton.setHeight(75);


		inputValue1.addKeyUpHandler(new SubmitListener(trackButton));
		inputValue2.addKeyUpHandler(new SubmitListener(trackButton));
		inputValue3.addKeyUpHandler(new SubmitListener(trackButton));
		inputValue4.addKeyUpHandler(new SubmitListener(trackButton));

		VBoxLayoutContainer searchAndCaptchaContainer = new VBoxLayoutContainer();
		searchAndCaptchaContainer.add(captchaContainer, thirtyMarginLeft);
		searchAndCaptchaContainer.add(trackButton, thirtyMarginLeft);
		searchAndCaptchaContainer.setStyleName("searchAndCaptchaContainer");
		// container to hold fields side by sidea
		HorizontalLayoutContainer hlc = new HorizontalLayoutContainer();
//		hlc.add(vlc, new HorizontalLayoutData(525,510));
//		hlc.add(vlc2, new HorizontalLayoutData(600,510));
		hlc.add(vlc);
		hlc.add(vlc2);
		hlc.setHeight(540);

		BoxLayoutData v = new BoxLayoutData();

		VBoxLayoutContainer parentContainer = new VBoxLayoutContainer();
		parentContainer.add(hlc,v);
		parentContainer.add(searchAndCaptchaContainer, v);


		form.add(parentContainer);

		//		form.add(hlc);

		trackButton.addSelectHandler(new FormSubmitHandler(form));
		trackButton.setShadow(true);

//		form.setHeight(890);
		form.setAction(GWT.getModuleBaseURL()+"submitform");
		form.setEncoding(Encoding.MULTIPART);
		form.setMethod(Method.POST);

		form.setId("submissionForm");

		return form;
	}

	public static FormPanel getFileUploadForm(JSONObject results, int i)
	{
		final FormPanel form = new FormPanel();
		
		final VerticalLayoutContainer vpParentPanel = new VerticalLayoutContainer();
		final HorizontalLayoutContainer hpFormFields = new HorizontalLayoutContainer();

		JSONArray ja = (JSONArray) results.get("cases");
		JSONObject tempjo = (JSONObject) ja.get(i);
		instructionLength = tempjo.get("instructions").toString().length();

		final Label commentLabel = new Label("Reply/Comment");
		commentLabel.setStyleName("replyLabel");
		TextArea commentBox = new TextArea(); 
		commentBox.setHeight(150); 
		commentBox.setEmptyText("Reply/Comment");
		commentBox.setWidth(600); 
		commentBox.setName("commentBox");
		commentBox.setLayoutData(new BoxLayoutData(new Margins(0, 0, 15, 0)));
		commentBox.addValidator(new AbstractValidator<String>(){

			@Override
			public List<EditorError> validate(Editor<String> editor,
					String value) {

				List<EditorError> errors = null;

				if(value != null && value.length() > 500){
					String message = "500 character limit.";
					errors = createError(editor, message, value);
				}
				
				if((value == null || value == "")&& fieldCounter == 0){
					String message = "Please enter a reply.";
					errors = createError(editor, message, value);
				}
				
				return errors;
			}});

		final VerticalLayoutContainer vp = new VerticalLayoutContainer();
		//vp.add(commentLabel); 
		vp.add(commentBox);
		vp.setLayoutData(new BoxLayoutData(new Margins(0, 0, 25, 15)));

		attachments = new VerticalLayoutContainer();
		attachments.setId("attachmentsVLC");

		attachMoreButton = new TextButton("Attach Documents");
		attachMoreButton.setLayoutData(new BoxLayoutData(new Margins(20, 0, 15, 40)));
		//attachMoreButton.setWidth(140);
		//attachMoreButton.setHeight(30);
		attachMoreButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				onAttachAdditionalDocuments(form);
			}
		});

		submitButton = new TextButton("Submit");
		submitButton.setLayoutData(new BoxLayoutData(new Margins(20, 0, 15, 40)));
		//submitButton.setWidth(140);
		//submitButton.setHeight(30);
		submitButton.addSelectHandler(new FileUploadHandler(form));

		// add formfields - these are added because the results need to be carried over
		TextField inputValue1 = new TextField();
		TextField inputValue2 = new TextField();
		TextField inputValue3 = new TextField();
		TextField inputValue4 = new TextField();

		// item ID is for search
		inputValue1.setItemId("inputValue1"); // First Name
		inputValue2.setItemId("inputValue2"); // Last Name
		inputValue3.setItemId("inputValue3"); // Last 4 SSN 
		inputValue4.setItemId("inputValue4"); // Case Number

		// name is set so that the field values can be grabbed during form submission
		inputValue1.setName("firstName"); 
		inputValue2.setName("lastName"); 
		inputValue3.setName("last4SSN"); 
		inputValue4.setName("caseNumber"); 

		// set values saved from search
		inputValue1.setValue(tempjo.get("firstName").toString().replace("\"", ""));
		inputValue2.setValue(tempjo.get("lastName").toString().replace("\"", ""));
		inputValue3.setValue(tempjo.get("last4SSN").toString().replace("\"", ""));
		inputValue4.setValue(tempjo.get("caseNumber").toString().replace("\"", ""));

		// add values to container
		hpFormFields.add(inputValue1);
		hpFormFields.add(inputValue2);
		hpFormFields.add(inputValue3);
		hpFormFields.add(inputValue4);

		// hide values
		hpFormFields.setVisible(false);

		final VerticalLayoutContainer infoVLC = new VerticalLayoutContainer();
		infoVLC.add(HTMLBuilder.getTabViewTableFor(results, i));
		infoVLC.setLayoutData(new BoxLayoutData(new Margins(40, 0, 25, 15)));

		// add panels to container
		vpParentPanel.add(hpFormFields);
		vpParentPanel.add(infoVLC);
		vpParentPanel.add(vp);	
		vpParentPanel.add(attachments);
		vpParentPanel.add(attachMoreButton);
		vpParentPanel.add(submitButton);

		// add container to form
		form.add(vpParentPanel);
		
		form.setHeight(850 + (instructionLength / 90) * 15);
		
		
		// check to make sure that this is correct for the file upload
		form.setAction(GWT.getModuleBaseURL()+"submitform");
		form.setEncoding(Encoding.MULTIPART);
		form.setMethod(Method.POST);
		form.setBorders(false);

		return form;
	}

	public static void onAttachAdditionalDocuments(final FormPanel form) {

		fileTypeLabel.setLayoutData(new BoxLayoutData(new Margins(0, 0, 10, 15)));
		
		// singleton counter for number of fields
		fieldCounter++;

		final HBoxLayoutContainer hp = new HBoxLayoutContainer();
		final HBoxLayoutContainer hp2 = new HBoxLayoutContainer();
		final HBoxLayoutContainer hp3 = new HBoxLayoutContainer();
		
		String counterStr = String.valueOf(fieldCounter);

		final FileUploadField file = new FileUploadField();

		file.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {

				FileExtensionFrontEndValidator fe = new FileExtensionFrontEndValidator();

				if(!fe.isValidExtension(file.getValue())){
					new MessageBox("Error","The file extension you are uploading is not acceptable. Please refer to the list of acceptable formats.").show();
					file.reset();
				}
			}
		});

		file.setName("uploadedfile" + counterStr);
		file.setAllowBlank(false);
		file.setWidth(550);

		FieldLabel fileLabel = new FieldLabel(file, "File");
		fileLabel.setLabelPad(20);
		fileLabel.setWidth(725);
		fileLabel.setHeight(45);
		fileLabel.setLabelWidth(50);
		fileLabel.setLayoutData(new BoxLayoutData(new Margins(0, 15, 0, 15)));
		hp.add(fileLabel);

		DropDownProperties properties = GWT.create(DropDownProperties.class);
		ListStore<DropDown> store = new ListStore<DropDown>(properties.key());
		store.addAll(getAttachmentDescription());

		
		ComboBox<DropDown> fileDesc = new ComboBox<DropDown>(store, properties.nameLabel());
		fileDesc.setName("fileDesc" + counterStr);
		fileDesc.setAllowBlank(false);
		fileDesc.setWidth(0);
		fileDesc.setTypeAhead(true);
		fileDesc.setOriginalValue(fileDesc.getStore().get(0));
		fileDesc.setValue(fileDesc.getStore().get(0));
		fileDesc.setTriggerAction(TriggerAction.ALL);

		FieldLabel fileDescLabel = new FieldLabel(fileDesc, "Description");
		fileDescLabel.setLabelPad(0);
		fileDescLabel.setWidth(0);
		fileDescLabel.setLabelWidth(0);
		fileDescLabel.setLayoutData(new BoxLayoutData(new Margins(5, 15, 5, 15)));
		fileDescLabel.setVisible(false);
		fileDescLabel.setHeight(45);
		fileDescLabel.setStyleName("fileDescLabel");
		hp2.add(fileDescLabel);


		Button remove = new Button();
		remove.setStyleName("removeButton");
		remove.setTitle("Remove Document");
		remove.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				hp.removeFromParent();
				hp2.removeFromParent();
				
				fieldCounter--;
				
				if(attachments.getWidgetCount() == 1){
					attachments.remove(0);
					form.setHeight(840 + (instructionLength / 90) * 12);
					
				}
				else
					form.setHeight(1125 + 55*fieldCounter + ((instructionLength / 90) * 12));
				
			}
		});
		
		hp.add(remove);
		hp.setWidth(790);
		hp.getElement().getStyle().setMarginTop(10, Unit.PX);
		if(fieldCounter == 1){
			if(hp3.getParent() == null){
				if(hp3.getWidgetCount() == 0)
					hp3.add(fileTypeLabel);
				attachments.add(hp3, new VerticalLayoutData(1, -1));
			}
			if(!hp3.isVisible())
				hp3.show();
			
		}
		
		attachments.add(hp, new VerticalLayoutData(1, -1));
		attachments.add(hp2, new VerticalLayoutData(1, -1));

		//attachMoreButton.setWidth(350);
		attachMoreButton.setText("Attach Additional Documents");

		form.setHeight(1115 + 55*fieldCounter + ((instructionLength / 90) * 12));
	}

	public static List<DropDown> getAttachmentDescription() {
		List<DropDown> attachmentDescList = new ArrayList<DropDown>();
		// At request of client, only 'other' is allowed to be selected
//		attachmentDescList.add(new DropDown("Appeal Letter"));
//		attachmentDescList.add(new DropDown("Notification Letter/Email"));
//		attachmentDescList.add(new DropDown("Postmarked Envelope"));
//		attachmentDescList.add(new DropDown("Classification Study"));
//		attachmentDescList.add(new DropDown("Original AP"));
//		attachmentDescList.add(new DropDown("Most Recent PE (1st)"));
//		attachmentDescList.add(new DropDown("Prior Year PE (2nd)"));
//		attachmentDescList.add(new DropDown("Previous Year PE (3rd)"));
//		attachmentDescList.add(new DropDown("Authorization for Release Confidential Information"));
		attachmentDescList.add(new DropDown("Other"));
		
		return attachmentDescList;
	}



}

