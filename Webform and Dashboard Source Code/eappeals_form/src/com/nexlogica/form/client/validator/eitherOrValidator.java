package com.nexlogica.form.client.validator;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;
public class eitherOrValidator extends AbstractValidator<String>{

	private AccordionLayoutContainer form;
	private boolean examTypeSelected;
	private TextField otherField;

	public eitherOrValidator(AccordionLayoutContainer form, TextField examNumber ) {
		super(); 
		this.form = form;
		this.examTypeSelected = false;
		this.otherField = examNumber;
	}

	@Override
	public List<EditorError> validate(Editor<String> field, String value) {

		List<EditorError> errors = null;

		// get selected radio button from form at validation time
		this.examTypeSelected = getSelectedRadioButton(this.form);

		String otherFieldText = this.otherField.getValue();


		// if selectedRadioButton is something, there must be an exam number
		if ((value != null && examTypeSelected == true && value == "") && (otherFieldText == null ||otherFieldText == "")) {
			String message = "Please enter either an Exam Number or a Job Title / Examining Department";
			errors = createError(field, message, value);
		} else if (value == null && examTypeSelected == true && (otherFieldText == null ||otherFieldText == "")) {
			String message = "Please enter an Exam Number or a Job Title / Examining Department";
			errors = createError(field, message, value);
		}

		return errors;
	}

	// at validation, will scan for selected radio button
	public boolean getSelectedRadioButton(AccordionLayoutContainer form) {

		String selectedRadio="";

		// get accordion with radio buttons to compare against attachments
		AccordionLayoutContainer accordion = form;

		// find out which radio button is selected
		for(int i = 0; i < form.getWidgetCount(); i++){

			if(accordion.getWidget(i).getClass() == ContentPanel.class){
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
		}


		// return true if exam selected
		if (selectedRadio == "appRejection" || selectedRadio == "appraisalPromotibility" ||
				selectedRadio == "evalTrainExp" || selectedRadio == "interview"||
				selectedRadio == "perfTest" || selectedRadio == "vetCredit"||
				selectedRadio == "writterTest" || selectedRadio == "classificationStudy" || 
				selectedRadio == "background" || selectedRadio == "inaccurateDiscOHP"){
			examTypeSelected = true;
		} else {
			examTypeSelected = false;
		}

		return examTypeSelected;
	}
}