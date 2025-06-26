package commons.helpers;

import org.openqa.selenium.WebDriver;

public class JavaScriptHelper {

    public static String getDOMPropertyValue(WebDriver driver, String rawLocator, String attributeName){
        return ElementHelper.getElement(driver,rawLocator).getDomProperty(attributeName);
    }

    public static String getDOMPropertyValue(WebDriver driver, String dynamicLocatorTemplate, String attributeName, String... dynamicParts){
        return ElementHelper.getElement(driver,LocatorHelper.formatLocator(dynamicLocatorTemplate,dynamicParts)).getDomProperty(attributeName);
    }
}
