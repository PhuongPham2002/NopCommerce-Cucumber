package stepdefinitions;

import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import helpers.DriverManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;



public class LoginSteps {
    LoginPageObject loginPage;
    HomePageObject homePage;
    WebDriver driver;


    @Given("I am on the login page")
    public void openLoginPage(){
        driver = DriverManager.getDriver();
        homePage = PageGenerator.getHomePage(driver);
        loginPage=homePage.clickLoginLink();

    }


    //Login_01:

    @When("I log in with {string} and {string}")
    public void loginWithCredentials(String emailAddress, String password) {
        loginPage.enterLoginForm(emailAddress,password);

    }

    @When("I clicks login button")
    public void iClicksLoginButton(){
      loginPage.clickLoginButton();
    }

    @Then("I should see the {string}")
    public void iShouldSeeErrorMessage(String expectedMessage) {
        loginPage.verifyErrorMessages(expectedMessage);
    }


    //Login_02:

    @Then("I should be navigated to HomePage")
    public void iShouldBeNavigatedToHomePage() {
        loginPage.isMyAccountLinkVisible();
    }

    @Given("I log in as an {word}")
    public void iLogInAsAdmin(String role) {

    }


}
