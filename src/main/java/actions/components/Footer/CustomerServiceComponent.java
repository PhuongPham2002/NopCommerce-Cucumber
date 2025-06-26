package actions.components.Footer;

import commons.base.BasePage;
import interfaces.componentUI.footer.CustomerServiceComponentUI;
import org.openqa.selenium.WebDriver;

public class CustomerServiceComponent extends BasePage {
    WebDriver driver;

    public CustomerServiceComponent(WebDriver driver) {
        this.driver = driver;
    }


    public void clickCustomerServiceLink(String customerServiceItem) {
        waitForElementClickable(driver, CustomerServiceComponentUI.DYNAMIC_CUSTOMER_SERVICE_LINK,customerServiceItem);
        clickElement(driver,CustomerServiceComponentUI.DYNAMIC_CUSTOMER_SERVICE_LINK,customerServiceItem);
    }
}
