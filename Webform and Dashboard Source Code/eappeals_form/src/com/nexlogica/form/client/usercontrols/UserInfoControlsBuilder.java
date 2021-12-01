package com.nexlogica.form.client.usercontrols;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.nexlogica.form.client.combobox.DropDown;
import com.nexlogica.form.client.combobox.DropDownProperties;
import com.nexlogica.form.client.validator.*;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell.TriggerAction;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.Padding;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer.HBoxLayoutAlign;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.BlurEvent;
import com.sencha.gxt.widget.core.client.event.BlurEvent.BlurHandler;
import com.sencha.gxt.widget.core.client.form.CheckBox;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FormPanel.LabelAlign;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.RegExValidator;

public class UserInfoControlsBuilder {
	// regex to accept 10 digit phone number with () around area code
	private static final String RegExString = "(?:(?:(\\s*\\(?([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\s*)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\)?\\s*(?:[.-]\\s*)?)([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\s*(?:[.-]\\s*)?([0-9]{4})";
	// regex to accept valid email
	private static final String RegExStringEmail = "(?:[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[A-Za-z0-9-]*[A-Za-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

	private static final int MAXIMUM_CHAR_LIMIT = 50;
	private static final int FIELD_SIZE = 300;
	
	private TextField appellantFirstName;
	private TextField appellantMiddleName;
	private TextField appellantLastName;
	private TextField aka;
	private TextField last4SSN;
	private TextField mailingAddress;
	private DropDownProperties properties;
	private TextField city;
	private ComboBox<DropDown> state;
	private ListStore<DropDown> store;
	private ListStore<DropDown> repStore;
	private ComboBox<DropDown> departmentList;
	private ListStore<DropDown> departmentStore;
	private TextField appellantPhone;
	private TextField appellantPhoneExt;
	private TextField zip;
	private TextField appellantEmailAddress;
	private TextField appellantEmailAddressConfirm;
	private CheckBox optOutCheckBox;
	private TextField appellantEmployeeNumber;
	private TextField appellantPayrollTitle;
	private TextField repCompanyName;
	private TextField repContactNameFirst;
	private TextField repContactNameLast;
	private TextField repContactAddress;
	private TextField repContactCity;
	private ComboBox<DropDown> repContactState;
	private TextField repContactZip;
	private TextField repContactPhone;
	private TextField repContactPhoneExt;
	private TextField repContactEmailAddress;
	private TextField repContactEmailAddressConfirm;
	private CheckBox repOptOutCheckBox;


	private FieldLabel firstNameLabel;
	private FieldLabel middleNameLabel;
	private FieldLabel lastNameLabel;
	private FieldLabel akaFieldLabel;
	private FieldLabel last4SSNLabel;
	private FieldLabel mailingAddressLabel;
	private FieldLabel cityLabel;
	private FieldLabel stateLabel;
	private FieldLabel appellantPhoneLabel;
	private FieldLabel appellantPhoneExtLabel;
	private FieldLabel appellantZipLabel;
	private FieldLabel appellantEmailAddressLabel;
	private FieldLabel appellantEmailAddressConfirmLabel;
	private FieldLabel appellantEmployeeNumberLabel;
	private FieldLabel appellantEmployingDeptLabel;
	private FieldLabel appellantPayrollTitleLabel;
	private HTML ifCountyEmployeeLabel;
	private FieldLabel repCompanyNameLabel;
	private FieldLabel repContactNameFirstLabel;
	private FieldLabel repContactNameLastLabel;
	private FieldLabel repContactAddressLabel;
	private FieldLabel repContactCityLabel;
	private FieldLabel repContactStateLabel;
	private FieldLabel repContactZipLabel;
	private FieldLabel repContactPhoneLabel;
	private FieldLabel repContactPhoneExtLabel;
	private FieldLabel repContactEmailAddressLabel;
	private FieldLabel repContactEmailAddressConfirmLabel;

	private FieldSet fs;

	VerticalLayoutContainer repVLC;

	public UserInfoControlsBuilder(AccordionLayoutContainer accordion) {
		appellantFirstName = new TextField();
		appellantFirstName.setName("appellantFirstName");
		appellantFirstName.setAllowBlank(false);
		appellantFirstName.setWidth(FIELD_SIZE);
		appellantFirstName.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));

		appellantMiddleName = new TextField();
		appellantMiddleName.setName("appellantMiddleName");
		appellantMiddleName.setAllowBlank(true);
		appellantMiddleName.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		appellantMiddleName.setWidth(FIELD_SIZE);
		
		appellantLastName = new TextField();
		appellantLastName.setName("appellantLasttName");
		appellantLastName.setAllowBlank(false);		
		appellantLastName.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		appellantLastName.setWidth(FIELD_SIZE);

		
		aka = new TextField();
		aka.setName("aka");
		aka.setAllowBlank(true);
		aka.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		aka.setWidth(FIELD_SIZE);

		last4SSN = new TextField();
		last4SSN.setName("last4SSN");
		last4SSN.setAllowBlank(false);
		last4SSN.addValidator(new SSNValidator());
		last4SSN.setWidth(FIELD_SIZE);


		mailingAddress = new TextField();
		mailingAddress.setName("mailingAddress");
		mailingAddress.setAllowBlank(false);
		mailingAddress.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		mailingAddress.setWidth(FIELD_SIZE);

		city = new TextField();
		city.setName("city");
		city.setAllowBlank(false);
		city.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		city.setWidth(FIELD_SIZE);

		properties = GWT.create(DropDownProperties.class);

		store = new ListStore<DropDown>(properties.key());
		store.addAll(getStates());

		state = new ComboBox<DropDown>(store, properties.nameLabel());
		state.setName("state");
		state.setAllowBlank(false);
		state.setTypeAhead(true);
		state.setTriggerAction(TriggerAction.ALL);
		state.setWidth(FIELD_SIZE);

		appellantPhone = new TextField();
		appellantPhone.setName("appellantPhone");
		appellantPhone.setAllowBlank(false);
		appellantPhone.addValidator(new RegExValidator(RegExString, "Valid Phone Numbers only."));
		appellantPhone.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		appellantPhone.setWidth(FIELD_SIZE);
		appellantPhone.addBlurHandler(new BlurHandler() {
			
			public void onBlur(BlurEvent arg0) {
				String phoneText = appellantPhone.getText();
				if(phoneText.length() == 10){
					appellantPhone.setText("(" + phoneText.substring(0, 3) + ") " + phoneText.substring(3, 6) + "-" + phoneText.substring(6, 10));
				}
			}
		});

		appellantPhoneExt = new TextField();
		appellantPhoneExt.setName("appellantPhoneExt");
		appellantPhoneExt.setAllowBlank(true);
		appellantPhoneExt.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		appellantPhoneExt.setWidth(FIELD_SIZE);

		zip = new TextField();
		zip.setName("zip");
		zip.setAllowBlank(false);
		zip.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		zip.setWidth(FIELD_SIZE);

		appellantEmailAddress = new TextField();
		appellantEmailAddress.setName("appellantEmailAddress");
		appellantEmailAddress.setAllowBlank(false);
		//appellantEmailAddress.addValidator(new EmailValidator());
		appellantEmailAddress.addValidator(new RegExValidator(RegExStringEmail, "Valid Emails only."));
		appellantEmailAddress.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		appellantEmailAddress.setWidth(FIELD_SIZE);

		appellantEmailAddressConfirm = new TextField();
		appellantEmailAddressConfirm.setName("appellantEmailAddressConfirm");
		appellantEmailAddressConfirm.setAllowBlank(false);
		//appellantEmailAddressConfirm.addValidator(new EmailValidator());
		appellantEmailAddressConfirm.addValidator(new EmailValidator(appellantEmailAddress));
		appellantEmailAddressConfirm.setWidth(FIELD_SIZE);

		optOutCheckBox = new CheckBox();
		optOutCheckBox.setName("optOutCheckBox");
		optOutCheckBox.setBoxLabel("<i>Select this option if you wish to receive the final appeal response by U.S. Mail. <b><u>Please note: if this option is selected, the Appeals Program will not send out any electronic communications.</b></u></i>");

		appellantEmployeeNumber = new TextField();
		appellantEmployeeNumber.setName("appellantEmployeeNumber");
		appellantEmployeeNumber.setAllowBlank(true);
		appellantEmployeeNumber.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		appellantEmployeeNumber.addValidator(new EmployeeNumberValidator(accordion));
		appellantEmployeeNumber.setWidth(FIELD_SIZE);

		// employing department changed to drop down instead of the above field/label
		departmentStore = new ListStore<DropDown>(properties.key());
		departmentStore.addAll(getDepartmentList());

		departmentList = new ComboBox<DropDown>(departmentStore, properties.nameLabel());
		departmentList.setName("appellantEmployingDept");
		departmentList.setAllowBlank(true);
		departmentList.setTypeAhead(true);
		departmentList.setTriggerAction(TriggerAction.ALL);
		departmentList.setWidth(600);
		departmentList.addValidator(new EmployingDepartmentValidator(appellantEmployeeNumber));

		appellantPayrollTitle = new TextField();
		appellantPayrollTitle.setName("appellantPayrollTitle");
		appellantPayrollTitle.setAllowBlank(true);
		appellantPayrollTitle.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		appellantPayrollTitle.setWidth(FIELD_SIZE);

		repCompanyName = new TextField();
		repCompanyName.setName("repCompanyName");
		repCompanyName.setAllowBlank(true);
		repCompanyName.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		repCompanyName.setWidth(FIELD_SIZE);

		repContactNameFirst = new TextField();
		repContactNameFirst.setName("repContactNameFirst");
		repContactNameFirst.setAllowBlank(true);
		repContactNameFirst.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		repContactNameFirst.setWidth(FIELD_SIZE);
		

		repContactNameLast = new TextField();
		repContactNameLast.setName("repContactNameLast");
		repContactNameLast.setAllowBlank(true);
		repContactNameLast.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		repContactNameLast.setWidth(FIELD_SIZE);
		

		repContactAddress = new TextField();
		repContactAddress.setName("repContactAddress");
		repContactAddress.setAllowBlank(true);
		repContactAddress.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		repContactAddress.setWidth(FIELD_SIZE);

		repContactCity = new TextField();
		repContactCity.setName("repContactCity");
		repContactCity.setAllowBlank(true);
		repContactCity.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		repContactCity.setWidth(FIELD_SIZE);

		
		repStore = new ListStore<DropDown>(properties.key());
		repStore.addAll(getStates());

		
		repContactState = new ComboBox<DropDown>(repStore, properties.nameLabel());
		repContactState.setName("repContactState");
		repContactState.setAllowBlank(true);
		repContactState.setTypeAhead(true);
		repContactState.setTriggerAction(TriggerAction.ALL);
		repContactState.setWidth(FIELD_SIZE);

		repContactZip = new TextField();
		repContactZip.setName("repContactZip");
		repContactZip.setAllowBlank(true);
		repContactZip.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		repContactZip.setWidth(FIELD_SIZE);

		repContactPhone = new TextField();
		repContactPhone.setName("repContactPhone");
		repContactPhone.setAllowBlank(true);
		repContactPhone.addValidator(new RegExValidator(RegExString, "Valid Phone Numbers only."));
		repContactPhone.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		repContactPhone.setWidth(FIELD_SIZE);
		repContactPhone.addBlurHandler(new BlurHandler() {
			
			public void onBlur(BlurEvent arg0) {
				String phoneText = repContactPhone.getText();
				if(phoneText.length() == 10){
					repContactPhone.setText("(" + phoneText.substring(0, 3) + ") " + phoneText.substring(3, 6) + "-" + phoneText.substring(6, 10));
				}
			}
		});
		
		repContactPhoneExt = new TextField();
		repContactPhoneExt.setName("repContactPhoneExt");
		repContactPhoneExt.setAllowBlank(true);
		repContactPhoneExt.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		repContactPhoneExt.setWidth(FIELD_SIZE);

		repContactEmailAddress = new TextField();
		repContactEmailAddress.setName("repContactEmailAddress");
		repContactEmailAddress.setAllowBlank(true);
		repContactEmailAddress.addValidator(new RegExValidator(RegExStringEmail, "Valid Emails only."));
		repContactEmailAddress.addValidator(new MaxLengthValidator(MAXIMUM_CHAR_LIMIT));
		repContactEmailAddress.setWidth(FIELD_SIZE);

		repContactEmailAddressConfirm = new TextField();
		repContactEmailAddressConfirm.setName("repContactEmailAddressConfirm");
		repContactEmailAddressConfirm.setAllowBlank(true);
		repContactEmailAddressConfirm.addValidator(new RegExValidator(RegExStringEmail, "Valid Emails only."));
		repContactEmailAddressConfirm.setWidth(FIELD_SIZE);

		repOptOutCheckBox = new CheckBox();
		repOptOutCheckBox.setName("repOptOutCheckBox");
		repOptOutCheckBox.setBoxLabel("<i>Select this option if you wish to receive the final appeal response by US Mail. <b><u>Please note, if this option is selected, the Appeals Program will not send out any electronic communications.</b></u></i>");

		firstNameLabel = new FieldLabel(appellantFirstName, "*First Name");
		firstNameLabel.setLabelAlign(LabelAlign.TOP);

		middleNameLabel = new FieldLabel(appellantMiddleName, "Middle Name");
		middleNameLabel.setLabelAlign(LabelAlign.TOP);

		lastNameLabel = new FieldLabel(appellantLastName, "*Last Name");
		lastNameLabel.setLabelAlign(LabelAlign.TOP);

		akaFieldLabel = new FieldLabel(aka, "Other Names Used");
		akaFieldLabel.setLabelAlign(LabelAlign.TOP);

		last4SSNLabel = new FieldLabel(last4SSN, "*Last 4 digits of SSN");
		last4SSNLabel.setLabelAlign(LabelAlign.TOP);

		mailingAddressLabel = new FieldLabel(mailingAddress, "*Mailing Address");
		mailingAddressLabel.setLabelAlign(LabelAlign.TOP);

		cityLabel = new FieldLabel(city, "*City");
		cityLabel.setLabelAlign(LabelAlign.TOP);

		stateLabel = new FieldLabel(state, "*State");
		stateLabel.setLabelAlign(LabelAlign.TOP);

		appellantZipLabel = new FieldLabel(zip, "*Zip Code");	
		appellantZipLabel.setLabelAlign(LabelAlign.TOP);

		appellantPhoneLabel = new FieldLabel(appellantPhone, "*Phone Number");
		appellantPhoneLabel.setLabelAlign(LabelAlign.TOP);

		appellantPhoneExtLabel = new FieldLabel(appellantPhoneExt, "ext");
		appellantPhoneExtLabel.setLabelAlign(LabelAlign.TOP);

		appellantEmailAddressLabel = new FieldLabel(appellantEmailAddress, "*Preferred E-mail Address");
		appellantEmailAddressLabel.setLabelAlign(LabelAlign.TOP);

		appellantEmailAddressConfirmLabel = new FieldLabel(appellantEmailAddressConfirm, "*Re-enter to confirm Email Address");
		appellantEmailAddressConfirmLabel.setLabelAlign(LabelAlign.TOP);

		appellantEmployeeNumberLabel = new FieldLabel(appellantEmployeeNumber, "*Your Employee Number e-");
		appellantEmployeeNumberLabel.setLabelAlign(LabelAlign.TOP);

		appellantPayrollTitleLabel = new FieldLabel(appellantPayrollTitle, "*Your Current Payroll Title");
		appellantPayrollTitleLabel.setLabelAlign(LabelAlign.TOP);

		// employing department label changed to a drop down per POC demo feedback
		//appellantEmployingDeptLabel = new FieldLabel(appellantEmployingDept, "Your Current Employing Department");
		appellantEmployingDeptLabel = new FieldLabel(departmentList, "Your Current Employing Department");
		appellantEmployingDeptLabel.setLabelAlign(LabelAlign.TOP);
		
		ifCountyEmployeeLabel = new HTML("<br>If you are a County employee, please also provide:");

		repCompanyNameLabel = new FieldLabel(repCompanyName, "*Company Name");
		repCompanyNameLabel.setLabelAlign(LabelAlign.TOP);

		repContactNameFirstLabel = new FieldLabel(repContactNameFirst, "*Representative's First Name");
		repContactNameFirstLabel.setLabelAlign(LabelAlign.TOP);
		
		repContactNameLastLabel = new FieldLabel(repContactNameLast, "*Representative's Last Name");
		repContactNameLastLabel.setLabelAlign(LabelAlign.TOP);

		repContactAddressLabel = new FieldLabel(repContactAddress, "*Representative's Mailing Address");
		repContactAddressLabel.setLabelAlign(LabelAlign.TOP);

		repContactCityLabel = new FieldLabel(repContactCity, "*City");
		repContactCityLabel.setLabelAlign(LabelAlign.TOP);

		repContactStateLabel = new FieldLabel(repContactState, "*State");
		repContactStateLabel.setLabelAlign(LabelAlign.TOP);

		repContactZipLabel = new FieldLabel(repContactZip, "*Zip Code");
		repContactZipLabel.setLabelAlign(LabelAlign.TOP);

		repContactPhoneLabel = new FieldLabel(repContactPhone, "*Representative's Phone Number");
		repContactPhoneLabel.setLabelAlign(LabelAlign.TOP);

		repContactPhoneExtLabel = new FieldLabel(repContactPhoneExt, "ext");
		repContactPhoneExtLabel.setLabelAlign(LabelAlign.TOP);

		repContactEmailAddressLabel = new FieldLabel(repContactEmailAddress, "*Representative's Email Address");
		repContactEmailAddressLabel.setLabelAlign(LabelAlign.TOP);

		repContactEmailAddressConfirmLabel = new FieldLabel(repContactEmailAddressConfirm, "*Re-enter to confirm Email Address");
		repContactEmailAddressConfirmLabel.setLabelAlign(LabelAlign.TOP);

		appellantEmployeeNumberLabel.setLabelSeparator("-");
		repVLC = new VerticalLayoutContainer();
	}

	public FieldSet getAppellantInfoForm() {

		BoxLayoutData layoutData = new BoxLayoutData(new Margins(25, 0, 2, 15));

		HBoxLayoutContainer c = new HBoxLayoutContainer();
		c.setPadding(new Padding(5));
		c.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
		c.setPack(BoxLayoutPack.CENTER);
		c.add(firstNameLabel, layoutData);
		c.add(middleNameLabel, layoutData);
		c.add(lastNameLabel, layoutData);

		HBoxLayoutContainer c2 = new HBoxLayoutContainer();
		c2.setPadding(new Padding(5));
		c2.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		c2.setPack(BoxLayoutPack.CENTER);
		c2.add(akaFieldLabel, layoutData);
		c2.add(last4SSNLabel, layoutData);
		c2.add(appellantPhoneLabel, layoutData);
		c2.add(appellantPhoneExtLabel, layoutData);

		HBoxLayoutContainer c3 = new HBoxLayoutContainer();
		c3.setPadding(new Padding(5));
		c3.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		c3.setPack(BoxLayoutPack.CENTER);
		c3.add(mailingAddressLabel, layoutData);
		c3.add(cityLabel, layoutData);
		c3.add(stateLabel, layoutData);
		c3.add(appellantZipLabel, layoutData);


		HBoxLayoutContainer c4 = new HBoxLayoutContainer();
		c4.setPadding(new Padding(5));
		c4.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		c4.setPack(BoxLayoutPack.CENTER);
		c4.add(appellantEmailAddressLabel, layoutData);
		c4.add(appellantEmailAddressConfirmLabel, layoutData);

		HBoxLayoutContainer c5 = new HBoxLayoutContainer();
		c5.setPadding(new Padding(5));
		c5.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		c5.setPack(BoxLayoutPack.CENTER);
		c5.add(appellantEmployeeNumberLabel, layoutData);
		c5.add(appellantPayrollTitleLabel, layoutData);
		c5.add(appellantEmployingDeptLabel, layoutData);

		fs = new FieldSet();
		fs.setHeadingHtml("<p class=\"userInfoHeaderText\" style=\"border: 1px solid black;\"><b>Representative's Information<b></p>");
		
		HBoxLayoutContainer c6 = new HBoxLayoutContainer();
		c6.setPadding(new Padding(5));
		c6.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		c6.setPack(BoxLayoutPack.CENTER);
		c6.add(repCompanyNameLabel, layoutData);
		c6.add(repContactNameFirstLabel, layoutData);
		c6.add(repContactNameLastLabel, layoutData);

		HBoxLayoutContainer c7 = new HBoxLayoutContainer();
		c7.setPadding(new Padding(5));
		c7.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		c7.setPack(BoxLayoutPack.CENTER);
		c7.add(repContactAddressLabel, layoutData);
		c7.add(repContactCityLabel, layoutData);
		c7.add(repContactStateLabel, layoutData);
		c7.add(repContactZipLabel, layoutData);

		HBoxLayoutContainer c8 = new HBoxLayoutContainer();
		c8.setPadding(new Padding(5));
		c8.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		c8.setPack(BoxLayoutPack.CENTER);
		c8.add(repContactPhoneLabel, layoutData);
		c8.add(repContactPhoneExtLabel, layoutData);
		c8.add(repContactEmailAddressLabel, layoutData);
		c8.add(repContactEmailAddressConfirmLabel, layoutData);

		repVLC.add(c6);
		repVLC.add(c7);
		repVLC.add(c8);

		fs.add(repVLC);

		HBoxLayoutContainer c9 = new HBoxLayoutContainer();
		c9.setPadding(new Padding(5));
		c9.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		c9.setPack(BoxLayoutPack.CENTER);
		c9.add(optOutCheckBox, layoutData);

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();

		
		vlc.add(new HTML("<br />Please provide the following information. Inaccurate or incomplete submission of information may lead to the delay or non-acceptance of this appeal"));
		vlc.add(new HTML("<br /><br />All fields marked with an asterisk (*) are required."));
		//vlc.setWidth(1000);
		vlc.add(c);

		//		vlc.add(akaFieldLabel);
		//		vlc.add(last4SSNLabel);
		//		vlc.add(mailingAddressLabel);
		vlc.add(c2);

		//		vlc.add(cityLabel);
		//		vlc.add(stateLabel);
		//		vlc.add(appellantPhoneLabel);
		vlc.add(c3);

		//		vlc.add(appellantEmailAddressLabel);
		//		vlc.add(appellantEmailAddressConfirmLabel);
		vlc.add(c4);

		vlc.add(ifCountyEmployeeLabel);

		//		vlc.add(appellantEmployeeNumberLabel);
		//		vlc.add(appellantEmployingDeptLabel);
		//		vlc.add(appellantPayrollTitleLabel);
		vlc.add(c5);

		//		vlc.add(repCompanyNameLabel);
		//		vlc.add(repContactNameLabel);
		//		vlc.add(repContactAddressLabel);
		//vlc.add(c6);

		//		vlc.add(repContactCityLabel);
		//		vlc.add(repContactStateLabel);
		//		vlc.add(repContactZipLabel);
		//vlc.add(c7);

		//		vlc.add(repContactPhoneLabel);
		//		vlc.add(repContactEmailAddressLabel);
		//		vlc.add(repContactEmailAddressConfirmLabel, new VerticalLayoutData(1, -1));
		//vlc.add(c8);

		vlc.add(fs);

		vlc.add(c9);

		FieldSet userInfo = new FieldSet();
		userInfo.add(vlc);
		userInfo.setCollapsible(true);
		userInfo.hide();
		return userInfo;
	}

	public void addAppellantInfoForm(VerticalLayoutContainer vlc) {


		BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 5, 0, 0));
		BoxLayoutData layoutData2 = new BoxLayoutData(new Margins(0));

		HBoxLayoutContainer c = new HBoxLayoutContainer();
		c.setPadding(new Padding(5));
		c.setHBoxLayoutAlign(HBoxLayoutAlign.MIDDLE);
		c.setPack(BoxLayoutPack.CENTER);
		c.add(firstNameLabel, layoutData);
		c.add(middleNameLabel, layoutData);
		c.add(lastNameLabel, layoutData2);

		vlc.setWidth(1000);
		vlc.add(c);
		vlc.add(akaFieldLabel);
		vlc.add(last4SSNLabel);
		vlc.add(mailingAddressLabel);
		vlc.add(cityLabel);
		vlc.add(stateLabel);

		vlc.add(appellantPhoneLabel);
		vlc.add(appellantEmailAddressLabel);
		vlc.add(appellantEmailAddressConfirmLabel);
		vlc.add(ifCountyEmployeeLabel);
		vlc.add(appellantEmployeeNumberLabel);
		vlc.add(appellantEmployingDeptLabel);
		vlc.add(appellantPayrollTitleLabel);
		vlc.add(repCompanyNameLabel);
		vlc.add(repContactNameFirstLabel);
		vlc.add(repContactNameLastLabel);
		vlc.add(repContactAddressLabel);
		vlc.add(repContactCityLabel);
		vlc.add(repContactStateLabel);
		vlc.add(repContactZipLabel);
		vlc.add(repContactPhoneLabel);
		vlc.add(repContactEmailAddressLabel);
		vlc.add(repContactEmailAddressConfirmLabel);
	}

	public void setAppellantInfoForm() {
		firstNameLabel.setText("*First Name");
		middleNameLabel.setText("Middle Name");
		lastNameLabel.setText("*Last Name");
		last4SSNLabel.setText("*Last 4 Digits of SSN");
		mailingAddressLabel.setText("*Mailing Address");
		appellantPhoneLabel.setText("*Phone Number");
		appellantEmailAddressLabel.setText("*Preferred Email Address");
		appellantEmployeeNumberLabel.setText("*Your Employee Number e-");
		appellantEmployeeNumberLabel.setLabelSeparator("");
		appellantEmployingDeptLabel.setText("*Your Current Employing Department");
		appellantPayrollTitleLabel.setText("*Your Current Payroll Title");
		ifCountyEmployeeLabel.setHTML("<br>If you are a County employee, please also provide:");
		ifCountyEmployeeLabel.setVisible(true);

		repCompanyNameLabel.setVisible(false);
		repContactNameFirstLabel.setVisible(false);
		repContactNameLastLabel.setVisible(false);
		repContactAddressLabel.setVisible(false);
		repContactCityLabel.setVisible(false);
		repContactStateLabel.setVisible(false);
		repContactZipLabel.setVisible(false);
		repContactPhoneLabel.setVisible(false);
		repContactPhoneExtLabel.setVisible(false);
		repContactEmailAddressLabel.setVisible(false);
		repContactEmailAddressConfirmLabel.setVisible(false);

		repCompanyName.setAllowBlank(true);
		repContactNameFirst.setAllowBlank(true);
		repContactNameLast.setAllowBlank(true);
		repContactAddress.setAllowBlank(true);
		repContactCity.setAllowBlank(true);
		repContactState.setAllowBlank(true);
		repContactZip.setAllowBlank(true);
		repContactPhone.setAllowBlank(true);

		fs.setVisible(false);
	}

	public void setRepresentativeInfoForm() {
		firstNameLabel.setText("*Appellant's First Name");
		middleNameLabel.setText("Appellant's Middle Name");
		lastNameLabel.setText("*Appellant's Last Name");
		last4SSNLabel.setText("*Last 4 Digits of SSN");
		mailingAddressLabel.setText("*Appellant's Mailing address");
		appellantPhoneLabel.setText("*Appellant's Phone Number");
		appellantEmailAddressLabel.setText("*Appellant's Email Address");
		appellantEmployeeNumberLabel.setText("Appellant's Employee Number (if known)");
		appellantEmployeeNumberLabel.setLabelSeparator(":");
		appellantEmployingDeptLabel.setText("Appellant's Current Employing Department (if known)");
		appellantPayrollTitleLabel.setText("Appellant's Current Payroll Title (if known)");
		ifCountyEmployeeLabel.setVisible(false);

		repCompanyNameLabel.setVisible(true);
		repContactNameFirstLabel.setVisible(true);
		repContactNameLastLabel.setVisible(true);
		repContactAddressLabel.setVisible(true);
		repContactCityLabel.setVisible(true);
		repContactStateLabel.setVisible(true);
		repContactZipLabel.setVisible(true);
		repContactPhoneLabel.setVisible(true);
		repContactPhoneExtLabel.setVisible(true);
		repContactEmailAddressLabel.setVisible(true);
		repContactEmailAddressConfirmLabel.setVisible(true);

		repCompanyName.setAllowBlank(false);
		repContactNameFirst.setAllowBlank(false);
		repContactNameLast.setAllowBlank(false);
		repContactAddress.setAllowBlank(false);
		repContactCity.setAllowBlank(false);
		repContactState.setAllowBlank(false);
		repContactZip.setAllowBlank(false);
		repContactPhone.setAllowBlank(false);

		fs.setVisible(true);

	}


	public List<DropDown> getStates() {
		List<DropDown> stateList = new ArrayList<DropDown>();
		stateList.add(new DropDown("AL"));
		stateList.add(new DropDown("AK"));
		stateList.add(new DropDown("AZ"));
		stateList.add(new DropDown("AR"));
		stateList.add(new DropDown("CA"));
		stateList.add(new DropDown("CO"));
		stateList.add(new DropDown("CT"));
		stateList.add(new DropDown("CT"));
		stateList.add(new DropDown("DC"));
		stateList.add(new DropDown("DE"));
		stateList.add(new DropDown("FL"));
		stateList.add(new DropDown("GA"));
		stateList.add(new DropDown("HI"));
		stateList.add(new DropDown("ID"));
		stateList.add(new DropDown("IL"));
		stateList.add(new DropDown("IN"));
		stateList.add(new DropDown("IA"));
		stateList.add(new DropDown("KS"));
		stateList.add(new DropDown("KY"));
		stateList.add(new DropDown("LA"));
		stateList.add(new DropDown("ME"));
		stateList.add(new DropDown("MD"));
		stateList.add(new DropDown("MA"));
		stateList.add(new DropDown("MI"));
		stateList.add(new DropDown("MN"));
		stateList.add(new DropDown("MS"));
		stateList.add(new DropDown("MO"));
		stateList.add(new DropDown("MT"));
		stateList.add(new DropDown("NE"));
		stateList.add(new DropDown("NV"));
		stateList.add(new DropDown("NH"));
		stateList.add(new DropDown("NJ"));
		stateList.add(new DropDown("NM"));
		stateList.add(new DropDown("NY"));
		stateList.add(new DropDown("NC"));
		stateList.add(new DropDown("ND"));
		stateList.add(new DropDown("OH"));
		stateList.add(new DropDown("OK"));
		stateList.add(new DropDown("OR"));
		stateList.add(new DropDown("PA"));
		stateList.add(new DropDown("RI"));
		stateList.add(new DropDown("SC"));
		stateList.add(new DropDown("SD"));
		stateList.add(new DropDown("TN"));
		stateList.add(new DropDown("TX"));
		stateList.add(new DropDown("UT"));
		stateList.add(new DropDown("VT"));
		stateList.add(new DropDown("VA"));
		stateList.add(new DropDown("WA"));
		stateList.add(new DropDown("WV"));
		stateList.add(new DropDown("WI"));
		stateList.add(new DropDown("WY"));

		return stateList;
	}


	public List<DropDown> getDepartmentList() {
		List<DropDown> attachmentDescList = new ArrayList<DropDown>();
		attachmentDescList.add(new DropDown("AGRIC COMM/WTS & MEASURES"));
		attachmentDescList.add(new DropDown("ALTERNATE PUBLIC DEFENDER"));
		attachmentDescList.add(new DropDown("ANIMAL CARE AND CONTROL"));
		attachmentDescList.add(new DropDown("ANTELOPE VALLEY REHABILITATION CENTER"));
		attachmentDescList.add(new DropDown("ARTS AND CULTURE"));
		attachmentDescList.add(new DropDown("ASSESSOR"));
		attachmentDescList.add(new DropDown("AUDITOR-CONTROLLER"));
		attachmentDescList.add(new DropDown("BEACHES & HARBORS"));
		attachmentDescList.add(new DropDown("BOARD OF SUPERVISORS"));
		attachmentDescList.add(new DropDown("CHIEF EXECUTIVE OFFICE"));
		attachmentDescList.add(new DropDown("CHILD SUPPORT SERVICES"));
		attachmentDescList.add(new DropDown("CHILDREN & FAMILY SERVICES"));
		attachmentDescList.add(new DropDown("CHILDREN'S MEDICAL SERVICES"));
		attachmentDescList.add(new DropDown("CONSUMER AND BUSINESS AFFAIRS"));
		attachmentDescList.add(new DropDown("COUNTY COUNSEL"));
		attachmentDescList.add(new DropDown("DEPARTMENT OF MEDICAL EXAMINER - CORONER"));
		attachmentDescList.add(new DropDown("DISTRICT ATTORNEY"));
		attachmentDescList.add(new DropDown("FIRE DEPARTMENT"));
		attachmentDescList.add(new DropDown("GRAND JURY"));
		attachmentDescList.add(new DropDown("HEALTH SERVICES"));
		attachmentDescList.add(new DropDown("HEALTH SERVICES INTEGRATED CORRECTIONAL"));
		attachmentDescList.add(new DropDown("HUMAN RESOURCES DEPT"));
		attachmentDescList.add(new DropDown("INTERNAL SERVICES DEPT"));
		attachmentDescList.add(new DropDown("JUVENILE COURT HEALTH SERVICES"));
		attachmentDescList.add(new DropDown("LAC+USC HEALTHCARE NETWORK"));
		attachmentDescList.add(new DropDown("LACERA"));
		attachmentDescList.add(new DropDown("MENTAL HEALTH"));
		attachmentDescList.add(new DropDown("METROCARE NETWORK"));
		attachmentDescList.add(new DropDown("MILITARY & VETS AFFAIRS"));
		attachmentDescList.add(new DropDown("MUSEUM OF ART"));
		attachmentDescList.add(new DropDown("MUSEUM OF NATURAL HISTORY"));
		attachmentDescList.add(new DropDown("OFFICE OF AIDS PROGRAMS & POLICY"));
		attachmentDescList.add(new DropDown("OFFICE OF MANAGED CARE"));
		attachmentDescList.add(new DropDown("PARKS & RECREATION DEPARTMENT"));
		attachmentDescList.add(new DropDown("PROBATION DEPARTMENT"));
		attachmentDescList.add(new DropDown("PUBLIC DEFENDER"));
		attachmentDescList.add(new DropDown("PUBLIC HEALTH PROGRAMS"));
		attachmentDescList.add(new DropDown("PUBLIC LIBRARY"));
		attachmentDescList.add(new DropDown("PUBLIC SOCIAL SERVICES DEPT"));
		attachmentDescList.add(new DropDown("PUBLIC WORKS DEPARTMENT"));
		attachmentDescList.add(new DropDown("RANCHO LOS AMIGOS NATIONAL REHABILITATION CENTER"));
		attachmentDescList.add(new DropDown("REGIONAL PLANNING DEPARTMENT"));
		attachmentDescList.add(new DropDown("REGISTRAR RECORDER"));
		attachmentDescList.add(new DropDown("SHERIFF"));
		attachmentDescList.add(new DropDown("SHERIFF'S LAW ENFORCEMENT"));
		attachmentDescList.add(new DropDown("SOUTHWEST CLUSTER (MLK JR MC)"));
		attachmentDescList.add(new DropDown("SUBSTANCE ABUSE PREVENTION AND  CONTROL"));
		attachmentDescList.add(new DropDown("SUPERIOR COURT & COUNTY CLERK"));
		attachmentDescList.add(new DropDown("TREASURER & TAX COLLECTOR"));
		attachmentDescList.add(new DropDown("VALLEYCARE NETWORK"));
		attachmentDescList.add(new DropDown("WORKFORCE DEV, AGING & COMM SERVICES"));


		// sort by name
		Collections.sort(attachmentDescList, new Comparator<DropDown>(){
			@Override
			public int compare(DropDown o1, DropDown o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		return attachmentDescList;
	}


}
