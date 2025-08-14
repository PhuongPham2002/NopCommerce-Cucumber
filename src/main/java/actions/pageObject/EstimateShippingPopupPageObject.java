package actions.pageObject;

import commons.base.BasePage;
import interfaces.pageUI.EstimateShippingPopupPageUI;
import org.openqa.selenium.WebDriver;

public class EstimateShippingPopupPageObject extends BasePage {

    public EstimateShippingPopupPageObject(WebDriver driver) {
        super(driver);
    }

    public void selectCountryDropdown(String country) {
        waitForElementVisible(EstimateShippingPopupPageUI.POPUP);
        waitForElementVisible(EstimateShippingPopupPageUI.COUNTRY_DROPDOWN);
        waitForTextToBePresentInElement(EstimateShippingPopupPageUI.COUNTRY_DROPDOWN, country);
        selectDropdownOption(EstimateShippingPopupPageUI.COUNTRY_DROPDOWN, country);
    }

    public void selectCityDropdown(String city) {
        waitForElementVisible(EstimateShippingPopupPageUI.POPUP);
        waitForElementVisible(EstimateShippingPopupPageUI.CITY_DROPDOWN);
        waitForTextToBePresentInElement(EstimateShippingPopupPageUI.CITY_DROPDOWN, city);
        selectDropdownOption(EstimateShippingPopupPageUI.CITY_DROPDOWN, city);
    }

    public void selectMethodShippingRadio(String shippingMethod) {
        waitForElementVisible(EstimateShippingPopupPageUI.POPUP);
        waitForElementClickable(EstimateShippingPopupPageUI.DYNAMIC_SHIPPING_METHOD_RADIO, shippingMethod);
        checkCustomRadio(EstimateShippingPopupPageUI.DYNAMIC_SHIPPING_METHOD_RADIO, "class", "active", shippingMethod);

    }

    public ShoppingCartPageObject clickApplyButton() {
        waitForElementClickable(EstimateShippingPopupPageUI.APPLY_BUTTON);
        clickElement(EstimateShippingPopupPageUI.APPLY_BUTTON);
        return PageGenerator.getShoppingCartPage(driver);
    }


    public void enterPostalCode(String postalCode) {
        waitForElementVisible(EstimateShippingPopupPageUI.POPUP);
        waitForElementVisible(EstimateShippingPopupPageUI.POSTAL_CODE_TEXTBOX);
        sendKeyToElement(EstimateShippingPopupPageUI.POSTAL_CODE_TEXTBOX, postalCode);
    }
}
