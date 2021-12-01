package com.nexlogica.dashboard.client.grid;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.logical.shared.BeforeSelectionEvent;
import com.google.gwt.event.logical.shared.BeforeSelectionHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.nexlogica.dashboard.client.FormBuilder;
import com.nexlogica.dashboard.client.model.Appeal;
import com.nexlogica.dashboard.client.model.AppealProperties;
import com.nexlogica.dashboard.html.HTMLBuilder;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.RowClickEvent.RowClickHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FormPanel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class GridBuilder implements IsWidget {

	private static final AppealProperties properties = GWT.create(AppealProperties.class);

	private ContentPanel panel;
	private List<Appeal> appealList;
	private JSONObject results;
	private FormPanel form;

	public GridBuilder(List<Appeal> appealList, JSONObject results, FormPanel form){
		this.appealList = appealList;
		this.results = results;
		this.form = form;
	}

	@Override
	public Widget asWidget() {
		if(panel == null){
			ColumnConfig<Appeal, Integer> caseNumberCol 	= new ColumnConfig<Appeal, Integer>(properties.caseNumber(), 200, "Case #");
			ColumnConfig<Appeal, String> examNumberCol 		= new ColumnConfig<Appeal, String>(properties.examNumber(), 236, "Exam #");
			ColumnConfig<Appeal, String> appealTypeCol 		= new ColumnConfig<Appeal, String>(properties.appealType(), 275, "Appeal Type");
			ColumnConfig<Appeal, String> dateSubmittedCol 	= new ColumnConfig<Appeal, String>(properties.dateSubmitted(), 276, "Date Submitted");
			ColumnConfig<Appeal, String> caseStatusCol 		= new ColumnConfig<Appeal, String>(properties.caseStatus(), 305, "Current Status");
			
			caseNumberCol.setColumnHeaderClassName("appealGrid");
			examNumberCol.setColumnHeaderClassName("appealGrid");
			appealTypeCol.setColumnHeaderClassName("appealGrid");
			dateSubmittedCol.setColumnHeaderClassName("appealGrid");
			caseStatusCol.setColumnHeaderClassName("appealGrid");
			
			
			List<ColumnConfig<Appeal, ?>> columns = new ArrayList<ColumnConfig<Appeal, ?>>();

			columns.add(caseNumberCol);
			columns.add(appealTypeCol);
			columns.add(examNumberCol);
			columns.add(dateSubmittedCol);
			columns.add(caseStatusCol);

			ColumnModel<Appeal> cm = new ColumnModel<Appeal>(columns);

			
			ListStore<Appeal> store = new ListStore<Appeal>(properties.key());
			store.addAll(appealList);

			final Grid<Appeal> grid = new Grid<Appeal>(store, cm);
			grid.setAllowTextSelection(true);
			grid.getView().setAutoExpandColumn(caseStatusCol);
			grid.getView().setStripeRows(true);
			grid.getView().setColumnLines(true);
			grid.setBorders(true);
			grid.setColumnReordering(false);
			grid.getView().setAutoFill(true);
			
			
			grid.addRowClickHandler(new RowClickHandler(){
				@Override
				public void onRowClick(RowClickEvent event) {

					int i = getJSONIndexWithCaseNumber(grid.getView().getCell(event.getRowIndex(), 0).getInnerText(), results);
					DrillDown(i, results);
				}
			});

			
			VerticalLayoutContainer con = new VerticalLayoutContainer();
			con.add(grid);

			panel = new ContentPanel();
			panel.setHeaderVisible(false);
			panel.add(con);
			panel.setId("gridContentPanel");
			panel.setBorders(true);
			
		}
		return panel;
	}

	protected int getJSONIndexWithCaseNumber(String string, JSONObject results2) {
		JSONArray ja = (JSONArray) results2.get("cases");
		String numCases =  results2.get("numberOfCases").toString();
		int i = Integer.valueOf(numCases.toString().replace("\"", ""));
		
		
		for(int j = 0; j < i; j++) {
			JSONObject tempjo = (JSONObject) ja.get(j);
			if(tempjo.get("caseNumber").toString().replace("\"", "").equals(string))
				return j;
		
		}
		return 0;
	}

	private void DrillDown(int i, JSONObject results){
		// clear existing window
		RootPanel.get("Main").clear();
		RootPanel.get("Header").clear();

		// clear previous styles
		RootPanel.get("Main").removeStyleName("mainSmallTransparent");
		RootPanel.get("Main").setStyleName("mainLargeTransparent");
		
		// create header
		HTML intro = HTMLBuilder.generateMultipleCasesFoundHeader(results);
		RootPanel.get("Header").add(intro);
		
		// create appeal progress bar
		HTML appealProgressBar = getProgressBar(results, i);

		// add buttons to go back to the multi view
		TextButton backButton = new TextButton("Back to search results");
		backButton.addSelectHandler(new SelectHandler(){
			@Override
			public void onSelect(SelectEvent event) {
				RootPanel.get("Main").removeStyleName("mainLargeTransparent");
				DisplayMultipleResults();
			}
		});

		TextButton searchButton = new TextButton("Track a new appeal");
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
		
		backButton.setLayoutData(new Margins(0, 15, 0, 0));
		
		HBoxLayoutContainer hblc = new HBoxLayoutContainer();
		hblc.add(backButton, new BoxLayoutData(new Margins(0, 15, 0, 0)));
		hblc.add(searchButton, new BoxLayoutData(new Margins(0, 0, 0, 15)));

		hblc.setHeight(75);
		hblc.setWidth(600);
		
		RootPanel.get("Main").add(hblc);
		
		RootPanel.get("Main").add(appealProgressBar);
		
		RootPanel.get("Main").add(getTabView(results, i));

	}


	private HTML getProgressBar(JSONObject results2, int i) {
		JSONArray ja = (JSONArray) results2.get("cases");
		JSONObject tempjo = (JSONObject) ja.get(i);
		
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

	private Widget getTabView(JSONObject results, int i) {
		// create view for information
		HTML caseDetailsHTML;
		caseDetailsHTML = HTMLBuilder.getCaseStatusTable(results, i);
		caseDetailsHTML.setStylePrimaryName("tabPanelView");
		// add form upload field
		if(fileUploadRequired(results, i))
				form = FormBuilder.getFileUploadForm(results, i);

		// disabled configuration for when Action Required is not needed
		TabItemConfig disabledTab = new TabItemConfig("Action Required");
		disabledTab.setEnabled(false);
		

		// create tab panel
		final TabPanel tabPanel = new TabPanel();
		tabPanel.add(caseDetailsHTML, "Case Details");
		//tabPanel.setBorders(true);
		tabPanel.setWidth(1200);
		
		
		// add Action Required panel if required
		if(fileUploadRequired(results, i)){
			tabPanel.add(form, "Action Required");
			tabPanel.addSelectionHandler(new SelectionHandler<Widget>(){

				boolean leftSelected = true;
				
				@Override
				public void onSelection(SelectionEvent<Widget> event) {
					TabPanel t = (TabPanel) event.getSource();
					this.leftSelected = !leftSelected;
					if(!this.leftSelected){
						t.setStyleName("tabPanel tabPanelSecondary");						
					}
					if(this.leftSelected){
						t.setStyleName("tabPanel tabPanelPrimary");
					}
				}

				
			});
		}
			
//		else
//			tabPanel.add(form, disabledTab);

		tabPanel.setStylePrimaryName("tabPanel");
		tabPanel.setStyleName("tabPanel tabPanelPrimary");
		
		
		VerticalLayoutContainer widget = new VerticalLayoutContainer();
		widget.setWidth(1200);
		widget.add(tabPanel, new VerticalLayoutData(-1, -1, new Margins(20, 0, 0, 0)));
		widget.setBorders(false);
		
		return widget;
	}

	private boolean fileUploadRequired(JSONObject results, int i) {
		JSONArray ja = (JSONArray) results.get("cases");

		JSONObject tempjo = (JSONObject) ja.get(i);

		return (("true").equals(tempjo.get("fileUploadRequired").toString().replace("\"", "")));
	}

	private void DisplayMultipleResults(){
		// clear existing window
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
				RootPanel.get("Main").removeStyleName("mainSmallTransparent");
				
				RootPanel.get("Header").add(intro);
				RootPanel.get("Main").add(form);
			}
		}); 
		
		RootPanel.get("Main").add(searchButton);
		
		RootPanel.get("Main").add(new HTML("<p style='font-size:20px;'>Select an appeal case below to drill down into that case</p><br />"));
		
		RootPanel.get("Main").add(this);



	}
}
