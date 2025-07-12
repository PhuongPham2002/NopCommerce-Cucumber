package interfaces.pageUI;

import org.checkerframework.checker.index.qual.PolyUpperBound;

public class LoginPageUI {
    public final static String LOGIN_BUTTON="CSS=button.login-button";
    public final static String LOGIN_FORM_TEXTBOX_ID ="ID=%s";
    public static final String MY_ACCOUNT_LINK="css=a.ico-account";
    // Cả lỗi EMPTY EMAIL/PW và INVALID EMAIL đều hiện tại cùng 1 chỗ (id=Email-error), Web không tách riêng locator theo từng loại lỗi
    public final static String EMAIL_ERROR_MESSAGE = "id=Email-error";
    //Unregistered email, empty pw, invalid pw hiển thị message ở cùng 1 vị trí - cùng locator
    public final static String LOGIN_SUMMARY_ERROR_MESSAGE = "css=div.validation-summary-errors";

}
