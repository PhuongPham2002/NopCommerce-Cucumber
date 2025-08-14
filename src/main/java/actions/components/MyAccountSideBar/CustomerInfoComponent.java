package actions.components.MyAccountSideBar;

import actions.components.Header.HeaderComponent;
import actions.components.NotificationBarComponent;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.base.BasePage;
import interfaces.componentUI.myAccountSideBar.CustomerInfoPageUI;
import org.openqa.selenium.WebDriver;

public class CustomerInfoComponent extends BasePage {
    NotificationBarComponent notification;
    HeaderComponent header;

    public CustomerInfoComponent(WebDriver driver) {
        super(driver);
        this.notification = new NotificationBarComponent(driver);
        this.header = new HeaderComponent(driver);
    }

    public void checkGenderRadio(String gender) {
        waitForElementVisible(CustomerInfoPageUI.GENDER_RADIO, gender);
        checkCheckboxOrRadio(CustomerInfoPageUI.GENDER_RADIO, gender);
    }
    public String getGenderValue(String gender){
        waitForElementVisible(CustomerInfoPageUI.GENDER_RADIO_VALUE, gender);
        return getElementText(CustomerInfoPageUI.GENDER_RADIO_VALUE, gender);
    }

    public void clickSaveButton() {
        waitForElementClickable(CustomerInfoPageUI.SAVE_BUTTON);
        clickElement(CustomerInfoPageUI.SAVE_BUTTON);
    }
    public String getTextboxValue(String idTextbox){
        return getAttributeValueByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, "value", idTextbox);

    }

    public void checkNewLetterCheckbox() {
        waitForElementVisible(CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
        checkNativeCheckbox(CustomerInfoPageUI.NEWSLETTER_CHECKBOX);

    }

    public void uncheckNewLetterCheckbox() {
        waitForElementVisible(CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
        uncheckNativeCheckbox(CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
    }


    public boolean isNewLetterChecked() {
        return isElementSelected(CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
    }
    public String getUpdatedInfoMessage(String message){
        return notification.getNotificationMessage(message);

    }

    public LoginPageObject clickLogOutLink(){
        header.account.clickLogoutLink();
        return PageGenerator.getLoginPage(driver);
    }
    public String getErrorMessage(String fieldName){
        waitForElementVisible(CustomerInfoPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);
        return getElementText(CustomerInfoPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);

    }

    public String getErrorMessage(){
        waitForElementVisible(CustomerInfoPageUI.EXISTING_EMAIL_MESSAGE);
        return getElementText(CustomerInfoPageUI.EXISTING_EMAIL_MESSAGE);

    }
}
