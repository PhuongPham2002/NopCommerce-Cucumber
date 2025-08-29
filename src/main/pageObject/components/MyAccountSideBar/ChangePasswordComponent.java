package pageObject.components.MyAccountSideBar;

import pageObject.HomePageObject;
import pageObject.PageGenerator;
import pageObject.BasePage;
import interfaces.componentUI.myAccountSideBar.ChangePasswordPageUI;
import org.openqa.selenium.WebDriver;

public class ChangePasswordComponent extends BasePage {
    
    public ChangePasswordComponent(WebDriver driver){
        super(driver);
    }
    public void clickChangePasswordButton() {
        waitForElementClickable(ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
        clickElement(ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
    }
    
    public void changePassword() {
//        log.info("old = " + customerData.getOldPassword());
//        log.info("new = " + customerData.getNewPassword());
//        enterTextboxByID(ChangePasswordPageUI.PASSWORD_TEXTBOX_BY_ID,customerData.getOldPassword(),"OldPassword");
//        enterTextboxByID(ChangePasswordPageUI.PASSWORD_TEXTBOX_BY_ID,customerData.getNewPassword(),"NewPassword");
//        enterTextboxByID(ChangePasswordPageUI.PASSWORD_TEXTBOX_BY_ID,customerData.getNewPassword(),"ConfirmNewPassword");
//        clickChangePasswordButton();

    }
    
    public String getSuccessfulChangePasswordMessage() {
        waitForElementVisible(ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE);
        return getElementText(ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE);
    }

    public HomePageObject clickLogoutButton() {
        waitForElementClickable(ChangePasswordPageUI.LOGOUT_LINK);
        clickElement(ChangePasswordPageUI.LOGOUT_LINK);
        return PageGenerator.getHomePage(driver);

    }

    public void closeUpdateSuccessfulMessage() {
        waitForElementVisible(ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE_CLOSE_BUTTON);
        clickElement(ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE_CLOSE_BUTTON);
    }
}
