package com.nexlogica.dashboard.client.model;

import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ProgressBar;

public class AppealProgressBar extends ProgressBar {

	public AppealProgressBar(JSONObject results){
		this(results, 0);
	}
	
	public AppealProgressBar(JSONObject results, int i){
		super();
		this.setWidth(1000);
		this.setLayoutData(new Margins(5));
		SetProgress(results, i);
		
	}
	
	private void SetProgress(JSONObject results, int i){
		JSONArray ja = (JSONArray) results.get("cases");
		JSONObject tempjo = (JSONObject) ja.get(i);
		String currentStep = tempjo.get("currentStep").toString().replace("\"", "");
		
		if("Data Validation".equals(currentStep) || "SS1".equals(currentStep))
			this.updateProgress(.1, "Appeal Received");
		else if("Docs Request".equals(currentStep))
			this.updateProgress(.3, "In Review");
		else if("Assigned to Analyst".equals(currentStep))
			this.updateProgress(.5, "In Review");
		else if("Info Request".equals(currentStep))
			this.updateProgress(.6, "Your Response Needed");
		else if("Peer Review".equals(currentStep))
			this.updateProgress(.75, "In Review");
		else if("Ready to Close".equals(currentStep) || "SS3".equals(currentStep))
			this.updateProgress(.9, "Closing Stage");
		else if("Closed".equals(currentStep))
			this.updateProgress(1, "Response Issued / Case Closed");
		else
			this.updateProgress(.5, "In Review");
		
		// Steps from Solution Design Document	
		// Data Validation (Support Staff)		= Appeal Received
		// Docs Request (Depts)					= In Review
		// Assigned to Analyst					= In Review
		// Info Request							= Your Response Needed
		// Peer Review							= In Review
		// Ready to Close						= Closing Stage
		// Closed								= Response Issued / Case Closed
	}
}
