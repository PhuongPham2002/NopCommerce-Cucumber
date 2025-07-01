package actions.components.MyAccountSideBar;

import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import commons.base.BasePage;
import dataTest.dataObject.CustomerData;
import interfaces.componentUI.myAccountSideBar.ChangePasswordPageUI;
import org.openqa.selenium.WebDriver;

public class ChangePasswordComponent extends BasePage {
    WebDriver driver;
    public ChangePasswordComponent(WebDriver driver){
        this.driver = driver;
    }
    public void clickChangePasswordButton() {
        waitForElementClickable(driver,ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
        clickElement(driver,ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
    }
    
    public void changePassword(CustomerData customerData) {
        log.info("old = " + customerData.getOldPassword());
        log.info("new = " + customerData.getNewPassword());
        enterTextboxByID(driver,ChangePasswordPageUI.PASSWORD_TEXTBOX_BY_ID,customerData.getOldPassword(),"OldPassword");
        enterTextboxByID(driver,ChangePasswordPageUI.PASSWORD_TEXTBOX_BY_ID,customerData.getNewPassword(),"NewPassword");
        enterTextboxByID(driver,ChangePasswordPageUI.PASSWORD_TEXTBOX_BY_ID,customerData.getNewPassword(),"ConfirmNewPassword");
        clickChangePasswordButton();

    }
    
    public String getSuccessfulChangePasswordMessage() {
        waitForElementVisible(driver,ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE);
        return getElementText(driver,ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE);
    }

    public HomePageObject clickLogoutButton() {
        waitForElementClickable(driver,ChangePasswordPageUI.LOGOUT_LINK);
        clickElement(driver,ChangePasswordPageUI.LOGOUT_LINK);
        return PageGenerator.getHomePage(driver);

    }

    public void closeUpdateSuccessfulMessage() {
        waitForElementVisible(driver,ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE_CLOSE_BUTTON);
        clickElement(driver,ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE_CLOSE_BUTTON);
    }
}
