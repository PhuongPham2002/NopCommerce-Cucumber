package interfaces.pageUI;

public class RegisterPageUI {

    public final static String REGISTER_BUTTON ="ID=register-button";
    public final static String EMAIL_TEXTBOX = "ID=Email";
    public final static String GENDER_RADIO_BUTTON = "xpath=//label[text()='%s']";
    public final static String NEWSLETTER_CHECKBOX ="ID=Newsletter";
    public final static String INVALID_EMAIL_MESSAGE="id=Email-error";
    public final static String EXISTED_EMAIL_MESSAGE ="CSS=div.message-error.validation-summary-errors li";
    public final static String LOGOUT_BUTTON="css=a.ico-logout";
    public final static String REGISTER_FORM_TEXTBOX_ID ="id=%s";
    public final static String REGISTER_ERROR_MESSAGE_ID="id=%s-error";
    public final static String SUCCESSFUL_REGISTER_MESSAGE ="XPATH=//div[contains(text(),'Your registration completed')]";

}
