package actions.pageObject;

import actions.components.Header.HeaderComponent;
import actions.components.ValidationMessageComponent;
import commons.base.BasePage;
import dataObjects.RegisterTestData;
import interfaces.pageUI.RegisterPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends BasePage {
    WebDriver driver;
    ValidationMessageComponent validationMessage;
    HeaderComponent header;

    public RegisterPageObject(WebDriver driver) {

        this.driver = driver;
        this.validationMessage = new ValidationMessageComponent(driver);
        this.header = new HeaderComponent(driver);
    }

    @Step("Click register button to navigate to Register Page ")
    public HomePageObject clickRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickElement(driver,RegisterPageUI.REGISTER_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    @Step("Check to genderRadio with female option")
    public void checkGenderRadio(String gender) {
        waitForElementVisible(driver,RegisterPageUI.GENDER_RADIO_BUTTON,gender);
      checkCheckboxOrRadio(driver,RegisterPageUI.GENDER_RADIO_BUTTON,gender);
    }


    @Step("Uncheck newsletter checkbox")
    public void checkNewletterCheckbox() {
        uncheckNativeCheckbox(driver,RegisterPageUI.NEWSLETTER_CHECKBOX);
    }


    @Step("Get invalid registered email message")
    public String getInvalidRegisterEmailMessage() {
        waitForElementVisible(driver,RegisterPageUI.INVALID_EMAIL_MESSAGE);
        return getElementText(driver,RegisterPageUI.INVALID_EMAIL_MESSAGE);
    }

    public String getSuccessfulRegisterMessage() {
        waitForElementVisible(driver, RegisterPageUI.SUCCESSFUL_REGISTER_MESSAGE);
        return getElementText(driver,RegisterPageUI.SUCCESSFUL_REGISTER_MESSAGE);
    }

    @Step("Get registered email")
    public String getRegisteredEmailAddress() {
        return this.getDOMPropertyValue(driver,RegisterPageUI.EMAIL_TEXTBOX,"value");
    }

    @Step("Get existed email message")
    public String getExistedEmailMessage() {
        waitForElementVisible(driver,RegisterPageUI.EXISTED_EMAIL_MESSAGE);
        return getElementText(driver,RegisterPageUI.EXISTED_EMAIL_MESSAGE);
    }
    @Step("Fill in register form")
    public void fillRegisterForm(RegisterTestData registerData) {

        if (registerData.getGender()!=null){
            checkGenderRadio(registerData.getGender());
        }
        if (registerData.getFirstName()!=null){
            enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,registerData.getFirstName(), "FirstName");
        }
        if (registerData.getLastName()!=null){
            enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,registerData.getLastName(),"LastName");
        }
        if (registerData.getEmailAddress()!=null){
            enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,registerData.getEmailAddress(),"Email");
        }
        if (registerData.getCompanyName()!=null){
            enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,registerData.getCompanyName(),"Company");
        }
        checkNewletterCheckbox();
        if (registerData.getPassword()!=null){
            enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,registerData.getPassword(),"Password");
        }
        if (registerData.getConfirmPassword()!=null){
            enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,registerData.getConfirmPassword(),"ConfirmPassword");
        }

    }



    @Step("Get error message about required field")
    public String getErrorMessageForRequireField(String fieldName){
        return validationMessage.getErrorMessageForRequiredField(fieldName);
    }

    @Step("Click Logout button to back to HomePage")
    public HomePageObject clickLogoutLink() {
        header.account.clickLogoutLink();
        return PageGenerator.getHomePage(driver);
    }

    public void clickRegisterLink() {
        header.account.clickRegisterLink();
    }
}
