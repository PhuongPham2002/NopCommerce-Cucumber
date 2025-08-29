package pageObject.components.Header;

import pageObject.BasePage;
import org.openqa.selenium.WebDriver;

public class HeaderSearchComponent extends BasePage {
    WebDriver driver;

    public HeaderSearchComponent(WebDriver driver) {
        super(driver);
    }
}
