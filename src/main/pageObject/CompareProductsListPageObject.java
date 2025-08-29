package pageObject;

import pageObject.components.Footer.FooterComponent;
import pageObject.components.Header.HeaderComponent;
import interfaces.pageUI.CompareProductListPageUI;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CompareProductsListPageObject extends BasePage {
    HeaderComponent header;
    FooterComponent footer;
    public static final String NO_ITEM_TO_COMPARE_MESSAGE="You have no items to compare.";

    public CompareProductsListPageObject(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
        this.footer=new FooterComponent(driver);
    }

    public boolean isProductNameContained(String productName) {
        try{
        waitForLoadingIconInvisible(driver);
            waitForNumberOfElementsTobe(CompareProductListPageUI.PRODUCT_NAME, 2);
            waitForListElementsVisible(CompareProductListPageUI.PRODUCT_NAME);
            List<WebElement> listElement = getListElement(CompareProductListPageUI.PRODUCT_NAME);
        List<String> productNames = new ArrayList<>();
        for (WebElement element:listElement){
            productNames.add(element.getText().trim());
        }
        return productNames.contains(productName);}
        catch (TimeoutException e){
            return false;
        }
    }

    public String getProductPrice(int productPlace) {
        waitForLoadingIconInvisible(driver);
        waitForNumberOfElementsTobe(CompareProductListPageUI.ALL_PRODUCT_PRICE, 2);
        waitForListElementsVisible(CompareProductListPageUI.ALL_PRODUCT_PRICE);
        return getElementText(CompareProductListPageUI.DYNAMIC_PRODUCT_PRICE, String.valueOf(productPlace));
    }


    public String getScreenSizePrice(int productPlace) {
        waitForNumberOfElementsTobe(CompareProductListPageUI.ALL_PRODUCT_SCREENS_SIZE, 2);
        waitForListElementsVisible(CompareProductListPageUI.ALL_PRODUCT_SCREENS_SIZE);
        return getElementText(CompareProductListPageUI.DYNAMIC_PRODUCT_SCREEN_SIZE, String.valueOf(productPlace));
    }

    public void clickClearListButton() {
        waitForElementClickable(CompareProductListPageUI.CLEAR_LIST_BUTTON);
        clickElement(CompareProductListPageUI.CLEAR_LIST_BUTTON);
    }

    public String getNoItemsToCompareMessage() {
        waitForLoadingIconInvisible(driver);
        waitForTextToBePresentInElement(CompareProductListPageUI.NO_ITEMS_TO_COMPARE_MESSAGE, NO_ITEM_TO_COMPARE_MESSAGE);
        return getElementText(CompareProductListPageUI.NO_ITEMS_TO_COMPARE_MESSAGE);

    }
    public BasePage hoverToHeaderProductCategoryAndClickToSubCategoryVisible(String productCategory,String subProductCategory) {
        header.productCategory.hoverToHeaderProductCategory(productCategory);
         return header.productCategory.clickSubProductCategory(productCategory,subProductCategory);
    }


    public NotebooksPageObject clickSubProductCategoryItem(String productCategory,String subProductCategory) {
        header.productCategory.clickSubProductCategory(productCategory,subProductCategory);
        return PageGenerator.getNoteBooksPage(driver);
    }

    public RecentlyViewedProductsPageObject clickRecentlyViewedProducts() {
        footer.customerService.clickCustomerServiceLink("Recently viewed products");
        return PageGenerator.getRecentlyReviewProduct(driver);
    }
}
