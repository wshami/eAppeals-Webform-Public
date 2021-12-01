package com.nexlogica.dashboard.validators;
import java.util.List;

import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.EditorError;
import com.sencha.gxt.widget.core.client.form.validator.AbstractValidator;

public class FileExtensionFrontEndValidator extends AbstractValidator<String>{

	@Override
	public List<EditorError> validate(Editor<String> field, String value) {
		List<EditorError> errors = null;
		if (value != null && (isValidExtension(value))) {
			String message = "The file extension you are uploading is not acceptable. Please refer to the list of acceptable formats.";
			errors = createError(field, message, value);
		}
		return errors;
	}

	public boolean isValidExtension(String value){

		// split string into character array
		char[] valueSplit = value.toCharArray();

		// find the period
		for(int i = valueSplit.length; i > 0; i--){

			// get the rest of the extension
			if(valueSplit[i] == '.'){

				// var to hold extension
				String extension = "";

				// loop through the remaining word
				for(int j=i+1; j < valueSplit.length; j++){

					extension += valueSplit[j];
				}

				// return whether or not extension is valid
				return checkAgainstValidExtensionTypes(extension);
			}
		}
		return false;
	}

	private boolean checkAgainstValidExtensionTypes(String extension){

		String ext = extension.toLowerCase();

		switch(ext){
		case   "jpg":
		case  "jpeg":
		case   "png":
		case   "tif":
		case  "tiff":
		case   "gif":
		case   "pdf":
		case   "doc":
		case  "docx":
		case   "msg":
		case   "wps":
		case   "m4a":
		case   "wav":
		case   "mp3":
		case   "mp4":
		case   "mpeg4":
			return true;
		default:
			return false;

		}
	}
}