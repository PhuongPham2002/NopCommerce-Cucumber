package interfaces.pageUI;

public class NotebooksPageUI {
    public final static String PRODUCT_TITLE_TEXT ="CSS=div.product-item div.details h2.product-title a";
    public final static String DYNAMIC_PRODUCT_PRICE_TEXT ="XPATH=//span[@class='price actual-price']";
    public final static String PAGINATION_NEXT_ICON = "CSS=li.next-page";
    public final static String CURRENT_PAGE="Css=li.current-page";
    public final static String DYNAMIC_PAGINATION_PAGE_BY_NUMBER ="xpath=//li[@class='individual-page']/a[text()='%s']";
    public final static String PAGINATION_PREVIOUS_ICON = "CSS=li.previous-page";
    public final static String ITEM_GRID ="css=div.item-grid";
    public static final String DYNAMIC_PRODUCT_TITLE = "xpath=//h2[@class='product-title']/a[text()='%s']";
    public static final String DYNAMIC_ADD_TO_COMPARE_BUTTON ="XPATH=//h2[@class='product-title']/a[text()='%s']/ancestor::div[@class='product-item']//button[contains(@class,'add-to-compare-list-button')]";
    public static final String SUCCESSFUL_MESSAGE_ADD_TO_COMPARE="CSS=div.bar-notification.success p";
    public static final String DYNAMIC_ADD_TO_CART_BUTTON="XPATH=//a[text()='%s']/parent::h2[@class='product-title']/following-sibling::div[@class='add-info']//button[contains(@class,'product-box-add-to-cart-button')]";
    public static final String ESTIMATE_SHIPPING_BUTTON="CSS=a.estimate-shipping-button";
}
