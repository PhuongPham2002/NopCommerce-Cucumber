package actions.components.Header;

import commons.base.BasePage;
import org.openqa.selenium.WebDriver;

public class HeaderComponent extends BasePage {
    private WebDriver driver;
    public HeaderAccountComponent account;
    public HeaderProductCategoryComponent productCategory;
    public HeaderSearchComponent search;
    public HeaderCartToolTipComponent cartToolTip;

    public HeaderComponent(WebDriver driver) {
        this.driver = driver;
        this.account = new HeaderAccountComponent(driver);
        this.productCategory = new HeaderProductCategoryComponent(driver);
        this.search = new HeaderSearchComponent(driver);
        this.cartToolTip = new HeaderCartToolTipComponent(driver);
    }
}
