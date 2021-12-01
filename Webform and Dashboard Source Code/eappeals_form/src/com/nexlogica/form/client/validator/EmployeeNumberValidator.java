package com.nexlogica.form.client.validator;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;


public class EmployeeNumberValidator extends AbstractValidator<String>{

	private AccordionLayoutContainer form;
	private Boolean disciplineOrPersonnelActionSelected;

	public EmployeeNumberValidator(AccordionLayoutContainer form) {
		super(); 
		this.form = form;
		this.disciplineOrPersonnelActionSelected = false;

	}

	@Override
	public List<EditorError> validate(Editor<String> field, String value) {
		List<EditorError> errors = null;

		this.disciplineOrPersonnelActionSelected = getSelectedRadioButton(this.form);

		// employee number must be exactly 6 digits
		if (value != null && value != "" && (value.length() < 6 || value.length() > 6)) {
			String message = "An employee number may only have 6 digits, please enter in your employee number without the leading letter.";
			errors = createError(field, message, value);
		}
		// employee number must exist if discipline or personnel action is selected as the test
		if (disciplineOrPersonnelActionSelected && (value == null || value == "")) {
			String message = "An employee number must be entered.";
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
		for(int i = 0; i < 4; i++){

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
		// return true if discipline or personnal action exam selected
		if (selectedRadio == "oneFiveDatSuspension" || selectedRadio == "layoff" ||
				selectedRadio == "probDischarge" || selectedRadio == "probReduction"||
				selectedRadio == "reductionLayOff" || selectedRadio == "relTempEmployment"||
				selectedRadio == "transfer" || selectedRadio == "classificationStudy" || 
				selectedRadio == "nonAppointment" || selectedRadio == "resignation"){
			disciplineOrPersonnelActionSelected = true;
		} else {
			disciplineOrPersonnelActionSelected = false;
		}

		return disciplineOrPersonnelActionSelected;
	}
}
