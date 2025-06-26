package stepdefinitions;

import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.DriverManager;
import data.helpers.LoginDataHelper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {
    LoginPageObject loginPage;
    HomePageObject homePage;
    WebDriver driver;


    @Given("user is on the login page")
    public void openLoginPage(){
        driver = DriverManager.getDriver();
        homePage = PageGenerator.getHomePage(driver);
        loginPage=homePage.clickLoginLink();

    }

    @When("user enters {string} and {string}")
    public void userEntersEmailAndPassword(String email, String password) {
        loginPage.enterLoginForm(email,password);
    }



    @When("user clicks login button")
    public void userClicksLoginButton(){
        homePage=loginPage.clickLoginButton();
    }
    @Then("a message {string} is displayed at {string}")
    public void verifyErrorMessages(String expectedMessage, String position){
        loginPage.verifyErrorMessages(expectedMessage,position);
    }



    //Login_02:

    @When("user enters valid email and valid password")
    public void userEntersValidEmailAndPassword(){
        loginPage.enterLoginForm(LoginDataHelper.EMAIL_ADDRESS,LoginDataHelper.PASSWORD);

    }

    @Then("my account link should be visible")
    public void verifyMyAccountVisible(){
        Assert.assertTrue(loginPage.isMyAccountLinkVisible());
    }



}
