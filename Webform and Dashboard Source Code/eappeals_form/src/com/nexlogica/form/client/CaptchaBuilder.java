package com.nexlogica.form.client;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;

import gwtquery.plugins.gwtcaptcha.client.Captcha;

public class CaptchaBuilder {
	public static Widget getCaptchaWidget() {
		VerticalPanel vp = new VerticalPanel();
		
		final Captcha captcha = new Captcha();
		TextButton submit = new TextButton("submit");
		
		vp.add(new HTML("<p><b>STEP 6 - SECURITY CHECK</b><br /><br />Please enter the text below</p>"));
		vp.add(captcha);
		vp.add(submit);
		vp.add(new HTML("<p>Can't read the text above? Try another set by refreshing the captcha with the refresh button</p>"));

		return vp;
	}
}
