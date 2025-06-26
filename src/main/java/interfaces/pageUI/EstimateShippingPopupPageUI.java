package interfaces.pageUI;

public class EstimateShippingPopupPageUI {
    public final static String COUNTRY_DROPDOWN="CSS=div.estimate-shipping-popup div.shipping-address select[id='CountryId']";
    public final static String CITY_DROPDOWN="CSS=div.estimate-shipping-popup div.shipping-address select[id='StateProvinceId']";
    public final static String POSTAL_CODE_TEXTBOX ="css=input[id='ZipPostalCode']";
    public final static String DYNAMIC_SHIPPING_METHOD_RADIO="XPATH=//div[@class='estimate-shipping-row-item-radio']/following-sibling::div[text()='%s']";
    public final static String APPLY_BUTTON="CSS=div.apply-shipping-button-container button";
    public final static String CLOSE_BUTTON="css=button.mfp-close";
    public final static String POPUP="css=div.estimate-shipping-popup";
    public final static String DYNAMIC_SHIPPING_METHOD="xpath=//label[contains(text(),'%s')]/preceding-sibling::input";

}
