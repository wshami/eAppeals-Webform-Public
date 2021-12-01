package com.nexlogica.form.client.usercontrols;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ConfirmationDialogBox extends DialogBox {
	
	private Boolean buttonSelectedYes = false;
	
	public ConfirmationDialogBox(String labeltext){
		setText("Confirmation");
		
		setAnimationEnabled(true);
		
		setGlassEnabled(true);
		
		Button yes = new Button("yes");
		yes.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ConfirmationDialogBox.this.hide();
				buttonSelectedYes = true;
			}
			
		});
		
		Button no  = new Button("no");
		no.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				ConfirmationDialogBox.this.hide();
				buttonSelectedYes = false;
			}
			
		});
		
		Label label = new Label(labeltext);
		
		VerticalPanel panel = new VerticalPanel();
		panel.setHeight("100");
		panel.setWidth("300");
		panel.setSpacing(10);
		panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        panel.add(label);
        panel.add(yes);
        panel.add(no);
		
        setWidget(panel);
	}
	
	public Boolean getButtonSelectedYes() {
		return buttonSelectedYes;
	}
	
}
