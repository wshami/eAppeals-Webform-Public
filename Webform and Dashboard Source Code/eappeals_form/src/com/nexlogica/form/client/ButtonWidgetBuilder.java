package com.nexlogica.form.client;

import gwtquery.plugins.gwtcaptcha.client.Captcha;

import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.nexlogica.form.client.handlers.FAQButtonHandler;
import com.nexlogica.form.client.handlers.FormSubmitHandler;
import com.nexlogica.form.client.handlers.ResetButtonHandler;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.form.FormPanel;

public class ButtonWidgetBuilder {
	// widget that contains FAQ and RESET buttons at the bottom right of the client window
	public Widget buttonsWidget3(FormPanel form) {
		VerticalPanel vp1 = new VerticalPanel();
		HorizontalPanel vp2 = new HorizontalPanel();

		Captcha captcha = new Captcha();
		
		int width = Window.getClientWidth();
		int height = Window.getClientHeight();
		double rightOffset = .09375; // fractional buffer from the right side of the client window for the buttons 
//
//		TextButton faqButton = new TextButton("FAQ");
//		faqButton.addSelectHandler(new FAQButtonHandler());
//		faqButton.setWidth(width / 12);
//		faqButton.setHeight(height/16);


		// reset button
		TextButton resetButton = new TextButton("Reset");
		resetButton.addSelectHandler(new ResetButtonHandler(form, captcha));
		resetButton.setWidth(width / 12);
		resetButton.setHeight(height/16);

		// submit button
		TextButton submitButton = new TextButton("Submit");
		submitButton.addSelectHandler(new FormSubmitHandler(form, captcha));
		submitButton.setWidth(width / 12);
		submitButton.setHeight(height/16);

//		vp2.add(faqButton);
		vp2.add(resetButton);
		vp2.add(submitButton);

		vp2.getElement().getStyle().setBottom(15, com.google.gwt.dom.client.Style.Unit.PX);
		vp2.getElement().getStyle().setRight((Window.getClientWidth() * rightOffset), com.google.gwt.dom.client.Style.Unit.PX);
		vp2.getElement().getStyle().setPosition(Position.FIXED);

		vp1.add(captcha);
		vp1.add(vp2);


		return vp1;
	}
	
	// widget that contains FAQ and RESET buttons at the bottom right of the client window with NO CAPTCHA
	// WARN: NO CAPTCHA IS CREATED WITH THIS FORM
		public Widget buttonsWidget4(FormPanel form) {
			VerticalPanel vp1 = new VerticalPanel();
			HorizontalPanel vp2 = new HorizontalPanel();
			
			int width = Window.getClientWidth();
			int height = Window.getClientHeight();
			double rightOffset = .09375; // fractional buffer from the right side of the client window for the buttons 


			// reset button
			TextButton resetButton = new TextButton("Reset");
			resetButton.addSelectHandler(new ResetButtonHandler(form));
			resetButton.setWidth(width / 12);
			resetButton.setHeight(height/16);

			// submit button
			TextButton submitButton = new TextButton("Submit");
			submitButton.addSelectHandler(new FormSubmitHandler(form));
			submitButton.setWidth(width / 12);
			submitButton.setHeight(height/16);
			
			vp2.add(resetButton);
			vp2.add(submitButton);

			vp2.getElement().getStyle().setBottom(15, com.google.gwt.dom.client.Style.Unit.PX);
			vp2.getElement().getStyle().setRight((Window.getClientWidth() * rightOffset), com.google.gwt.dom.client.Style.Unit.PX);
			vp2.getElement().getStyle().setPosition(Position.FIXED);

			vp1.add(vp2);


			return vp1;
		}
}
