package stepdefinitions;

import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import helpers.DriverManager;
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


    @Given("The user access Nopcommerce Webpage {string}")
    public void theUserAccessIntoNopcommerceWebpage(String url) {
        DriverManager.getDriver().get(url);
    }

    @Given("The user is on the login page")
    public void openLoginPage(){
        homePage = PageGenerator.getHomePage(DriverManager.getDriver());
        loginPage=homePage.clickLoginLink();
    }


    @When("The user log in with {string} and {string}")
    public void loginWithCredentials(String emailAddress, String password) {
        loginPage.enterLoginForm(emailAddress,password);

    }

    @When("The user click login button")
    public void clicksLoginButton(){
        loginPage.clickLoginButton();
    }

    @Then("The user should see the message {string}")
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
}
