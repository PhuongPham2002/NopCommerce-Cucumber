package commons.base;

import commons.constants.GlobalConstants;
import commons.helpers.*;
import interfaces.helperUI.WaitHelperUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class BasePage {
    protected WebDriver driver;
    protected final Logger log = LogManager.getLogger(getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String formatLocator(String dynamicLocatorTemplate, String... dynamicParts) {
        return String.format(dynamicLocatorTemplate, (Object[]) dynamicParts);
    }

    public By getByLocator(String rawLocator) {
        if (!rawLocator.contains("=")) {
            throw new IllegalArgumentException("Invalid locator format (missing '=' character): " + rawLocator);
        }
        if (rawLocator.toLowerCase().startsWith("xpath")) {
            return By.xpath(rawLocator.substring(6));
        } else if (rawLocator.toLowerCase().startsWith("css")) {
            return By.cssSelector(rawLocator.substring(4));
        } else if (rawLocator.toLowerCase().startsWith("id")) {
            return By.id(rawLocator.substring(3));
        } else if (rawLocator.toLowerCase().startsWith("name")) {
            return By.name(rawLocator.substring(5));
        } else if (rawLocator.toLowerCase().startsWith("tagname")) {
            return By.tagName(rawLocator.substring(8));
        } else if (rawLocator.toLowerCase().startsWith("linktext")) {  //bắt chính xác text của thẻ a
            return By.linkText(rawLocator.substring(9));
        } else if (rawLocator.toLowerCase().startsWith("partiallinktext")) {
            return By.linkText(rawLocator.substring(16));
        }
        throw new IllegalArgumentException("Unsupported locator type:  " + rawLocator);
    }

    //Elements:
    public WebElement getElement(String rawLocator) {
        log.info("Attempting to find element with locator: {}",rawLocator);
        WebElement element = driver.findElement(getByLocator(rawLocator));
        log.info("Element found successfully");
        return element ;
    }

    public WebElement getElement(String dynamicLocatorTemplate, String... dynamicParts) {
        log.info("Attempting to find element with locator: " + formatLocator(dynamicLocatorTemplate,dynamicParts));
        WebElement element = driver.findElement(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts)));
        log.info("Element found successfully");
        return element;
    }

    public List<WebElement> getListElement(String rawLocator) {
        log.info("Attempting to find list of elements with locator: " + rawLocator);
        List<WebElement> listElements = driver.findElements(getByLocator(rawLocator));
        log.info("List of elements found successfully");
        return listElements;
    }

    public List<WebElement> getListElement(String dynamicLocatorTemplate, String... dynamicParts) {
        log.info("Attempting to find list of elements with locator: " + formatLocator(dynamicLocatorTemplate,dynamicParts));
        List<WebElement> listElements = driver.findElements(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts)));
        log.info("List of elements found successfully");
        return listElements;
    }

    public void waitForExpectedConditionMet(Function<WebDriver, Boolean> innerText) {

    }

    //Click:

    public void clickElement(String locatorForLog) {
        log.info("Attempting to click element with locator: " + locatorForLog);
        getElement(locatorForLog).click();
        log.info("Element clicked successfully.");
    }



    public void clickElement(String dynamicLocatorTemplate, String... dynamicParts) {
        String locator = formatLocator(dynamicLocatorTemplate, dynamicParts);
        log.info("Attempting to click element with locator: " + locator );
        getElement(locator).click();
        log.info("Element clicked successfully.");
    }


    public void sendKeyToElement(String rawLocator, String valueToSend) {
        clearKeyInElement(rawLocator);
        log.info("Attempting to send keys with locator: "+rawLocator);
        getElement(rawLocator).sendKeys(valueToSend);
        getElement(rawLocator).sendKeys(Keys.TAB);
        log.info("Keys sent successfully.");
    }

    public void sendKeyToElement(String dynamicLocatorTemplate, String valueToSend, String... dynamicParts) {
        clearKeyInElement(formatLocator(dynamicLocatorTemplate, dynamicParts));
        log.info("Attempting to send keys with locator: "+formatLocator(dynamicLocatorTemplate, dynamicParts));
        getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).sendKeys(valueToSend);
        getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).sendKeys(Keys.TAB);
        log.info("Keys sent successfully.");
    }

    public void clearKeyInElement(String rawLocator) {
        log.info("Attempting to clear keys with locator: " + rawLocator);
        getElement(rawLocator).sendKeys(Keys.CONTROL + "a");
        getElement(rawLocator).sendKeys(Keys.DELETE);
        log.info("Keys cleared successfully");
    }

    public void clearKeyInElement(String dynamicLocatorTemplate, String... dynamicParts) {
        log.info("Attempting to clear keys with locator: " + formatLocator(dynamicLocatorTemplate,dynamicParts));
        getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).sendKeys(Keys.CONTROL + "a");
        getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).sendKeys(Keys.DELETE);
        log.info("Keys cleared successfully.");
    }

    public String getElementText(String rawLocator) {
        log.info("Attempting to retrieve text with locator: " +rawLocator);
        String elementText = getElement(rawLocator).getText();
        log.info("Text retrieved successfully from element.");
        return elementText;
    }

    public String getElementText(String dynamicLocatorTemplate, String... dynamicParts) {
        log.info("Attempting to retrieve text with locator: " + formatLocator(dynamicLocatorTemplate,dynamicParts));
        String elementText = getElement(formatLocator(dynamicLocatorTemplate,dynamicParts)).getText();
        log.info("Text retrieved successfully from element.");
        return elementText;
    }

    public List<String> getListElementText(String rawLocator) {
        log.info("Attempting to retrieve text of list elements with locator: " + rawLocator);
        List<WebElement> listElements = getListElement(rawLocator);
        List<String> listElementsText = new ArrayList<>();
        for (WebElement element : listElements) {
            log.info("Attempting to retrieve text of element: " + element);
            listElementsText.add(element.getText().trim());
        }
        log.info("Text retrieved successfully from elements: " + listElements.size());
        return listElementsText;
    }

    public String getDOMPropertyValue(String rawLocator, String attributeName) {
        log.info("Attempting to retrieve property value with locator: "+rawLocator);
        String propertyValue = getElement(rawLocator).getDomProperty(attributeName);
        log.info("Property value retrieved successfully from element");
        return propertyValue;
    }

    public String getDOMPropertyValue(String dynamicLocatorTemplate, String attributeName, String... dynamicParts) {
        return getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).getDomProperty(attributeName);
    }

    public String getAttributeValue(String rawLocator, String attributeName) {
        return getElement(rawLocator).getDomAttribute(attributeName);
    }

    public String getAttributeValue(String templateLocator, String attributeName, String... dynamicParts) {
        return getElement(formatLocator(templateLocator, dynamicParts)).getDomAttribute(attributeName);
    }


    public int getListElementsSize(String rawLocator) {
        return getListElement(rawLocator).size();
    }

    public int getListElementsSize(String dynamicLocatorTemplate, String... dynamicParts) {
        return getListElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).size();
    }

    public void selectDropdownOption(String rawLocator, String option) {
        new Select(getElement(rawLocator)).selectByVisibleText(option);

    }


    public void selectDropdownOption(String option, String dynamicLocatorTemplate, String... dynamicParts) {
        new Select(getElement(formatLocator(dynamicLocatorTemplate, dynamicParts))).selectByVisibleText(option);
    }

    //public void selectMultipleDropdownOptions ( String locator, String option)  --> khi làm làm đến dynamic locator thì mình apply
    public void deselectDropdownOption(String rawLocator, String option) {
        new Select(getElement(rawLocator)).deselectByVisibleText(option);
    }

    public void deselectDropdownOption(String option, String dynamicLocatorTemplate, String... dynamicParts) {
        new Select(getElement(formatLocator(dynamicLocatorTemplate, dynamicParts))).deselectByVisibleText(option);
    }

    public String getSelectedDropdownOption(String rawLocator) {
        return new Select(getElement(rawLocator)).getFirstSelectedOption().getText();
    }

    public String getSelectedDropdownOption(String dynamicLocatorTemplate, String... dynamicParts) {
        String value = "chưa implement method";
        return value;
    }

    public List<String> getAllSelectedDropdownOptions(String locator) {
        List<WebElement> allSelectedOptions = new Select(getElement(locator)).getAllSelectedOptions();
        List<String> selectedOptions = new ArrayList<String>();
        for (WebElement option : allSelectedOptions) {
            selectedOptions.add(option.getText());
        }
        return selectedOptions;
    }

    public List<String> getOptions(String locator) {
        List<WebElement> allDropdownOptions = new Select(getElement(locator)).getOptions();
        List<String> dropdownOptions = new ArrayList<String>();
        for (WebElement option : allDropdownOptions) {
            dropdownOptions.add(option.getText());
        }
        return dropdownOptions;
    }

    public boolean isDropdownMultiple(String locator) {
        return new Select(getElement(locator)).isMultiple();
    }

    public void selectCustomizedDropdownOptions(String DropdownIconLocator, String optionsLocator, String option) {
        //CLick vào drop down icon
        clickElement(DropdownIconLocator);
        //Lấy về list các options
        List<WebElement> allDropdownOptions = getListElement(optionsLocator);
        for (WebElement dropdownOption : allDropdownOptions) {
            if (dropdownOption.getText().trim().equals(option)) {
                dropdownOption.click();
                break;
            }
        }
    }

    public void checkNativeRadio(String rawLocator) {
        if (!getElement(rawLocator).isSelected()) {
            getElement(rawLocator).click();
            log.info("Radio Selected (native): " + rawLocator);
        } else {
            log.info("Radio already selected (native), skip click: " + rawLocator);
        }
    }

    public void checkNativeRadio(String templateLocator, String... dynamicParts) {
        if (!getElement(templateLocator, dynamicParts).isSelected()) {
            getElement(templateLocator, dynamicParts).click();
            log.info("Radio Selected (native): " + formatLocator(templateLocator, dynamicParts));
        } else {
            log.info("Radio already selected (native), skip click: " + formatLocator(templateLocator, dynamicParts));
        }
    }

    public void uncheckNativeRadio(String rawLocator) {
        if (getElement(rawLocator).isSelected()) {
            getElement(rawLocator).click();
            log.info("Radio unselected (native): " + rawLocator);
        } else {
            log.info("Radio already unselected (native), skip click: " + rawLocator);
        }
    }

    public void uncheckNativeRadio(String templateLocator, String... dynamicParts) {
        if (getElement(formatLocator(templateLocator, dynamicParts)).isSelected()) {
            getElement(formatLocator(templateLocator, dynamicParts)).click();
            log.info("Radio unselected (native): " + formatLocator(templateLocator, dynamicParts));
        } else {
            log.info("Radio already unselected (native), skip click: " + formatLocator(templateLocator, dynamicParts));
        }
    }


    public void checkNativeCheckbox(String rawLocator) {
        if (!getElement(rawLocator).isSelected()) {
            getElement(rawLocator).click();
            log.info("Checkbox Selected (native): " + rawLocator);
        } else {
            log.info("Checkbox already selected (native), skip click: " + rawLocator);
        }
    }

    public void checkNativeCheckbox(String templateLocator, String... dynamicParts) {
        if (!getElement(templateLocator, dynamicParts).isSelected()) {
            getElement(templateLocator, dynamicParts).click();
            log.info("Checkbox Selected (native): " + formatLocator(templateLocator, dynamicParts));
        } else {
            log.info("Checkbox already selected (native), skip click: " + formatLocator(templateLocator, dynamicParts));
        }
    }

    public void checkCustomCheckbox(String rawLocator, String attributeName, String expectedAttributeValue) {
        String attributeValue = getDOMPropertyValue(rawLocator, attributeName);
        if (attributeValue == null || !attributeValue.contains(expectedAttributeValue)) {
            getElement(rawLocator).click();
            log.info("Checkbox Selected (custom): " + rawLocator);
        } else {
            log.info("Checkbox  already selected (custom), skip click: " + rawLocator);
        }
    }

    public void checkCustomRadio(String rawLocator, String attributeName, String expectedAttributeValue) {
        String attributeValue = getDOMPropertyValue(rawLocator, attributeName);
        if (attributeValue == null || !attributeValue.contains(expectedAttributeValue)) {
            getElement(rawLocator).click();
            log.info("Radio Selected (custom): " + rawLocator);
        } else {
            log.info("Radio already selected (custom), skip click: " + rawLocator);
        }
    }

    public void checkCustomRadio(String templateDynamicLocator, String attributeName, String expectedAttributeValue, String... dynamicParts) {

        String attributeValue = getDOMPropertyValue(formatLocator(templateDynamicLocator, dynamicParts), attributeName);
        if (attributeValue == null || !attributeValue.contains(expectedAttributeValue)) {
            getElement(formatLocator(templateDynamicLocator, dynamicParts)).click();
            log.info("Radio Selected (custom): " + formatLocator(templateDynamicLocator, dynamicParts));
        } else {
            log.info("Radio already selected (custom), skip click: " + formatLocator(templateDynamicLocator, dynamicParts));
        }

    }

    public void checkAllNativeCheckboxes(String rawLocator) {
        int numberOfCheckboxes = getListElementsSize(rawLocator);
        for (int i = 0; i < numberOfCheckboxes; i++) {
            List<WebElement> checkboxes = getListElement(rawLocator);
            WebElement checkbox = checkboxes.get(i);
            if (!checkbox.isSelected()) {
                checkbox.click();
                log.info("Checkbox [" + i + "] selected (native): " + rawLocator);
            } else {
                log.info("Checkbox [" + i + "] already selected (native), skip click: " + rawLocator);
            }
        }
    }

    public void checkAllCustomCheckboxes(String rawLocator, String attributeName, String expectedAttributeValue) {
        int numberOfCheckboxes = getListElementsSize(rawLocator);
        for (int i = 0; i < numberOfCheckboxes; i++) {
            List<WebElement> checkboxes = getListElement(rawLocator);
            WebElement checkbox = checkboxes.get(i);
            String attributeValue = checkbox.getDomProperty(attributeName);
            if (attributeValue == null || !attributeValue.contains(expectedAttributeValue)) {
                checkbox.click();
                log.info("Checkbox [" + i + "] selected (custom): " + rawLocator);
            } else {
                log.info("Checkbox [" + i + "] already selected (custom), skip click: " + rawLocator);
            }
        }
    }


    public void checkCheckboxOrRadio(String dynamicLocatorTemplate, String... dynamicParts) {
        if (!getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).isSelected()) {
            getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).click();
        }
    }


    public void uncheckNativeCheckbox(String rawLocator) {
        if (getElement(rawLocator).isSelected()) {
            getElement(rawLocator).click();
            log.info("Unchecked Checkbox(native) successfully: " + rawLocator);
        } else {
            log.info("Checkbox(native) is already unchecked: " + rawLocator);
        }

    }

    public void uncheckCustomCheckbox(String rawLocator) {
        //To write 
    }

    public boolean isElementDisplayed(String locator) {
        return getElement(locator).isDisplayed();
    }

    public boolean isElementEnable(String locator) {
        return getElement(locator).isEnabled();
    }

    public boolean isElementSelected(String locator) {
        return getElement(locator).isSelected();
    }

    //Wait
    public WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.TIMEOUT));
    }

    public WebElement waitForElementVisible(String rawLocator) {
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(LocatorHelper.getByLocator(rawLocator)));
    }

    public WebElement waitForElementVisible(String dynamicLocatorTemplate, String... dynamicParts) {
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public List<WebElement> waitForListElementsVisible(String rawLocator) {
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LocatorHelper.getByLocator(rawLocator)));
    }

    public List<WebElement> waitForListElementsVisible(String dynamicLocatorTemplate, String... dynamicParts) {
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public void waitForElementInvisible(String dynamicLocatorTemplate, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public void waitForElementInvisible(String rawLocator) {
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(LocatorHelper.getByLocator(rawLocator)));
    }

    public void waitForListElementInvisible(String rawLocator) {
        List<WebElement> listElements = getListElement(rawLocator);
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfAllElements(listElements));
    }

    public void waitForElementPresence(String rawlocator) {
        getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(LocatorHelper.getByLocator(rawlocator)));
    }

    public void waitForElementPresence(String templateLocator, String... dynamicParts) {

        getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(LocatorHelper.getByLocator(LocatorHelper.formatLocator(templateLocator, dynamicParts))));
    }

    public void waitForListElementsPresence(String locator) {
        getWebDriverWait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(LocatorHelper.getByLocator(locator)));
    }

    public void waitForElementSelected(String rawLocator) {
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeSelected(LocatorHelper.getByLocator(rawLocator)));
    }

    public void waitForElementSelected(String dynamicLocatorTemplate, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeSelected(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public void waitForElementClickable(String rawLocator) {
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(LocatorHelper.getByLocator(rawLocator)));
    }

    public void waitForElementClickable(WebElement element) {
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementClickable(String dynamicLocatorTemplate, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public void waitForTextToBePresentInElement(String rawLocator, String text) {
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(LocatorHelper.getByLocator(rawLocator), text));
    }


    public void waitForTextToBePresentInElement(WebElement element, String text) {
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElement(element, text));

    }

    public void waitForTextToBePresentInElement(String dynamicLocatorTemplate, String text, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate, dynamicParts)), text));
    }

    public void waitForAttributeToBe(String rawLocator, String attributeName, String value) {
        getWebDriverWait(driver).until(ExpectedConditions.attributeToBe(LocatorHelper.getByLocator(rawLocator), attributeName, value));
    }

    public void enterTextboxByID(String dynamicLocatorTemplate, String valueToSend, String idTextboxValue) {
        waitForElementVisible(dynamicLocatorTemplate, idTextboxValue);
        sendKeyToElement(dynamicLocatorTemplate, valueToSend, idTextboxValue);
    }

    public void waitForUrlContains(String valueToContain) {
        getWebDriverWait(driver).until(ExpectedConditions.urlContains(valueToContain));
    }


    public String getAttributeValueByID(String dynamicLocatorTemplate, String attributeValue, String idTextboxValue) {
        waitForElementVisible(dynamicLocatorTemplate, idTextboxValue);
        return getDOMPropertyValue(dynamicLocatorTemplate, attributeValue, idTextboxValue);
    }


    public void hoverToElementWithLog(String locatorForLog) {
        log.info("Perform hover to element: " + locatorForLog);
        new Actions(driver).moveToElement(getElement(locatorForLog)).perform();
        log.info("Hover to element successfully");
    }

    public void hoverToElement(String rawLocator) {
        hoverToElementWithLog(rawLocator);
    }

    public void hoverToElement(String templateLocator, String... dynamicParts) {
        String locatorForLog = formatLocator(templateLocator, dynamicParts);
        hoverToElementWithLog(locatorForLog);
    }

    public void waitForAttributeContains(String rawLocator, String attributeName, String valueToContain, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.attributeContains(LocatorHelper.getByLocator(LocatorHelper.formatLocator(rawLocator, dynamicParts)), attributeName, valueToContain));
    }


    public void waitForNumberOfElementsTobe(String rawLocator, int number) {
        getWebDriverWait(driver).until(ExpectedConditions.numberOfElementsToBe(LocatorHelper.getByLocator(rawLocator), number));

    }

    public void waitForDomPropertyTobe(WebElement element, String value) {
        getWebDriverWait(driver).until(ExpectedConditions.domPropertyToBe(element, "innerText", value));
    }

    public void waitForTextVisible(String templateLocator, String... dynamics) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(20));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(templateLocator, "innerText", dynamics);
                return text != null && !text.trim().isEmpty();
            }

            @Override
            public String toString() {
                return "waiting for non-empty innerText of element: " + LocatorHelper.formatLocator(templateLocator, dynamics);
            }
        });
    }

    public void waitForTextVisible(String templateLocator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(templateLocator, "innerText");
                return text != null && !text.trim().isEmpty();
            }

            @Override
            public String toString() {
                return "waiting for non-empty innerText of element: " + LocatorHelper.getByLocator(templateLocator);
            }
        });
    }

    //Trong trường hợp không chắc có spinner hay không (có thể có hoặc không)
    public void waitForSpinnerInvisibleOrSkipSpinner(WebDriver driver) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(300));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        try {
            wait.until(new Function<WebDriver, Boolean>() {

                @Override
                public Boolean apply(WebDriver driver) {
                    WebElement spinner = getElement(WaitHelperUI.LOADING_ICON);
                    if (spinner.isDisplayed()) {
                        log.info("Spinner display is still visible...");
                    }
                    return !spinner.isDisplayed();
                }
            });
            log.info("Spinner already disappeared");
        } catch (TimeoutException e) {
            System.out.println("Spinner did not appear or disappear too fast");
        }

    }


    // Trường hợp chắc chắn có spinner
    public void waitForLoadingIconInvisible(WebDriver driver) {
        waitForElementInvisible(WaitHelperUI.LOADING_ICON);
    }

    public void waitForLoadingScreenInvisible(WebDriver driver) {
        waitForElementInvisible(WaitHelperUI.LOADING_SCREEN);
    }


}


