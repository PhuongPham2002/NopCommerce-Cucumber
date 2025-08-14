package actions.components;

import commons.base.BasePage;
import interfaces.componentUI.ValidationMessageComponentUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidationMessageComponent extends BasePage {
    WebDriver driver;

    public ValidationMessageComponent(WebDriver driver) {
        super(driver);
    }
    public String getErrorMessageForRequiredField(String fieldName, String value){
        waitForElementVisible(ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,fieldName);
        WebElement element = driver.findElement(getByLocator(formatLocator(ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,fieldName)));
        waitForDomPropertyTobe(element,value);
       return getDOMPropertyValue(ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,"innerText",fieldName).trim();
    }

}
