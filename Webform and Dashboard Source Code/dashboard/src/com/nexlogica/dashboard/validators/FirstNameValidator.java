package com.nexlogica.dashboard.validators;

import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class FirstNameValidator extends AbstractValidator<String> {

	public FirstNameValidator() {
		super(); 
	}

	@Override
	public List<EditorError> validate(Editor<String> editor, String value) {
		if(value == "")
			value = null;

		if(value == null)
			return null;

		List<EditorError> errors = null;


		// check if empty
		if(value.isEmpty()){
			String message = "This is a required field.";
			errors = createError(editor, message, value);
		}

		// check for illegal characters
		if(!isAlphaOrHyphen(value)){
			String message = "Please enter only upper and lower case letters or numbers.";
			errors = createError(editor, message, value);
		}

		// check for illegal characters
		if(value != null && value.length() > 25){
			String message = "25 character limit.";
			errors = createError(editor, message, value);
		}

		return errors;
	}

	private boolean isAlphaOrHyphen(String v){
		char[] chars = v.toCharArray();

		for(char c : chars) {
			if(!Character.isLetter(c)){
				if(!(c == '-'|| c == ','|| c == ' ' || c == '\'' || c == '.')){
					if(!Character.isDigit(c)){
						return false;}
				}
			}
		}

		return true;
	}


}
