// Có nên define step open page và back thành 1 method trong test case ko?

package testcases;

import actions.pageObject.*;
import commons.helpers.RegisterLoginHelper;
import commons.base.BaseTest;
import commons.helpers.CommonHelper;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.*;

public class WishlistTests extends BaseTest {
    private HomePageObject homePage;
    private NotebooksPageObject notebooksPage;
    private ProductDetailPageObject productDetailPage;
    private WishListPageObject wishListPage;
    private String productName;
    private ShoppingCartPageObject shoppingCartPage;
    private CompareProductsListPageObject compareProductsListPage;
    private RecentlyViewedProductsPageObject recentlyViewedPage;


    Set<String> expectedProductNames = new HashSet<>(Arrays.asList(
            "HP Envy 15.6-Inch Sleekbook",
            "Lenovo Thinkpad Carbon Laptop",
            "HP Spectre XT Pro UltraBook"));

    Set<String> unexpectedProductNames = new HashSet<>(Arrays.asList(
            "Apple MacBook Pro",
            "Asus Laptop"));
    private final static String EXPECTED_SUCCESSFUL_MESSAGE_FOR_ADDING_WISHLIST ="The product has been added to your wishlist";
    private final static String EMPTY_WISHLIST_MESSAGE ="The wishlist is empty!";
    private final static String ADDING_PRODUCT_T0_COMPARISON_LIST_MESSAGE ="The product has been added to your product comparison";
    private final static String N0_ITEM_COMPARE_MESSAGE="You have no items to compare.";


    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        WebDriver driver;
        productName = "Apple MacBook Pro";
        driver = getBrowserDriver(browser,url);
        CommonHelper.setCookies(driver, RegisterLoginHelper.getNopCommerceCookie());
        log.info("Open HomePage");

        homePage=PageGenerator.getHomePage(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        log.info("Navigate to Notebooks category");
        notebooksPage=(NotebooksPageObject) homePage.hoverToHeaderProductCategoryAndClickToSubProductCategory("Computers","Notebooks");
    }
//    public void OpenProductAndGoBack(String productName){
//        productDetailPage = notebooksPage.clickProduct(productName);
//        productDetailPage.clickBackToPreviousPage();}

    public void viewMultipleProducts(List<String> productNames){
        for (String productName:productNames){
            productDetailPage = notebooksPage.clickProduct(productName);
            productDetailPage.clickBackToPreviousPage();
        }
    }

    @Test
    public void Wishlist01_AddProductToWishlist() {
        log.info("Step 1: Navigate to a specific product: "+productName);
        productDetailPage = notebooksPage.clickProduct(productName);

        log.info("Step 2: Add products to Wishlist: "+ productName);
        productDetailPage.clickAddToWishListButton(productName);

        log.info("Step 3: Verify adding product successful message :"+EXPECTED_SUCCESSFUL_MESSAGE_FOR_ADDING_WISHLIST);
        Assert.assertEquals(productDetailPage.getSuccessfulMessageForAddingProductToWishList(),EXPECTED_SUCCESSFUL_MESSAGE_FOR_ADDING_WISHLIST);

        log.info("Step 4: Open header wishlist link");
        wishListPage = productDetailPage.clickWishListLinkFromHeader();

        log.info("Step 5: Verify wishlist contain added products");
        Assert.assertTrue(wishListPage.hasWishListContained(productName));

        log.info("Step 6: Open wishlist link from shared link");
        wishListPage.clickWishListLinkFromSharedLink();

        log.info("Step 7: Verify wishlist contain added products");
        Assert.assertTrue(wishListPage.hasWishListContained(productName));
    }

    public void addProductWishListPrecondition(){
        wishListPage.clearWishListIfNotEmpty();
        notebooksPage= (NotebooksPageObject) wishListPage.hoverToHeaderProductCategoryAndClickToSubCategoryVisible("Computers","Notebooks");

        notebooksPage.clickProduct(productName);
        productDetailPage.clickAddToWishListButton(productName);
        wishListPage = productDetailPage.clickWishListLinkFromHeader();
    }


    @Test
    public void Wishlist02_RemoveProductInWishlist() {
        addProductWishListPrecondition();
        wishListPage.clickProductRemoveButton();
        Assert.assertEquals(wishListPage.getMessageForEmptyWishList(),EMPTY_WISHLIST_MESSAGE);
        Assert.assertTrue(wishListPage.isWishListProductEmpty());
    }

    @Test
    public void Wishlist03_AddProductToCartFromWishlist() {
        addProductWishListPrecondition();
        wishListPage.clickAddToCartCheckbox(productName);
        shoppingCartPage = wishListPage.clickAddToCartButton();
        Assert.assertTrue(shoppingCartPage.hasShoppingCartContained(productName));
        wishListPage=shoppingCartPage.clickWishListLinkFromHeader();
        Assert.assertFalse(wishListPage.hasWishListContained(productName));
    }


    @Test
    public void Wishlist04_AddProductToCompare() {
        notebooksPage= (NotebooksPageObject) wishListPage.hoverToHeaderProductCategoryAndClickToSubCategoryVisible("Computers","Notebooks");
        notebooksPage.clickAddProductToCompareListButton("Apple MacBook Pro");
        notebooksPage.clickAddProductToCompareListButton("Asus Laptop");

        Assert.assertEquals(notebooksPage.getSuccessfulMessageForAddingProductsToComparisonList(),ADDING_PRODUCT_T0_COMPARISON_LIST_MESSAGE);

        compareProductsListPage = wishListPage.clickCompareProductsListLink();
        Assert.assertTrue(compareProductsListPage.isProductNameContained("Apple MacBook Pro"));
        Assert.assertTrue(compareProductsListPage.isProductNameContained("Asus Laptop"));
        Assert.assertEquals(compareProductsListPage.getProductPrice(1),"$1,500.00");
        Assert.assertEquals(compareProductsListPage.getProductPrice(2),"$1,800.00");

        Assert.assertEquals(compareProductsListPage.getScreenSizePrice(1),"15.6''");
        Assert.assertEquals(compareProductsListPage.getScreenSizePrice(2),"13.0''");
        compareProductsListPage.clickClearListButton();
        Assert.assertEquals(compareProductsListPage.getNoItemsToCompareMessage(),N0_ITEM_COMPARE_MESSAGE);
        Assert.assertFalse(compareProductsListPage.isProductNameContained("Asus Laptop"));
        Assert.assertFalse(compareProductsListPage.isProductNameContained("Apple MacBook Pro"));
    }

    @Test
    public void Wishlist05_ViewRecentlyViewedProduct() {
        notebooksPage= (NotebooksPageObject) compareProductsListPage.hoverToHeaderProductCategoryAndClickToSubCategoryVisible("Computers","Notebooks");

        List<String> productToView = new ArrayList<>(Arrays.asList(
                "Apple MacBook Pro",
                "Asus Laptop",
                "HP Envy 15.6-Inch Sleekbook",
                "Lenovo Thinkpad Carbon Laptop",
                "HP Spectre XT Pro UltraBook"));
        viewMultipleProducts(productToView);
        recentlyViewedPage = compareProductsListPage.clickRecentlyViewedProducts();
        Assert.assertTrue(recentlyViewedPage.isRecentlyViewedProductsMatched(expectedProductNames));
        Assert.assertFalse(recentlyViewedPage.isRecentlyViewedProductsMatched(unexpectedProductNames));

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
