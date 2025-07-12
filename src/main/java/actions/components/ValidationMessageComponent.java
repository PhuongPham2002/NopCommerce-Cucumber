package actions.components;

import commons.base.BasePage;
import commons.helpers.LocatorHelper;
import commons.helpers.WaitHelper;
import interfaces.componentUI.ValidationMessageComponentUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidationMessageComponent extends BasePage {
    WebDriver driver;

    public ValidationMessageComponent(WebDriver driver) {
        this.driver = driver;
    }

    public String getErrorMessageForRequiredField(String fieldName, String value){
        waitForElementVisible(driver,ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,fieldName);
        WebElement element = driver.findElement(LocatorHelper.getByLocator(formatLocator(ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,fieldName)));
        WaitHelper.waitForDomPropertyTobe(driver,element,value);
       return getDOMPropertyValue(driver,ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,"innerText",fieldName).trim();
    }

}
