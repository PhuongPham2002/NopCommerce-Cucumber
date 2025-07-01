package interfaces.pageUI;

import org.checkerframework.checker.index.qual.PolyUpperBound;

public class LoginPageUI {
    public final static String LOGIN_BUTTON="CSS=button.login-button";
    public final static String UNREGISTERED_EMAIL_ERROR_MESSAGE ="css=div.validation-summary-errors";
    public final static String EMPTY_PASSWORD_ERROR_MESSAGE ="css=div.validation-summary-errors";
    public final static String INVALID_PASSWORD_ERROR_MESSAGE ="css=div.validation-summary-errors";
    public final static String LOGIN_FORM_TEXTBOX_ID ="ID=%s";
    public final static String LOGIN_ERROR_MESSAGE="id=Email-error";
    public static final String WRONG_PASSWORD_ERROR_MESSAGE ="css=div.validation-summary-errors";

    //Cucumber Implement:
    public static final String ERROR_MESSAGE ="css=div.validation-summary-errors";
    public final static String INVALID_EMAIL_ERROR_MESSAGE="id=Email-error";
    public static final String MY_ACCOUNT_LINK="css=a.ico-account";

}
