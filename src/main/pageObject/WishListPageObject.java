package pageObject;

import pageObject.components.Footer.FooterComponent;
import pageObject.components.Header.HeaderComponent;
import interfaces.pageUI.WishListPageUI;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WishListPageObject extends BasePage {
    HeaderComponent header;
    FooterComponent footer;
    private final static String EMPTY_WISHLIST_MESSAGE ="The wishlist is empty!";


    public WishListPageObject(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
        this.footer = new FooterComponent(driver);
    }
    public List<String> getProductName() {
        waitForLoadingIconInvisible(driver);
        waitForListElementsVisible(WishListPageUI.PRODUCT_NAME);
        return getListElementText(WishListPageUI.PRODUCT_NAME);

    }

    public boolean hasWishListContained(String productName) {
        return getProductName().contains(productName);
    }

    public void clickWishListLinkFromSharedLink() {
        waitForLoadingIconInvisible(driver);
        //Vẫn cùng màn hình wishlist và vẫn test case này, step trước - getProductName mình đã wait icon visible rồi thì tới method này cần ko?
        waitForElementClickable( WishListPageUI.SHARED_LINK_WISHLIST);
    }

    public void clickAddToCartCheckbox(String productName) {
        waitForElementClickable(WishListPageUI.DYNAMIC_ADD_TO_CART_CHECKBOX,productName);
        //Có cần thêm wait để icon loading visible ko? mình nghĩ là ko vì test case trc đã làm, còn nếu đùng 1 cái mà mình
        //vào màn hình này thì cần wait biến mất đi rồi mới nên clickable. 
        clickElement( WishListPageUI.DYNAMIC_ADD_TO_CART_CHECKBOX,productName);
    }

    public ShoppingCartPageObject clickAddToCartButton() {
        waitForElementClickable(WishListPageUI.ADD_TO_CART_BUTTON);
        clickElement(WishListPageUI.ADD_TO_CART_BUTTON);
        return PageGenerator.getShoppingCartPage(driver);

    }

    public void clickProductRemoveButton() {
        waitForListElementsVisible(WishListPageUI.PRODUCT_REMOVE_BUTTON);
        List<WebElement> listElement = getListElement(WishListPageUI.PRODUCT_REMOVE_BUTTON);
        for (WebElement element:listElement){
            waitForElementClickable(element);
            element.click();
        }
    }


    public String getMessageForEmptyWishList() {
        waitForLoadingIconInvisible(driver);
        waitForTextToBePresentInElement(WishListPageUI.EMPTY_WISHLIST_MESSAGE,EMPTY_WISHLIST_MESSAGE);
        return getElementText(WishListPageUI.EMPTY_WISHLIST_MESSAGE);
    }

    public boolean isWishListProductEmpty() {
       try {
           waitForNumberOfElementsTobe(WishListPageUI.PRODUCT_NAME,0);
           return getListElementsSize(WishListPageUI.PRODUCT_NAME)==0;
       } catch (TimeoutException e){
           log.info("WishList is not empty within timeout");
           return false;
       }


    }


    public BasePage hoverToHeaderProductCategoryAndClickToSubCategoryVisible(String productCategory,String subProductCategory) {
        header.productCategory.hoverToHeaderProductCategory(productCategory);
        return header.productCategory.clickSubProductCategory(productCategory,subProductCategory);
    }

    public NotebooksPageObject clickSubProductCategory(String productCategory, String subProductCategory) {
        header.productCategory.clickSubProductCategory(productCategory,subProductCategory);
        return PageGenerator.getNoteBooksPage(driver);
    }

    public CompareProductsListPageObject clickCompareProductsListLink() {
        footer.customerService.clickCustomerServiceLink("Compare products list");
        return PageGenerator.getCompareProductsListPage(driver);
    }

    public void clearWishListIfNotEmpty() {
        if (!isWishListProductEmpty()){
            log.info("Cleaning up Wishlist if not empty.");
            clickProductRemoveButton();
        } else {
            log.info("Wishlist already empty → no cleanup needed.");
        }
    }
}
