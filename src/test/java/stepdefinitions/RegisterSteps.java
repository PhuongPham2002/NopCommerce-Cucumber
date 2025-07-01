package stepdefinitions;

import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import actions.pageObject.RegisterPageObject;
import helpers.DriverManager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

public class RegisterSteps {
    WebDriver driver;
    HomePageObject homePage;
    RegisterPageObject registerPage;

    @Given("I am on the register page")
    public void iAmOnTheRegisterPage() {
        driver = DriverManager.getDriver();
        homePage= PageGenerator.getHomePage(driver);
        registerPage=homePage.clickRegisterLink();
    }

    @When("I fill in register form with data:")
    public void iFillInRegisterFormWithData(DataTable registerData) {
        registerPage.fillInRegisterForm(registerData);
    }

    @And("I click register button")
    public void iClickRegisterButton() {
        registerPage.clickRegisterButton();
    }

    @Then("I should see the empty data error messages:")
    public void iShouldSeeTheErrorMessages(DataTable expectedMessage) {
        Map<String, String> expectedErrorMessage = expectedMessage.asMap();
//        Assert.assertEquals(expectedErrorMessage.get("FirstName"),registerPage.getErrorMessageForRequireField("FirstName"));
//        Assert.assertEquals(expectedErrorMessage.get("LastName"),registerPage.getErrorMessageForRequireField("LastName"));
//        Assert.assertEquals(expectedErrorMessage.get("EmailAddress"),registerPage.getErrorMessageForRequireField("Email"));
//        Assert.assertEquals(expectedErrorMessage.get("Password"),registerPage.getErrorMessageForRequireField("ConfirmPassword"));
        for (Map.Entry<String,String> entry: expectedErrorMessage.entrySet()) {
            String actualMessage = registerPage.getErrorMessageForRequireField(entry.getKey());
            Assert.assertEquals(actualMessage,entry.getValue(),"Failed at field: "+ entry.getKey());
        }
    }


    @Then("I should see the invalid password error messages {string}")
    public void iShouldSeeTheErrorMessages(String expectedMessage) {
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("Password"),expectedMessage);
    }
    @Then("I should see the mismatch password error messages {string}")
    public void iShouldSeeTheMismatchPasswordErrorMessages(String expectedMessage) {
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("ConfirmPassword"),expectedMessage);
    }


    @Then("I should see the invalid email error message {string}")
    public void iShouldSeeTheRegisterErrorMessage(String expectedMessage) {
        Assert.assertEquals(registerPage.getInvalidRegisterEmailMessage(),expectedMessage);
    }


    @Then("I should see the register successful messages {string}")
    public void iShouldSeeTheRegisterSuccessfulMessages(String expectedMessage) {
        Assert.assertEquals(registerPage.getSuccessfulRegisterMessage(),expectedMessage);
    }

    @When("I enter {string},{string},{string},{string},{string} in register form")
    public void fillInRegisterForm (String firstName, String lastName, String email, String password, String confirmPassword) {
        registerPage.fillInRegisterForm(firstName,lastName,email,password,confirmPassword);
    }




}
