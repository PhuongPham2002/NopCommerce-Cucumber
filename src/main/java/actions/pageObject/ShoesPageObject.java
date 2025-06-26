package actions.pageObject;

import commons.base.BasePage;
import org.openqa.selenium.WebDriver;

public class ShoesPageObject extends BasePage {
    WebDriver driver;

    public ShoesPageObject(WebDriver driver) {
        this.driver= driver;
    }

}
