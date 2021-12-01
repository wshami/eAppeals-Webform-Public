package com.nexlogica.dashboard.validators;

import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class CaseNumberValidator extends AbstractValidator<String> {

	public CaseNumberValidator() {
		super(); 
	}

	@Override
	public List<EditorError> validate(Editor<String> editor, String value) {

		List<EditorError> errors = null;

		// check for illegal characters
		if(value != null && !isAlphaOrDigit(value)){
			String message = "Please enter only numbers or letters separated by commas";
			errors = createError(editor, message, value);
		}
		
		// check for illegal characters
		if(value != null && value.length() > 500){
			String message = "100 character limit.";
			errors = createError(editor, message, value);
		}

		if(("Type one or multiple Appeal Case numbers, separated by commas".equals(value))){
			value = null;
		}
		
		return errors;
	}

	private boolean isAlphaOrDigit(String v){

		if(v.isEmpty())
			return true;

		if(v.endsWith(","))
			return false;

		String[] v2 = v.split(",");

		for(String v3 : v2){
			char[] chars = v3.trim().toCharArray();

			for(char c : chars) {
					if(!(Character.isDigit(c))){
						return false;
					}
			}

			if(chars.length == 0)
				return false;
		}
		return true;
	}

}