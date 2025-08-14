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

import static io.restassured.RestAssured.given;

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

    @And("The user updates the following user information random")
    public void theUserUpdatesTheFollowingUserInformationRandom(DataTable userData) {
        Map<String, String> userInfo = userData.asMap();
        Map<String, String> editedUserInfo = new LinkedHashMap<>(userInfo);
        for (Map.Entry<String, String> entry : editedUserInfo.entrySet()) {
            String field = entry.getKey().toLowerCase();
            String value = entry.getValue();
            if (value == null) {
                entry.setValue("");
            }
            switch (field) {
                case "gender":
                    customerInfoPage.checkGenderRadio(TestDataHelper.getData(value));
                    break;
                case "newsletter":
                    if (TestDataHelper.getData(value).equalsIgnoreCase("Subscribed")) {
                        customerInfoPage.checkNewLetterCheckbox();
                    } else {
                        customerInfoPage.uncheckNewLetterCheckbox();
                    }
                    break;
                default:
                    customerInfoPage.enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, TestDataHelper.getData(value), entry.getKey());
            }
        }
    }


    @When("The user updates the following user information")
    public void theUserUpdatesTheFollowingUserInformation(DataTable userData) {
        Map<String, String> userInfo = userData.asMap();
        Map<String, String> editedUserInfo = new LinkedHashMap<>(userInfo);
        for (Map.Entry<String, String> entry : editedUserInfo.entrySet()) {
            String field = entry.getKey().toLowerCase();
            String value = entry.getValue();
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
                        customerInfoPage.enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, unRegisteredEmail, entry.getKey());
                    } else if (value != null && value.equals("existed email")) {
                        // Dùng code API check lấy được data là 1 email đã tồn tại trong hệ thống
                        String existedEmail = given()
                                .baseUri("https://api.yourapp.com")
                                .header("Authorization", "Bearer ")
                                .when()
                                .get("/users?limit=1&status=active")
                                .then()
                                .statusCode(200)
                                .extract()
                                .path("data[0].email");
                        customerInfoPage.enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, existedEmail, entry.getKey());


                    } else {
                        customerInfoPage.enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, entry.getValue(), entry.getKey());
                    }
                    break;
                default:
                    customerInfoPage.enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, entry.getValue(), entry.getKey());
            }
        }
        //Lưu lại các thông tin gốc nhằm re-set sau mỗi scenario:
        context.set("originalEmail", "admin@yourstore.com");
        context.set("emailChanged", true);

        customerInfoPage.clickSaveButton();
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
                    } else {
                        Assert.assertFalse(customerInfoPage.isNewLetterChecked());
                    }
                    break;
                case "email":
                    Assert.assertEquals(customerInfoPage.getTextboxValue(field), unRegisteredEmail);
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
                    if (expectedMessage.equals(EXISTING_EMAIL_MESSAGE)) {
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
            switch (field.toLowerCase()) {
                case "gender":
                    Assert.assertEquals(customerInfoPage.getGenderValue(value.toLowerCase()), TestDataHelper.getData(value));
                    break;
                case "newsletter":
                    if (TestDataHelper.getData(value).equalsIgnoreCase("subscribed")) {
                        Assert.assertTrue(customerInfoPage.isNewLetterChecked());
                    } else {
                        Assert.assertFalse(customerInfoPage.isNewLetterChecked());
                    }
                    break;
                default:
                    Assert.assertEquals(customerInfoPage.getTextboxValue(field), TestDataHelper.getData(value));
            }
        }


    }
}
