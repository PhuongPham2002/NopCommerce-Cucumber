package commons.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ElementHelper {

    protected static final Logger log = LogManager.getLogger(ElementHelper.class);


    public static WebElement getElement (WebDriver driver, String rawLocator) {
        return driver.findElement(LocatorHelper.getByLocator(rawLocator));
    }

    public static WebElement getElement (WebDriver driver, String dynamicLocatorTemplate,String...dynamicParts) {
        return driver.findElement(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)));
    }

    public static List<WebElement> getListElement (WebDriver driver, String rawLocator){
        return driver.findElements(LocatorHelper.getByLocator(rawLocator));
    }
    public static List<WebElement> getListElement (WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        return driver.findElements(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)));
    }

    public static void clickElementWithLogging(WebDriver driver, String locatorForLog){
        try{
            log.info("Attempting to click element with locator: "+locatorForLog);
            getElement(driver,locatorForLog).click();
            log.info("Element clicked successfully.");
        } catch (NoSuchElementException e){
            log.error("Element not found: "+ locatorForLog);
            throw new RuntimeException("Element not found at locator : "+locatorForLog,e);
        } catch (ElementClickInterceptedException e) {
            log.error("Element is being obscured or blocked :"+ locatorForLog);
            throw new RuntimeException("Unable to click element because other element obscured it");
        } catch (ElementNotInteractableException e){
            log.error("Element is not interactable (possibly hidden or disabled: "+ locatorForLog);
            throw new RuntimeException("Element is not interactable: "+ locatorForLog,e);
        } catch (StaleElementReferenceException e){
            log.error("Element became stale before clicking");
            throw new RuntimeException("Stale element during click: "+ locatorForLog,e);
        } catch (Exception e){
            log.error("Unexpected error occurred while trying to click: " + locatorForLog);
            log.error("Exception Message: "+e.getMessage());
            throw new RuntimeException("Unexpected error during click: "+locatorForLog,e);
        }

    }
    public static void clickElement(WebDriver driver, String rawLocator){

        clickElementWithLogging(driver,rawLocator);
    }
    public static void clickElement(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        String locator = LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts);
        clickElementWithLogging(driver,locator);
    }
    public static void sendKeyToElement(WebDriver driver, String rawLocator, String valueToSend){
        clearKeyInElement(driver,rawLocator);
        getElement(driver,rawLocator).sendKeys(valueToSend);
        getElement(driver,rawLocator).sendKeys(Keys.TAB);
    }
    public static void sendKeyToElement(WebDriver driver,String dynamicLocatorTemplate,String valueToSend,String...dynamicParts){
        clearKeyInElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts));
        getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).sendKeys(valueToSend);
        getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).sendKeys(Keys.TAB);

    }

    public static void clearKeyInElement(WebDriver driver, String rawLocator){
        getElement(driver,rawLocator).sendKeys(Keys.CONTROL+"a");
        getElement(driver,rawLocator).sendKeys(Keys.DELETE);
    }

    public static void clearKeyInElement(WebDriver driver,String dynamicLocatorTemplate,String...dynamicParts){
        getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).sendKeys(Keys.CONTROL+"a");
        getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).sendKeys(Keys.DELETE);
    }

    public static String getElementText(WebDriver driver, String rawLocator){
        return getElement(driver,rawLocator).getText();
    }
    public static String getElementText(WebDriver driver, String dynamicLocatorTemplate,String... dynamicParts){
        return getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).getText();
    }
    public static List<String> getListElementText(WebDriver driver,String rawLocator){
        List<WebElement> listElements = getListElement(driver, rawLocator);
        List<String> listElementsText = new ArrayList<>();
        for (WebElement element:listElements){
            listElementsText.add(element.getText().trim());
        }
        return listElementsText;
    }

    public static String getDOMPropertyValue(WebDriver driver, String rawLocator, String attributeName){
        return getElement(driver,rawLocator).getDomProperty(attributeName);
    }

    public static String getDOMPropertyValue(WebDriver driver, String dynamicLocatorTemplate, String attributeName, String... dynamicParts){
        return getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).getDomProperty(attributeName);
    }

    public static String getAttributeValue(WebDriver driver, String rawLocator, String attributeName){
        return getElement(driver,rawLocator).getDomAttribute(attributeName);
    }
    public static String getAttributeValue(WebDriver driver, String templateLocator, String attributeName, String...dynamicParts){
        return getElement(driver,LocatorHelper.formatLocator(templateLocator,dynamicParts)).getDomAttribute(attributeName);
    }

    public static int getListElementsSize(WebDriver driver, String rawLocator){
        return getListElement(driver,rawLocator).size();
    }
    public static int getListElementsSize(WebDriver driver, String dynamicLocatorTemplate,String... dynamicParts){
        return getListElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).size();
    }

    public static void selectDropdownOption(WebDriver driver, String rawLocator, String option){
            new Select(getElement(driver, rawLocator)).selectByVisibleText(option);
    }


    public static void selectDropdownOption(WebDriver driver,String option, String dynamicLocatorTemplate,String... dynamicParts){
        new Select(getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts))).selectByVisibleText(option);
    }

    //public static void selectMultipleDropdownOptions (WebDriver driver, String locator, String option)  --> khi làm làm đến dynamic locator thì mình apply
    public static void deselectDropdownOption(WebDriver driver, String rawLocator,String option){
        new Select(getElement(driver,rawLocator)).deselectByVisibleText(option);
    }
    public static void deselectDropdownOption(WebDriver driver,String option, String dynamicLocatorTemplate,String... dynamicParts){
        new Select(getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts))).deselectByVisibleText(option);
    }

    public static String getSelectedDropdownOption(WebDriver driver, String rawLocator){
        return new Select(getElement(driver,rawLocator)).getFirstSelectedOption().getText();
    }

    public static String getSelectedDropdownOption(WebDriver driver, String dynamicLocatorTemplate,String... dynamicParts){
        return new Select(getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts))).getFirstSelectedOption().getText();
    }
    public static List<String> getAllSelectedDropdownOptions(WebDriver driver, String locator){
        List <WebElement> allSelectedOptions =  new Select(getElement(driver,locator)).getAllSelectedOptions();
        List <String> selectedOptions = new ArrayList<String>();
        for (WebElement option: allSelectedOptions){
            selectedOptions.add(option.getText());
        }
        return selectedOptions;
    }
    public static List<String> getOptions(WebDriver driver, String locator){
        List<WebElement> allDropdownOptions = new Select(getElement(driver,locator)).getOptions();
        List<String> dropdownOptions = new ArrayList<String>();
        for (WebElement option: allDropdownOptions){
            dropdownOptions.add(option.getText());
        }
        return dropdownOptions;
    }
    public static boolean isDropdownMultiple(WebDriver driver, String locator){
        return new Select(getElement(driver,locator)).isMultiple();
    }
    public static void selectCustomizedDropdownOptions (WebDriver driver, String DropdownIconLocator, String optionsLocator, String option){
        //CLick vào drop down icon
        clickElement(driver,DropdownIconLocator);
        //Lấy về list các options
        List<WebElement> allDropdownOptions = getListElement(driver,optionsLocator);
        for (WebElement dropdownOption: allDropdownOptions){
            if (dropdownOption.getText().trim().equals(option)){
                dropdownOption.click();
                break;
            }
        }
    }


    public static void checkNativeRadio(WebDriver driver, String rawLocator) {
        if (!getElement(driver,rawLocator).isSelected()){
            getElement(driver,rawLocator).click();
            log.info("Radio Selected (native): "+ rawLocator);}
        else {
            log.info("Radio already selected (native), skip click: " + rawLocator);
        }
    }

    public static void checkNativeRadio(WebDriver driver, String templateLocator, String...dynamicParts) {
        if (!getElement(driver,templateLocator,dynamicParts).isSelected()){
            getElement(driver,templateLocator,dynamicParts).click();
            log.info("Radio Selected (native): "+ LocatorHelper.formatLocator(templateLocator,dynamicParts));}
        else {
            log.info("Radio already selected (native), skip click: " + LocatorHelper.formatLocator(templateLocator,dynamicParts));
        }
    }

    public static void uncheckNativeRadio(WebDriver driver, String rawLocator) {
        if (getElement(driver,rawLocator).isSelected()){
            getElement(driver,rawLocator).click();
            log.info("Radio unselected (native): "+ rawLocator);}
        else {
            log.info("Radio already unselected (native), skip click: " + rawLocator);
        }
    }

    public static void uncheckNativeRadio(WebDriver driver, String templateLocator, String...dynamicParts) {
        if (getElement(driver,LocatorHelper.formatLocator(templateLocator,dynamicParts)).isSelected()){
            getElement(driver,LocatorHelper.formatLocator(templateLocator,dynamicParts)).click();
            log.info("Radio unselected (native): "+ LocatorHelper.formatLocator(templateLocator,dynamicParts));}
        else {
            log.info("Radio already unselected (native), skip click: " + LocatorHelper.formatLocator(templateLocator,dynamicParts));
        }
    }




    public static void checkNativeCheckbox(WebDriver driver, String rawLocator) {
        if (!getElement(driver,rawLocator).isSelected()){
            getElement(driver,rawLocator).click();
            log.info("Checkbox Selected (native): "+ rawLocator);}
        else {
            log.info("Checkbox already selected (native), skip click: " + rawLocator);
        }
    }

    public static void checkNativeCheckbox(WebDriver driver, String templateLocator, String...dynamicParts) {
        if (!getElement(driver,templateLocator,dynamicParts).isSelected()){
            getElement(driver,templateLocator,dynamicParts).click();
            log.info("Checkbox Selected (native): "+ LocatorHelper.formatLocator(templateLocator,dynamicParts));}
        else {
            log.info("Checkbox already selected (native), skip click: " + LocatorHelper.formatLocator(templateLocator,dynamicParts));
        }
    }

    public static void checkCustomCheckbox (WebDriver driver, String rawLocator,String attributeName,String expectedAttributeValue){
        String attributeValue= JavaScriptHelper.getDOMPropertyValue(driver,rawLocator,attributeName);
        if (attributeValue==null || !attributeValue.contains(expectedAttributeValue)){
            getElement(driver,rawLocator).click();
            log.info("Checkbox Selected (custom): "+ rawLocator);
        }
        else {
            log.info("Checkbox  already selected (custom), skip click: " + rawLocator);
        }
    }

    public static void checkCustomRadio (WebDriver driver, String rawLocator,String attributeName,String expectedAttributeValue){
        String attributeValue= JavaScriptHelper.getDOMPropertyValue(driver,rawLocator,attributeName);
        if (attributeValue==null || !attributeValue.contains(expectedAttributeValue)){
            getElement(driver,rawLocator).click();
            log.info("Radio Selected (custom): "+ rawLocator);
        }
        else {
            log.info("Radio already selected (custom), skip click: " + rawLocator);
        }
    }

    public static void checkCustomRadio (WebDriver driver, String templateDynamicLocator,String attributeName,String expectedAttributeValue,String...dynamicParts){
        String attributeValue= JavaScriptHelper.getDOMPropertyValue(driver,LocatorHelper.formatLocator(templateDynamicLocator,dynamicParts),attributeName);
        if (attributeValue==null || !attributeValue.contains(expectedAttributeValue)){
            getElement(driver,LocatorHelper.formatLocator(templateDynamicLocator,dynamicParts)).click();
            log.info("Radio Selected (custom): "+ LocatorHelper.formatLocator(templateDynamicLocator,dynamicParts));
        }
        else {
            log.info("Radio already selected (custom), skip click: " + LocatorHelper.formatLocator(templateDynamicLocator,dynamicParts));
        }
    }



    public static void checkAllNativeCheckboxes(WebDriver driver, String rawLocator){
        int numberOfCheckboxes = ElementHelper.getListElementsSize(driver, rawLocator);
        for (int i=0;i<numberOfCheckboxes;i++) {
            List<WebElement> checkboxes = getListElement(driver,rawLocator);
            WebElement checkbox = checkboxes.get(i);
            if (!checkbox.isSelected()){
                checkbox.click();
                log.info("Checkbox [" +i+"] selected (native): "+rawLocator);
            } else {
                log.info("Checkbox [" +i+"] already selected (native), skip click: " + rawLocator);
            }
        }

    }

    public static void checkAllCustomCheckboxes(WebDriver driver, String rawLocator,String attributeName,String expectedAttributeValue){
        int numberOfCheckboxes = getListElementsSize(driver, rawLocator);
        for (int i=0;i<numberOfCheckboxes;i++) {
            List<WebElement> checkboxes = getListElement(driver,rawLocator);
            WebElement checkbox = checkboxes.get(i);
            String attributeValue = checkbox.getDomProperty(attributeName);
            if (attributeValue==null || !attributeValue.contains(expectedAttributeValue)){
                checkbox.click();
                log.info("Checkbox [" +i+"] selected (custom): "+rawLocator);
            } else {
                log.info("Checkbox [" +i+"] already selected (custom), skip click: " + rawLocator);
            }
        }

    }


    public static void checkCheckboxOrRadio(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts) {
        if (!getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).isSelected()){
            getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).click();}
    }


    public static void uncheckNativeCheckbox(WebDriver driver, String rawLocator) {
        if (getElement(driver,rawLocator).isSelected()){
            getElement(driver,rawLocator).click();
            log.info("Unchecked Checkbox(native) successfully: "+rawLocator);
        } else {
            log.info("Checkbox(native) is already unchecked: "+ rawLocator);
        }
    }


    public static void uncheckCustomCheckbox(WebDriver driver, String rawLocator) {


    }


    public static boolean isElementDisplayed(WebDriver driver, String locator){
        return getElement(driver,locator).isDisplayed();
    }
    public static boolean isElementEnable(WebDriver driver, String locator){
        return getElement(driver,locator).isEnabled();
    }
    public static boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver,locator).isSelected();
    }


}
