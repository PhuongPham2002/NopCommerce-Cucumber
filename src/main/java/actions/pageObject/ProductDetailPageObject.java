package actions.pageObject;

import actions.components.Header.HeaderComponent;
import actions.components.NotificationBarComponent;
import commons.base.BasePage;
import commons.helpers.WaitHelper;
import interfaces.pageUI.ProductDetailPageUI;
import org.openqa.selenium.WebDriver;

public class ProductDetailPageObject extends BasePage {
    WebDriver driver;
    HeaderComponent header;
    NotificationBarComponent notificationBar;
    public static final String ADD_WISHLIST_SUCCESSFUL_MESSAGE="The product has been added to your wishlist";
    public static final String ADD_TO_CART_SUCCESSFUL_MESSAGE="The product has been added to your shopping cart";

    public ProductDetailPageObject(WebDriver driver) {
        this.driver = driver;
        this.header = new HeaderComponent(driver);
        this.notificationBar = new NotificationBarComponent(driver);
    }

    public WishListPageObject clickWishListLinkFromHeader() {
        waitForLoadingIconInvisible(driver);
        waitForElementInvisible(driver,ProductDetailPageUI.ADD_WISHLIST_SUCCESSFUL_MESSAGE);
        header.account.clickWishListLink();
        return PageGenerator.getWishListPage(driver);
    }

    public void clickAddToWishListButton(String appleMacBookPro) {
        waitForElementClickable(driver, ProductDetailPageUI.ADD_WISHLIST_BUTTON);
        clickElement(driver,ProductDetailPageUI.ADD_WISHLIST_BUTTON);

    }

    public String getSuccessfulMessageForAddingProductToWishList() {
        return notificationBar.getNotificationMessage(ADD_TO_CART_SUCCESSFUL_MESSAGE);
    }

    public void viewProduct() {
        driver.navigate().back();
    }


    public void clickBackToPreviousPage() {
        driver.navigate().back();
    }

    public void selectRAMValueDropdown(String option) {
        waitForElementVisible(driver,ProductDetailPageUI.RAM_SELECT_DROPDOWN);
        waitForElementVisible(driver,ProductDetailPageUI.RAM_SELECT_DROPDOWN,option);
        selectDropdownOption(driver,ProductDetailPageUI.RAM_SELECT_DROPDOWN,option);
    }

    public void selectRadioButton(String label, String option) {
        waitForElementClickable(driver,ProductDetailPageUI.RADIO_BUTTON,label,option);
        checkNativeRadio(driver,ProductDetailPageUI.RADIO_BUTTON,label,option);
    }

    public void selectSoftwareCheckbox(String option) {
        waitForElementClickable(driver,ProductDetailPageUI.CHECKBOX_OPTION,option);
        checkNativeCheckbox(driver,ProductDetailPageUI.CHECKBOX_OPTION,option);

    }

    public void clickAddToCartButton() {
        waitForElementClickable(driver,ProductDetailPageUI.ADD_TO_CART_BUTTON);
        clickElement(driver,ProductDetailPageUI.ADD_TO_CART_BUTTON);
    }

    public String getSuccessfulMessageForAddingProductToCart() {
        waitForLoadingIconInvisible(driver);
       waitForTextToBePresentInElement(driver,ProductDetailPageUI.SUCCESSFUL_MESSAGE_ADDING_TO_CART,ADD_TO_CART_SUCCESSFUL_MESSAGE);
       return getElementText(driver,ProductDetailPageUI.SUCCESSFUL_MESSAGE_ADDING_TO_CART);

    }

    public void hoverToShoppingCartFromHeader() {
        clickElement(driver,ProductDetailPageUI.MESSAGE_CLOSE_BUTTON);
        header.account.hoverToShoppingCart();

    }


    public int getNumberOfItemInShoppingCart() {
        return header.cartToolTip.getNumberOfItemInCart();
    }


    public boolean isProductDetailContained(String productDetail) {
        return header.cartToolTip.getProductDetail().contains(productDetail);


    }

    public String getProductPriceInMiniShoppingCart() {
        return header.cartToolTip.getProductPrice();
    }

    public ShoppingCartPageObject clickGoToCartButton() {
        header.cartToolTip.clickGoToCartButton();
        return PageGenerator.getShoppingCartPage(driver);
    }

    public void selectProcessorDropdown(String dropdownOption) {
        waitForElementClickable(driver,ProductDetailPageUI.PROCESSOR_SELECT_DROPDOWN);
        waitForElementVisible(driver,ProductDetailPageUI.PROCESSOR_SELECT_DROPDOWN_OPTION,dropdownOption);
        selectDropdownOption(driver,ProductDetailPageUI.PROCESSOR_SELECT_DROPDOWN,dropdownOption);

    }

    public void enterProductQuantity(String quantity) {
        waitForElementVisible(driver,ProductDetailPageUI.QUANTITY_INPUT);
        sendKeyToElement(driver,ProductDetailPageUI.QUANTITY_INPUT,quantity);
    }

    public void clickUpdateButton() {
        waitForElementClickable(driver,ProductDetailPageUI.UPDATE_CART_BUTTON);
        clickElement(driver,ProductDetailPageUI.UPDATE_CART_BUTTON);
    }

    public void closeNotification() {
        notificationBar.closeNotification();
    }

    public ShoppingCartPageObject clickShoppingCartFromHeader() {
        header.account.clickShoppingCartLink();
        return PageGenerator.getShoppingCartPage(driver);
    }
    public String getProductPrice() {
        WaitHelper.waitForTextVisible(driver,ProductDetailPageUI.PRODUCT_PRICE);
        String productPrice = getDOMPropertyValue(driver,ProductDetailPageUI.PRODUCT_PRICE,"innerText").trim().substring(1);
        return productPrice;

    }
    public void clickAddYourReviewLink() {
        waitForElementClickable(driver,ProductDetailPageUI.ADD_REVIEW_LINK);
        clickElement(driver,ProductDetailPageUI.ADD_REVIEW_LINK);

    }
    public void enterReviewTitle(String reviewTitle) {
        waitForElementVisible(driver,ProductDetailPageUI.PRODUCT_REVIEW_TITLE);
        sendKeyToElement(driver,ProductDetailPageUI.PRODUCT_REVIEW_TITLE,reviewTitle);
    }
    public void enterReviewText(String reviewBody) {
        waitForElementVisible(driver,ProductDetailPageUI.PRODUCT_REVIEW_BODY);
        sendKeyToElement(driver,ProductDetailPageUI.PRODUCT_REVIEW_BODY,reviewBody);
    }

    public void selectProductRating(String rating) {
        waitForElementVisible(driver,ProductDetailPageUI.PRODUCT_RATING,rating);
        checkNativeRadio(driver,ProductDetailPageUI.PRODUCT_RATING,rating);
    }

    public void clickSubmitReview() {
        waitForElementClickable(driver,ProductDetailPageUI.SUBMIT_REVIEW_BUTTON);
        clickElement(driver,ProductDetailPageUI.SUBMIT_REVIEW_BUTTON);
    }

    public String getSuccessfulMessageForAddingProductReview() {
        return notificationBar.getNotificationMessage("Product review is successfully added.");

    }

    public void clickMyAccountLink() {
        header.account.clickMyAccountLink();
    }
}
