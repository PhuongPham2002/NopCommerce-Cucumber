package stepdefinitions;

import actions.components.MyAccountSideBar.CustomerInfoComponent;
import actions.components.MyAccountSideBar.MyAccountSideBarPageObject;
import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.helpers.CommonHelper;
import commons.helpers.DriverManager;
import commons.helpers.ScenarioContext;
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

import java.util.LinkedHashMap;
import java.util.Map;

public class MyAccountSteps {
    LoginPageObject loginPage;
    HomePageObject homePage = PageGenerator.getHomePage(DriverManager.getDriver());
    MyAccountSideBarPageObject myAccountSideBar;
    CustomerInfoComponent customerInfoPage;
    ScenarioContext context;
    String unRegisteredEmail;
    public final static String EXISTING_EMAIL_MESSAGE = "The e-mail address is already in use";

    public MyAccountSteps(ScenarioContext context) {
        this.context = context;

    }

    @And("The user navigate to the My Account page")
    public void theUserNavigateToTheMyAccountPage() {
        myAccountSideBar = homePage.clickMyAccountLink();


    }

    @When("The user navigates to {string} tab")
    public void theUserNavigatesToCustomerInfoTab(String tabName) {
        customerInfoPage = (CustomerInfoComponent) myAccountSideBar.navigateToMyAccountSideBarMenu(tabName.substring(0, 1).toUpperCase() + tabName.substring(1).toLowerCase());
    }

    @And("The user updates the following user information")
    @Given("The user has the following registered information")
    public void theUserUpdatesTheFollowingUserInformation(DataTable userData) {
        Map<String, String> userInfo = userData.asMap();
        Map<String, String> editedUserInfo = new LinkedHashMap<>(userInfo);
        for (Map.Entry<String, String> entry : editedUserInfo.entrySet()) {
            String field = entry.getKey().toLowerCase();
            String value = entry.getValue();
            value = TestDataHelper.getData(value);
            if (value == null) {
                entry.setValue("");
            }
            switch (field) {
                case "gender":
                    customerInfoPage.checkGenderRadio(entry.getValue().toLowerCase());
                    break;
                case "newsletter":
                    if (value != null && value.equalsIgnoreCase("Subscribed")) {
                        customerInfoPage.checkNewLetterCheckbox();
                    } else {
                        customerInfoPage.uncheckNewLetterCheckbox();
                    }
                    break;
                case "email":
                    if (value != null && value.equals("unregistered email")) {
                        unRegisteredEmail = CommonHelper.generateUniqueEmail();
                        customerInfoPage.enterTextboxByID(DriverManager.getDriver(), CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, unRegisteredEmail, entry.getKey());
                    } else {
                        customerInfoPage.enterTextboxByID(DriverManager.getDriver(), CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, entry.getValue(), entry.getKey());
                    }
                    break;
                default:
                    customerInfoPage.enterTextboxByID(DriverManager.getDriver(), CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, entry.getValue(), entry.getKey());
            }
        }
        //Lưu lại các thông tin gốc nhằm re-set sau mỗi scenario:
        context.set("originalEmail", "admin@yourstore.com");
        context.set("emailChanged", true);

        customerInfoPage.clickSaveButton();
    }

    @Then("The user should see the following updated information")
    public void theUserShouldSeeTheUpdatedInformation(DataTable expectedData, String page) {
        Map<String, String> expectedUserInfo = expectedData.asMap();
        if (page.equalsIgnoreCase("customer info tab")) {
            //Call verify information of customerInfoPage
            customerInfoPage.verifyUserInformation(expectedData.asMap());
        }
    }

    @Then("The user should see the update info success message {string}")
    public void theUserShouldSeeTheUpdatedSuccessfullyMessage(String expectedMessage) {
        customerInfoPage.getUpdatedInfoMessage(expectedMessage);
    }

    @When("The user log out from the webpage")
    public void theUserLogOutFromTheWebpage() {
        loginPage = customerInfoPage.clickLogOutLink();

    }

    @Then("The user {string} the error/success message on the {string} page")
    public void theUserShouldSeeTheErrorMessageOnThePage(String userAction, String pageName, DataTable errorMessageTable) {
        Assert.assertTrue(userAction.equals("should see") || userAction.equals("should not see"), "Invalid user action. {should see, should not see} are allowed");
        Map<String, String> errorMessage = errorMessageTable.asMap();
        for (Map.Entry<String, String> entry : errorMessage.entrySet()) {
            String expectedMessage = entry.getValue();
            String actualMessage;
            switch (pageName.toLowerCase()) {
                case "customer info":
                    if (expectedMessage.equals(EXISTING_EMAIL_MESSAGE)) {
                        actualMessage = customerInfoPage.getErrorMessage();
                    } else {
                        actualMessage = customerInfoPage.getErrorMessage(entry.getKey());
                    }
                    if (userAction.equals("should see")) {
                        Assert.assertEquals(actualMessage, expectedMessage);
                    } else {
                        Assert.assertNotEquals(actualMessage, expectedMessage);
                    }
                    break;
                default:
                    throw new CucumberException("Unsupported page");
            }
        }
    }


}

