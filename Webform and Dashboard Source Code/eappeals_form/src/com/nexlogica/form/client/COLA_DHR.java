/**
 * Sencha GXT 3.1.1 - Sencha for GWT
 * Copyright(c) 2007-2014, Sencha, Inc.
 * licensing@sencha.com
 *
 * http://www.sencha.com/products/gxt/license/
 */
package com.nexlogica.form.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.form.FormPanel;

//@Detail(name = "Forms Example", icon = "forms", category = "Forms")
public class COLA_DHR implements IsWidget, EntryPoint {

	private FormPanel form ;
	//private FramedPanel panel;
	private ContentPanel panel;
	final FormBuilder formBuilder = new FormBuilder();
	
	final ButtonWidgetBuilder buttonBuilder = new ButtonWidgetBuilder();
	
	// widget that contains the appeal form
	public Widget asWidget() {
		if (panel == null) {
			form = formBuilder.getForm();
			//panel = new FramedPanel();
			panel = new ContentPanel();
			panel.setButtonAlign(BoxLayoutPack.CENTER);
			panel.add(form);
			panel.setHeadingHtml("<p style=\"color:black;text-align:center;\" class=\"headertext\">eAppeal Online Form</p>");
		}

		return panel;

	}

	// widget that contains DHR logo at the top left of the page
	public Widget imgWidget() {
		VerticalPanel vp = new VerticalPanel();

		Image img = new Image("HR.LOGOred3.jpg");

		vp.add(img);
		
		return vp;
	}
	
	// entry point into the application
	//REMEMBER YOU HAVE TO REPLACE THE CSS FILES EVERY TIME
	//DASHBOARD HAS MORE UPDATED LEVERAGE OF GXT/GWT FORMATTING - REFERENCE IT
	public void onModuleLoad() {

		RootPanel.get().add(imgWidget());
		
		RootPanel.get().add(asWidget());
		
		// request on 4/25/19 to have a different version of the form generated 
		// without a captcha so that automated scripts can go through the form
		// SET THIS TO FALSE WHEN THE APPLICATION IS NOT IN TEST
//		Boolean weAreInTestAndDontNeedACaptcha = ;
//		
//		if(weAreInTestAndDontNeedACaptcha){
//			
//		} else {
//			RootPanel.get().add(buttonBuilder.buttonsWidget3(form));
//			//RootPanel.get().add(CaptchaBuilder.getCaptchaWidget());
//		}
		RootPanel.get().add(buttonBuilder.buttonsWidget4(form));
		
		
		formBuilder.getInitialAckHtml().show();        
	}
	
}
