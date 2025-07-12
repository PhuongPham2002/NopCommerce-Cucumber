package actions.pageObject;

import commons.base.BasePage;
import interfaces.pageUI.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {

        this.driver = driver;
    }


    public HomePageObject clickLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickElement(driver,LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    public void enterLoginForm(String emailAddress, String password){
        if (emailAddress != null){
        enterTextboxByID(driver,LoginPageUI.LOGIN_FORM_TEXTBOX_ID,emailAddress,"Email");
        }
        if (password != null) {
            enterTextboxByID(driver,LoginPageUI.LOGIN_FORM_TEXTBOX_ID,password,"Password");
        }
    }

    public String getLoginSummaryErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.LOGIN_SUMMARY_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.LOGIN_SUMMARY_ERROR_MESSAGE);
    }

    public String getEmailErrorMessage(){
        waitForElementVisible(driver,LoginPageUI.EMAIL_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.EMAIL_ERROR_MESSAGE);
    }

    public boolean isMyAccountLinkVisible(){
        waitForElementVisible(driver,LoginPageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver,LoginPageUI.MY_ACCOUNT_LINK);
    }

}

