package interfaces.pageUI;

public class CheckoutPageUI {
    public static final String SHIPPING_TO_SAME_ADDRESS_CHECKBOX="ID=ShipToSameAddress";
    public static final String DYNAMIC_CONTINUE_BUTTON="xpath=//div[@id='%s-buttons-container']/button[contains(@class,'%s-next-step-button')]";
    public static final String DYNAMIC_SHIPPING_METHOD="xpath=//label[contains(text(),'%s')]/preceding-sibling::input";
    public static final String DYNAMIC_CHECKOUT_TABS="xpath=//h2[text()='%s']//ancestor::li[contains(@class,'tab-section')]";
    public static final String DYNAMIC_PAYMENT_METHOD="xpath=//label[text()='%s']/preceding-sibling::input";
    public static final String CREDIT_CARD_DROPDOWN="ID=CreditCardType";
    public static final String CARDHOLDER_NAME_TEXTBOX="id=CardholderName";
    public static final String CARD_NUMBER_TEXTBOX="ID=CardNumber";
    public static final String MONTH_EXPIRATION="ID=ExpireMonth";
    public static final String YEAR_EXPIRATION="ID=ExpireYear";
    public static final String CARD_CODE_TEXTBOX ="ID=CardCode";
    public static final String DYNAMIC_FIELD_BY_ID ="id=BillingNewAddress_%s";
    public static final String STATE_LOADING_ICON="css=span[id='states-loading-progress']";
    public static final String DYNAMIC_LOADING_NEXT_STEP_TEXT="ID=%s-please-wait";
    public static final String PAGE_TITLE="xpath=//div[@class='page-title']/h1[text()='Checkout']";



}
