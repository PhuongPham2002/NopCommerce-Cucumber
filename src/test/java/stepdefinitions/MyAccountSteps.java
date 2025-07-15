package stepdefinitions;

import actions.components.MyAccountSideBar.CustomerInfoComponent;
import actions.components.MyAccountSideBar.MyAccountSideBarPageObject;
import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.constants.GlobalConstants;
import helpers.DriverManager;
import interfaces.componentUI.myAccountSideBar.CustomerInfoPageUI;
import interfaces.pageUI.LoginPageUI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;

import java.util.HashMap;
import java.util.Map;

public class MyAccountSteps {
//    LoginPageObject loginPage;
//    HomePageObject homePage;
//    MyAccountSideBarPageObject myAccountSideBar;
//    CustomerInfoComponent customerInfoPage;
//
//
//    @And("The user log in with a valid account")
//    public void theUserLogInWithAValidAccount() {
//        homePage = PageGenerator.getHomePage(DriverManager.getDriver());
//        loginPage = homePage.clickLoginLink();
//        loginPage.enterLoginForm(GlobalConstants.LOGIN_EMAIL,GlobalConstants.LOGIN_PASSWORD);
//        homePage=loginPage.clickLoginButton();
//    }
//
//    @And("The user navigate to the My Account page")
//    public void theUserNavigateToTheMyAccountPage() {
//        myAccountSideBar=homePage.clickMyAccountLink();
//
//
//    }
//
//    @When("The user navigates to customer info tab")
//    public void theUserNavigatesToCustomerInfoTab() {
//
//        customerInfoPage = (CustomerInfoComponent) myAccountSideBar.navigateToMyAccountSideBarMenu("Customer info");
//    }
//
//    @And("The user updates the following user information")
//    public void theUserUpdatesTheFollowingUserInformation(DataTable userData) {
//        Map<String,String> userInfo = userData.asMap();
//        customerInfoPage.checkGenderRadio("Female");
//        customerInfoPage.enterTextboxByID(DriverManager.getDriver(), CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,userInfo.get("First name"),"FirstName");
//    }
}
