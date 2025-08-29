package stepdefinitions;

import commons.constants.MyAccountMessage;
import pageObject.components.MyAccountSideBar.CustomerInfoComponent;
import pageObject.components.MyAccountSideBar.MyAccountSideBarPageObject;
import pageObject.HomePageObject;
import pageObject.LoginPageObject;
import pageObject.PageGenerator;
import commons.helpers.CommonHelper;
import commons.helpers.DriverManager;
import commons.helpers.TestDataHelper;
import interfaces.componentUI.myAccountSideBar.CustomerInfoPageUI;
import io.cucumber.core.exception.CucumberException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.JSONObject;
import org.testng.Assert;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MyAccountSteps {
    LoginPageObject loginPage;
    HomePageObject homePage = PageGenerator.getHomePage(DriverManager.getDriver());
    MyAccountSideBarPageObject myAccountSideBar;
    CustomerInfoComponent customerInfoPage;
    String unRegisteredEmail = "hong" + CommonHelper.generateRandomNumber() + "@gmail.com";
    Map<String,String> updatedUserInfo = new HashMap<>();

    @And("The user navigate to the My Account page")

    public void theUserNavigateToTheMyAccountPage() {
        myAccountSideBar = homePage.clickMyAccountLink();
    }

    @When("The user navigates to {string} tab")
    public void theUserNavigatesToCustomerInfoTab(String tabName) {
        customerInfoPage = (CustomerInfoComponent) myAccountSideBar.navigateToMyAccountSideBarMenu(tabName.substring(0, 1).toUpperCase() + tabName.substring(1).toLowerCase());
    }

    @And("The user updates the following user information random")
    public void theUserUpdatesTheFollowingUserInformationRandom(DataTable userData) {
        Map<String, String> userInfo = userData.asMap();
        Map<String, String> editedUserInfo = new LinkedHashMap<>(userInfo);
        for (Map.Entry<String, String> entry : editedUserInfo.entrySet()) {
            String field = entry.getKey().toLowerCase();
            String value = entry.getValue();
            String randomValue = TestDataHelper.getData(value);
            updatedUserInfo.put(value,randomValue);
            if (value == null) {
                entry.setValue("");
            }
            switch (field) {
                case "gender":
                    customerInfoPage.checkGenderRadio(randomValue);
                    break;
                case "newsletter":
                    if (randomValue.equalsIgnoreCase("Subscribed")) {
                        customerInfoPage.checkNewLetterCheckbox();
                    } else {
                        customerInfoPage.uncheckNewLetterCheckbox();
                    }
                    break;
                default:
                    customerInfoPage.enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, randomValue, entry.getKey());
            }
        }
        customerInfoPage.clickSaveButton();
    }

    @When("The user updates the following user information")
    public void theUserUpdatesTheFollowingUserInformation(DataTable userData) {
        Map<String, String> userInfo = userData.asMap();
        Map<String, String> editedUserInfo = new LinkedHashMap<>(userInfo);
        customerInfoPage.updateUserInformation(editedUserInfo);
    }

    @Then("The user should see the following updated information")
    public void theUserShouldSeeTheUpdatedInformation(DataTable expectedData) {
        Map<String, String> expectedUserInfo = expectedData.asMap();
        for (Map.Entry<String, String> entry : expectedUserInfo.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
            switch (field.toLowerCase()) {
                case "gender":
                    Assert.assertEquals(customerInfoPage.getGenderValue(value.toLowerCase()), value);
                    break;
                case "newsletter":
                    if (value.equalsIgnoreCase("subscribed")) {
                        Assert.assertTrue(customerInfoPage.isNewLetterChecked());
                    } else if (value.equalsIgnoreCase("unsubscribed")) {
                        Assert.assertFalse(customerInfoPage.isNewLetterChecked());
                    } else {
                        throw new CucumberException("invalid parameter: " + value);
                    }
                    break;
                case "email":
                    Assert.assertEquals(customerInfoPage.getTextboxValue(field), unRegisteredEmail);
                    break;
                default:
                    Assert.assertEquals(customerInfoPage.getTextboxValue(field), value);
            }
        }
    }

    @Then("The user should see the update info success message {string}")
    public void theUserShouldSeeTheUpdatedSuccessfullyMessage(String expectedMessage) {
        customerInfoPage.getUpdatedInfoMessage(expectedMessage);
    }

    @Given("The user information is updated via API")
    public void theUserHasTheFollowingRegisteredInformation(DataTable dataTable) {
        Map<String, String> registeredUserInfo = dataTable.asMap();
        JSONObject body = new JSONObject();
        for (Map.Entry<String, String> entry : registeredUserInfo.entrySet()) {
            body.put(entry.getKey(), entry.getValue());
        }

        // Dùng RestFull API để update user ìnformation như mình muốn - Implement later

    }

    @When("The user log out from the webpage")
    public void theUserLogOutFromTheWebpage() {
        loginPage = customerInfoPage.clickLogOutLink();
    }

    @Then("The user should see the error message on the {string} page")
    public void theUserShouldSeeTheErrorMessageOnThePage(String pageName, DataTable errorMessageTable) {
        Map<String, String> errorMessage = errorMessageTable.asMap();
        for (Map.Entry<String, String> entry : errorMessage.entrySet()) {
            String expectedMessage = entry.getValue();
            String actualMessage;
            switch (pageName.toLowerCase()) {
                case "customer info":
                    if (expectedMessage.equals(MyAccountMessage.EXISTING_EMAIL_MESSAGE)) {
                        actualMessage = customerInfoPage.getErrorMessage();
                    } else {
                        actualMessage = customerInfoPage.getErrorMessage(entry.getKey());
                        Assert.assertEquals(actualMessage, expectedMessage);
                    }
                    break;
                default:
                    throw new CucumberException("Page name is not valid: " + pageName);

            }
        }

    }


    @And("The user should see the following updated information random")
    public void theUserShouldSeeTheFollowingUpdatedInformationRandom(DataTable expectedData) {
        Map<String, String> expectedUserInfo = expectedData.asMap();
        for (Map.Entry<String, String> entry : expectedUserInfo.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
            String expectedValue = updatedUserInfo.get(value);
            switch (field.toLowerCase()) {
                case "gender":
                    Assert.assertEquals(customerInfoPage.getGenderValue(expectedValue.toLowerCase()),expectedValue);
                    break;
                case "newsletter":
                    if (expectedValue.equalsIgnoreCase("subscribed")) {
                        Assert.assertTrue(customerInfoPage.isNewLetterChecked());
                    } else {
                        Assert.assertFalse(customerInfoPage.isNewLetterChecked());
                    }
                    break;
                default:
                    Assert.assertEquals(customerInfoPage.getTextboxValue(field), expectedValue);
            }
        }


    }
}
