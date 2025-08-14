package actions.pageObject;

import actions.components.Header.HeaderComponent;
import commons.base.BasePage;
import interfaces.pageUI.ShoppingCartPageUI;
import interfaces.pageUI.WishListPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPageObject extends BasePage {
    HeaderComponent header;

    public ShoppingCartPageObject(WebDriver driver) {

        super(driver);
        this.header = new HeaderComponent(driver);
    }

    public boolean hasShoppingCartContained(String productName) {
        return getProductNames().contains(productName);

    }

    public List<String> getProductNames(){
        waitForLoadingIconInvisible(driver);
        waitForListElementsVisible(ShoppingCartPageUI.PRODUCT_NAME);
        List<WebElement> listProducts = getListElement(WishListPageUI.PRODUCT_NAME);
        List<String> listProductName = new ArrayList<>();
        for (WebElement product:listProducts){
            listProductName.add(product.getText().trim());
        }
        return listProductName;

    }

    public WishListPageObject clickWishListLinkFromHeader() {
        header.account.clickWishListLink();
        return PageGenerator.getWishListPage(driver);
    }

    public ProductDetailPageObject clickEditLink() {
        waitForLoadingIconInvisible(driver);
        waitForElementClickable(ShoppingCartPageUI.EDIT_LINK);
        clickElement(ShoppingCartPageUI.EDIT_LINK);
        return PageGenerator.getProductDetailPage(driver);



    }

    public String getQuantityOfProductInShoppingCart(String productName) {
        waitForLoadingIconInvisible(driver);
        waitForElementVisible(ShoppingCartPageUI.DYNAMIC_PRODUCT_QUANTITY, productName);
        return getAttributeValue(ShoppingCartPageUI.DYNAMIC_PRODUCT_QUANTITY, "value", productName);
    }


    public boolean isProductDetailContained(String productName, String valueToAssert) {
        waitForTextVisible(ShoppingCartPageUI.DYNAMIC_PRODUCT_DETAIL, productName);
        String productDetail = getDOMPropertyValue(ShoppingCartPageUI.DYNAMIC_PRODUCT_DETAIL, "innerText", productName).trim();
      return productDetail.contains(valueToAssert);
    }

    public String getProductPrice() {
        waitForTextVisible(ShoppingCartPageUI.TOTAL_PRICE);
        return getDOMPropertyValue(ShoppingCartPageUI.TOTAL_PRICE, "innerText").trim();

    }

    public void clickRemoveButton() {
        waitForElementVisible(ShoppingCartPageUI.REMOVE_BUTTON);
        clickElement(ShoppingCartPageUI.REMOVE_BUTTON);
    }


    public String getEmptyProductMessageOfShoppingCart() {
        waitForTextVisible(ShoppingCartPageUI.PRODUCT_EMPTY_MESSAGE);
        return getDOMPropertyValue(ShoppingCartPageUI.PRODUCT_EMPTY_MESSAGE, "innerText");
    }


    public boolean isShoppingCartEmpty() {
        waitForNumberOfElementsTobe(ShoppingCartPageUI.PRODUCT_NAME, 0);
        return getListElementsSize(ShoppingCartPageUI.PRODUCT_NAME) == 0;
    }

    public BasePage hoverToHeaderProductCategoryAndClickToSubProductCategory(String productCategory, String subProductCategory) {
        header.productCategory.hoverToHeaderProductCategory(productCategory);
        header.productCategory.waitForSubProductCategoryVisible(subProductCategory);
        return header.productCategory.clickSubProductCategory(productCategory,subProductCategory);
    }

    public void enterProductQuantity(String productName, String productQuantity) {
        waitForLoadingIconInvisible(driver);
        waitForElementVisible(ShoppingCartPageUI.DYNAMIC_PRODUCT_QUANTITY, productName);
        sendKeyToElement(ShoppingCartPageUI.DYNAMIC_PRODUCT_QUANTITY, productQuantity, productName);
    }

    public String getTotalPriceOfShoppingCart(String productName) {
        waitForLoadingIconInvisible(driver);
        waitForTextVisible(ShoppingCartPageUI.DYNAMIC_PRODUCT_TOTAL_PRICE, productName);
        return getDOMPropertyValue(ShoppingCartPageUI.DYNAMIC_PRODUCT_TOTAL_PRICE, "innerText", productName);
    }

    public EstimateShippingPopupPageObject clickEstimateShippingButton() {
        waitForElementClickable(ShoppingCartPageUI.ESTIMATE_SHIPPING_BUTTON);
        clickElement(ShoppingCartPageUI.ESTIMATE_SHIPPING_BUTTON);
        return PageGenerator.getEstimateShippingPopupPage(driver);
    }

    public void clickTermOfServiceAgreementButton() {
        waitForLoadingIconInvisible(driver);
        waitForElementClickable(ShoppingCartPageUI.TERM_OF_SERVICE_BUTTON);
        clickElement(ShoppingCartPageUI.TERM_OF_SERVICE_BUTTON);
    }

    public CheckoutPageObject clickCheckoutButton() {
        waitForLoadingIconInvisible(driver);
        waitForElementClickable(ShoppingCartPageUI.CHECKOUT_BUTTON);
        clickElement(ShoppingCartPageUI.CHECKOUT_BUTTON);
        return PageGenerator.getCheckoutPage(driver);
    }
}
