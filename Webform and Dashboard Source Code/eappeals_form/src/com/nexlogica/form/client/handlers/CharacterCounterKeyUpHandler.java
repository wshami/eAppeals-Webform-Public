package com.nexlogica.form.client.handlers;

import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.Label; 


public class CharacterCounterKeyUpHandler implements KeyUpHandler, ValueChangeHandler{

	private int characterLimit;
	private Label characterLimitLabel;
	private int charactersEntered;
	private com.sencha.gxt.widget.core.client.form.TextArea textArea;
	
	// user passes in the label to keep updated and the desired character limit
	public CharacterCounterKeyUpHandler(Label l, int cl, com.sencha.gxt.widget.core.client.form.TextArea appealRemedy){
		this.characterLimit = cl;
		this.characterLimitLabel = l;
		this.charactersEntered = 0;
		this.textArea = appealRemedy;
	}
	
	@Override
	public void onKeyUp(KeyUpEvent event) {
		
		charactersEntered = textArea.getText().length();
		
		this.characterLimitLabel.setText(charactersEntered + "/" + characterLimit);	
		
		if (charactersEntered > characterLimit)
			this.characterLimitLabel.getElement().getStyle().setColor("red");
		else
			this.characterLimitLabel.getElement().getStyle().setColor("black");
	}

	@Override
	public void onValueChange(ValueChangeEvent arg0) {
charactersEntered = textArea.getText().length();
		
		this.characterLimitLabel.setText(charactersEntered + "/" + characterLimit);	
		
		if (charactersEntered > characterLimit)
			this.characterLimitLabel.getElement().getStyle().setColor("red");
		else
			this.characterLimitLabel.getElement().getStyle().setColor("black");
		
	}
}

