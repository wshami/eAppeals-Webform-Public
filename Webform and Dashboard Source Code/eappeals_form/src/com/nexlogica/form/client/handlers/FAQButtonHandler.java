package com.nexlogica.form.client.handlers;

import com.google.gwt.user.client.Window;
import com.nexlogica.form.client.FormBuilder;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

// import will be used if reqs change to have FAQ button route to external link instead of popup form
public class FAQButtonHandler implements SelectHandler{
	
	final FormBuilder formBuilder = new FormBuilder();
	
	public FAQButtonHandler () {
	}

	@Override
	public void onSelect(SelectEvent event) {
		//formBuilder.getFAQ().show();
		Window.open("FAQ.html", "_blank", "");
	}
}
