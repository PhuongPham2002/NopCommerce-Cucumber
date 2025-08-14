package stepdefinitions;

import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.constants.GlobalConstants;
import commons.helpers.DriverManager;
import commons.helpers.TestDataHelper;
import io.cucumber.core.exception.CucumberException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.List;


public class LoginSteps {
    LoginPageObject loginPage;
    HomePageObject homePage;
    public static final String EMPTY_EMAIL_PASSWORD_ERROR_MESSAGE = "Please enter your email";
    public static final String INVALID_EMAIL_ERROR_MESSAGE = "Please enter a valid email address.";
    public final static String NON_REGISTERED_EMAIL_ERROR_MESSAGE = "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found";
    public final static String EMPTY_PASSWORD_ERROR_MESSAGE = "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found";
    public final static String INVALID_PASSWORD_ERROR_MESSAGE = "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found";


    @And("The user access {string}")
    public void theUserAccessIntoNopcommerceWebpage(String webName) {
        String url = TestDataHelper.getData(webName.replace(" ", "_"));
        DriverManager.getDriver().get(url);
    }

    @Given("The user is on the login page")
    public void openLoginPage() {
        homePage = PageGenerator.getHomePage(DriverManager.getDriver());
        loginPage = homePage.clickLoginLink();
    }


    @When("The user log in with {string} and {string}")
    public void loginWithCredentials(String emailAddress, String password) {
        loginPage.enterLoginForm(TestDataHelper.getData("Email"), TestDataHelper.getData("Password"));
        homePage = loginPage.clickLoginButton();


    }


    @Then("The user {string} the login message {string}")
    public void userShouldSeeErrorMessage(String expectation, String expectedMessage) {
        if (expectation.equalsIgnoreCase("should see")) {
            if (expectedMessage.equals(EMPTY_EMAIL_PASSWORD_ERROR_MESSAGE) || expectedMessage.equals(INVALID_EMAIL_ERROR_MESSAGE)) {
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(expectedMessage));
            }
            if (List.of(
                    NON_REGISTERED_EMAIL_ERROR_MESSAGE,
                    EMPTY_PASSWORD_ERROR_MESSAGE,
                    INVALID_PASSWORD_ERROR_MESSAGE).contains(expectedMessage)) {
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(expectedMessage));
            }
        } else if (expectation.equalsIgnoreCase("should not see")) {
            if (expectedMessage.equals(EMPTY_EMAIL_PASSWORD_ERROR_MESSAGE) || expectedMessage.equals(INVALID_EMAIL_ERROR_MESSAGE)) {
                Assert.assertFalse(loginPage.isErrorMessageDisplayed(expectedMessage));
            }
            if (List.of(
                    NON_REGISTERED_EMAIL_ERROR_MESSAGE,
                    EMPTY_PASSWORD_ERROR_MESSAGE,
                    INVALID_PASSWORD_ERROR_MESSAGE).contains(expectedMessage)) {
                Assert.assertFalse(loginPage.isErrorMessageDisplayed(expectedMessage));
            }
        }
    }


    @Then("The user {string} navigated to {string}")
    public void iShouldBeNavigatedToHomePage(String expectation, String pageName) {
        if (expectation.equalsIgnoreCase("should be")) {
            switch (pageName.toLowerCase()) {
                case "home page":
                    Assert.assertTrue(loginPage.isMyAccountLinkVisible());
                    break;
                default:
                    throw new CucumberException("Page name is not valid: " + pageName);
            }
        } else if (expectation.equalsIgnoreCase("should not be")) {
            switch (pageName.toLowerCase()) {
                case "home page":
                    Assert.assertFalse(loginPage.isMyAccountLinkVisible());
                    break;
                default:
                    throw new CucumberException("Page name is not valid: " + pageName);
            }
        }
    }

    @When("The user enter {string} into email field and move to {string} field")
    public void theUserEnterIntoEmailFieldAndMoveToField(String emailAddress, String password) {
        loginPage.enterLoginForm(emailAddress, null);
    }
}
