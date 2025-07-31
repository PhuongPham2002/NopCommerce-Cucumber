package actions.pageObject;

import actions.components.Header.HeaderComponent;
import actions.components.MyAccountSideBar.MyAccountSideBarPageObject;
import commons.base.BasePage;
import interfaces.componentUI.header.HeaderAccountComponentUI;
import interfaces.pageUI.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {
    WebDriver driver;
    HeaderComponent header;


    public HomePageObject(WebDriver driver) {

        this.driver = driver;
        this.header = new HeaderComponent(driver);
    }

    public RegisterPageObject clickRegisterLink() {
        header.account.clickRegisterLink();
        return PageGenerator.getRegisterPage(driver);

    }

    public String getSuccessfulRegisterMessage() {
        waitForElementVisible(driver,HomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
        return getElementText(driver,HomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
    }

    public LoginPageObject clickLoginLink() {
        header.account.clickLoginLink();
        waitForElementClickable(driver,HeaderAccountComponentUI.LOGIN_LINK);
        clickElement(driver,HeaderAccountComponentUI.LOGIN_LINK);
        return PageGenerator.getLoginPage(driver);
    }

    public MyAccountSideBarPageObject clickMyAccountLink() {
        clickElement(driver, HeaderAccountComponentUI.ACCOUNT_LINK);
        return PageGenerator.getMyAccountSideBarPage(driver);
    }

    public BasePage hoverToHeaderProductCategoryAndClickToSubProductCategory(String productCategory,String subProductCategory) {
        header.productCategory.hoverToHeaderProductCategory(productCategory);
        header.productCategory.waitForSubProductCategoryVisible(subProductCategory);
        return header.productCategory.clickSubProductCategory(productCategory,subProductCategory);
    }

}
