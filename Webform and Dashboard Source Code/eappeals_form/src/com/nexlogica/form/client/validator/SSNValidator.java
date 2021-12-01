package com.nexlogica.form.client.validator;
import java.util.List;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;


public class SSNValidator extends AbstractValidator<String>{
	 @Override
	    public List<EditorError> validate(Editor<String> field, String value) {
	      List<EditorError> errors = null;
	      if (value != null && (value.length() != 4 || isAlpha(value))) {
	        String message = "Invalid SSN Value. Please enter exactly 4 digits.";
	        errors = createError(field, message, value);
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