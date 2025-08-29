package pageObject.components;

import pageObject.BasePage;
import interfaces.componentUI.ValidationMessageComponentUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidationMessageComponent extends BasePage {
    public ValidationMessageComponent(WebDriver driver) {
        super(driver);
    }
    public String getErrorMessageForRequiredField(String fieldName, String value){
        waitForElementVisible(ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,fieldName);
        WebElement element = getElement(ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,fieldName);
        waitForDomPropertyTobe(element,value);
       return getDOMPropertyValue(ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,"innerText",fieldName).trim();
    }

}
