
package testcases;
import actions.components.MyAccountSideBar.*;
import actions.pageObject.*;
import actions.pageObject.ComputerPageObject;
import actions.pageObject.PageGenerator;
import commons.base.BaseTest;
import commons.helpers.RegisterLoginHelper;
import commons.helpers.CommonHelper;
import data.helpers.CustomerDataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import dataObjects.CustomerData;
import org.testng.annotations.Test;

public class MyAccountTests extends BaseTest {
    WebDriver driver;
    private HomePageObject homePage;
    private CustomerInfoComponent customerInfoPage;
    private AddressesComponent addressesPage;
    private ChangePasswordComponent changePasswordPage;
    private MyAccountSideBarPageObject myAccountSideBarPage;
    private LoginPageObject loginPageObject;
    private MyProductReviewsComponent myProductReviewsPage;
    private ComputerPageObject computerPage;
    private CustomerData customerInfoData;
    private NotebooksPageObject notebooksPage;
    private ProductDetailPageObject productDetailPage;
    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        driver = getBrowserDriver(browser,url);
        CommonHelper.setCookies(driver, RegisterLoginHelper.getNopCommerceCookie());
        homePage=PageGenerator.getHomePage(driver);
        myAccountSideBarPage = homePage.clickMyAccountLink();

    }

    @Test
    public void MyAccount_01_UpdateCustomerInformation() {
        customerInfoPage = (CustomerInfoComponent) myAccountSideBarPage.navigateToMyAccountSideBarMenu("Customer info");
        customerInfoPage.updateCustomerInformation(CustomerDataHelper.updateCustomerInfo());
        customerInfoPage.assertUpdatedCustomerInfo(CustomerDataHelper.updateCustomerInfo());
    }

    @Test
    public void MyAccount_02_UpdateAddressInformation() {
        addressesPage = (AddressesComponent) myAccountSideBarPage.navigateToMyAccountSideBarMenu("Addresses");
        addressesPage.clickAddNewButton();
        addressesPage.updateCustomerAddresses(CustomerDataHelper.updateCustomerAddress());
        Assert.assertEquals(addressesPage.getAddingInfoSuccessfulMessage(),"The new address has been added successfully.");
        addressesPage.assertAddressesInfoAfterAdding(CustomerDataHelper.updateCustomerAddress());
    }

    @Test
    public void MyAccount_03_ChangePassword() {
        changePasswordPage= (ChangePasswordComponent) myAccountSideBarPage.navigateToMyAccountSideBarMenu("Change password");
        changePasswordPage.changePassword(CustomerDataHelper.updatePassword());
        Assert.assertEquals(changePasswordPage.getSuccessfulChangePasswordMessage(),"Password was changed");
        changePasswordPage.closeUpdateSuccessfulMessage();
        homePage=changePasswordPage.clickLogoutButton();
        loginPageObject = homePage.clickLoginLink();
        //Nhập với password cũ:  (chưa refactor builder cho login form)
        loginPageObject.enterLoginForm(CustomerDataHelper.EMAIL_ADDRESS, CustomerDataHelper.OLD_PASSWORD);
        loginPageObject.clickLoginButton();
        Assert.assertEquals(loginPageObject.getWrongPasswordErrorMessage(),"Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");

        //Nhập với password mới:
        loginPageObject.enterLoginForm(CustomerDataHelper.EMAIL_ADDRESS,CustomerDataHelper.OLD_PASSWORD);
        homePage =loginPageObject.clickLoginButton();
    }


    @Test
    public void MyAccount_04_AddProductReviews() {
        //pre-condition - cần có sẵn product review:
        notebooksPage=(NotebooksPageObject) homePage.hoverToHeaderProductCategoryAndClickToSubProductCategory("Computers","Notebooks");
        productDetailPage=notebooksPage.clickProduct("Apple MacBook Pro");
        productDetailPage.clickAddYourReviewLink();
        productDetailPage.enterReviewTitle("Review");
        productDetailPage.enterReviewText("Good");
        productDetailPage.selectProductRating("3");
        productDetailPage.clickSubmitReview();
        Assert.assertEquals(productDetailPage.getSuccessfulMessageForAddingProductReview(),"Product review is successfully added.");
        productDetailPage.closeNotification();
        productDetailPage.clickMyAccountLink();

        //

        myProductReviewsPage = (MyProductReviewsComponent) myAccountSideBarPage.navigateToMyAccountSideBarMenu("My product reviews");

        Assert.assertTrue(myProductReviewsPage.isNumberOfProductReviewAtLeastOne());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
