package actions.pageObject;

import commons.base.BasePage;
import interfaces.pageUI.HomePageUI;
import interfaces.pageUI.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {

    public LoginPageObject(WebDriver driver) {
        super(driver);
    }


    public HomePageObject clickLoginButton() {
        waitForElementClickable(LoginPageUI.LOGIN_BUTTON);
        clickElement(LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getHomePage(driver);

    }

    public void enterLoginForm(String emailAddress, String password) {
        if (emailAddress != null) {
            enterTextboxByID(LoginPageUI.LOGIN_FORM_TEXTBOX_ID, emailAddress, "Email");
        }
        if (password != null) {
            enterTextboxByID(LoginPageUI.LOGIN_FORM_TEXTBOX_ID, password, "Password");
        }
    }

    public String getLoginSummaryErrorMessage() {
        waitForElementVisible(LoginPageUI.LOGIN_SUMMARY_ERROR_MESSAGE);
        return getElementText(LoginPageUI.LOGIN_SUMMARY_ERROR_MESSAGE);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(LoginPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(LoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    public boolean isErrorMessageDisplayed(String expectedMessage) {
        waitForElementVisible(LoginPageUI.EMAIL_ERROR_MESSAGE);
        return getEmailErrorMessage().equals(expectedMessage);

    }

    public boolean isMyAccountLinkVisible() {
        waitForElementVisible(LoginPageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(LoginPageUI.MY_ACCOUNT_LINK);
    }


}

