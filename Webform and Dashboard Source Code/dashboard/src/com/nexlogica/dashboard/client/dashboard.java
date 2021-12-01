package com.nexlogica.dashboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.nexlogica.dashboard.html.HTMLBuilder;
import com.sencha.gxt.widget.core.client.form.FormPanel;

public class dashboard implements IsWidget, EntryPoint{

	private FormPanel form;
	
	public void onModuleLoad() {
		if(form == null)
			form = FormBuilder.getInputForm();
		
		HTML intro = new HTML(HTMLBuilder.INTRO2);
		
		RootPanel.get("Header").add(intro);
		
		RootPanel.get("Main").add(form);
		
		//RootPanel.get("Main").add(new ButtonWidgetBuilder().buttonsWidget3(form));
		
		//RootPanel.get("Footer").add(new HTML(HTMLBuilder.FOOTER_HTML));
		
	}

	public Widget asWidget() {
		return null;
	}

}
