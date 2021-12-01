package com.nexlogica.dashboard.client.model;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface AppealProperties extends PropertyAccess<Appeal>{

	@Path("id")
	ModelKeyProvider<Appeal> key();
	
	ValueProvider<Appeal, Integer> caseNumber(); 
	
	ValueProvider<Appeal, String> appealType(); 
	
	ValueProvider<Appeal, String> examNumber(); 
	
	ValueProvider<Appeal, String> dateSubmitted(); 
	
	ValueProvider<Appeal, String> caseStatus();
}
