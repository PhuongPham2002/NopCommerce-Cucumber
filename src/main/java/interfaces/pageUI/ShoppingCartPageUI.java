package interfaces.pageUI;

public class ShoppingCartPageUI {
    public final static String PRODUCT_NAME ="css=a.product-name";
    public final static String EDIT_LINK="css=div.edit-item a";
    public final static String DYNAMIC_PRODUCT_QUANTITY="xpath=//a[text()='%s']/parent::td/following-sibling::td[@class='quantity']//input";
    public final static String DYNAMIC_PRODUCT_DETAIL="XPATH=//a[text()='%s']/parent::td/div[@class='attributes']";
    public final static String TOTAL_PRICE ="css=tr.order-total span strong";
    public final static String PRODUCT_EMPTY_MESSAGE="CSS=div.page.shopping-cart-page div.page-body div.no-data";
    public final static String REMOVE_BUTTON = "css=button.remove-btn";
    public final static String DYNAMIC_PRODUCT_TOTAL_PRICE="xpath=//a[text()='%s']/parent::td[@class='product']/following-sibling::td[@class='subtotal']/span";
    public final static String ESTIMATE_SHIPPING_BUTTON="css=a.estimate-shipping-button";
    public final static String TERM_OF_SERVICE_BUTTON="css=div.terms-of-service input";
    public final static String CHECKOUT_BUTTON="css=button.checkout-button";





}
