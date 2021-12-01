package com.nexlogica.dashboard.validators;

import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class LastNameValidator extends AbstractValidator<String> {

	public LastNameValidator() {
		super(); 
	}

	@Override
	public List<EditorError> validate(Editor<String> editor, String value) {

		if(value == null)
			return null;
		
		List<EditorError> errors = null;

		// check if empty
		if(value.isEmpty() || value == null){
			String message = "This is a required field.";
			errors = createError(editor, message, value);
		}
		
		// check if illegal characters
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
				if(!(c == '-' || c == ',' || c == ' '|| c == '\'' || c == '.' )){
					if(!Character.isDigit(c)){
						if(!(c == ' ')){
						return false;}
				}
			}
		}
		}
		return true;
	}


}