package stepdefinitions;

import actions.components.MyAccountSideBar.CustomerInfoComponent;
import actions.components.MyAccountSideBar.MyAccountSideBarPageObject;
import actions.components.NotificationBarComponent;
import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.constants.GlobalConstants;
import commons.helpers.CommonHelper;
import helpers.DriverManager;
import helpers.ScenarioContext;
import interfaces.componentUI.myAccountSideBar.CustomerInfoPageUI;
import interfaces.pageUI.LoginPageUI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.poi.ss.formula.atp.Switch;
import org.testng.Assert;

import java.util.Formattable;
import java.util.HashMap;
import java.util.Map;

public class MyAccountSteps {
    LoginPageObject loginPage;
    HomePageObject homePage;
    MyAccountSideBarPageObject myAccountSideBar;
    CustomerInfoComponent customerInfoPage;
    ScenarioContext context = new ScenarioContext();
    String unRegisteredEmail = CommonHelper.generateUniqueEmail();



    @And("The user log in with a valid account")
    public void theUserLogInWithAValidAccount() {
        homePage = PageGenerator.getHomePage(DriverManager.getDriver());
        loginPage = homePage.clickLoginLink();
        loginPage.enterLoginForm(GlobalConstants.LOGIN_EMAIL,GlobalConstants.LOGIN_PASSWORD);
        homePage=loginPage.clickLoginButton();
    }

    @And("The user navigate to the My Account page")
    public void theUserNavigateToTheMyAccountPage() {
        myAccountSideBar=homePage.clickMyAccountLink();


    }

    @When("The user navigates to customer info tab")
    public void theUserNavigatesToCustomerInfoTab() {
        customerInfoPage = (CustomerInfoComponent) myAccountSideBar.navigateToMyAccountSideBarMenu("Customer info");
    }

    @And("The user updates the following user information")
    public void theUserUpdatesTheFollowingUserInformation(DataTable userData) {
        Map<String,String> userInfo = userData.asMap();

        for (Map.Entry<String,String> entry: userInfo.entrySet()){
            String field = entry.getKey().toLowerCase();
            String value = entry.getValue();
            switch (field){
                case "gender":
                    customerInfoPage.checkGenderRadio(entry.getValue().toLowerCase());
                    break;
                case "newsletter":
                    if (value.equalsIgnoreCase("Subscribed")){
                    customerInfoPage.checkNewLetterCheckbox();
                } else {
                    customerInfoPage.uncheckNewLetterCheckbox();
                }
                    break;
//                case "email":
//                    context.set("email",unRegisteredEmail);
//                    customerInfoPage.enterTextboxByID(DriverManager.getDriver(), CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,unRegisteredEmail,entry.getKey());
//                    break;
                default:
                    customerInfoPage.enterTextboxByID(DriverManager.getDriver(), CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,entry.getValue(),entry.getKey());}
            }

        customerInfoPage.clickSaveButton();
    }

    @Then("The user should see the following updated information")
    public void theUserShouldSeeTheUpdatedInformation(DataTable expectedData) {
        Map<String,String> expectedUserInfo = expectedData.asMap();
        for (Map.Entry<String,String> entry : expectedUserInfo.entrySet()){
            String field = entry.getKey();
            String value = entry.getValue();
            switch (field.toLowerCase()){
                case "gender":
                    Assert.assertEquals(customerInfoPage.getGenderValue(value.toLowerCase()),value);
                    break;
                case "newsletter":
                    if (value.equalsIgnoreCase("subscribed")){
                        Assert.assertTrue(customerInfoPage.isNewLetterChecked());
                    } else {
                        Assert.assertFalse(customerInfoPage.isNewLetterChecked());
                    }
                    break;
//                case "email":
//                    String expectedEmail =context.get("email");
//                    Assert.assertEquals(customerInfoPage.getTextboxValue(field),expectedEmail);
//                    break;
                default:
                    Assert.assertEquals(customerInfoPage.getTextboxValue(field),value);
            }
        }
    }

    @Then("The user should see the update info success message {string}")
    public void theUserShouldSeeTheUpdatedSuccessfullyMessage(String expectedMessage) {
        customerInfoPage.getUpdatedInfoMessage(expectedMessage);
    }
}

