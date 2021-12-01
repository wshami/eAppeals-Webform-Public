package com.nexlogica.form.client.validator;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;


public class EmailValidator extends AbstractValidator<String>{
	
	private TextField reference;
	private RegExp regexEmailPattern; 

	public EmailValidator(TextField appellantEmailAddress) {
		super(); 
		this.reference = appellantEmailAddress; 
		regexEmailPattern = RegExp.compile("(?:[a-z0-9!#$%&\'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&\'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
		
	}

	@Override
	    public List<EditorError> validate(Editor<String> field, String value) {
	      List<EditorError> errors = null;
	      
	      if (value != null && ((!value.contains("@") || (value.contains(" "))))) {
	        String message = "Please enter a valid email address";
	        errors = createError(field, message, value);
	      }
	      
//	      MatchResult mr = regexEmailPattern.exec(value);
//	      
//	      if(mr.equals(null)){
//	    	  String message = "Please enter a valid email address";
//	    	  errors = createError(field, message, value);
//	      }
	      
	      if (value != reference.getText()) {
	    	 String message = "This email does not match the previous one entered.";
	    	 errors = createError(field, message, value);
	      }
	      
	      return errors;
	    }
}
