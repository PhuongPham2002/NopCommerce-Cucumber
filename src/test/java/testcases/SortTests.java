package testcases;

import actions.pageObject.ComputerPageObject;
import actions.pageObject.NotebooksPageObject;
import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import commons.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SortTests extends BaseTest {
    private ComputerPageObject computerPage;
    private NotebooksPageObject notebooksPage;
    private HomePageObject homePageObject;

    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        WebDriver driver;
        driver = getBrowserDriver(browser,url);
        homePageObject = PageGenerator.getHomePage(driver);
        notebooksPage= (NotebooksPageObject) homePageObject.hoverToHeaderProductCategoryAndClickToSubProductCategory("Computers","Notebooks");
    }

    @Test
    public void Sort01_AscendingSortProductByName() {
        notebooksPage.sortProductNameByAscending("Name: A to Z");
        Assert.assertTrue(notebooksPage.isProductNameSortedByAscending());}

    @Test
    public void Sort02_DescendingSortProductByName() {
        notebooksPage.sortProductNameByAscending("Name: Z to A");
        Assert.assertTrue(notebooksPage.isProductNameSortedByDescending());}

    @Test
    public void Sort03_AscendingSortProductByPrice() {
        notebooksPage.sortProductNameByAscending("Price: Low to High");
        Assert.assertTrue(notebooksPage.isProductPriceSortedByAscending());}


    @Test
    public void Sort04_DescendingSortProductByPrice() {
        notebooksPage.sortProductNameByAscending("Price: High to Low");
        Assert.assertTrue(notebooksPage.isProductPriceSortedByDescending());}

    @Test
    public void Display01_DisplayMaximumThreeProductsAfterSelection() {
        notebooksPage.selectProductPerPageDropdown(3);
        Assert.assertTrue(notebooksPage.isProductListSizeEqualTo(3));
        Assert.assertTrue(notebooksPage.isPaginationAtPageOne(1));
        Assert.assertTrue(notebooksPage.isPaginationNextIconDisplayed());
        notebooksPage.clickPageNumber(2);
        Assert.assertTrue(notebooksPage.isPaginationPreviousIconDisplayed());}

    @Test
    public void Display02_DisplayMaximumSixProductsAfterSelection() {
        notebooksPage.selectProductPerPageDropdown(6);
        Assert.assertTrue(notebooksPage.isProductListSizeEqualTo(6));
        Assert.assertFalse(notebooksPage.isPaginationNextIconDisplayed());

    }
    @Test
    public void Display03_DisplayMaximumNineProductsAfterSelection() {
        notebooksPage.selectProductPerPageDropdown(9);
        Assert.assertTrue(notebooksPage.isProductListSizeEqualTo(9));
        Assert.assertFalse(notebooksPage.isPaginationNextIconDisplayed());

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
