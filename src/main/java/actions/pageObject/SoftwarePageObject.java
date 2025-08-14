package actions.pageObject;

import commons.base.BasePage;
import org.openqa.selenium.WebDriver;

public class SoftwarePageObject extends BasePage {
    WebDriver driver;

    public SoftwarePageObject(WebDriver driver) {
        super(driver);
    }

}
