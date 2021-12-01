package com.nexlogica.dashboard.client.handlers;

import com.google.gwt.user.client.Window;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.SubmitEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.event.SubmitEvent.SubmitHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel;

public class FileUploadHandler implements SelectHandler{

	private FormPanel form;

	public FileUploadHandler(FormPanel form) {
		this.form = form;

		this.form.addSubmitHandler(new SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
			}
		});

		// Submission Complete
		// input: event results - should be a JSON object
		// output: the appropriate page should be showing, based on the JSON object
		this.form.addSubmitCompleteHandler(new SubmitCompleteHandler(){
			public void onSubmitComplete(SubmitCompleteEvent event) {

				//String resultHtml = event.getResults();
				//Info.display("results of upload", resultHtml);

				if(event.getResults() == "SUBMIT_GOOD")
					OnUploadSuccess();	
				else
					new MessageBox("upload not good", event.getResults()).show();

			}
		});
	}

	@Override
	// Method that takes place when the "submit" button is pressed
	public void onSelect(SelectEvent event) {

		if(!form.isValid()){
			new MessageBox("Form Invalid", "Please enter a note or attach a document to submit this form.").show();	
		}

		else{			

			form.submit();

		} // -------------- END FORM SUBMISSION ------------

	}



	private void OnUploadSuccess(){

		Window.open("post_search_landing.html", "_self", null);

	}
}
