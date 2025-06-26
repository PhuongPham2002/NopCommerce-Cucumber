package actions.pageObject;

import actions.components.Header.HeaderComponent;
import commons.base.BasePage;
import commons.helpers.WaitHelper;
import interfaces.pageUI.EstimateShippingPopupPageUI;
import interfaces.pageUI.ShoppingCartPageUI;
import interfaces.pageUI.WishListPageUI;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPageObject extends BasePage {
    WebDriver driver;
    HeaderComponent header;

    public ShoppingCartPageObject(WebDriver driver) {

        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }

    public boolean hasShoppingCartContained(String productName) {
        return getProductNames().contains(productName);

    }

    public List<String> getProductNames(){
        waitForLoadingIconInvisible(driver);
        waitForListElementsVisible(driver, ShoppingCartPageUI.PRODUCT_NAME);
        List<WebElement> listProducts = getListElement(driver, WishListPageUI.PRODUCT_NAME);
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
        waitForElementClickable(driver, ShoppingCartPageUI.EDIT_LINK);
        clickElement(driver,ShoppingCartPageUI.EDIT_LINK);
        return PageGenerator.getProductDetailPage(driver);



    }

    public String getQuantityOfProductInShoppingCart(String productName) {
        waitForLoadingIconInvisible(driver);
        waitForElementVisible(driver,ShoppingCartPageUI.DYNAMIC_PRODUCT_QUANTITY,productName);
        return getAttributeValue(driver,ShoppingCartPageUI.DYNAMIC_PRODUCT_QUANTITY,"value",productName);
    }


    public boolean isProductDetailContained(String productName, String valueToAssert) {
      WaitHelper.waitForTextVisible(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_DETAIL,productName);
      String productDetail = getDOMPropertyValue(driver, ShoppingCartPageUI.DYNAMIC_PRODUCT_DETAIL,"innerText",productName).trim();
      return productDetail.contains(valueToAssert);
    }

    public String getProductPrice() {
        WaitHelper.waitForTextVisible(driver,ShoppingCartPageUI.TOTAL_PRICE);
        return getDOMPropertyValue(driver,ShoppingCartPageUI.TOTAL_PRICE,"innerText").trim();

    }

    public void clickRemoveButton() {
        waitForElementVisible(driver,ShoppingCartPageUI.REMOVE_BUTTON);
        clickElement(driver,ShoppingCartPageUI.REMOVE_BUTTON);
    }


    public String getEmptyProductMessageOfShoppingCart() {
        WaitHelper.waitForTextVisible(driver,ShoppingCartPageUI.PRODUCT_EMPTY_MESSAGE);
       return getDOMPropertyValue(driver,ShoppingCartPageUI.PRODUCT_EMPTY_MESSAGE,"innerText");
    }


    public boolean isShoppingCartEmpty() {
        WaitHelper.waitForNumberOfElementsTobe(driver,ShoppingCartPageUI.PRODUCT_NAME,0);
        return getListElementsSize(driver,ShoppingCartPageUI.PRODUCT_NAME)==0;
    }

    public BasePage hoverToHeaderProductCategoryAndClickToSubProductCategory(String productCategory, String subProductCategory) {
        header.productCategory.hoverToHeaderProductCategory(productCategory);
        header.productCategory.waitForSubProductCategoryVisible(subProductCategory);
        return header.productCategory.clickSubProductCategory(productCategory,subProductCategory);
    }

    public void enterProductQuantity(String productName, String productQuantity) {
        WaitHelper.waitForLoadingIconInvisible(driver);
        waitForElementVisible(driver,ShoppingCartPageUI.DYNAMIC_PRODUCT_QUANTITY,productName);
        sendKeyToElement(driver,ShoppingCartPageUI.DYNAMIC_PRODUCT_QUANTITY,productQuantity,productName);
    }

    public String getTotalPriceOfShoppingCart(String productName) {
        WaitHelper.waitForLoadingIconInvisible(driver);
        WaitHelper.waitForTextVisible(driver,ShoppingCartPageUI.DYNAMIC_PRODUCT_TOTAL_PRICE,productName);
        return getDOMPropertyValue(driver,ShoppingCartPageUI.DYNAMIC_PRODUCT_TOTAL_PRICE,"innerText",productName);
    }

    public EstimateShippingPopupPageObject clickEstimateShippingButton() {
        waitForElementClickable(driver, ShoppingCartPageUI.ESTIMATE_SHIPPING_BUTTON);
        clickElement(driver,ShoppingCartPageUI.ESTIMATE_SHIPPING_BUTTON);
        return PageGenerator.getEstimateShippingPopupPage(driver);
    }

    public void clickTermOfServiceAgreementButton() {
        WaitHelper.waitForLoadingIconInvisible(driver);
        waitForElementClickable(driver,ShoppingCartPageUI.TERM_OF_SERVICE_BUTTON);
        clickElement(driver,ShoppingCartPageUI.TERM_OF_SERVICE_BUTTON);
    }

    public CheckoutPageObject clickCheckoutButton() {
        WaitHelper.waitForLoadingIconInvisible(driver);
        waitForElementClickable(driver,ShoppingCartPageUI.CHECKOUT_BUTTON);
        clickElement(driver,ShoppingCartPageUI.CHECKOUT_BUTTON);
        return PageGenerator.getCheckoutPage(driver);
    }
}
