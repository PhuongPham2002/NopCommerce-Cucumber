package actions.pageObject;

import commons.base.BasePage;
import commons.constants.LoginMessageConstants;
import interfaces.pageUI.LoginPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click login button")
    public HomePageObject clickLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickElement(driver,LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    @Step("Enter login form with email & password")
    public void enterLoginForm(String emailAddress, String password){
        enterTextboxByID(driver,LoginPageUI.LOGIN_FORM_TEXTBOX_ID,emailAddress,"Email");
        enterTextboxByID(driver,LoginPageUI.LOGIN_FORM_TEXTBOX_ID,password,"Password");
    }
    @Step("Get error login message")
    public String getErrorLoginMessage(){
        waitForElementVisible(driver,LoginPageUI.LOGIN_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.LOGIN_ERROR_MESSAGE);
    }

    @Step("Get unregistered error message")
    public String getUnregisteredErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.UNREGISTERED_EMAIL_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.UNREGISTERED_EMAIL_ERROR_MESSAGE);
    }

    public String getEmptyPasswordErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
    }

    public String getInvalidPasswordErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
    }

    public String getWrongPasswordErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.WRONG_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.WRONG_PASSWORD_ERROR_MESSAGE);
    }

    //Cucumber implement:

    public String getErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.ERROR_MESSAGE);
    }


//    public void verifyErrorMessages(String expectedMessage, String position){
//        switch (position){
//            case "email":
//                Assert.assertEquals(getErrorLoginMessage(),expectedMessage);
//                break;
//            case "summary":
//                Assert.assertEquals(getErrorMessage(),expectedMessage);
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid position of message: "+position);
//        }
//
//    }

    public void verifyErrorMessages(String expectedMessage){
        if (expectedMessage.equals(LoginMessageConstants.EMPTY_EMAIL_PASSWORD_MESSAGE) || expectedMessage.equals(LoginMessageConstants.INVALID_EMAIL_MESSAGE)){
            Assert.assertEquals(getErrorLoginMessage(),expectedMessage);
        }
        else if (expectedMessage.equals(LoginMessageConstants.NON_REGISTER_EMAIL_MESSAGE) || expectedMessage.equals(LoginMessageConstants.INVALID_PASSWORD_MESSAGE)){
            Assert.assertEquals(getErrorMessage(),expectedMessage);
        } else {
            throw new RuntimeException("Invalid expected message" + expectedMessage);
        }

    }


    public boolean isMyAccountLinkVisible(){
        waitForElementVisible(driver,LoginPageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver,LoginPageUI.MY_ACCOUNT_LINK);
    }


}

