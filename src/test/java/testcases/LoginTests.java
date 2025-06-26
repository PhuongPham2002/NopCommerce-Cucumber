package testcases;

import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.base.BaseTest;
import commons.constants.LoginMessageConstants;
import data.helpers.LoginDataHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    private HomePageObject homePage;
    private LoginPageObject loginPage;
    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        WebDriver driver;
        driver = getBrowserDriver(browser,url);
        homePage = PageGenerator.getHomePage(driver);
        loginPage = homePage.clickLoginLink();
    }

    @Test
    public void Login_01_EmptyData() {
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorLoginMessage(), LoginMessageConstants.EMPTY_EMAIL_PASSWORD_MESSAGE);
    }

    @Test
    public void Login_02_InvalidEmail() {
        loginPage.enterLoginForm(LoginDataHelper.INVALID_EMAIL,LoginDataHelper.PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getErrorLoginMessage(),LoginMessageConstants.INVALID_EMAIL_MESSAGE);
    }

    @Test
    public void Login_03_NonRegisteredEmail() {
        loginPage.enterLoginForm(LoginDataHelper.UNREGISTERED_EMAIL,LoginDataHelper.PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getUnregisteredErrorMessage(),LoginMessageConstants.NON_REGISTER_EMAIL_MESSAGE);
    }


    @Test
    public void Login_04_EmptyPassword() {
        loginPage.enterLoginForm(LoginDataHelper.EMAIL_ADDRESS,"");
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(),LoginMessageConstants.INVALID_PASSWORD_MESSAGE);

    }

    @Test
    public void Login_05_InvalidPassword(){
        loginPage.enterLoginForm(LoginDataHelper.EMAIL_ADDRESS,LoginDataHelper.INVALID_PASSWORD);
        loginPage.clickLoginButton();
        Assert.assertEquals(loginPage.getInvalidPasswordErrorMessage(),LoginMessageConstants.INVALID_PASSWORD_MESSAGE);
    }

    @Test
    public void Login_06_ValidInfo() {
       loginPage.enterLoginForm(LoginDataHelper.EMAIL_ADDRESS,LoginDataHelper.PASSWORD);
       homePage=loginPage.clickLoginButton();

    }
    public void Login_07_LoginWithInactiveAccount(){
        //TO DO IMPLEMENT - SAU KHI HỌC XONG SQL

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
