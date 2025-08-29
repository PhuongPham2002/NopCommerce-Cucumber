package interfaces.pageUI;

public class CompareProductListPageUI {
    public final static String PRODUCT_NAME ="css=tr.product-name a";
    public final static String ALL_PRODUCT_PRICE ="xpath=//tr[@class='product-price']/td[not(label)]";
    public final static String DYNAMIC_PRODUCT_PRICE ="xpath=//tr[@class='product-price']/td[not(label)][%s]";
    public final static String ALL_PRODUCT_SCREENS_SIZE ="xpath=//label[text()='Screensize']/ancestor::tr/td[not(label)]";
    public final static String DYNAMIC_PRODUCT_SCREEN_SIZE ="xpath=//label[text()='Screensize']/ancestor::tr/td[not(label)][%s]";
    public final static String CLEAR_LIST_BUTTON="css=a.clear-list";
    public final static String NO_ITEMS_TO_COMPARE_MESSAGE="css=div.no-data";

}
