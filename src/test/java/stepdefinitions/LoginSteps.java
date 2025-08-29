package stepdefinitions;

import commons.constants.LoginMessage;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.PageGenerator;
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

    @Given("The user access {string}")
    public void theUserAccessIntoNopcommerceWebpage(String webName) {
        String url = TestDataHelper.getData(webName);
        DriverManager.getDriver().get(url);
    }

    @And("The user is on the login page")
    public void openLoginPage() {
        homePage = PageGenerator.getHomePage(DriverManager.getDriver());
        loginPage = homePage.clickLoginLink();
    }


    @When("The user log in with {string} and {string}")
    public void loginWithCredentials(String emailAddress, String password) {
        loginPage.enterLoginForm(emailAddress, password);
        homePage = loginPage.clickLoginButton();


    }


    @Then("The user {string} the login message {string}")
    public void userShouldSeeErrorMessage(String expectation, String expectedMessage) {
        if (expectation.equalsIgnoreCase("should see")) {
            if (expectedMessage.equals(LoginMessage.EMPTY_EMAIL_PASSWORD_ERROR_MESSAGE) || expectedMessage.equals(LoginMessage.INVALID_EMAIL_ERROR_MESSAGE)) {
                Assert.assertTrue(loginPage.isErrorMessageDisplayed(expectedMessage));
            }
            else if (List.of(
                    LoginMessage.NON_REGISTERED_EMAIL_ERROR_MESSAGE,
                    LoginMessage.EMPTY_PASSWORD_ERROR_MESSAGE,
                    LoginMessage.INVALID_PASSWORD_ERROR_MESSAGE).contains(expectedMessage)) {
                Assert.assertTrue(loginPage.isSummaryErrorMessageDisplayed(expectedMessage));
            }
        } else if (expectation.equalsIgnoreCase("should not see")) {
            if (expectedMessage.equals(LoginMessage.EMPTY_EMAIL_PASSWORD_ERROR_MESSAGE) || expectedMessage.equals(LoginMessage.INVALID_EMAIL_ERROR_MESSAGE)) {
                Assert.assertFalse(loginPage.isErrorMessageDisplayed(expectedMessage));
            }
            if (List.of(
                    LoginMessage.NON_REGISTERED_EMAIL_ERROR_MESSAGE,
                    LoginMessage.EMPTY_PASSWORD_ERROR_MESSAGE,
                    LoginMessage.INVALID_PASSWORD_ERROR_MESSAGE).contains(expectedMessage)) {
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
