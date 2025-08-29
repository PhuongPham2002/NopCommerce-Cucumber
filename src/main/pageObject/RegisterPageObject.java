package pageObject;

import pageObject.components.Header.HeaderAccountComponent;
import pageObject.components.Header.HeaderComponent;
import pageObject.components.ValidationMessageComponent;
import commons.helpers.CommonHelper;
import interfaces.pageUI.RegisterPageUI;
import org.openqa.selenium.WebDriver;

import java.util.LinkedHashMap;
import java.util.Map;


public class RegisterPageObject extends BasePage {
    ValidationMessageComponent validationMessage;
    HeaderComponent header;
    HeaderAccountComponent headerAccount;

    public RegisterPageObject(WebDriver driver) {
        super(driver);
        this.validationMessage = new ValidationMessageComponent(driver);
        this.header = new HeaderComponent(driver);
        this.headerAccount = new HeaderAccountComponent(driver);
    }

    public HomePageObject clickRegisterButton() {
        waitForElementClickable(RegisterPageUI.REGISTER_BUTTON);
        clickElement(RegisterPageUI.REGISTER_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    public String getSuccessfulRegisterMessage() {
        waitForElementVisible(RegisterPageUI.SUCCESSFUL_REGISTER_MESSAGE);
        return getElementText(RegisterPageUI.SUCCESSFUL_REGISTER_MESSAGE);
    }


    public String getErrorMessageForRequireField(String fieldName, String expectedMessage){
        return validationMessage.getErrorMessageForRequiredField(expectedMessage,fieldName);}



    public void fillInRegisterForm(Map<String,String> registerData) {
        waitForElementVisible(RegisterPageUI.REGISTER_TITLE);
        Map<String, String> editableRegisterData = new LinkedHashMap<>(registerData);

        for (Map.Entry<String, String> entry : editableRegisterData.entrySet()) {


            if (entry.getValue() == null || entry.getValue().equals("[empty]")) {
                log.info("Giá trị ban đầu của key [" + entry.getKey() + "] là: " + entry.getValue());
                entry.setValue("");
            }

            if (entry.getValue().equalsIgnoreCase("[random]")) {
                entry.setValue(CommonHelper.generateUniqueEmail());
            }

            enterTextboxByID(RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, entry.getValue(), entry.getKey());
        }
    }

}
