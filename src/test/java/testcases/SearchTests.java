package testcases;

import actions.components.Footer.FooterComponent;
import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import actions.pageObject.SearchPageObject;
import commons.helpers.RegisterLoginHelper;
import commons.base.BaseTest;
import commons.helpers.CommonHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


//NỢ PHẦN SOFT ASSERT VÀ HARD ASSERT:
public class SearchTests extends BaseTest {
    private HomePageObject homePageObject;
    private FooterComponent footerComponent;
    private SearchPageObject searchPage;

    private static final String EMPTY_KEYWORD_SEARCH_MESSAGE="Search term minimum length is 3 characters";
    private static final String NON_EXISTED_KEYWORD_SEARCH_MESSAGE="No products were found that matched your criteria.";

    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        WebDriver driver;
        driver = getBrowserDriver(browser,url);
        CommonHelper.setCookies(driver, RegisterLoginHelper.getNopCommerceCookie());
        homePageObject = PageGenerator.getHomePage(driver);
        footerComponent = PageGenerator.getFooterComponent(driver);
        searchPage = footerComponent.clickSearchLink("customer-service","Search");
        Assert.assertEquals(searchPage.getPageTitleText(),"Search");
    }

    @Test
    public void Search01_SearchWithEmptyKeyword() {
        searchPage.searchProduct("");
        Assert.assertEquals(searchPage.getValidationMessageForEmptyKeyword(),EMPTY_KEYWORD_SEARCH_MESSAGE);
    }

    @Test
    public void Search02_SearchWithNonExistedKeyword() {
        searchPage.searchProduct("Macbook Pro 2025");
        Assert.assertEquals(searchPage.getValidationMessageForNonExistedKeyword(),NON_EXISTED_KEYWORD_SEARCH_MESSAGE);

    }

    @Test
    public void Search03_SearchWithPartialProductName() {
        searchPage.searchProduct("Lenovo");
        Assert.assertTrue(searchPage.isProductSizeEqualTo(2));
        Assert.assertTrue(searchPage.displayedProductsContain("Lenovo IdeaCentre"));
        Assert.assertTrue(searchPage.displayedProductsContain("Lenovo Thinkpad Carbon Laptop"));
    }
    @Test
    public void Search04_SearchWithExactMatchProductName() {
        searchPage.searchProduct("Lenovo Thinkpad Carbon Laptop");
        //searchPage.waitUntilProductListSizeEqualTo(1);
        Assert.assertTrue(searchPage.isProductSizeEqualTo(1));
        Assert.assertTrue(searchPage.isDisplayedProductEqualTo("Lenovo Thinkpad Carbon Laptop"));
    }

    @Test
    public void Search05_AdvancedSearchWithParentCategory() {
        searchPage.enterKeywordSearch("Apple Macbook Pro");
        searchPage.checkAdvancedSearchCheckbox();
        searchPage.selectCategoryDropdown("Computers");
        searchPage.clickSearchButton();
        Assert.assertEquals(searchPage.getValidationMessageForNonExistedKeyword(),NON_EXISTED_KEYWORD_SEARCH_MESSAGE);
    }
    @Test
    public void Search06_AdvancedSearchWithSubCategory() {
        searchPage.enterKeywordAdvancedSearch("Apple Macbook Pro","Computers");
        searchPage.checkSubCategorySearchCheckbox();
        searchPage.clickSearchButton();
        Assert.assertTrue(searchPage.isProductSizeEqualTo(1));
        Assert.assertTrue(searchPage.isDisplayedProductEqualTo("Apple MacBook Pro"));
    }

    @Test
    public void Search07_AdvancedSearchWithIncorrectManufacturer() {
        searchPage.enterKeywordAdvancedSearch("Apple Macbook Pro","Computers","HP");
        searchPage.clickSearchButton();
        Assert.assertEquals(searchPage.getValidationMessageForNonExistedKeyword(),NON_EXISTED_KEYWORD_SEARCH_MESSAGE);
    }

    @Test
    public void Search08_AdvancedSearchWithCorrectManufacturer() {
        searchPage.enterKeywordAdvancedSearch("Apple Macbook Pro","Computers","Apple");
        searchPage.clickSearchButton();
        Assert.assertTrue(searchPage.isProductSizeEqualTo(1));
        Assert.assertTrue(searchPage.isDisplayedProductEqualTo("Apple MacBook Pro"));
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
