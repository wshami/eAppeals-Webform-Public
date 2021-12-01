package com.nexlogica.form.client;

 import gwtquery.plugins.gwtcaptcha.client.Captcha;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.nexlogica.form.client.combobox.DropDown;
import com.nexlogica.form.client.combobox.DropDownProperties;
import com.nexlogica.form.client.handlers.CharacterCounterKeyUpHandler;
import com.nexlogica.form.client.html.HTMLBuilder;
import com.nexlogica.form.client.radio.AppealTypeRadioBuilder;
import com.nexlogica.form.client.usercontrols.UserInfoControlsBuilder;
import com.nexlogica.form.client.validator.FileExtensionFrontEndValidator;
import com.nexlogica.form.client.validator.eitherOrValidator;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.ExpandMode;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.event.FocusEvent.FocusHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FileUploadField;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.FormPanel.Encoding;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.FormPanel.Method;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;

public class FormBuilder {
	private static final int MAX_CHAR_REMEDY = 1700;

	private UserInfoControlsBuilder userInfobuilder;
	private HBoxLayoutContainer repQuestionContainer; 
	private VerticalLayoutContainer vlc;
	private FormPanel form;
	private AccordionLayoutContainer accordion;
	private FieldSet userInfo;
	private VerticalLayoutContainer attachments;
	private static int fieldCounter = 0;
	private VerticalLayoutContainer vlcAppealsIssue;
	private VerticalLayoutContainer vlcExamInfo;
	private TextButton attachMoreButton;
	private HTML reqDocHTML_upper;
	private HTML reqDocHTML_lower;
	private HTML appealsRemedyLabel;
	private HTML appealsIssueLabel;
	private HTML captchaLabel;
	private HTML captchaLabel_lower;
	private Captcha captcha;
	private BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 0, 0, 15));

	private TextField examNumber;
	private TextField jobTitle;

	public FormBuilder() {
		repQuestionContainer = new HBoxLayoutContainer();
		accordion = new AccordionLayoutContainer();
		// accordion is used in the instantiation so that one of the fields inside of the user info builder can be created with validation referencing the accordion
		userInfobuilder = new UserInfoControlsBuilder(accordion);
		vlcExamInfo = new VerticalLayoutContainer();
		reqDocHTML_upper = new HTML(HTMLBuilder.APPEALS_REQ_DOC_HTML_UPPER);
		reqDocHTML_lower = new HTML(HTMLBuilder.APPEALS_REQ_DOC_HTML_LOWER);
		reqDocHTML_lower.getElement().getStyle().setWidth(60, Unit.PCT);
		reqDocHTML_lower.setVisible(false);
		examNumber = new TextField();
		examNumber.setName("examNumber");
		examNumber.setAllowBlank(true);
		examNumber.setWidth(170);

		jobTitle = new TextField();
		jobTitle.setName("jobTitle");
		jobTitle.setAllowBlank(true);
		jobTitle.setToolTip("The position for which you applied for");
		jobTitle.setWidth(170);
	}

	public FormPanel getForm() {

		form = new FormPanel();

		vlc = new VerticalLayoutContainer();
		vlc.setId("baseVLC"); // getWidget(0)

		// create VLC2 to hold HTML to enable padding/UI configuration per UAT
		VerticalLayoutContainer vlc2 = new VerticalLayoutContainer();
		vlc2.setLayoutData(layoutData);
		vlc2.add(new HTML(HTMLBuilder.APPEALS_TYPE_SELECT_HTML));
		vlc.add(vlc2); //getwidget0 getwidget 0

		this.accordion = getAccordionContainer();
		vlc.add(this.accordion);	// getwidget0 getwidget1
		vlc.add(getExamInformationForm()); // getwidget0 getwidget2
		vlc.add(getRepQuestion()); // getwidget0 getwidget3

		userInfo = userInfobuilder.getAppellantInfoForm();

		vlc.add(userInfo); // getwidget0 getwidget4
		vlc.add(getAppealIssueForm()); // getwidget0 getwidget5 id="issueAndRemedy"

		form.setAction(GWT.getModuleBaseURL()+"submitform");
		form.setEncoding(Encoding.MULTIPART);
		form.setMethod(Method.POST);
		form.add(vlc);
		return form;
	}

	public VerticalLayoutContainer getAppealIssueForm() {

		BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 0, 0, 0));

		vlcAppealsIssue = new VerticalLayoutContainer();
		RichTextArea appealIssue = new RichTextArea();
		appealIssue.setWidth("600px");
		appealIssue.setHeight("150px");

		TextArea appealRemedy = new TextArea();
		appealRemedy.setHeight("150px");
		appealRemedy.setWidth("600px");
		appealRemedy.addValidator(new MaxLengthValidator(MAX_CHAR_REMEDY));
		appealRemedy.setName("appealRemedy");
		appealRemedy.setAllowBlank(false);
		appealRemedy.setValidateOnBlur(true);
		Label appealRemedyCharacterCount = new Label("0/" + MAX_CHAR_REMEDY);
		appealRemedy.addKeyUpHandler(new CharacterCounterKeyUpHandler(appealRemedyCharacterCount, MAX_CHAR_REMEDY , appealRemedy));
		appealRemedy.addValueChangeHandler(new CharacterCounterKeyUpHandler(appealRemedyCharacterCount, MAX_CHAR_REMEDY , appealRemedy));
		attachments = new VerticalLayoutContainer();
		attachments.setId("attachmentsVLC");

		attachMoreButton = new TextButton("Attach Documents");
		attachMoreButton.setLayoutData(layoutData);
//		attachMoreButton.setWidth(140);
		attachMoreButton.setHeight(30);
		attachMoreButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				onAttachAdditionalDocuments();
			}
		});

		appealsIssueLabel = new HTML(HTMLBuilder.APPEALS_ISSUE_LABEL);
		appealsIssueLabel.setLayoutData(layoutData);

		appealsRemedyLabel = new HTML(HTMLBuilder.APPEALS_REMEDY_LABEL);
		appealsRemedyLabel.setLayoutData(layoutData);

		captchaLabel = new HTML(HTMLBuilder.CAPTCHA_LABEL);
		captchaLabel_lower = new HTML(HTMLBuilder.CAPTCHA_LABEL_BELOW); 
		captchaLabel_lower.setWidth("800px");

		captcha = new Captcha();
		
		
		final TextField issueHTML = new TextField();
		issueHTML.setVisible(false);
		issueHTML.setName("appealIssue");
		issueHTML.setHeight(0);
		issueHTML.setWidth(0);
//		issueHTML.markInvalid("issue not allowed to be blank!");
//		issueHTML.setAllowBlank(false);
		appealIssue.addKeyUpHandler(new KeyUpHandler(){
			String htmlText = "";
			@Override
			public void onKeyUp(KeyUpEvent arg0) {
				RichTextArea rta = (RichTextArea) arg0.getSource();
				htmlText = rta.getHTML().toString();
				issueHTML.setText(htmlText);
			}

		});
		appealIssue.addMouseOutHandler(new MouseOutHandler(){
			String htmlText = "";
			@Override
			public void onMouseOut(MouseOutEvent arg0) {
				RichTextArea rta = (RichTextArea) arg0.getSource();
				htmlText = rta.getHTML().toString();
				issueHTML.setText(htmlText);
			}
			
		});
		
		//		
		//		final TextField remedyHTML = new TextField();
		//		remedyHTML.setVisible(false);
		//		remedyHTML.setName("appealRemedy");
		//		remedyHTML.setHeight(1);
		//		remedyHTML.setWidth(1);
		//		remedyHTML.addValidator(new MaxLengthValidator(MAX_CHAR_REMEDY));
		//		remedyHTML.setLayoutData(layoutData);
		//		remedyHTML.getElement().getStyle().setWidth(15, Unit.PX);
		//		remedyHTML.getElement().getStyle().setHeight(15, Unit.PX);
		//		remedyHTML.getElement().getStyle().setPosition(Position.RELATIVE);
		//		remedyHTML.setStylePrimaryName("remedyHTMLTag");
		//		
		//		appealRemedy.addKeyUpHandler(new KeyUpHandler(){
		//			String htmlText = "";
		//			@Override
		//			public void onKeyUp(KeyUpEvent arg0) {
		//				RichTextArea rta = (RichTextArea) arg0.getSource();
		//				htmlText = rta.getHTML();			
		//				remedyHTML.setText(htmlText);
		//				if(htmlText.length() > MAX_CHAR_REMEDY)
		//					remedyHTML.forceInvalid("Too many characters in appeal remedy field!");
		//				else
		//					remedyHTML.clearInvalid();
		//			}
		//			
		//		});		
		
		vlcAppealsIssue.add(appealsIssueLabel);
		vlcAppealsIssue.add(appealIssue);
		//vlcAppealsIssue.add(appealIssueCharacterCount);
		vlcAppealsIssue.add(reqDocHTML_upper);
		vlcAppealsIssue.add(attachments);
		vlcAppealsIssue.add(attachMoreButton);
		vlcAppealsIssue.add(reqDocHTML_lower);
		vlcAppealsIssue.add(appealsRemedyLabel);
		vlcAppealsIssue.add(appealRemedy);
		vlcAppealsIssue.add(appealRemedyCharacterCount);
		//		vlcAppealsIssue.add(remedyHTML);
		vlcAppealsIssue.add(captchaLabel);
		vlcAppealsIssue.add(captcha);
		vlcAppealsIssue.add(captchaLabel_lower);
		vlcAppealsIssue.add(issueHTML);
		vlcAppealsIssue.setStyleName("AppealsIssueContainer");
		vlcAppealsIssue.setId("issueAndRemedy");
		vlcAppealsIssue.hide();

		return vlcAppealsIssue;
	}

	public VerticalLayoutContainer getExamInformationForm() {
		// if an examining test is selected, either an exam number or job title must be selected
		jobTitle.addValidator(new eitherOrValidator(this.accordion, examNumber));
		examNumber.addValidator(new eitherOrValidator(this.accordion, jobTitle));

		FieldLabel examNumberFieldLabel = new FieldLabel(examNumber, "Examination Number (case sensitive) - Enter only one examination number per appeal");
		examNumberFieldLabel.setLabelAlign(LabelAlign.TOP);
		examNumberFieldLabel.setWidth(500);

		FieldLabel jobTitleFieldLabel = new FieldLabel(jobTitle, "Job Title/Examination Name");
		jobTitleFieldLabel.setLabelAlign(LabelAlign.TOP);	
		jobTitleFieldLabel.setWidth(500);

		vlcExamInfo.add(new HTML(HTMLBuilder.APPEALS_EXAM_LABEL));
		vlcExamInfo.add(examNumberFieldLabel);
		vlcExamInfo.add(jobTitleFieldLabel);
		vlcExamInfo.add(new HTML("<br>"));
		vlcExamInfo.hide();
		vlcExamInfo.setLayoutData(layoutData);

		//onAttachAdditionalDocuments();
		return vlcExamInfo;
	}

	public void onAttachAdditionalDocuments() {

		// singleton counter for number of fields
		fieldCounter++;
		String counterStr = String.valueOf(fieldCounter);


		if(!reqDocHTML_lower.isVisible())
			reqDocHTML_lower.setVisible(true);
		
		final HorizontalPanel hp = new HorizontalPanel();
		final BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 25, 0, 0));

		// file upload field
		final FileUploadField file = new FileUploadField();
		file.setName("uploadedfile" + counterStr);
		file.setAllowBlank(false);
		file.setWidth(350);
		file.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {

				FileExtensionFrontEndValidator fe = new FileExtensionFrontEndValidator();

				if(!fe.isValidExtension(file.getValue())){
					new MessageBox("Error", file.getValue() + " has an invalid file extension. Please see the FAQs for valid file extensions.").show();
					file.reset();
				}
			}
		});


		// dropdown for file type
		DropDownProperties properties = GWT.create(DropDownProperties.class);
		ListStore<DropDown> store = new ListStore<DropDown>(properties.key());
		store.addAll(getAttachmentDescription());
		ComboBox<DropDown> fileDesc = new ComboBox<DropDown>(store, properties.nameLabel());
		fileDesc.setName("fileDesc" + counterStr);
		fileDesc.setAllowBlank(false);
		fileDesc.setWidth(450);
		fileDesc.setTypeAhead(true);
		fileDesc.setTriggerAction(TriggerAction.ALL);


		FieldLabel fileLabel = new FieldLabel(file, "File");
		fileLabel.setLabelWidth(40);
		fileLabel.setLabelPad(10);

		FieldLabel fileDescLabel = new FieldLabel(fileDesc, "Description");
		fileDescLabel.setLabelWidth(110);
		fileDescLabel.setLabelPad(10);
		fileDescLabel.getElement().getStyle().setPaddingLeft(40, Unit.PX);

		Button remove = new Button();
		remove.setLayoutData(layoutData);
		remove.getElement().getStyle().setBackgroundImage("minusicon.png");
		remove.setStyleName("removeButton");
		remove.setTitle("Remove Document");
		remove.addClickHandler(new ClickHandler(){
			@Override
			public void onClick(ClickEvent arg0) {
				hp.removeFromParent();
			}
		});

		hp.add(fileLabel);
		hp.add(fileDescLabel);
		hp.add(remove);
		attachments.add(hp);

//		attachMoreButton.setWidth(200);
		attachMoreButton.setText("Attach Additional Documents");

	}

	public void onSelectedAppellant() {
		userInfo.show();
		vlcAppealsIssue.show();
		userInfo.setHeadingHtml("<p class=\"userInfoHeaderText\" style=\"border: 1px solid black;\"><b>Appellant's Information</b></p>");
		userInfobuilder.setAppellantInfoForm();
	}

	public void onSelectedRepresentative() {
		userInfo.show();
		vlcAppealsIssue.show();
		userInfo.setHeadingHtml("<p class=\"userInfoHeaderText\" style=\"border: 1px solid black;\"><b>Appellant's Information</b></p>");
		userInfobuilder.setRepresentativeInfoForm();
	}

	public HBoxLayoutContainer getRepQuestion() {
		Radio radio = new Radio();
		radio.setName("radioYourself");
		radio.setBoxLabel("Appellant (self)");
		radio.addFocusHandler(new FocusHandler(){

			@Override
			public void onFocus(FocusEvent event) {
				onSelectedAppellant();
			}

		});

		Radio radio2 = new Radio();
		radio2.setName("radioRep");
		radio2.setBoxLabel("An authorized representative on behalf of the appellant");
		radio2.addFocusHandler(new FocusHandler(){

			@Override
			public void onFocus(FocusEvent event) {
				onSelectedRepresentative();
			}

		});

		// we can set name on radios or use toggle group
		ToggleGroup toggle = new ToggleGroup();
		toggle.add(radio);
		toggle.add(radio2);
		toggle.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {
			@Override
			public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
				ToggleGroup group = (ToggleGroup) event.getSource();
				Radio radio = (Radio) group.getValue();
				if(radio.getName().equals("radioYourself")) {
					onSelectedAppellant();
				}
				else {
					onSelectedRepresentative();
				}
			}
		});



		BoxLayoutData flex = new BoxLayoutData(new Margins(0, 5, 0, 0));
		flex.setFlex(1);

		repQuestionContainer.setPadding(new Padding(5));
		repQuestionContainer.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		repQuestionContainer.add(new HTML("<center><b>STEP 2 - IDENTIFY THE PERSON COMPLETING THIS FORM</b></center>"), new BoxLayoutData(new Margins(0, 5, 0, 15)));
		repQuestionContainer.add(new Label(), flex);
		repQuestionContainer.add(radio, new BoxLayoutData(new Margins(0, 5, 0, 0)));
		repQuestionContainer.add(new Label(), flex);
		repQuestionContainer.add(radio2, new BoxLayoutData(new Margins(0)));
		repQuestionContainer.setStyleName("RepQuestionContainer");
		repQuestionContainer.hide();
		return repQuestionContainer;
	}



	public AccordionLayoutContainer getAccordionContainer() {
		AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);
		AppealTypeRadioBuilder appRadio = new AppealTypeRadioBuilder(repQuestionContainer, vlcExamInfo, null, reqDocHTML_lower);

		ContentPanel cpDisciplinePA = new ContentPanel(appearance);
		cpDisciplinePA.setAnimCollapse(true);
		cpDisciplinePA.setHeadingText("DISCIPLINE / PERSONNEL ACTION");
		cpDisciplinePA.add(appRadio.getDisciplineRadioGroup());

		ContentPanel cpDisqualification = new ContentPanel(appearance);
		cpDisqualification.setAnimCollapse(true);
		cpDisqualification.setHeadingText("DISQUALIFICATION / WITHHOLD");
		cpDisqualification.add(appRadio.getDisqualificationRadioGroup());

		ContentPanel cpExamPosition = new ContentPanel(appearance);
		cpExamPosition.setAnimCollapse(true);
		cpExamPosition.setHeadingText("EXAMINATION COMPONENT");
		cpExamPosition.add(appRadio.getExamPositionRadioGroup());

		ContentPanel cpOther = new ContentPanel(appearance);
		cpOther.setAnimCollapse(true);
		cpOther.setHeadingText("OTHER");
		cpOther.add(new HTML("If you did not find your appeal type listed, please contact the Appeals Program at (213) 738-3934."));

		ContentPanel cpClassificationStudy = new ContentPanel(appearance);
		cpClassificationStudy.setAnimCollapse(true);
		cpClassificationStudy.setHeadingText("CLASSIFICATION STUDY");
		cpClassificationStudy.add(appRadio.getClassificationStudyRadioGroup());

		accordion.setExpandMode(ExpandMode.SINGLE_FILL);
		accordion.add(cpExamPosition);
		accordion.add(cpDisqualification);
		accordion.add(cpDisciplinePA);
		accordion.add(cpClassificationStudy);
		accordion.add(cpOther);
		accordion.setStylePrimaryName("appealTypeContainer");
		return accordion;
	}	

	public Window getFAQ() {
		AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);

		ContentPanel cp2 = new ContentPanel(appearance);
		cp2.setHeadingText(HTMLBuilder.APPEALS_QUESTION);
		cp2.add(new HTML(HTMLBuilder.APPEALS_ANSWER), new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp3 = new ContentPanel(appearance);
		cp3.setHeadingHtml(HTMLBuilder.APPEALS_ELIG_QUESTION);
		cp3.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_ELIG_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp4 = new ContentPanel(appearance);
		cp4.setHeadingText(HTMLBuilder.APPEALS_DEPARTMENT_QUESTION);
		cp4.add(new HTML(HTMLBuilder.APPEALS_DEPARTMENT_ANSWER),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp5 = new ContentPanel(appearance);
		cp5.setHeadingHtml(HTMLBuilder.APPEALS_DEADLINE_QUESTION);
		cp5.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_DEADLINE_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp6 = new ContentPanel(appearance);
		cp6.setHeadingHtml(HTMLBuilder.APPEALS_REQUIRE_QUESTION);
		cp6.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_REQUIRE_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp7 = new ContentPanel(appearance);
		cp7.setHeadingText(HTMLBuilder.APPEALS_FORMAT_QUESTION);
		cp7.add(new HTML(HTMLBuilder.APPEALS_FORMAT_ANSWER),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp8 = new ContentPanel(appearance);
		cp8.setHeadingHtml(HTMLBuilder.APPEALS_WORK_QUESTION);
		cp8.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_WORK_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp9 = new ContentPanel(appearance);
		cp9.setHeadingHtml(HTMLBuilder.APPEALS_MEET_QUESTION);
		cp9.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_MEET_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp10 = new ContentPanel(appearance);
		cp10.setHeadingText(HTMLBuilder.APPEALS_RESPONSE_QUESTION);
		cp10.add(new HTML(HTMLBuilder.APPEALS_RESPONSE_ANSWER),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp11 = new ContentPanel(appearance);
		cp11.setHeadingHtml(HTMLBuilder.APPEALS_ADDITIONAL_QUESTION);
		cp11.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_ADDITIONAL_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp12a = new ContentPanel(appearance);
		cp12a.setHeadingHtml(HTMLBuilder.APPEALS_TENDAY_QUESTION);
		cp12a.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_TENDAY_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp12 = new ContentPanel(appearance);
		cp12.setHeadingHtml(HTMLBuilder.APPEALS_ADMIN_QUESTION);
		cp12.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_ADMIN_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp13 = new ContentPanel(appearance);
		cp13.setHeadingText(HTMLBuilder.APPEALS_RELEVANT_QUESTION);
		cp13.add(new HTML(HTMLBuilder.APPEALS_RELEVANT_ANSWER),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp14 = new ContentPanel(appearance);
		cp14.setHeadingHtml(HTMLBuilder.APPEALS_DISQUAL_QUESTION);
		cp14.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_DISQUAL_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp15 = new ContentPanel(appearance);
		cp15.setHeadingHtml(HTMLBuilder.APPEALS_DONOTAGREE_QUESTION);
		cp15.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_DONOTAGREE_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp16 = new ContentPanel(appearance);
		cp16.setHeadingText(HTMLBuilder.APPEALS_CHANGEMIND_QUESTION);
		cp16.add(new HTML(HTMLBuilder.APPEALS_CHANGEMIND_ANSWER),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp17 = new ContentPanel(appearance);
		cp17.setHeadingHtml(HTMLBuilder.APPEALS_MOVE_QUESTION);
		cp17.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_MOVE_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp18 = new ContentPanel(appearance);
		cp18.setHeadingHtml(HTMLBuilder.APPEALS_GRIEVANCE_QUESTION);
		cp18.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_GRIEVANCE_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp19 = new ContentPanel(appearance);
		cp19.setHeadingHtml(HTMLBuilder.APPEALS_DISAB_QUESTION);
		cp19.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_DISAB_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp20 = new ContentPanel(appearance);
		cp20.setHeadingHtml(HTMLBuilder.APPEALS_ADD_QUESTION);
		cp20.add(new ScrollPanel(new HTML(HTMLBuilder.APPEALS_ADD_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		ContentPanel cp21 = new ContentPanel(appearance);
		cp21.setHeadingHtml(HTMLBuilder.END_FAQ_QUESTION);
		cp21.add(new ScrollPanel(new HTML(HTMLBuilder.END_FAQ_ANSWER)),  new BoxLayoutData(new Margins(0, 5, 0, 15)));

		AccordionLayoutContainer accordion = new AccordionLayoutContainer();
		accordion.setExpandMode(ExpandMode.SINGLE_FILL);
		accordion.add(cp2); //1
		accordion.add(cp3); //2
		accordion.add(cp18);//3
		accordion.add(cp4); //4
		accordion.add(cp5);// 5
		accordion.add(cp6);// 6
		accordion.add(cp7);// 7
		accordion.add(cp8);// 8
		accordion.add(cp9);// 9
		accordion.add(cp10);//10
		accordion.add(cp11);//11
		accordion.add(cp12a);//12
		accordion.add(cp12);//13
		accordion.add(cp13);//14
		accordion.add(cp14);//15
		accordion.add(cp15);//16
		accordion.add(cp16);//17
		accordion.add(cp17);//18

		accordion.add(cp20);//19
		accordion.add(cp19);//20

		accordion.add(cp21);

		final Window complex = new Window();
		complex.setResizable(true);
		complex.setModal(true);
		complex.setHeadingText("<b>Frequently Asked Questions</b>");
		complex.setWidth(1000);
		//		complex.setHeight(900);
		complex.add(accordion);

		return complex;

	}


	public List<DropDown> getAttachmentDescription() {
		List<DropDown> attachmentDescList = new ArrayList<DropDown>();
		attachmentDescList.add(new DropDown("Appeal Letter"));
		attachmentDescList.add(new DropDown("Notification Letter/Email"));
		attachmentDescList.add(new DropDown("Postmarked Envelope"));
		attachmentDescList.add(new DropDown("Classification Study"));
		attachmentDescList.add(new DropDown("Original AP"));
		attachmentDescList.add(new DropDown("Most Recent PE (1st)"));
		attachmentDescList.add(new DropDown("Prior Year PE (2nd)"));
		attachmentDescList.add(new DropDown("Previous Year PE (3rd)"));
		attachmentDescList.add(new DropDown("Authorization for Release Confidential Information"));
		attachmentDescList.add(new DropDown("Other"));

		return attachmentDescList;
	}



	public Window getInitialAckHtml() {
		final Window complex = new Window();
		complex.setResizable(true);
		complex.setModal(true);
		//complex.setHeadingText("Acknowledgement");
		complex.setWidth(1000);
		complex.setHeight(700);
		complex.setClosable(false);
		complex.setBlinkModal(true);
		TextButton agreeButton = new TextButton("I Agree");
		agreeButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				complex.hide();
			}
		});
		agreeButton.setWidth(250);
		agreeButton.setHeight(40);

		complex.setButtonAlign(BoxLayoutPack.CENTER);
		complex.addButton(agreeButton);

		ScrollPanel sp = new ScrollPanel(new HTML(HTMLBuilder.INITIAL_ACK));
		sp.setLayoutData(new BoxLayoutData(new Margins(10,25,0,25)));
		complex.add(sp);

		return complex;

	}


}
