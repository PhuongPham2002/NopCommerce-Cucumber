package pageObject.components;

import pageObject.BasePage;
import interfaces.componentUI.DisplayProductsComponentUI;
import org.openqa.selenium.WebDriver;

public class DisplayProductComponent extends BasePage {
    WebDriver driver;

    public DisplayProductComponent(WebDriver driver) {
        super(driver);
    }

    public void selectProductsDisplayOption(int displayOption){
        waitForElementClickable(DisplayProductsComponentUI.PRODUCTS_PER_PAGE_DROPDOWN);
        selectDropdownOption(DisplayProductsComponentUI.PRODUCTS_PER_PAGE_DROPDOWN, String.valueOf(displayOption));
        waitForTextToBePresentInElement(DisplayProductsComponentUI.PRODUCTS_PER_PAGE_DROPDOWN, String.valueOf(displayOption));
        log.info("giá trị đã chọn: " + getSelectedDropdownOption(DisplayProductsComponentUI.PRODUCTS_PER_PAGE_DROPDOWN));
    }

}
