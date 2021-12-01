package com.nexlogica.dashboard.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.sencha.gxt.widget.core.client.button.TextButton;

public class SubmitListener implements KeyUpHandler {
	private TextButton button;
	
	SubmitListener(TextButton button){
		this.button = button;
	}
	@Override
	public void onKeyUp(KeyUpEvent arg0) {
		if(arg0.equals(KeyCodes.KEY_ENTER)){
			button.fireEvent(new ClickEvent(){});
		}
	}
}