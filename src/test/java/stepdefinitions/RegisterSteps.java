package stepdefinitions;

import pageObject.HomePageObject;
import pageObject.PageGenerator;
import pageObject.RegisterPageObject;
import commons.helpers.DriverManager;
import io.cucumber.core.exception.CucumberException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class RegisterSteps {
    HomePageObject homePage;
    RegisterPageObject registerPage;


    @Given("The user is on the register page")
    public void iAmOnTheRegisterPage() {
        homePage= PageGenerator.getHomePage(DriverManager.getDriver());
        registerPage=homePage.clickRegisterLink();
    }

    @When("The user register a new account with the following information")
    public void iFillInRegisterFormWithData(DataTable registerInfo) {
        Map<String,String> registerData = registerInfo.asMap();
        registerPage.fillInRegisterForm(registerData);
        registerPage.clickRegisterButton();
    }

    @Then("The user should see the following register error message")
    public void iShouldSeeTheErrorMessages(DataTable expectedMessage) {
        Map<String, String> expectedErrorMessage = expectedMessage.asMap();

        for (Map.Entry<String,String> entry: expectedErrorMessage.entrySet()) {
            String actualMessage = registerPage.getErrorMessageForRequireField(entry.getValue(),entry.getKey());
            Assert.assertEquals(actualMessage,entry.getValue(),"Failed at field: "+ entry.getKey());
        }
    }



    @Then("The user should see the register error message {string} at {string} field")
    public void iShouldSeeTheErrorMessages(String expectedMessage, String fieldName) {
        Map<String, String> registerFields = new HashMap<>();
        registerFields.put("password", "Password");
        registerFields.put("email", "Email");
        registerFields.put("confirm password", "ConfirmPassword");
        if (registerFields.get(fieldName)==null){
            throw new CucumberException("FieldName: "+ fieldName + " is not mapped in registerFields");
        }
        Assert.assertEquals(registerPage.getErrorMessageForRequireField(expectedMessage,registerFields.get(fieldName.toLowerCase())),expectedMessage);
    }


    @Then("The user should see the register successful messages {string}")
    public void iShouldSeeTheRegisterSuccessfulMessages(String expectedMessage) {
        Assert.assertEquals(registerPage.getSuccessfulRegisterMessage(),expectedMessage);
    }




}
