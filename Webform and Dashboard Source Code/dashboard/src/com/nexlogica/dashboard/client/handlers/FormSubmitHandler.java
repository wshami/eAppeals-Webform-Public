package com.nexlogica.dashboard.client.handlers;

//import org.json.simple.JSONObject;

import gwtquery.plugins.gwtcaptcha.client.Captcha;

import java.util.List;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.nexlogica.dashboard.client.FormBuilder;
import com.nexlogica.dashboard.client.grid.GridBuilder;
import com.nexlogica.dashboard.client.model.Appeal;
import com.nexlogica.dashboard.html.HTMLBuilder;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.box.AutoProgressMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent;
import com.sencha.gxt.widget.core.client.event.SubmitEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.event.SubmitCompleteEvent.SubmitCompleteHandler;
import com.sencha.gxt.widget.core.client.event.SubmitEvent.SubmitHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;

public class FormSubmitHandler implements SelectHandler{
	final AutoProgressMessageBox waiter = new AutoProgressMessageBox("Searching...", "Please wait while your records are retrieved.");
	private FormPanel form;
	private Captcha captcha;
	private TextArea textArea;

	public FormSubmitHandler(FormPanel form) {
		this.form = form;

		// grab the containers that hold the form fields inside of the form
		Container hlc = (Container) form.getWidget(0);
		Container vlc = (Container) hlc.getWidget(1);
		Container hlc2 = (Container) vlc.getWidget(0);
		this.captcha = (Captcha) hlc2.getWidget(1);

		Container hlc3 = (Container) hlc.getWidget(0);
		Container vlc2 = (Container) hlc3.getWidget(1);
		FieldLabel fl = (FieldLabel) vlc2.getWidget(0);
		this.textArea = (TextArea) fl.getWidget(0);
	}

	@Override
	// Method that takes place when the "submit" button is pressed
	public void onSelect(SelectEvent event) {

		form.addSubmitHandler(new SubmitHandler() {
			public void onSubmit(SubmitEvent event) {
			}
		});


		// add a new handler to the form that takes care of a complete submission
		// input: event results - should be a JSON object
		// output: the appropriate page should be showing, based on the JSON object
		form.addSubmitCompleteHandler(new SubmitCompleteHandler(){
			@SuppressWarnings("deprecation")
			public void onSubmitComplete(SubmitCompleteEvent event) {
				waiter.hide();

				// check response from POST
				String resultHtml = event.getResults();

				// failure code handling
				if(("SUBMIT_FAIL General Failure").equals(resultHtml))
					onNoCaseFound_GeneralFailure();
				else if(("SUBMIT_FAIL Upload Fail").equals(resultHtml))
					onNoCaseFound_UploadFail();

				// successful response handling
				else {
					JSONObject jsc = (JSONObject) JSONParser.parse(resultHtml);

					if(jsc.get("numberOfCases").toString().replace("\"", "") == "0")
						onNoCaseFound(); // 
					else if(jsc.get("numberOfCases").toString().replace("\"", "") != "1")
						DisplayMultipleResults(jsc);
					else
						DisplayResults(jsc);
				}

			}
		});

		// Upon selecting the 'submit' button, the below happens

		checkFormValidityAndSubmit();

		// -------------- END FORM SUBMISSION ------------

	}


	private void checkFormValidityAndSubmit() {
		final boolean WE_NEED_CAPTCHA = true;

		String textAreaText = this.textArea.getText();

		// Set the text to 'null' if the field was never changed - problems were occurring with the placeholder text
		if("Type one or multiple Appeal Case numbers, separated by commas".equals(textAreaText) ||(textAreaText != null && textAreaText.contains("Type one")))
			this.textArea.setText(null);

		// perform two more checks before finally submitting
		// 1. do all form fields have valid inputs
		// 2. is the captcha correct

		if(!form.isValid()){
			new MessageBox("Form Invalid", "Please review the form and correct all fields with a red \"!\" mark.").show();	
		} else if(WE_NEED_CAPTCHA && captcha.validate() == false){
			new MessageBox("Form Invalid", "Please re-enter the security code, or press the refresh button to generate a new code.").show();	
		} else {
			createWait();
			form.submit();
		}
		
	}

	private void DisplayResults(JSONObject results){
		// clear existing window
		RootPanel.get("Main").clear();
		RootPanel.get("Header").clear();

		// clear previous styles
		RootPanel.get("Main").removeStyleName("mainSmallTransparent");
		RootPanel.get("Main").setStyleName("mainLargeTransparent");

		// create header
		HTML intro = HTMLBuilder.generateMultipleCasesFoundHeader(results);
		RootPanel.get("Header").add(intro);

		HTML progressBar = getProgressBar(results);

		TextButton searchButton = new TextButton("Return to Search");
		searchButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				HTML intro = new HTML(HTMLBuilder.INTRO2);
				form = FormBuilder.getInputForm();

				RootPanel.get("Main").clear();
				RootPanel.get("Header").clear();
				RootPanel.get("Main").removeStyleName("mainLargeTransparent");

				RootPanel.get("Header").add(intro);
				RootPanel.get("Main").add(form);
			}
		});

		searchButton.setLayoutData(new Margins(5));

		HBoxLayoutContainer hblc = new HBoxLayoutContainer();
		hblc.add(searchButton);

		hblc.setHeight(75);
		hblc.setWidth(400);

		RootPanel.get("Main").add(hblc);
		RootPanel.get("Main").add(progressBar);
		RootPanel.get("Main").add(getTabView(results));

	}

	private HTML getProgressBar(JSONObject results2) {
		JSONArray ja = (JSONArray) results2.get("cases");
		JSONObject tempjo = (JSONObject) ja.get(0);

		String step = tempjo.get("currentStep").toString().replace("\"", "");

		if(("Appeal Received").equals(step)){
			return new HTML("<img src=\"Step0.PNG\" width=\"1200\">");
		} else if(("In Review").equals(step)){
			return new HTML("<img src=\"Step1.PNG\" width=\"1200\">");
		} else if(("Your Response Needed").equals(step)){
			return new HTML("<img src=\"Step2.PNG\" width=\"1200\">");
		} else if(("Closing Stage").equals(step)){
			return new HTML("<img src=\"Step3.PNG\" width=\"1200\">");
		} else if(("Response Issued / Case Closed").equals(step)){
			return new HTML("<img src=\"Step4.PNG\" width=\"1200\">");
		}  else if(("Case Closed").equals(step)){
			return new HTML("<img src=\"Step4.PNG\" width=\"1200\">");}
		else


			return new HTML("<img src=\"Step0.PNG\" width=\"1200\">");
	}
	private void DisplayMultipleResults(JSONObject results){

		RootPanel.get("Main").clear();
		RootPanel.get("Header").clear();
		RootPanel.get("Main").setStyleName("mainSmallTransparent");

		// create header
		HTML intro = HTMLBuilder.generateMultipleCasesFoundHeader(results);
		RootPanel.get("Header").add(intro);

		TextButton searchButton = new TextButton("Return to Search");
		searchButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				HTML intro = new HTML(HTMLBuilder.INTRO2);
				form = FormBuilder.getInputForm();

				RootPanel.get("Main").clear();
				RootPanel.get("Header").clear();
				// clear previous styles
				RootPanel.get("Main").removeStyleName("mainSmallTransparent");

				RootPanel.get("Header").add(intro);
				RootPanel.get("Main").add(form);
			}
		}); 


		RootPanel.get("Main").add(searchButton);

		RootPanel.get("Main").add(new HTML("<p style='font-size:20px;'>Select an appeal case to view case detailed information</p><br />"));

		// get appeal table
		List<Appeal> appealList = Appeal.createListFromJSONObject(results);

		GridBuilder gb = new GridBuilder(appealList, results, form);

		RootPanel.get("Main").add(gb);


	}



	// SUPPORTING FUNCTIONS BELOW

	private void createWait() {
		waiter.setHeight(155);
		waiter.auto();
		waiter.show();

	}
	private void onNoCaseFound(){
		new MessageBox("No Case Found", "No case was found matching the information that was provided. Please check it and try again").show();
	}

	private void onNoCaseFound_GeneralFailure(){
		new MessageBox("Communication Error", "The web from is having trouble communicating with the server. Check error logs.").show();
	}

	private void onNoCaseFound_UploadFail(){
		new MessageBox("File Upload Error", "The application failed to upload attachments to the server. Check error logs.").show();
	}

	// input: JSON Object containing the results from a single case
	// output: tabbed view for case details and Action Required
	private VerticalLayoutContainer getTabView(JSONObject results){

		// create view for information
		HTML caseDetailsHTML;
		caseDetailsHTML = HTMLBuilder.getCaseStatusTable(results, 0);

		// disabled configuration for when Action Required is not needed
		TabItemConfig disabledTab = new TabItemConfig("Action Required");
		disabledTab.setEnabled(false);

		// create tab panel
		final TabPanel tabPanel = new TabPanel();
		tabPanel.add(caseDetailsHTML, "Case Details");
		tabPanel.setWidth(1200);

		// add Action Required panel if required		
		if(fileUploadRequired(results)){
			form = FormBuilder.getFileUploadForm(results, 0);
			tabPanel.add(form, "Action Required");
		}

		//		else
		//			tabPanel.add(form, disabledTab);

		VerticalLayoutContainer widget = new VerticalLayoutContainer();
		widget.add(tabPanel, new VerticalLayoutData(-1, -1, new Margins(20, 0, 0, 0)));
		widget.setBorders(false);

		return widget;
	}


	private boolean fileUploadRequired(JSONObject results) {

		JSONArray ja = (JSONArray) results.get("cases");

		JSONObject tempjo = (JSONObject) ja.get(0);

		return (("true").equals(tempjo.get("fileUploadRequired").toString().replace("\"", "")));
	}

	class ModifiedTextButton extends TextButton{
		public int number;

		public ModifiedTextButton(String text, int number){
			super(text);
			this.number = number;	
		}
	}

}
