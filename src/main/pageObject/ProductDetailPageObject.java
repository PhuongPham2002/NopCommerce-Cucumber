package pageObject;

import pageObject.components.Header.HeaderAccountComponent;
import pageObject.components.Header.HeaderComponent;
import pageObject.components.NotificationBarComponent;
import interfaces.pageUI.ProductDetailPageUI;
import org.openqa.selenium.WebDriver;

public class ProductDetailPageObject extends BasePage {
    HeaderAccountComponent headerAccount;
    HeaderComponent header;
    NotificationBarComponent notificationBar;
    public static final String ADD_WISHLIST_SUCCESSFUL_MESSAGE="The product has been added to your wishlist";
    public static final String ADD_TO_CART_SUCCESSFUL_MESSAGE="The product has been added to your shopping cart";

    public ProductDetailPageObject(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
        this.headerAccount = new HeaderAccountComponent(driver);
        this.notificationBar = new NotificationBarComponent(driver);
    }

    public WishListPageObject clickWishListLinkFromHeader() {
        waitForLoadingIconInvisible(driver);
        waitForElementInvisible(ProductDetailPageUI.ADD_WISHLIST_SUCCESSFUL_MESSAGE);
        header.account.clickWishListLink();
        return PageGenerator.getWishListPage(driver);
    }

    public WishListPageObject clickWishListLinkFromHeaderAccount() {
        return headerAccount.clickWishListLink();
    }

    public void clickAddToWishListButton(String appleMacBookPro) {
        waitForElementClickable(ProductDetailPageUI.ADD_WISHLIST_BUTTON);
        clickElement(ProductDetailPageUI.ADD_WISHLIST_BUTTON);

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
        waitForElementVisible(ProductDetailPageUI.RAM_SELECT_DROPDOWN);
        waitForElementVisible(ProductDetailPageUI.RAM_SELECT_DROPDOWN, option);
        selectDropdownOption(ProductDetailPageUI.RAM_SELECT_DROPDOWN, option);
    }

    public void selectRadioButton(String label, String option) {
        waitForElementClickable(ProductDetailPageUI.RADIO_BUTTON, label, option);
        checkNativeRadio(ProductDetailPageUI.RADIO_BUTTON, label, option);
    }

    public void selectSoftwareCheckbox(String option) {
        waitForElementClickable(ProductDetailPageUI.CHECKBOX_OPTION, option);
        checkNativeCheckbox(ProductDetailPageUI.CHECKBOX_OPTION, option);

    }

    public void clickAddToCartButton() {
        waitForElementClickable(ProductDetailPageUI.ADD_TO_CART_BUTTON);
        clickElement(ProductDetailPageUI.ADD_TO_CART_BUTTON);
    }

    public String getSuccessfulMessageForAddingProductToCart() {
        waitForLoadingIconInvisible(driver);
        waitForTextToBePresentInElement(ProductDetailPageUI.SUCCESSFUL_MESSAGE_ADDING_TO_CART, ADD_TO_CART_SUCCESSFUL_MESSAGE);
        return getElementText(ProductDetailPageUI.SUCCESSFUL_MESSAGE_ADDING_TO_CART);

    }

    public void hoverToShoppingCartFromHeader() {
        clickElement(ProductDetailPageUI.MESSAGE_CLOSE_BUTTON);
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
        waitForElementClickable(ProductDetailPageUI.PROCESSOR_SELECT_DROPDOWN);
        waitForElementVisible(ProductDetailPageUI.PROCESSOR_SELECT_DROPDOWN_OPTION, dropdownOption);
        selectDropdownOption(ProductDetailPageUI.PROCESSOR_SELECT_DROPDOWN, dropdownOption);

    }

    public void enterProductQuantity(String quantity) {
        waitForElementVisible(ProductDetailPageUI.QUANTITY_INPUT);
        sendKeyToElement(ProductDetailPageUI.QUANTITY_INPUT, quantity);
    }

    public void clickUpdateButton() {
        waitForElementClickable(ProductDetailPageUI.UPDATE_CART_BUTTON);
        clickElement(ProductDetailPageUI.UPDATE_CART_BUTTON);
    }

    public void closeNotification() {
        notificationBar.closeNotification();
    }

    public ShoppingCartPageObject clickShoppingCartFromHeader() {
        header.account.clickShoppingCartLink();
        return PageGenerator.getShoppingCartPage(driver);
    }
    public String getProductPrice() {
        waitForTextVisible(ProductDetailPageUI.PRODUCT_PRICE);
        String productPrice = getDOMPropertyValue(ProductDetailPageUI.PRODUCT_PRICE, "innerText").trim().substring(1);
        return productPrice;

    }
    public void clickAddYourReviewLink() {
        waitForElementClickable(ProductDetailPageUI.ADD_REVIEW_LINK);
        clickElement(ProductDetailPageUI.ADD_REVIEW_LINK);

    }
    public void enterReviewTitle(String reviewTitle) {
        waitForElementVisible(ProductDetailPageUI.PRODUCT_REVIEW_TITLE);
        sendKeyToElement(ProductDetailPageUI.PRODUCT_REVIEW_TITLE, reviewTitle);
    }
    public void enterReviewText(String reviewBody) {
        waitForElementVisible(ProductDetailPageUI.PRODUCT_REVIEW_BODY);
        sendKeyToElement(ProductDetailPageUI.PRODUCT_REVIEW_BODY, reviewBody);
    }

    public void selectProductRating(String rating) {
        waitForElementVisible(ProductDetailPageUI.PRODUCT_RATING, rating);
        checkNativeRadio(ProductDetailPageUI.PRODUCT_RATING, rating);
    }

    public void clickSubmitReview() {
        waitForElementClickable(ProductDetailPageUI.SUBMIT_REVIEW_BUTTON);
        clickElement(ProductDetailPageUI.SUBMIT_REVIEW_BUTTON);
    }

    public String getSuccessfulMessageForAddingProductReview() {
        return notificationBar.getNotificationMessage("Product review is successfully added.");

    }

    public void clickMyAccountLink() {
        header.account.clickMyAccountLink();
    }
}
