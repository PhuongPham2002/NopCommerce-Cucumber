package actions.pageObject;

import actions.components.Header.HeaderComponent;
import actions.components.ValidationMessageComponent;
import commons.base.BasePage;
import commons.helpers.CommonHelper;
import dataTest.dataObject.RegisterTestData;
import interfaces.pageUI.RegisterPageUI;
import org.openqa.selenium.WebDriver;
import java.util.LinkedHashMap;
import java.util.Map;


public class RegisterPageObject extends BasePage {
    WebDriver driver;
    ValidationMessageComponent validationMessage;
    HeaderComponent header;

    public RegisterPageObject(WebDriver driver) {

        this.driver = driver;
        this.validationMessage = new ValidationMessageComponent(driver);
        this.header = new HeaderComponent(driver);
    }

    public HomePageObject clickRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickElement(driver,RegisterPageUI.REGISTER_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    public String getSuccessfulRegisterMessage() {
        waitForElementVisible(driver, RegisterPageUI.SUCCESSFUL_REGISTER_MESSAGE);
        return getElementText(driver,RegisterPageUI.SUCCESSFUL_REGISTER_MESSAGE);
    }


    public String getErrorMessageForRequireField(String fieldName, String expectedMessage){
        return validationMessage.getErrorMessageForRequiredField(expectedMessage,fieldName);}



    public void fillInRegisterForm(Map<String,String> registerData) {
        waitForElementVisible(driver, RegisterPageUI.REGISTER_TITLE);
        Map<String, String> editableRegisterData = new LinkedHashMap<>(registerData);

        for (Map.Entry<String, String> entry : editableRegisterData.entrySet()) {


            if (entry.getValue() == null || entry.getValue().equals("[empty]")) {
                log.info("Giá trị ban đầu của key [" + entry.getKey() + "] là: " + entry.getValue());
                entry.setValue("");
            }

            if (entry.getValue().equalsIgnoreCase("[random]")) {
                entry.setValue(CommonHelper.generateUniqueEmail());
            }

            enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, entry.getValue(), entry.getKey());
        }
    }

}
