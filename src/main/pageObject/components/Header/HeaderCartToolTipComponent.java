package pageObject.components.Header;

import pageObject.BasePage;
import interfaces.componentUI.header.HeaderCartToolTipComponentUI;
import org.openqa.selenium.WebDriver;

import java.util.function.Function;

public class HeaderCartToolTipComponent extends BasePage {

    public HeaderCartToolTipComponent(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfItemInCart(){
       waitForExpectedConditionMet( new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(HeaderCartToolTipComponentUI.NUMBER_OF_ITEM_IN_SHOPPING_CART,"innerText");
                return text!=null && !text.trim().isEmpty();
            }
        });
        String text = getDOMPropertyValue(HeaderCartToolTipComponentUI.NUMBER_OF_ITEM_IN_SHOPPING_CART,"innerText").trim();
        String numberPart = text.split(" ")[0];

        return Integer.parseInt(numberPart);
    }

    public String getProductDetail() {
        waitForExpectedConditionMet( new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(HeaderCartToolTipComponentUI.PRODUCT_DETAIL,"innerText");
                return text!=null && !text.trim().isEmpty();
            }
        });
        String productDetail = getDOMPropertyValue(HeaderCartToolTipComponentUI.PRODUCT_DETAIL,"innerText").trim();
        return productDetail;
    }

    public String getProductPrice() {
       waitForExpectedConditionMet( new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(HeaderCartToolTipComponentUI.PRODUCT_PRICE,"innerText");
                return text!=null && !text.trim().isEmpty();
            }
        });


        String productPrice = getDOMPropertyValue(HeaderCartToolTipComponentUI.PRODUCT_PRICE,"innerText").trim();
        log.info("product price: "+productPrice.substring(1));

        return productPrice.substring(1);



    }

    public void clickGoToCartButton(){
        waitForElementClickable(HeaderCartToolTipComponentUI.GO_TO_CART_BUTTON);
        clickElement(HeaderCartToolTipComponentUI.GO_TO_CART_BUTTON);
    }
}

