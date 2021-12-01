package com.nexlogica.dashboard.client.handlers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HTML;
import com.nexlogica.dashboard.html.HTMLBuilder;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.AccordionLayoutAppearance;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer.ExpandMode;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class FAQButtonHandler implements SelectHandler {
	
	public FAQButtonHandler () {
	}

	public void onSelect(SelectEvent event) {
		getFAQ().show();
	}
	
	
	private Window getFAQ() {
		AccordionLayoutAppearance appearance = GWT.<AccordionLayoutAppearance> create(AccordionLayoutAppearance.class);
		
		ContentPanel cp2 = new ContentPanel(appearance);
		cp2.setHeadingText(HTMLBuilder.APPEALS_QUESTION);
		cp2.add(new HTML(HTMLBuilder.APPEALS_ANSWER), new BoxLayoutData(new Margins(0, 5, 0, 15)));


		AccordionLayoutContainer accordion = new AccordionLayoutContainer();
		accordion.setExpandMode(ExpandMode.SINGLE_FILL);
		accordion.add(cp2); //1

		final Window complex = new Window();
		complex.setResizable(true);
		complex.setModal(true);
		complex.setHeadingText("<b>Frequently Asked Questions</b>");
		complex.setWidth(1000);
//		complex.setHeight(900);
		complex.add(accordion);

		return complex;

	}
}


