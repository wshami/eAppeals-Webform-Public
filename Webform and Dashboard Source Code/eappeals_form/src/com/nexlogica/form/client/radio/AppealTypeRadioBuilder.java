package com.nexlogica.form.client.radio;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Widget;
import com.nexlogica.form.client.html.HTMLBuilder;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutData;
import com.sencha.gxt.widget.core.client.form.Radio;
import com.sencha.gxt.widget.core.client.form.TextArea;

// uncomment below imports to add other text box back into UI
//import com.google.gwt.user.client.ui.Label;
//import com.nexlogica.form.client.handlers.CharacterCounterKeyUpHandler;
//import com.sencha.gxt.widget.core.client.form.FieldLabel;
//import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;

public class AppealTypeRadioBuilder implements ValueChangeHandler<HasValue<Boolean>> {
	private  ToggleGroup toggle;
	private Radio radio1;
	private Radio radio2;
	private Radio radio3;
	private Radio radio4;
	private Radio radio5;
	private Radio radio6;
	private Radio radio7;
	private Radio radio8;
	private Radio radio9;
	private Radio radio10;
	private Radio radio11;
	private Radio radio12;
	private Radio radio13;
	private Radio radio14;
	private Radio radio15;
	private Radio radio16;
	private Radio radio17;
	private Radio radio18;
	private Radio radio19;
	private Radio radio20;
	private Radio radio21;
	private Radio radio22;
	
	final private TextArea otherTextBox = new TextArea();
	private HBoxLayoutContainer repQuestionContainer; 
	private VerticalLayoutContainer vlcExamInfo;
	// private VerticalLayoutContainer vlcReqDocHTML;
	private HTML reqDocHTML;
	private BoxLayoutData layoutData = new BoxLayoutData(new Margins(0, 0, 0, 15));

	public AppealTypeRadioBuilder(HBoxLayoutContainer repQuestionContainer,VerticalLayoutContainer vlcExamInfo, 
			VerticalLayoutContainer vlcReqDocHTML, HTML reqDocHTML) {
		this.repQuestionContainer = repQuestionContainer;
		this.vlcExamInfo = vlcExamInfo;
		// this.vlcReqDocHTML = vlcReqDocHTML;
		this.reqDocHTML = reqDocHTML;
		toggle = new ToggleGroup();

		
		// DISCIPLINE radio buttons

		radio1 = new Radio();
		radio1.setName("oneFiveDatSuspension");
		radio1.setBoxLabel("<b>Suspension of 1-5 day(s):</b> Only suspensions from a County position for 1 to 5 days");
		
		radio2 = new Radio();
		radio2.setName("layoff");
		radio2.setBoxLabel("Lay-Off - Separation from a permanent position because of economy, lack of funds, lack of work or because the position has been abolished. CSR 2.32 ");

		radio3 = new Radio();
		radio3.setName("probDischarge");
		radio3.setBoxLabel("<b>Probationary Discharge:</b> Release from County employment due to an unsatisfactory performance rating during the initial probationary period");

		radio4 = new Radio();
		radio4.setName("probReduction");
		radio4.setBoxLabel("<b>Probationary Reduction:</b> Reduction from a promotional position due to an unsatisfactory performance rating during the probationary period");

		radio5 = new Radio();
		radio5.setName("reductionLayOff");
		radio5.setBoxLabel("Reduction due to Lay-Off - Reduction of current payroll title instead of Lay-off");

		radio6 = new Radio();
		radio6.setName("relTempEmployment");
		radio6.setBoxLabel("Release from Temporary Employment - Release from a temporary position within a County department");

		radio7 = new Radio();
		radio7.setName("transfer");
		radio7.setBoxLabel("<b>Interdepartmental Transfer:</b> Transfer to a different department without being requested by the transferee");

		radio8 = new Radio();
		radio8.setName("classificationStudy");
		radio8.setBoxLabel("<b>Classification Study:</b> To determine if the duties and responsibilities assigned to a position are properly classified");

		radio9 = new Radio();
		radio9.setName("nonAppointment");
		radio9.setBoxLabel("Non-Appointment");

		radio10 = new Radio();
		radio10.setName("resignation");
		radio10.setBoxLabel("<b>Resignation:</b> Resignation from County employment under duress, fraud, or undue influence");

		// Disqualifications radio buttons

		radio11 = new Radio();
		radio11.setName("background");
		radio11.setBoxLabel("<b>Disqualification/Withhold</b> from the recruitment process due to the results of a background review/investigation");

		radio12 = new Radio();
		radio12.setName("inaccurateDiscOHP");
		radio12.setBoxLabel("<b>Inaccurate Disclosure to Occupational Health Program -</b> Disqualification due to an inaccurate disclosure to the Occupational Health Program");

		radio22 = new Radio();
		radio22.setName("appRejection");
		radio22.setBoxLabel("<b>Supplemental Questionnaire (JSQ/SQ):</b> Your application was not accepted based on response(s) you provided on the Job Specific Questionnaire or Supplemental Questionnaire");

		// Exam radio buttons

		radio13 = new Radio();
		radio13.setName("appRejection");
		radio13.setBoxLabel("<b>Application Rejection/Non-Acceptance:</b> Your application was not accepted, because it was determined that you did not meet the examination requirements as stated in the bulletin");

		radio14 = new Radio();
		radio14.setName("appraisalPromotibility");
		radio14.setBoxLabel("<b>Appraisal of Promotability (AP):</b> <u>County employees only</u> - The score you received based on management's assessment of your potential to perform at the higher-level position");

		radio15 = new Radio();
		radio15.setName("evalTrainExp");
		radio15.setBoxLabel("<b>Evaluation of Training and Experience/Rating from Records:</b> The score you received based on the evaluation of information you provided on your application Supplemental Questionnaire, and other applicable documents");

		radio16 = new Radio();
		radio16.setName("interview");
		radio16.setBoxLabel("<b>Oral Interview:</b> The score you received based on the responses you provided to questions presented during the interview portion of the examination");

		radio17 = new Radio();
		radio17.setName("perfTest");
		radio17.setBoxLabel("<b>Performance Test</b> (for example, Driving, Physical Agility, Swimming, Typing, etc.): The score you received based on your performance");

		radio18 = new Radio();
		radio18.setName("vetCredit");
		radio18.setBoxLabel("<b>Veteran's Credit:</b> Ten (10) additional points added to the <u>final passing score</u> of an open competitive examination based on qualifying service in the military - See <a href=\"https://library.municode.com/ca/los_angeles_county/codes/code_of_ordinances?nodeId=TIT5PE_APX1CISERU_RULE_7COEX_7.15VECR\">CSR 7.15</a> for details");

		radio19 = new Radio();
		radio19.setName("writterTest");
		radio19.setBoxLabel("<b>Paper-and-Pencil Written Tests:</b> The score you received in the following non-computerized tests: Broad-Based Employment Skills Test (BBEST), Writing Assessment, or Other, as specified in the examination result notification");

		radio20 = new Radio();
		radio20.setName("workstyleAssessment");
		radio20.setBoxLabel("<b>Computer Administered Tests:</b> The score you received on a computerized test, including the Work Styles Assessment (WSA), Writing Assessment, or Other, as specified in the examination result notification");

		radio21 = new Radio();
		radio21.setName("other");
		radio21.setBoxLabel("Other - <b>If your appeal type does not appear on the list above, please select 'Other'. There will be room to describe your appeal in the fields below</b>");
		

		toggle.add(radio1);
		toggle.add(radio2);
		toggle.add(radio3);
		toggle.add(radio4);
		toggle.add(radio5);
		toggle.add(radio6);
		toggle.add(radio7);
		toggle.add(radio8);
		toggle.add(radio9);
		toggle.add(radio10);
		toggle.add(radio11);
		toggle.add(radio12);
		toggle.add(radio13);
		toggle.add(radio14);
		toggle.add(radio15);
		toggle.add(radio16);
		toggle.add(radio17);
		toggle.add(radio18);
		toggle.add(radio19);
		toggle.add(radio20);
		toggle.add(radio21);
		toggle.add(radio22);

	}

	public VerticalLayoutContainer getDisciplineRadioGroup() {

		toggle.addValueChangeHandler(this);

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.add(new HTML("<i>County Employee Only</i><br /><br />"));
		vlc.add(radio1);
		//vlc.add(radio2);
		vlc.add(radio3);
		vlc.add(radio4);
		//vlc.add(radio5);
		//vlc.add(radio6);
		vlc.add(radio7);
		//vlc.add(radio8);
		//vlc.add(radio9);
		vlc.add(radio10);

		vlc.setLayoutData(layoutData);

		return vlc;
	}

	public VerticalLayoutContainer getDisqualificationRadioGroup() {
		toggle.addValueChangeHandler(this);
		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.add(new HTML("<br />"));
		vlc.add(radio11);
		//vlc.add(radio12);
		vlc.add(radio22);
		
		vlc.setLayoutData(layoutData);

		return vlc;
	}

	public VerticalLayoutContainer getExamPositionRadioGroup() {
		toggle.addValueChangeHandler(this);

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.add(new HTML("<i>You may file an appeal on multiple examination components; however, you must file a separate appeal for each component.  Upon completion of each appeal, the eAppeal online form will prompt you to file another appeal.</i><br /><br />"));
		vlc.add(radio13);
		vlc.add(radio14);
		vlc.add(radio20);
		vlc.add(radio15);
		vlc.add(radio16);
		vlc.add(radio19);
		vlc.add(radio17);
		vlc.add(radio18);
		
		

		vlc.setLayoutData(layoutData);

		return vlc;
	}

	public VerticalLayoutContainer getOtherRadioGroup() {

		toggle.addValueChangeHandler(this);

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();

		vlc.add(radio21);

		vlc.setLayoutData(layoutData);

		return vlc;
	}
	
	public Widget getClassificationStudyRadioGroup() {
		toggle.addValueChangeHandler(this);

		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		
		vlc.add(new HTML("<i>County Employee Only</i><br /><br />"));
		
		vlc.add(radio8);
	
		vlc.setLayoutData(layoutData);

		return vlc;
	}

	@Override
	public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
		ToggleGroup group = (ToggleGroup) event.getSource();
		Radio radio = (Radio) group.getValue();
		repQuestionContainer.show();
		if(radio.getName().equals("background")        || 
				radio.getName().equals("appRejection") || 
				radio.getName().equals("evalTrainExp") || radio.getName().equals("interview") || 
				radio.getName().equals("perfTest")     || radio.getName().equals("vetCredit") || 
				radio.getName().equals("writterTest")  || radio.getName().equals("workstyleAssessment") ) {


			vlcExamInfo.show();
			//vlcReqDocHTML.show();

		}
		else if(radio.getName().equals("appraisalPromotibility")){
			
			vlcExamInfo.show();
			
		}
		else if(radio.getName().equals("classificationStudy")){
		
			vlcExamInfo.show();
		
		}
		else if(radio.getName().equals("inaccurateDiscOHP")) {
		
			vlcExamInfo.show();
	
		
		}

		else if(radio.getName().equals("other")) {
			
			vlcExamInfo.hide();
			
		}
		else {

			vlcExamInfo.hide();
			//vlcReqDocHTML.show();
		
		}
	}

	

}
