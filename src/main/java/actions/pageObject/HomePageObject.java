package actions.pageObject;

import actions.components.Header.HeaderComponent;
import actions.components.MyAccountSideBar.MyAccountSideBarPageObject;
import commons.base.BasePage;
import interfaces.pageUI.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {
    HeaderComponent header;


    public HomePageObject(WebDriver driver) {
        super(driver);
        this.header = new HeaderComponent(driver);
    }

    public RegisterPageObject clickRegisterLink() {
        header.account.clickRegisterLink();
        return PageGenerator.getRegisterPage(driver);

    }

    public String getSuccessfulRegisterMessage() {
        waitForElementVisible(HomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
        return getElementText(HomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
    }

    public LoginPageObject clickLoginLink() {
        header.account.clickLoginLink();
        return PageGenerator.getLoginPage(driver);
    }

    public MyAccountSideBarPageObject clickMyAccountLink() {
        header.account.clickMyAccountLink();
        return PageGenerator.getMyAccountSideBarPage(driver);
    }

    public BasePage hoverToHeaderProductCategoryAndClickToSubProductCategory(String productCategory,String subProductCategory) {
        header.productCategory.hoverToHeaderProductCategory(productCategory);
        header.productCategory.waitForSubProductCategoryVisible(subProductCategory);
        return header.productCategory.clickSubProductCategory(productCategory,subProductCategory);
    }

}
