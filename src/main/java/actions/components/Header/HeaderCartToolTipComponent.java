package actions.components.Header;

import commons.base.BasePage;
import commons.helpers.WaitHelper;
import interfaces.componentUI.header.HeaderCartToolTipComponentUI;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public class HeaderCartToolTipComponent extends BasePage {
    WebDriver driver;

    public HeaderCartToolTipComponent(WebDriver driver) {
        this.driver = driver;
    }

    public int getNumberOfItemInCart(){
        WaitHelper.waitForExpectedConditionMet(driver, new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(driver,HeaderCartToolTipComponentUI.NUMBER_OF_ITEM_IN_SHOPPING_CART,"innerText");
                return text!=null && !text.trim().isEmpty();
            }
        });
        String text = getDOMPropertyValue(driver,HeaderCartToolTipComponentUI.NUMBER_OF_ITEM_IN_SHOPPING_CART,"innerText").trim();
        String numberPart = text.split(" ")[0];

        return Integer.parseInt(numberPart);
    }

    public String getProductDetail() {
        WaitHelper.waitForExpectedConditionMet(driver, new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(driver,HeaderCartToolTipComponentUI.PRODUCT_DETAIL,"innerText");
                return text!=null && !text.trim().isEmpty();
            }
        });
        String productDetail = getDOMPropertyValue(driver,HeaderCartToolTipComponentUI.PRODUCT_DETAIL,"innerText").trim();
        return productDetail;
    }

    public String getProductPrice() {
        WaitHelper.waitForExpectedConditionMet(driver, new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(driver,HeaderCartToolTipComponentUI.PRODUCT_PRICE,"innerText");
                return text!=null && !text.trim().isEmpty();
            }
        });


        String productPrice = getDOMPropertyValue(driver,HeaderCartToolTipComponentUI.PRODUCT_PRICE,"innerText").trim();
        log.info("product price: "+productPrice.substring(1));

        return productPrice.substring(1);



    }

    public void clickGoToCartButton(){
        waitForElementClickable(driver,HeaderCartToolTipComponentUI.GO_TO_CART_BUTTON);
        clickElement(driver,HeaderCartToolTipComponentUI.GO_TO_CART_BUTTON);
    }
}

