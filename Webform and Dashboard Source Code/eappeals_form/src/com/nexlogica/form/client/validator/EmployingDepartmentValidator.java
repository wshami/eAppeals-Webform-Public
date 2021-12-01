package com.nexlogica.form.client.validator;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.nexlogica.form.client.combobox.DropDown;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;


public class EmployingDepartmentValidator extends AbstractValidator<DropDown>{
	
	private TextField reference;
	
	public EmployingDepartmentValidator(TextField appellantEmployeeNumber) {
		super(); 
		this.reference = appellantEmployeeNumber; 
	}
	@Override
	public List<EditorError> validate(Editor<DropDown> editor, DropDown value) {
		List<EditorError> errors = null;
	      
	      // if there exists an employee number, there should exist an employing department
	      if ((reference.getText() != "") && (value == null)) {
	    	 String message = "Please select an employing department";
	    	 errors = createError(editor, message, value);
	      }
	      return errors;
	    }
	}