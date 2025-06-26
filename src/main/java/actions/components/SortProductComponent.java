package actions.components;

import commons.base.BasePage;
import interfaces.componentUI.SortProductsComponentUI;
import org.openqa.selenium.WebDriver;

public class SortProductComponent extends BasePage {
    WebDriver driver;

    public SortProductComponent(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSortOption(String sortOption) {
        waitForElementClickable(driver, SortProductsComponentUI.SELECT_SORT_TYPE);

        selectDropdownOption(driver, SortProductsComponentUI.SELECT_SORT_TYPE,sortOption);


    }
}
