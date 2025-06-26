package actions.pageObject;

import commons.base.BasePage;
import interfaces.pageUI.EstimateShippingPopupPageUI;
import org.openqa.selenium.WebDriver;

public class EstimateShippingPopupPageObject extends BasePage {
    WebDriver driver;

    public EstimateShippingPopupPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void selectCountryDropdown(String country) {
        waitForElementVisible(driver, EstimateShippingPopupPageUI.POPUP);
        waitForElementVisible(driver,EstimateShippingPopupPageUI.COUNTRY_DROPDOWN);
        waitForTextToBePresentInElement(driver,EstimateShippingPopupPageUI.COUNTRY_DROPDOWN,country);
        selectDropdownOption(driver,EstimateShippingPopupPageUI.COUNTRY_DROPDOWN,country);
    }

    public void selectCityDropdown(String city) {
        waitForElementVisible(driver, EstimateShippingPopupPageUI.POPUP);
        waitForElementVisible(driver,EstimateShippingPopupPageUI.CITY_DROPDOWN);
        waitForTextToBePresentInElement(driver,EstimateShippingPopupPageUI.CITY_DROPDOWN,city);
        selectDropdownOption(driver,EstimateShippingPopupPageUI.CITY_DROPDOWN,city);
    }

    public void selectMethodShippingRadio(String shippingMethod) {
        waitForElementVisible(driver, EstimateShippingPopupPageUI.POPUP);
        waitForElementClickable(driver,EstimateShippingPopupPageUI.DYNAMIC_SHIPPING_METHOD_RADIO,shippingMethod);
        checkCustomRadio(driver,EstimateShippingPopupPageUI.DYNAMIC_SHIPPING_METHOD_RADIO,"class","active",shippingMethod);

    }

    public ShoppingCartPageObject clickApplyButton() {
        waitForElementClickable(driver,EstimateShippingPopupPageUI.APPLY_BUTTON);
        clickElement(driver,EstimateShippingPopupPageUI.APPLY_BUTTON);
        return PageGenerator.getShoppingCartPage(driver);
    }


    public void enterPostalCode(String postalCode) {
        waitForElementVisible(driver,EstimateShippingPopupPageUI.POPUP);
        waitForElementVisible(driver,EstimateShippingPopupPageUI.POSTAL_CODE_TEXTBOX);
        sendKeyToElement(driver,EstimateShippingPopupPageUI.POSTAL_CODE_TEXTBOX,postalCode);
    }
}
