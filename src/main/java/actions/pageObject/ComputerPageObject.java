package actions.pageObject;

import commons.base.BasePage;
import org.openqa.selenium.WebDriver;

public class ComputerPageObject extends BasePage {
    WebDriver driver;

    public ComputerPageObject(WebDriver driver) {
        super(driver);
    }

}
