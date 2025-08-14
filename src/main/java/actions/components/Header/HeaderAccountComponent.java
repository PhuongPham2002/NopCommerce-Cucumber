package actions.components.Header;

import actions.pageObject.PageGenerator;
import actions.pageObject.WishListPageObject;
import commons.base.BasePage;
import interfaces.componentUI.header.HeaderAccountComponentUI;
import org.openqa.selenium.WebDriver;

import static interfaces.componentUI.header.HeaderAccountComponentUI.ACCOUNT_LINK;

public class HeaderAccountComponent extends BasePage {

    public HeaderAccountComponent(WebDriver driver) {
        super(driver);
    }

    public WishListPageObject clickWishListLink() {
        waitForElementClickable(HeaderAccountComponentUI.WISHLIST_LINK);
        clickElement(HeaderAccountComponentUI.WISHLIST_LINK);
        return PageGenerator.getWishListPage(driver);
    }

    public void clickShoppingCartLink() {
        waitForElementClickable(HeaderAccountComponentUI.SHOPPING_CART_LINK);
        clickElement(HeaderAccountComponentUI.SHOPPING_CART_LINK);
    }

    public void hoverToShoppingCart() {
        hoverToElement(HeaderAccountComponentUI.SHOPPING_CART_LINK);
    }

    public void clickRegisterLink() {
        waitForElementClickable(HeaderAccountComponentUI.REGISTER_LINK);
        clickElement(HeaderAccountComponentUI.REGISTER_LINK);
    }

    public void clickLogoutLink() {
        waitForElementClickable(HeaderAccountComponentUI.LOGOUT_LINK);
        clickElement(HeaderAccountComponentUI.LOGOUT_LINK);
    }

    public void clickLoginLink() {
        waitForElementClickable(HeaderAccountComponentUI.defineElementLocatorBasedOnLanguage().get(ACCOUNT_LINK));
        clickElement(HeaderAccountComponentUI.LOGIN_LINK);
    }

    public void clickMyAccountLink() {
        waitForElementClickable(ACCOUNT_LINK);
        clickElement(ACCOUNT_LINK);
    }

}
