package actions.components.Header;

import commons.base.BasePage;
import interfaces.componentUI.header.HeaderAccountComponentUI;
import org.openqa.selenium.WebDriver;

public class HeaderAccountComponent extends BasePage {
    WebDriver driver;

    public HeaderAccountComponent(WebDriver driver) {
        this.driver = driver;
    }
    public void clickWishListLink() {
        waitForElementClickable(driver, HeaderAccountComponentUI.WISHLIST_LINK);
        clickElement(driver, HeaderAccountComponentUI.WISHLIST_LINK);
    }

    public void clickShoppingCartLink() {
        waitForElementClickable(driver, HeaderAccountComponentUI.SHOPPING_CART_LINK);
        clickElement(driver, HeaderAccountComponentUI.SHOPPING_CART_LINK);
    }
    public void hoverToShoppingCart(){
        hoverToElement(driver,HeaderAccountComponentUI.SHOPPING_CART_LINK);
    }

    public void clickRegisterLink(){
        waitForElementClickable(driver,HeaderAccountComponentUI.REGISTER_LINK);
        clickElement(driver,HeaderAccountComponentUI.REGISTER_LINK);
    }
    public void clickLogoutLink(){
        waitForElementClickable(driver,HeaderAccountComponentUI.LOGOUT_LINK);
        clickElement(driver,HeaderAccountComponentUI.LOGOUT_LINK);
    }
    public void clickLoginLink(){
        waitForElementClickable(driver,HeaderAccountComponentUI.LOGIN_LINK);
        clickElement(driver,HeaderAccountComponentUI.LOGIN_LINK);
    }

    public void clickMyAccountLink(){
        waitForElementClickable(driver,HeaderAccountComponentUI.ACCOUNT_LINK);
        clickElement(driver,HeaderAccountComponentUI.ACCOUNT_LINK);
    }

}
