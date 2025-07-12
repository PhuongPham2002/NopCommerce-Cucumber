package commons.helpers;
import commons.constants.GlobalConstants;
import interfaces.helperUI.WaitHelperUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class WaitHelper {
    protected static final Logger log = LogManager.getLogger(WaitHelper.class);;

    public static WebDriverWait getWebDriverWait(WebDriver driver){
        return new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.TIMEOUT));
    }

    public static void waitForElementVisible(WebDriver driver, String rawLocator){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(LocatorHelper.getByLocator(rawLocator)));
    }
    public static void waitForElementVisible(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts))));
    }
    public static void waitForListElementsVisible(WebDriver driver, String rawLocator){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LocatorHelper.getByLocator(rawLocator)));}
    public static void waitForListElementsVisible(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts))));}

    public static void waitForElementPresence (WebDriver driver, String rawlocator) {
        getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(LocatorHelper.getByLocator(rawlocator)));}

    public static void waitForElementPresence (WebDriver driver, String templateLocator, String...dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(LocatorHelper.getByLocator(LocatorHelper.formatLocator(templateLocator,dynamicParts))));}
    public static void waitForListElementsPresence (WebDriver driver, String locator) {
        getWebDriverWait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(LocatorHelper.getByLocator(locator)));}
    public static void waitForElementSelected (WebDriver driver, String rawLocator){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeSelected(LocatorHelper.getByLocator(rawLocator)));}
    public static void waitForElementSelected (WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeSelected(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts))));}
    public static void waitForElementClickable(WebDriver driver, String rawLocator){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(LocatorHelper.getByLocator(rawLocator)));}
    public static void waitForElementClickable (WebDriver driver, WebElement element){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void waitForElementClickable(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts))));}

    public static void waitForTextToBePresentInElement(WebDriver driver,String rawLocator,String text){
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(LocatorHelper.getByLocator(rawLocator),text));}

    public static void waitForTextToBePresentInElement(WebDriver driver, WebElement element,String text){
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElement(element,text));

    }

    public static void waitForTextToBePresentInElement(WebDriver driver,String dynamicLocatorTemplate,String text,String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)),text));}
    public static void waitForAttributeToBe(WebDriver driver,String rawLocator,String attributeName, String value){
        getWebDriverWait(driver).until(ExpectedConditions.attributeToBe(LocatorHelper.getByLocator(rawLocator),attributeName,value));
    }

    public static void waitForUrlContains(WebDriver driver,String valueToContain){
        getWebDriverWait(driver).until(ExpectedConditions.urlContains(valueToContain));
    }

    public static void waitForElementInvisible (WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(LocatorHelper.getByLocator(LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts))));
    }
    public static void waitForElementInvisible (WebDriver driver, String rawLocator){
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(LocatorHelper.getByLocator(rawLocator)));
    }

    public static void waitForAttributeContains(WebDriver driver,String rawLocator, String attributeName, String valueToContain, String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.attributeContains(LocatorHelper.getByLocator(LocatorHelper.formatLocator(rawLocator,dynamicParts)),attributeName,valueToContain));
    }

    public static void waitForListElementInvisible (WebDriver driver, String rawLocator){
        List<WebElement> listElements = ElementHelper.getListElement(driver,rawLocator);
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfAllElements(listElements));}

    public static void waitForNumberOfElementsTobe(WebDriver driver, String rawLocator, int number){
        getWebDriverWait(driver).until(ExpectedConditions.numberOfElementsToBe(LocatorHelper.getByLocator(rawLocator),number));

    }
    public static void waitForDomPropertyTobe(WebDriver driver,WebElement element, String value){
        getWebDriverWait(driver).until(ExpectedConditions.domPropertyToBe(element,"innerText",value));
    }

    public static void waitForTextVisible (WebDriver driver, String templateLocator, String...dynamics){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(20));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = JavaScriptHelper.getDOMPropertyValue(driver, templateLocator,"innerText",dynamics);
                return text!=null && !text.trim().isEmpty();
            }

            @Override
            public String toString() {
                return "waiting for non-empty innerText of element: " + LocatorHelper.formatLocator(templateLocator, dynamics);
            }
        });
    }

    public static void waitForTextVisible (WebDriver driver, String templateLocator){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = JavaScriptHelper.getDOMPropertyValue(driver, templateLocator,"innerText");
                return text!=null && !text.trim().isEmpty();
            }

            @Override
            public String toString() {
                return "waiting for non-empty innerText of element: " + LocatorHelper.getByLocator(templateLocator);
            }
        });
    }

    //Trong trường hợp không chắc có spinner hay không (có thể có hoặc không)
    public static void waitForSpinnerInvisibleOrSkipSpinner(WebDriver driver){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(300));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        try {
            wait.until(new Function<WebDriver, Boolean>() {

                @Override
                public Boolean apply(WebDriver driver) {
                    WebElement spinner = ElementHelper.getElement(driver,WaitHelperUI.LOADING_ICON);
                    if (spinner.isDisplayed()){
                        log.info("Spinner display is still visible...");
                    }
                    return !spinner.isDisplayed();
                }
            });
            log.info("Spinner already disappeared");
        } catch (TimeoutException e){
            System.out.println("Spinner did not appear or disappear too fast");
        }

    }



    // Trường hợp chắc chắn có spinner
    public static void waitForLoadingIconInvisible(WebDriver driver){
        waitForElementInvisible(driver, WaitHelperUI.LOADING_ICON);
    }
    public static void waitForLoadingScreenInvisible(WebDriver driver){
        waitForElementInvisible(driver, WaitHelperUI.LOADING_SCREEN);
    }


    public static  <T> T waitForExpectedConditionMet(WebDriver driver, Function<WebDriver, T> expectedCondition){
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(GlobalConstants.TIMEOUT));
        wait.pollingEvery(Duration.ofSeconds(GlobalConstants.POLLING_TIME));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        return wait.until(expectedCondition);
    }
}
