package interfaces.pageUI;

public class ProductDetailPageUI {

    public static final String ADD_WISHLIST_BUTTON ="CSS=div.add-to-wishlist button";
    public static final String ADD_WISHLIST_SUCCESSFUL_MESSAGE ="CSS=div.bar-notification.success p.content";
    public static final String PROCESSOR_SELECT_DROPDOWN="xpath=//label[contains(text(),'Processor')]/parent::dt/following-sibling::dd[1]/select";
    public static final String PROCESSOR_SELECT_DROPDOWN_OPTION="xpath=//label[contains(text(),'Processor')]/parent::dt/following-sibling::dd[1]/select/option[text()='%s']";
    public static final String RAM_SELECT_DROPDOWN ="xpath=//label[contains(text(),'RAM')]/parent::dt/following-sibling::dd/select";
    public static final String RAM_SELECT_OPTION_DROPDOWN ="xpath=//label[contains(text(),'RAM')]/parent::dt/following-sibling::dd/select/option[text()='%s']";
    public static final String RADIO_BUTTON = "xpath=//label[contains(text(),'%s')]/parent::dt/following-sibling::dd//input/following-sibling::label[contains(text(),'%s')]";
    public static final String CHECKBOX_OPTION = "xpath=//label[text()='%s']";
    public static final String ADD_TO_CART_BUTTON="css=button.add-to-cart-button";
    public static final String UPDATE_CART_BUTTON="Css=button.add-to-cart-button";
    public static final String SUCCESSFUL_MESSAGE_ADDING_TO_CART ="css=div.bar-notification.success p.content";
    public static final String SUCCESSFUL_MESSAGE_UPDATE_TO_CART ="css=div.bar-notification.success p.content";
    public static final String MESSAGE_CLOSE_BUTTON="css=div.bar-notification.success p.content+span.close";
    public static final String QUANTITY_INPUT="CSS=input.qty-input";
    public static final String PRODUCT_PRICE ="css=div.product-price span";
    public static final String ADD_REVIEW_LINK="xpath=//a[text()='Add your review']";
    public static final String PRODUCT_REVIEW_TITLE="id=AddProductReview_Title";
    public static final String PRODUCT_REVIEW_BODY="id=AddProductReview_ReviewText";
    public static final String PRODUCT_RATING ="id=addproductrating_%s";
    public static final String SUBMIT_REVIEW_BUTTON="id=add-review";


}
