package actions.components.MyAccountSideBar;

import commons.base.BasePage;
import interfaces.componentUI.myAccountSideBar.MyProductReviewsPageUI;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@Slf4j
public class MyProductReviewsComponent extends BasePage {
    WebDriver driver;

    public MyProductReviewsComponent(WebDriver driver) {

        this.driver = driver;
    }


    public boolean isNumberOfProductReviewAtLeastOne() {
        waitForListElementsVisible(driver,MyProductReviewsPageUI.PRODUCT_ITEM_REVIEW_TITLE_TEXT);
        int size = getListElementsSize(driver,MyProductReviewsPageUI.PRODUCT_ITEM_REVIEW_TITLE_TEXT);
        return size>=1;
    }
}
