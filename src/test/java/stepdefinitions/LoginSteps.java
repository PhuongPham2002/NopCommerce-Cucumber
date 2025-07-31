package stepdefinitions;

import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.constants.GlobalConstants;
import commons.helpers.DriverManager;
import commons.helpers.TestDataHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;


public class LoginSteps {
    LoginPageObject loginPage;
    HomePageObject homePage;
    public static final String EMPTY_EMAIL_PASSWORD_ERROR_MESSAGE ="Please enter your email";
    public static final String INVALID_EMAIL_ERROR_MESSAGE ="Please enter a valid email address.";
    public final static String NON_REGISTERED_EMAIL_ERROR_MESSAGE = "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found";
    public final static String EMPTY_PASSWORD_ERROR_MESSAGE = "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found";
    public final static String INVALID_PASSWORD_ERROR_MESSAGE = "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found";


    @Given("The user access {string}")
    public void theUserAccessIntoNopcommerceWebpage(String webName) {
        String url = TestDataHelper.getData(webName.replace(" ","_"));
        System.out.println();
        DriverManager.getDriver().get(url);
    }

    @Given("The user is on the login page")
    public void openLoginPage(){
        homePage = PageGenerator.getHomePage(DriverManager.getDriver());
        loginPage=homePage.clickLoginLink();
    }


    @When("The user log in with {string} and {string}")
    public void loginWithCredentials(String emailAddress, String password) {
        loginPage.enterLoginForm(GlobalConstants.LOGIN_EMAIL,GlobalConstants.LOGIN_PASSWORD);
        homePage=loginPage.clickLoginButton();


    }


    @Then("The user should see the login message {string}")
    public void userShouldSeeErrorMessage(String expectedMessage) {
        if (expectedMessage.equals(EMPTY_EMAIL_PASSWORD_ERROR_MESSAGE) || expectedMessage.equals(INVALID_EMAIL_ERROR_MESSAGE)){
            Assert.assertEquals(loginPage.getEmailErrorMessage(),expectedMessage);
        }
        if (List.of(
                NON_REGISTERED_EMAIL_ERROR_MESSAGE,
                EMPTY_PASSWORD_ERROR_MESSAGE,
                INVALID_PASSWORD_ERROR_MESSAGE).contains(expectedMessage)){
            Assert.assertEquals(loginPage.getLoginSummaryErrorMessage(),expectedMessage);
        }
    }


    @Then("The user should be navigated to HomePage")
    public void iShouldBeNavigatedToHomePage() {
        loginPage.isMyAccountLinkVisible();
    }

    @When("The user enter {string} into email field and move to {string} field")
    public void theUserEnterIntoEmailFieldAndMoveToField(String emailAddress, String password) {
        loginPage.enterLoginForm(emailAddress,null);
    }

    @And("the user logins application {string} with {string} and {string}")
    public void the_user_logins_application_with_username_and_password(String applicationName, String username, String password) {
        theUserAccessIntoNopcommerceWebpage(applicationName);
        loginPage.clickLoginButton();
        loginPage.enterLoginForm(username, password);
        loginPage.clickLoginButton();
    }
}
