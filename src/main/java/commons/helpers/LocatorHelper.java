package commons.helpers;

import org.openqa.selenium.By;

public class LocatorHelper {
    public static By getByLocator(String rawLocator) {
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

        throw new IllegalArgumentException("Raw Locator is not valid: " + rawLocator);
    }

    public static String formatLocator(String dynamicLocatorTemplate, String... dynamicParts) {
        return String.format(dynamicLocatorTemplate, (Object[]) dynamicParts);
    }


}
