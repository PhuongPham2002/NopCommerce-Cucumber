package actions.pageObject;

import actions.components.Header.HeaderComponent;
import actions.components.ValidationMessageComponent;
import commons.base.BasePage;
import dataTest.dataObject.RegisterTestData;
import interfaces.pageUI.RegisterPageUI;
import io.cucumber.datatable.DataTable;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        //nên có thêm wait URL THAY ĐỔI - CÓ CHỨA REGISTER RESULT
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
        }}
    @Step("Get error message about required field")
    public String getErrorMessageForRequireField(String fieldName){
        return validationMessage.getErrorMessageForRequiredField(fieldName);}

    @Step("Click Logout button to back to HomePage")
    public HomePageObject clickLogoutLink() {
        header.account.clickLogoutLink();
        return PageGenerator.getHomePage(driver);}

    public void clickRegisterLink() {
        header.account.clickRegisterLink();}

    //Cucumber
    public void fillInRegisterForm(DataTable registerForm){
        String emailAddress = "mua" + System.currentTimeMillis()+"_"+ UUID.randomUUID().toString().substring(0,8)+"@gmail.com";
        waitForElementVisible(driver,RegisterPageUI.REGISTER_TITLE);
        Map<String,String> registerData = registerForm.asMap();
        Map<String,String> editableRegisterData = new LinkedHashMap<>(registerData);

        for (Map.Entry<String,String> entry: editableRegisterData.entrySet() ) {
            if (entry.getValue()==null||entry.getValue().equals("[empty]")){
               log.info("Giá trị ban đầu của key [" + entry.getKey() + "] là: " + entry.getValue());
               entry.setValue("");
           }
            if (entry.getValue().equalsIgnoreCase("[random]")){
                entry.setValue(emailAddress);
            }

        }
        log.info("✅ Map sau xử lý: " + editableRegisterData);
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, editableRegisterData.get("FirstName"), "FirstName");
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, editableRegisterData.get("LastName"), "LastName");
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, editableRegisterData.get("Email"), "Email");
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, editableRegisterData.get("Password"), "Password");
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, editableRegisterData.get("Confirm Password"), "ConfirmPassword");
    }

    public void fillInRegisterForm(String firstName, String lastName, String email, String password, String confirmPassword){
        waitForElementVisible(driver,RegisterPageUI.REGISTER_TITLE);
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,firstName, "FirstName");
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,lastName, "LastName");
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,email, "Email");
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,password, "Password");
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,confirmPassword, "ConfirmPassword");

    }


}
