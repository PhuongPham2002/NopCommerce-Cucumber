package actions.components;

import commons.base.BasePage;
import interfaces.componentUI.DisplayProductsComponentUI;
import org.openqa.selenium.WebDriver;

public class DisplayProductComponent extends BasePage {
    WebDriver driver;

    public DisplayProductComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void selectProductsDisplayOption(int displayOption){
        waitForElementClickable(driver, DisplayProductsComponentUI.PRODUCTS_PER_PAGE_DROPDOWN);
        selectDropdownOption(driver, DisplayProductsComponentUI.PRODUCTS_PER_PAGE_DROPDOWN,String.valueOf(displayOption));
        waitForTextToBePresentInElement(driver,DisplayProductsComponentUI.PRODUCTS_PER_PAGE_DROPDOWN,String.valueOf(displayOption));
        log.info("giá trị đã chọn: " + getSelectedDropdownOption(driver,DisplayProductsComponentUI.PRODUCTS_PER_PAGE_DROPDOWN));
    }

}
