package com.nexlogica.dashboard.validators;

import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class Last4SSNValidator extends AbstractValidator<String> {

	public  Last4SSNValidator() {
		super(); 
	}

	@Override
	public List<EditorError> validate(Editor<String> editor, String value) {

		if(value == null)
			return null;
		
		List<EditorError> errors = null;

		// check that it's only 4 numbers
		if(value.length() != 4){
			String message = "Please enter 4 numbers only";
			errors = createError(editor, message, value);
		}

		// check if it has any letters
		if(isAlpha(value)){
			String message = "Please enter 4 numbers only";
			errors = createError(editor, message, value);
		}

		// check if empty
		if(value.isEmpty()){
			String message = "This is a required field.";
			errors = createError(editor, message, value);
		}

		
		return errors;
	}

	private boolean isAlpha(String value){
		char[] chars = value.toCharArray();
		boolean a = false;
		for (char c : chars) {
			if(Character.isLetter(c)) {
				a = true;
			}
		}
		return a;
	}
}
