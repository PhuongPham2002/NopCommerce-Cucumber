package pageObject.components.Footer;

import pageObject.BasePage;
import interfaces.componentUI.footer.CustomerServiceComponentUI;
import org.openqa.selenium.WebDriver;

public class CustomerServiceComponent extends BasePage {
    WebDriver driver;

    public CustomerServiceComponent(WebDriver driver) {
        super(driver);
    }


    public void clickCustomerServiceLink(String customerServiceItem) {
        waitForElementClickable(CustomerServiceComponentUI.DYNAMIC_CUSTOMER_SERVICE_LINK,customerServiceItem);
        clickElement(CustomerServiceComponentUI.DYNAMIC_CUSTOMER_SERVICE_LINK,customerServiceItem);
    }
}
