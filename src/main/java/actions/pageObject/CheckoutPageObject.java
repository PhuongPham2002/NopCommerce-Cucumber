package actions.pageObject;

import commons.base.BasePage;
import commons.helpers.WaitHelper;
import interfaces.enums.AddressFields;
import interfaces.enums.CheckoutStep;
import interfaces.pageUI.CheckoutPageUI;
import org.openqa.selenium.WebDriver;

public class CheckoutPageObject extends BasePage {
    WebDriver driver;
    public CheckoutPageObject(WebDriver driver){
        this.driver = driver;
    }


    public void selectShipToTheSameAddressCheckbox() {
        waitForUrlContains(driver,"onepagecheckout");
        //waitForElementVisible(driver,CheckoutPageUI.PAGE_TITLE);
        waitForElementClickable(driver, CheckoutPageUI.SHIPPING_TO_SAME_ADDRESS_CHECKBOX);
        checkNativeCheckbox(driver,CheckoutPageUI.SHIPPING_TO_SAME_ADDRESS_CHECKBOX);
    }

    public void clickContinueButton(String checkoutTabName) {
        CheckoutStep checkoutStep = CheckoutStep.fromDisplayName(checkoutTabName);
        String locator = checkoutStep.getLocator(CheckoutPageUI.DYNAMIC_CONTINUE_BUTTON);
        waitForElementClickable(driver,locator);
        clickElement(driver,locator);

    }
    public void waitForLoadingNextStepInvisible(String tabName){

    }

    public boolean isPreviousCheckoutTabClosedAndNextCheckoutTabShowed(String previousTabName, String nextTabName) {
        String locator = CheckoutStep.fromDisplayName(previousTabName).getLocator(CheckoutPageUI.DYNAMIC_CHECKOUT_TABS);
        WaitHelper.waitForAttributeContains(driver,CheckoutPageUI.DYNAMIC_CHECKOUT_TABS,"class","active",nextTabName);
        String attributeValue = getAttributeValue(driver,CheckoutPageUI.DYNAMIC_CHECKOUT_TABS,"class",nextTabName);
        log.info("Value of attribute Class: "+ attributeValue);
        return attributeValue!=null && attributeValue.contains("active");
    }

    public void selectShippingMethodRadioButton(String option) {
        waitForElementClickable(driver,CheckoutPageUI.DYNAMIC_SHIPPING_METHOD,option);
        checkNativeRadio(driver,CheckoutPageUI.DYNAMIC_SHIPPING_METHOD,option,option);

    }

    public void selectPaymentMethodRadioButton(String paymentMethod) {
        waitForElementClickable(driver,CheckoutPageUI.DYNAMIC_PAYMENT_METHOD,paymentMethod);
        checkNativeRadio(driver,CheckoutPageUI.DYNAMIC_PAYMENT_METHOD,paymentMethod);
    }


    public void selectCreditCardDropdown(String creditCard) {
        waitForElementVisible(driver,CheckoutPageUI.CREDIT_CARD_DROPDOWN);
        waitForTextToBePresentInElement(driver,CheckoutPageUI.CREDIT_CARD_DROPDOWN,creditCard);
        selectDropdownOption(driver,CheckoutPageUI.CREDIT_CARD_DROPDOWN,creditCard);
    }

    public void enterCardHolderName(String cardName) {
        waitForElementVisible(driver,CheckoutPageUI.CARDHOLDER_NAME_TEXTBOX);
        sendKeyToElement(driver,CheckoutPageUI.CARDHOLDER_NAME_TEXTBOX,cardName);
    }

    public void enterCardNumber(String cardNumber) {
        waitForElementVisible(driver,CheckoutPageUI.CARD_NUMBER_TEXTBOX);
        sendKeyToElement(driver,CheckoutPageUI.CARD_NUMBER_TEXTBOX,cardNumber);
    }

    public void selectExpirationMonthDropdown(String monthExpiration) {
        waitForElementClickable(driver,CheckoutPageUI.MONTH_EXPIRATION);
        waitForTextToBePresentInElement(driver,CheckoutPageUI.MONTH_EXPIRATION,monthExpiration);
        selectDropdownOption(driver,CheckoutPageUI.MONTH_EXPIRATION,monthExpiration);

    }

    public void selectExpirationYearDropdown(String yearExpiration) {
        waitForElementClickable(driver,CheckoutPageUI.YEAR_EXPIRATION);
        waitForTextToBePresentInElement(driver,CheckoutPageUI.YEAR_EXPIRATION,yearExpiration);
        selectDropdownOption(driver,CheckoutPageUI.YEAR_EXPIRATION,yearExpiration);

    }

    public void enterCardCode(String cardCode) {
        waitForElementVisible(driver,CheckoutPageUI.CARD_CODE_TEXTBOX);
        sendKeyToElement(driver,CheckoutPageUI.CARD_CODE_TEXTBOX,cardCode);
    }

    public void enterFirstnameTextbox(String firstName) {
       String locator = AddressFields.fieldsName("First name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(driver,locator);
        sendKeyToElement(driver,locator,firstName);
    }

    public void enterLastnameTextbox(String lastName) {
        String locator = AddressFields.fieldsName("Last name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(driver,locator);
        sendKeyToElement(driver,locator,lastName);
    }

    public void enterEmailAddressTextbox(String emailAddress) {
        String locator = AddressFields.fieldsName("Email address").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(driver,locator);
        sendKeyToElement(driver,locator,emailAddress);
    }

    public void selectCountryDropdown(String countryName) {
        String locator = AddressFields.fieldsName("Country name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(driver,locator);
        waitForTextToBePresentInElement(driver,locator,countryName);
        selectDropdownOption(driver,locator,countryName);
    }

    public void enterCityTextbox(String cityName) {
        String locator = AddressFields.fieldsName("City name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(driver,locator);
        sendKeyToElement(driver,locator,cityName);

    }

    public void enterAddress1Textbox(String firstAddress) {
        String locator = AddressFields.fieldsName("First address").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(driver,locator);
        sendKeyToElement(driver,locator,firstAddress);
    }

    public void enterPostalCodeTextbox(String zipPostalCode) {
        String locator = AddressFields.fieldsName("Zip / postal code").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(driver,locator);
        sendKeyToElement(driver,locator,zipPostalCode);
    }

    public void enterPhoneNumberTextbox(String phoneNumber) {
        String locator = AddressFields.fieldsName("Phone number").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(driver,locator);
        sendKeyToElement(driver,locator,phoneNumber);
    }

    public void selectProvinceDropdown(String provinceName) {
        String locator = AddressFields.fieldsName("State/Province name").getLocator(CheckoutPageUI.DYNAMIC_FIELD_BY_ID);
        waitForElementVisible(driver,locator);
        waitForElementInvisible(driver,CheckoutPageUI.STATE_LOADING_ICON);
        waitForTextToBePresentInElement(driver,locator,provinceName);
        selectDropdownOption(driver,locator,provinceName);


    }
}
