package pageObject.components.MyAccountSideBar;

import commons.helpers.CommonHelper;
import io.cucumber.core.exception.CucumberException;
import org.openqa.selenium.remote.http.ClientConfig;
import org.testng.Assert;
import pageObject.components.Header.HeaderComponent;
import pageObject.components.NotificationBarComponent;
import pageObject.LoginPageObject;
import pageObject.PageGenerator;
import pageObject.BasePage;
import interfaces.componentUI.myAccountSideBar.CustomerInfoPageUI;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class CustomerInfoComponent extends BasePage {
    NotificationBarComponent notification;
    HeaderComponent header;
    String unRegisteredEmail;

    public CustomerInfoComponent(WebDriver driver) {
        super(driver);
        this.notification = new NotificationBarComponent(driver);
        this.header = new HeaderComponent(driver);
    }

    public void checkGenderRadio(String gender) {
        waitForElementVisible(CustomerInfoPageUI.GENDER_RADIO, gender.toLowerCase());
        checkCheckboxOrRadio(CustomerInfoPageUI.GENDER_RADIO, gender.toLowerCase());
    }
    public String getGenderValue(String gender){
        waitForElementVisible(CustomerInfoPageUI.GENDER_RADIO_VALUE, gender.toLowerCase());
        return getElementText(CustomerInfoPageUI.GENDER_RADIO_VALUE, gender.toLowerCase());
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

    public void updateUserInformation(Map<String,String> userInfo){
        for (Map.Entry<String, String> entry : userInfo.entrySet()) {
            String field = entry.getKey().toLowerCase();
            String value = entry.getValue();
            if (value == null) {
                entry.setValue("");
            }
            switch (field) {
                case "gender":
                    checkGenderRadio(entry.getValue().toLowerCase());
                    break;
                case "newsletter":
                    if (value != null && value.equalsIgnoreCase("Subscribed")) {
                        checkNewLetterCheckbox();
                    } else {
                        uncheckNewLetterCheckbox();
                    }
                    break;
                case "email":
                    if (value != null && value.equals("unregistered email")) {
                        unRegisteredEmail = CommonHelper.generateUniqueEmail();
                        enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, unRegisteredEmail, entry.getKey());
                    } else if (value != null && value.equals("existed email")) {
                        // Dùng code API check lấy được data là 1 email đã tồn tại trong hệ thống
                        String existedEmail ="lay qua api";

                       enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, existedEmail, entry.getKey());
                    } else {
                       enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, entry.getValue(), entry.getKey());
                    }
                    break;
                default:
                    enterTextboxByID(CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, entry.getValue(), entry.getKey());
            }
        }
        clickSaveButton();
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
