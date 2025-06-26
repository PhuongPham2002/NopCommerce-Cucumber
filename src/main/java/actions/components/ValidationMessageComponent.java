package actions.components;

import commons.base.BasePage;
import commons.helpers.WaitHelper;
import interfaces.componentUI.ValidationMessageComponentUI;
import org.openqa.selenium.WebDriver;

public class ValidationMessageComponent extends BasePage {
    WebDriver driver;

    public ValidationMessageComponent(WebDriver driver) {
        this.driver = driver;
    }

    public String getErrorMessageForRequiredField(String fieldName){
        WaitHelper.waitForTextVisible(driver, ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,fieldName);
       return getDOMPropertyValue(driver,ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,"innerText",fieldName).trim();
    }

}
