package actions.pageObject;

import commons.base.BasePage;
import interfaces.enums.AddressFields;
import interfaces.enums.CheckoutStep;
import interfaces.pageUI.CheckoutPageUI;
import org.openqa.selenium.WebDriver;

public class CheckoutPageObject extends BasePage {
    WebDriver driver;
    public CheckoutPageObject(WebDriver driver){
        super(driver);
    }


    public void selectShipToTheSameAddressCheckbox() {
        waitForUrlContains("onepagecheckout");
        //waitForElementVisible(CheckoutPageUI.PAGE_TITLE);
        waitForElementClickable(CheckoutPageUI.SHIPPING_TO_SAME_ADDRESS_CHECKBOX);
        checkNativeCheckbox(CheckoutPageUI.SHIPPING_TO_SAME_ADDRESS_CHECKBOX);
    }

    public void clickContinueButton(String checkoutTabName) {
        CheckoutStep checkoutStep = CheckoutStep.fromDisplayName(checkoutTabName);
        String locator = checkoutStep.getLocator(CheckoutPageUI.DYNAMIC_CONTINUE_BUTTON);
        waitForElementClickable(locator);
        clickElement(locator);

    }
    public void waitForLoadingNextStepInvisible(String tabName){

    }

    public boolean isPreviousCheckoutTabClosedAndNextCheckoutTabShowed(String previousTabName, String nextTabName) {
        String locator = CheckoutStep.fromDisplayName(previousTabName).getLocator(CheckoutPageUI.DYNAMIC_CHECKOUT_TABS);
        waitForAttributeContains(CheckoutPageUI.DYNAMIC_CHECKOUT_TABS, "class", "active", nextTabName);
        String attributeValue = getAttributeValue(CheckoutPageUI.DYNAMIC_CHECKOUT_TABS, "class", nextTabName);
        log.info("Value of attribute Class: "+ attributeValue);
        return attributeValue!=null && attributeValue.contains("active");
    }

    public void selectShippingMethodRadioButton(String option) {
        waitForElementClickable(CheckoutPageUI.DYNAMIC_SHIPPING_METHOD, option);
        checkNativeRadio(CheckoutPageUI.DYNAMIC_SHIPPING_METHOD, option, option);

    }

    public void selectPaymentMethodRadioButton(String paymentMethod) {
        waitForElementClickable(CheckoutPageUI.DYNAMIC_PAYMENT_METHOD, paymentMethod);
        checkNativeRadio(CheckoutPageUI.DYNAMIC_PAYMENT_METHOD, paymentMethod);
    }


    public void selectCreditCardDropdown(String creditCard) {
        waitForElementVisible(CheckoutPageUI.CREDIT_CARD_DROPDOWN);
        waitForTextToBePresentInElement(CheckoutPageUI.CREDIT_CARD_DROPDOWN, creditCard);
        selectDropdownOption(CheckoutPageUI.CREDIT_CARD_DROPDOWN, creditCard);
    }

    public void enterCardHolderName(String cardName) {
        waitForElementVisible(CheckoutPageUI.CARDHOLDER_NAME_TEXTBOX);
        sendKeyToElement(CheckoutPageUI.CARDHOLDER_NAME_TEXTBOX, cardName);
    }

    public void enterCardNumber(String cardNumber) {
        waitForElementVisible(CheckoutPageUI.CARD_NUMBER_TEXTBOX);
        sendKeyToElement(CheckoutPageUI.CARD_NUMBER_TEXTBOX, cardNumber);
    }

    public void selectExpirationMonthDropdown(String monthExpiration) {
        waitForElementClickable(CheckoutPageUI.MONTH_EXPIRATION);
        waitForTextToBePresentInElement(CheckoutPageUI.MONTH_EXPIRATION, monthExpiration);
        selectDropdownOption(CheckoutPageUI.MONTH_EXPIRATION, monthExpiration);

    }

    public void selectExpirationYearDropdown(String yearExpiration) {
        waitForElementClickable(CheckoutPageUI.YEAR_EXPIRATION);
        waitForTextToBePresentInElement(CheckoutPageUI.YEAR_EXPIRATION, yearExpiration);
        selectDropdownOption(CheckoutPageUI.YEAR_EXPIRATION, yearExpiration);

    }

    public void enterCardCode(String cardCode) {
        waitForElementVisible(CheckoutPageUI.CARD_CODE_TEXTBOX);
        sendKeyToElement(CheckoutPageUI.CARD_CODE_TEXTBOX, cardCode);
    }

    public void enterFirstnameTextbox(String firstName) {
       String locator = AddressFields.fieldsName("First name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(locator);
        sendKeyToElement(locator, firstName);
    }

    public void enterLastnameTextbox(String lastName) {
        String locator = AddressFields.fieldsName("Last name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(locator);
        sendKeyToElement(locator, lastName);
    }

    public void enterEmailAddressTextbox(String emailAddress) {
        String locator = AddressFields.fieldsName("Email address").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(locator);
        sendKeyToElement(locator, emailAddress);
    }

    public void selectCountryDropdown(String countryName) {
        String locator = AddressFields.fieldsName("Country name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(locator);
        waitForTextToBePresentInElement(locator, countryName);
        selectDropdownOption(locator, countryName);
    }

    public void enterCityTextbox(String cityName) {
        String locator = AddressFields.fieldsName("City name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(locator);
        sendKeyToElement(locator, cityName);

    }

    public void enterAddress1Textbox(String firstAddress) {
        String locator = AddressFields.fieldsName("First address").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(locator);
        sendKeyToElement(locator, firstAddress);
    }

    public void enterPostalCodeTextbox(String zipPostalCode) {
        String locator = AddressFields.fieldsName("Zip / postal code").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(locator);
        sendKeyToElement(locator, zipPostalCode);
    }

    public void enterPhoneNumberTextbox(String phoneNumber) {
        String locator = AddressFields.fieldsName("Phone number").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(locator);
        sendKeyToElement(locator, phoneNumber);
    }

    public void selectProvinceDropdown(String provinceName) {
        String locator = AddressFields.fieldsName("State/Province name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(locator);
        waitForElementInvisible(CheckoutPageUI.STATE_LOADING_ICON);
        waitForTextToBePresentInElement(locator, provinceName);
        selectDropdownOption(locator, provinceName);


    }
}
