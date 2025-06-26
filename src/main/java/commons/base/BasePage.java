package commons.base;

import commons.constants.GlobalConstants;
import commons.helpers.*;
import interfaces.pageUI.BasePageUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class BasePage {
    protected final Logger log = LogManager.getLogger(getClass());

    public String formatLocator(String dynamicLocatorTemplate, String... dynamicParts ){
        return LocatorHelper.formatLocator(dynamicLocatorTemplate, dynamicParts);
    }

    public By getByLocator(String rawLocator) {
        return LocatorHelper.getByLocator(rawLocator);

    }
    public WebElement getElement (WebDriver driver, String rawLocator) {
        return ElementHelper.getElement(driver,rawLocator);
    }

    public WebElement getElement (WebDriver driver, String dynamicLocatorTemplate,String...dynamicParts) {
        return ElementHelper.getElement(driver, dynamicLocatorTemplate,dynamicParts);
    }

    public List<WebElement> getListElement (WebDriver driver, String rawLocator){
        return ElementHelper.getListElement(driver,rawLocator);
    }

    public List<WebElement> getListElement (WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        return ElementHelper.getListElement(driver,dynamicLocatorTemplate,dynamicParts);
    }
    public void clickElementWithLogging(WebDriver driver, String locatorForLog){
        ElementHelper.clickElementWithLogging(driver,locatorForLog);

    }
    public void clickElement(WebDriver driver, String rawLocator){
        ElementHelper.clickElement(driver,rawLocator);
    }
    public void clickElement(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        String locator = formatLocator(dynamicLocatorTemplate,dynamicParts);
        ElementHelper.clickElementWithLogging(driver,locator);
    }
    public void sendKeyToElement(WebDriver driver, String rawLocator, String valueToSend){
        ElementHelper.sendKeyToElement(driver,rawLocator,valueToSend);
    }
    public void sendKeyToElement(WebDriver driver,String dynamicLocatorTemplate,String valueToSend,String...dynamicParts){
        ElementHelper.sendKeyToElement(driver, dynamicLocatorTemplate,valueToSend,dynamicParts);
    }
    public void clearKeyInElement(WebDriver driver,String rawLocator){
        ElementHelper.clearKeyInElement(driver,rawLocator);
    }

    public void clearKeyInElement(WebDriver driver,String dynamicLocatorTemplate,String...dynamicParts){
        ElementHelper.clearKeyInElement(driver,dynamicLocatorTemplate,dynamicParts);
    }

    public String getElementText(WebDriver driver, String rawLocator){
        return ElementHelper.getElement(driver,rawLocator).getText();
    }
    public String getElementText(WebDriver driver, String dynamicLocatorTemplate,String... dynamicParts){
        return ElementHelper.getElementText(driver,dynamicLocatorTemplate,dynamicParts);
    }
    public List<String> getListElementText(WebDriver driver,String rawLocator){
        return ElementHelper.getListElementText(driver,rawLocator);
    }

    public String getDOMPropertyValue(WebDriver driver, String rawLocator, String attributeName){
        return ElementHelper.getDOMPropertyValue(driver,rawLocator,attributeName);
    }

    public String getDOMPropertyValue(WebDriver driver, String dynamicLocatorTemplate, String attributeName, String... dynamicParts){
        return ElementHelper.getDOMPropertyValue(driver,dynamicLocatorTemplate,attributeName,dynamicParts);
    }

    public String getAttributeValue(WebDriver driver, String rawLocator, String attributeName){
        return ElementHelper.getAttributeValue(driver,rawLocator,attributeName);
    }
    public String getAttributeValue(WebDriver driver, String templateLocator, String attributeName, String...dynamicParts){
        return ElementHelper.getAttributeValue(driver,templateLocator,attributeName,dynamicParts);
    }


    public int getListElementsSize(WebDriver driver, String rawLocator){
        return ElementHelper.getListElementsSize(driver,rawLocator);
    }
    public int getListElementsSize(WebDriver driver, String dynamicLocatorTemplate,String... dynamicParts){
        return ElementHelper.getListElementsSize(driver,dynamicLocatorTemplate,dynamicParts);
    }

    public void selectDropdownOption(WebDriver driver, String rawLocator, String option){
            ElementHelper.selectDropdownOption(driver,rawLocator,option);

    }
    public static void waitForUrlContains(WebDriver driver,String valueToContain){
        WaitHelper.waitForUrlContains(driver,valueToContain);
    }
    public void selectDropdownOption(WebDriver driver,String option, String dynamicLocatorTemplate,String... dynamicParts){
       ElementHelper.selectDropdownOption(driver,option,dynamicLocatorTemplate,dynamicParts);
    }

    //public void selectMultipleDropdownOptions (WebDriver driver, String locator, String option)  --> khi làm làm đến dynamic locator thì mình apply
    public void deselectDropdownOption(WebDriver driver, String rawLocator,String option){
        ElementHelper.deselectDropdownOption(driver,rawLocator,option);
    }
    public void deselectDropdownOption(WebDriver driver,String option, String dynamicLocatorTemplate,String... dynamicParts){
        ElementHelper.deselectDropdownOption(driver,option,dynamicLocatorTemplate,dynamicParts);
    }

    public String getSelectedDropdownOption(WebDriver driver, String rawLocator){
        return ElementHelper.getSelectedDropdownOption(driver,rawLocator);
    }

    public String getSelectedDropdownOption(WebDriver driver, String dynamicLocatorTemplate,String... dynamicParts){
        return ElementHelper.getSelectedDropdownOption(driver,dynamicLocatorTemplate,dynamicParts);
    }
    public List<String> getAllSelectedDropdownOptions(WebDriver driver, String locator){
      return ElementHelper.getAllSelectedDropdownOptions(driver,locator);
    }
    public List<String> getOptions(WebDriver driver, String locator){
        return ElementHelper.getOptions(driver,locator);
    }
    public boolean isDropdownMultiple(WebDriver driver, String locator){
        return ElementHelper.isDropdownMultiple(driver,locator);
    }
    public void selectCustomizedDropdownOptions (WebDriver driver, String DropdownIconLocator, String optionsLocator, String option){
      ElementHelper.selectCustomizedDropdownOptions(driver,DropdownIconLocator,optionsLocator,option);
    }

    public void checkNativeRadio(WebDriver driver, String rawLocator) {
       ElementHelper.checkNativeRadio(driver,rawLocator);
    }

    public void checkNativeRadio(WebDriver driver, String templateLocator, String...dynamicParts) {
        ElementHelper.checkNativeRadio(driver,templateLocator,dynamicParts);
    }

    public void uncheckNativeRadio(WebDriver driver, String templateLocator, String...dynamicParts) {
        ElementHelper.uncheckNativeRadio(driver,templateLocator,dynamicParts);
    }



    public void checkNativeCheckbox(WebDriver driver, String rawLocator) {
        ElementHelper.checkNativeCheckbox(driver,rawLocator);
    }

    public void checkNativeCheckbox(WebDriver driver, String templateLocator, String...dynamicParts) {
       ElementHelper.checkNativeCheckbox(driver,templateLocator,dynamicParts);
    }

    public void checkCustomCheckbox (WebDriver driver, String rawLocator,String attributeName,String expectedAttributeValue){
      ElementHelper.checkCustomCheckbox(driver,rawLocator,attributeName,expectedAttributeValue);
    }

    public void checkCustomRadio (WebDriver driver, String rawLocator,String attributeName,String expectedAttributeValue){
      ElementHelper.checkCustomRadio(driver,rawLocator,attributeName,expectedAttributeValue);
    }
    public void checkCustomRadio (WebDriver driver, String rawLocator,String attributeName,String expectedAttributeValue,String...dynamicPart){
        ElementHelper.checkCustomRadio(driver,rawLocator,attributeName,expectedAttributeValue,dynamicPart);
    }

    public void checkAllNativeCheckboxes(WebDriver driver, String rawLocator){
      ElementHelper.checkAllNativeCheckboxes(driver,rawLocator);
    }

    public void checkAllCustomCheckboxes(WebDriver driver, String rawLocator,String attributeName,String expectedAttributeValue){
       ElementHelper.checkAllCustomCheckboxes(driver,rawLocator,attributeName,expectedAttributeValue);
    }


    public void checkCheckboxOrRadio(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts) {
     ElementHelper.checkCheckboxOrRadio(driver,dynamicLocatorTemplate,dynamicParts);
    }



    public void uncheckNativeCheckbox(WebDriver driver, String rawLocator) {
       ElementHelper.uncheckNativeCheckbox(driver,rawLocator);
    }

    public void uncheckCustomCheckbox(WebDriver driver, String rawLocator) {
        ElementHelper.uncheckCustomCheckbox(driver,rawLocator);
    }

    public boolean isElementDisplayed(WebDriver driver, String locator){
        return ElementHelper.isElementDisplayed(driver,locator);
    }
    public boolean isElementEnable(WebDriver driver, String locator){
        return ElementHelper.isElementEnable(driver,locator);
    }
    public boolean isElementSelected(WebDriver driver, String locator){
        return ElementHelper.isElementSelected(driver,locator);
    }

    public void waitForElementVisible(WebDriver driver, String rawLocator){
        WaitHelper.waitForElementVisible(driver,rawLocator);
    }
    public void waitForElementVisible(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        WaitHelper.waitForElementVisible(driver,dynamicLocatorTemplate,dynamicParts);
    }
    public void waitForListElementsVisible(WebDriver driver, String rawLocator){
        WaitHelper.waitForElementVisible(driver,rawLocator);}
    public void waitForListElementsVisible(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        WaitHelper.waitForListElementsVisible(driver,dynamicLocatorTemplate,dynamicParts);}

    public void waitForElementInvisible (WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts) {
        WaitHelper.waitForElementInvisible(driver,dynamicLocatorTemplate,dynamicParts);}

    public static void waitForElementInvisible (WebDriver driver, String rawLocator){
        WaitHelper.waitForElementInvisible(driver,rawLocator);
    }

    public void waitForListElementInvisible (WebDriver driver, String rawLocator){
        WaitHelper.waitForListElementInvisible(driver,rawLocator);}

    public void waitForElementPresence (WebDriver driver, String rawlocator) {
        WaitHelper.waitForElementPresence(driver,rawlocator);}

    public void waitForElementPresence (WebDriver driver, String templateLocator, String...dynamicParts) {

        WaitHelper.waitForElementPresence(driver,templateLocator,dynamicParts);
    }
    public void waitForListElementsPresence (WebDriver driver, String locator) {
        WaitHelper.waitForListElementsPresence(driver,locator);}

    public void waitForElementSelected (WebDriver driver, String rawLocator){
        WaitHelper.waitForElementSelected(driver,rawLocator);
        }
    public void waitForElementSelected (WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts) {
        WaitHelper.waitForElementSelected(driver,dynamicLocatorTemplate,dynamicParts);
    }
    public void waitForElementClickable(WebDriver driver, String rawLocator){
        WaitHelper.waitForElementClickable(driver,rawLocator);
    }
    public void waitForElementClickable (WebDriver driver, WebElement element){
        WaitHelper.waitForElementClickable(driver,element);
    }
    public void waitForElementClickable(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        WaitHelper.waitForElementClickable(driver,dynamicLocatorTemplate,dynamicParts);}

    public void waitForTextToBePresentInElement(WebDriver driver,String rawLocator,String text){
        WaitHelper.waitForTextToBePresentInElement(driver,rawLocator,text);}

    public void waitForTextToBePresentInElement(WebDriver driver, WebElement element,String text){
        WaitHelper.waitForTextToBePresentInElement(driver,element,text);
    }

    public void waitForTextToBePresentInElement(WebDriver driver,String dynamicLocatorTemplate,String text,String...dynamicParts){
        WaitHelper.waitForTextToBePresentInElement(driver,dynamicLocatorTemplate,text,dynamicParts);
    }
    public void waitForAttributeToBe(WebDriver driver,String rawLocator,String attributeName, String value){
        WaitHelper.waitForAttributeToBe(driver,rawLocator,attributeName,value);
    }

    public void enterTextboxByID(WebDriver driver,String dynamicLocatorTemplate,String valueToSend,String idTextboxValue){
       waitForElementVisible(driver,dynamicLocatorTemplate,idTextboxValue);
       sendKeyToElement(driver,dynamicLocatorTemplate,valueToSend,idTextboxValue);}


    public String getAttributeValueByID(WebDriver driver,String dynamicLocatorTemplate,String attributeValue,String idTextboxValue){
        waitForElementVisible(driver,dynamicLocatorTemplate,idTextboxValue);
        return getDOMPropertyValue(driver,dynamicLocatorTemplate,attributeValue,idTextboxValue);}


    public void hoverToElementWithLog(WebDriver driver,String locatorForLog){
    ActionHelper.hoverToElementWithLog(driver,locatorForLog);
    }

    public void hoverToElement(WebDriver driver,String rawLocator){
        hoverToElementWithLog(driver,rawLocator);
    }
    public void hoverToElement(WebDriver driver,String templateLocator,String...dynamicParts){
        String locatorForLog = formatLocator(templateLocator,dynamicParts);
       hoverToElementWithLog(driver,locatorForLog);
    }

    //Bỏ
    public void waitForLoadingScreenInvisible(WebDriver driver){
        WaitHelper.waitForLoadingScreenInvisible(driver);
    }

    //BỎ - cân nhắc
    public void waitForLoadingIconInvisible(WebDriver driver){
        WaitHelper.waitForLoadingIconInvisible(driver);
    }

    //BỎ - cân nhắc
    public void waitForNumberOfElementsTobe(WebDriver driver, String rawLocator, int number){
        WaitHelper.waitForNumberOfElementsTobe(driver,rawLocator,number);
    }





}
