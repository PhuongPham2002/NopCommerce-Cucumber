package commons.helpers;

import actions.pageObject.*;
import actions.pageObject.PageGenerator;
import commons.base.BaseTest;
import data.helpers.RegisterDataHelper;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Set;


public class RegisterLoginHelper extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private String registeredEmail;

    @Setter @Getter
    private static Set<Cookie> nopCommerceCookie;


    @BeforeTest
    @Parameters({"browser","url"})
    public void preconditionBeforeTest(String browser, String url){
        driver = getBrowserDriver(browser,url);
        homePage = PageGenerator.getHomePage(driver);
        registerPage = homePage.clickRegisterLink();

        //Register new account:
        registerPage.fillRegisterForm(RegisterDataHelper.provideValidRequiredFields());
        registeredEmail = registerPage.getRegisteredEmailAddress();
        homePage = registerPage.clickRegisterButton();

        Assert.assertEquals(homePage.getSuccessfulRegisterMessage(),"Your registration completed");
        homePage = registerPage.clickLogoutLink();

        //Login with registered data:
        loginPage = homePage.clickLoginLink();
        loginPage.enterLoginForm(registeredEmail,RegisterDataHelper.PASSWORD);
        homePage=loginPage.clickLoginButton();

        nopCommerceCookie=driver.manage().getCookies();
        log.info("all cookie value :"+nopCommerceCookie);
        driver.quit();

    }


}
