package com.nexlogica.form.client.validator;

import org.apache.commons.io.FilenameUtils;



public class FileExtensionValidator {

	

	public Boolean validate(String filename){
		
		String ext = FilenameUtils.getExtension(filename).toLowerCase();
		
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