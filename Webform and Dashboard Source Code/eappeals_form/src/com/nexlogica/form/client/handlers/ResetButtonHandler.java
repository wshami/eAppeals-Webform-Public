package com.nexlogica.form.client.handlers;

import gwtquery.plugins.gwtcaptcha.client.Captcha;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RichTextArea;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AccordionLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldSet;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.IsField;

public class ResetButtonHandler implements SelectHandler{
	private FormPanel form = null;
	private Captcha captcha = null;
	private int newWindowPositionTop = 1600;
	private int newWindowPositionLeft = 500;
	private static int newWindowOffset = 800;
	
	public ResetButtonHandler (FormPanel form, Captcha captcha) {
		this.form = form;
		this.captcha = captcha;
	}

	// constructor for when we have no captcha while the application is in test
	public ResetButtonHandler(FormPanel form) {
		this.form = form;
	}

	@Override
	public void onSelect(SelectEvent event) {		
		final Dialog simple = new Dialog();
		setNewWindowPositions();
		simple.setPredefinedButtons();
		simple.setHeadingText("Reset Confirmation");
		simple.setWidth(250);
//		simple.setHeight(180);
		simple.setResizable(false);
		simple.setHideOnButtonClick(true);
		simple.setBodyStyleName("pad-text");
		simple.setBodyStyle("padding: 10px 7px 10px 7px");
		//simple.setPosition(newWindowPositionLeft, newWindowPositionTop);
		Label prompt = new Label("Are you sure you would like to reset this form? All information and attachments will be deleted.");

		simple.add(prompt);

		TextButton y = new TextButton("Yes", new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {

				doReset();
				form.reset();
				if(captcha != null) captcha.reset();
				simple.hide();
			}
		});

		TextButton n = new TextButton("No", new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				simple.hide();
			}
		});

		simple.addButton(y);
		simple.addButton(n);		
		simple.setModal(true);
		simple.show();
//		simple.setPagePosition(0, 0);
		simple.center();
		
	}

	protected void doReset() {

		if(this.captcha == null){
			Container base = (Container) form.getWidget(0);
			Container issueAndRemedy = (Container) base.getWidget(5);
			for(int i = 0; i < issueAndRemedy.getWidgetCount(); i++)
				if(issueAndRemedy.getWidget(i).getClass() == Captcha.class)
					this.captcha = (Captcha) issueAndRemedy.getWidget(i);
				
		}
		
		// reset form after saving relevant values
		form.reset();

		// collapse the content panels
		VerticalLayoutContainer base = (VerticalLayoutContainer) form.getWidget(0);
		if(base.getWidget(1).getClass() == AccordionLayoutContainer.class) {
			AccordionLayoutContainer a = (AccordionLayoutContainer) base.getWidget(1);
			for(int widget = 0; widget<a.getWidgetCount(); widget++){
				if(a.getWidget(widget).getClass() == ContentPanel.class){
					ContentPanel cp = (ContentPanel) a.getWidget(widget);
					cp.setExpanded(false);
				}

			}
		}
		// exam information
		if(base.getWidget(2).getClass() == VerticalLayoutContainer.class) {
			VerticalLayoutContainer a = (VerticalLayoutContainer) base.getWidget(2);
			a.hide();
		}

		//repquestiuon
		if(base.getWidget(3).getClass() == HBoxLayoutContainer.class) {
			HBoxLayoutContainer a = (HBoxLayoutContainer) base.getWidget(3);
			a.hide();
		}

		//appellantinfo
		if(base.getWidget(4).getClass() == FieldSet.class) {
			FieldSet a = (FieldSet) base.getWidget(4);
			a.hide();
		}

		// appeal issues
		if(base.getWidget(5).getClass() == VerticalLayoutContainer.class) {
			VerticalLayoutContainer a = (VerticalLayoutContainer) base.getWidget(5);

			if(a.getWidget(1).getClass() == RichTextArea.class){
				RichTextArea rta = (RichTextArea) a.getWidget(1);
				rta.setHTML("");
			}

			a.hide();
		}

		
		
		// fill form with saved relevant values
		for(IsField<?> field : form.getFields()) {
			if (field.getClass() == ComboBox.class) {
				ComboBox<?> cb = (ComboBox<?>) field;
				if (cb.getName() == "state"){
					cb.markInvalid("");
				}
			}

		}

		// reset captcha
		if (captcha != null) captcha.reset();



	}
	private void setNewWindowPositions() {
		VerticalLayoutContainer vlc = (VerticalLayoutContainer) this.form.getWidget(0);
		if(vlc.getWidget(4).getClass() == Widget.class && vlc.getWidget(5).isVisible()){
			Widget vlc2 = vlc.getWidget(4);
			newWindowPositionTop = vlc2.getAbsoluteTop();
			newWindowPositionLeft = vlc2.getAbsoluteLeft() + newWindowOffset;
		}
	}
}
