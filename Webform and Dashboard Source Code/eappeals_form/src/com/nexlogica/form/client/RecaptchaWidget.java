package com.nexlogica.form.client;

import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.widget.core.client.Composite;



public class RecaptchaWidget extends Composite {
	protected static final String RECAPTCHA_DIV_HTML = "<div class=\"g-recaptcha\" id=\"recaptcha_div\"/>";
	protected static final String RECAPTCHA_DIV_ID = "recaptcha_div";

	private HTML html;
	private String key;
	private int type = -1;

	private String lang;
	private String theme;
	private String customTheme;
	private CustomTranslation customTranslation;
	private int tabIndex;

	/**
	 * This Constructor is used to create an default reCAPTCHA widget
	 * 
	 * @param key
	 *            Your public key
	 */
	public RecaptchaWidget(String key) {
		this.key = key;
		html = new HTML(RECAPTCHA_DIV_HTML);
		initWidget(html);
		type = 0;
	}

	/**
	 * This Constructor is used to create an reCAPTCHA widget with standard
	 * customization
	 * 
	 * @param key
	 *            Your public key
	 * @param lang
	 *            the specific language
	 * @param theme
	 *            an specific theme
	 */
	public RecaptchaWidget(String key, String lang, String theme) {
		this.key = key;
		this.lang = lang;
		this.theme = theme;
		html = new HTML(RECAPTCHA_DIV_HTML);
		initWidget(html);
		type = 1;
	}

	/**
	 * This Constructor is used to create an reCAPTCHA widget with standard
	 * customization with tabindex specified
	 * 
	 * @param key
	 *            Your public key
	 * @param lang
	 *            the specific language
	 * @param theme
	 *            an specific theme
	 * @param tabIndex
	 *            the tabIndex of the widget or 0 for default
	 */
	public RecaptchaWidget(String key, String lang, String theme, int tabIndex) {
		this.key = key;
		this.lang = lang;
		this.theme = theme;
		this.tabIndex = tabIndex;
		html = new HTML(RECAPTCHA_DIV_HTML);
		initWidget(html);
		type = 2;
	}

	/**
	 * This Constructor is used to create an reCAPTCHA widget with customization
	 * and customTranslation
	 * 
	 * @param key
	 *            Your public key
	 * @param lang
	 *            the specific language, has also to be set for audio output
	 * @param theme
	 *            an specific theme
	 * @param tabIndex
	 *            the tabIndex of the widget or 0 for default
	 * @param customTranslation
	 *            a custom translation for the widget's Strings
	 */
	public RecaptchaWidget(String key, String lang, String theme, int tabIndex,
			CustomTranslation customTranslation) {
		this.key = key;
		this.lang = lang;
		this.theme = theme;
		this.tabIndex = tabIndex;
		this.customTranslation = customTranslation;
		html = new HTML(RECAPTCHA_DIV_HTML);
		initWidget(html);
		type = 3;
	}

	/**
	 * This Constructor is used to create an reCAPTCHA widget with customization
	 * and customTheme
	 * 
	 * @param key
	 *            Your public key
	 * @param lang
	 *            the specific language
	 * @param theme
	 *            an specific theme
	 * @param tabIndex
	 *            the tabIndex of the widget or 0 for default
	 * @param customTheme
	 *            the ID of a DOM-Element
	 */
	public RecaptchaWidget(String key, String lang, String theme, int tabIndex,
			String customTheme) {
		this.key = key;
		this.lang = lang;
		this.theme = theme;
		this.tabIndex = tabIndex;
		this.customTheme = customTheme;
		html = new HTML(RECAPTCHA_DIV_HTML);
		initWidget(html);
		type = 4;
	}

	/**
	 * This Constructor is used to create an reCAPTCHA widget with customization
	 * and customTranslation and theme
	 * 
	 * @param key
	 *            Your public key
	 * @param lang
	 *            the specific language, has also to be set for audio output
	 * @param theme
	 *            an specific theme
	 * @param tabIndex
	 *            the tabIndex of the widget or 0 for default
	 * @param customTranslation
	 *            a custom translation for the widget's Strings
	 * @param customTheme
	 *            the ID of a DOM-Element
	 */
	public RecaptchaWidget(String key, String lang, String theme, int tabIndex,
			CustomTranslation customTranslation, String customTheme) {
		this.key = key;
		this.lang = lang;
		this.theme = theme;
		this.tabIndex = tabIndex;
		this.customTranslation = customTranslation;
		this.customTheme = customTheme;
		html = new HTML(RECAPTCHA_DIV_HTML);
		initWidget(html);
		type = 5;
	}

	@Override
	protected void onAttach() {
		super.onAttach();
		switch (type) {
		case 0:
			create();
			break;
		case 1:
			create(lang, theme);
			break;
		case 2:
			create(lang, theme, tabIndex);
			break;
		case 3:
			create(lang, theme, tabIndex, customTranslation);
			break;
		case 4:
			create(lang, theme, tabIndex, customTheme);
			break;
		case 5:
			create(lang, theme, tabIndex, customTranslation, customTheme);
			break;
		default:
			create();
			break;
		}
	}

	@Override
	protected void onDetach() {
		destroy();
		super.onDetach();
	}

	/**
	 * This Method is used to create an default reCAPTCHA widget
	 */
	protected void create() {
		Recaptcha.create(key, RECAPTCHA_DIV_ID, "red", "en", 0);
	}

	/**
	 * This Method is used to create an reCAPTCHA widget with standard
	 * customization
	 */
	protected void create(String lang, String theme) {

		Recaptcha.create(key, RECAPTCHA_DIV_ID, theme, lang, 0);
	}

	/**
	 * This Method is used to create an reCAPTCHA widget with standard
	 * customization with tabindex specified
	 */
	protected void create(String lang, String theme, int tabIndex) {
		Recaptcha.create(key, RECAPTCHA_DIV_ID, theme, lang, tabIndex);
	}

	/**
	 * This Method is used to create an reCAPTCHA widget with customization and
	 * customTranslation
	 */
	protected void create(String lang, String theme, int tabIndex,
			CustomTranslation translation) {
		Recaptcha
				.create(key, RECAPTCHA_DIV_ID, theme, lang, tabIndex,
						translation.getInstructionVisual(), translation
								.getInstructionAudio(), translation
								.getPlayAgain(), translation.getCantHereThis(),
						translation.getVisualChalange(), translation
								.getAudioChalange(), translation
								.getRefreshButton(), translation
								.getHelpButton(), translation
								.getIncorrectTryAgain());
	}

	/**
	 * This Method is used to create an reCAPTCHA widget with customization and
	 * customTheme
	 */
	protected void create(String lang, String theme, int tabIndex,
			String customtheme) {
		Recaptcha.create(key, RECAPTCHA_DIV_ID, theme, lang, tabIndex, theme);
	}

	/**
	 * This Method is used to create an reCAPTCHA widget with customization and
	 * customTranslation and theme
	 */
	protected void create(String lang, String theme, int tabIndex,
			CustomTranslation translation, String customtheme) {
		Recaptcha
				.create(key, RECAPTCHA_DIV_ID, theme, lang, tabIndex,
						customtheme, translation.getInstructionVisual(),
						translation.getInstructionAudio(), translation
								.getPlayAgain(), translation.getCantHereThis(),
						translation.getVisualChalange(), translation
								.getAudioChalange(), translation
								.getRefreshButton(), translation
								.getHelpButton(), translation
								.getIncorrectTryAgain());
	}

	protected void destroy() {
		Recaptcha.destroy();
	}

	public void reload() {
		Recaptcha.reload();
	}

	public String getChallenge() {
		return Recaptcha.getChallenge();
	}

	public String getResponse() {
		return Recaptcha.getResponse();
	}

	public void focusResponseField() {
		Recaptcha.focusResponseField();
	}

	public void showHelp() {
		Recaptcha.showHelp();
	}

	public void switchType(String newType) {
		Recaptcha.switchType(newType);
	}
}