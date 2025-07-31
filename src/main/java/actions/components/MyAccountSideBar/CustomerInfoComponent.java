package actions.components.MyAccountSideBar;

import actions.components.Header.HeaderComponent;
import actions.components.NotificationBarComponent;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.base.BasePage;
import interfaces.componentUI.myAccountSideBar.CustomerInfoPageUI;
import io.cucumber.core.exception.CucumberException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Map;

public class CustomerInfoComponent extends BasePage {
    WebDriver driver;
    NotificationBarComponent notification;
    HeaderComponent header;

    public CustomerInfoComponent(WebDriver driver) {
        this.driver = driver;
        this.notification = new NotificationBarComponent(driver);
        this.header = new HeaderComponent(driver);
    }

    public void checkGenderRadio(String gender) {
        waitForElementVisible(driver, CustomerInfoPageUI.GENDER_RADIO, gender);
        checkCheckboxOrRadio(driver, CustomerInfoPageUI.GENDER_RADIO, gender);
    }

    public String getGenderValue(String gender) {
        waitForElementVisible(driver, CustomerInfoPageUI.GENDER_RADIO_VALUE, gender);
        return getElementText(driver, CustomerInfoPageUI.GENDER_RADIO_VALUE, gender);
    }

    public void clickSaveButton() {
        waitForElementClickable(driver, CustomerInfoPageUI.SAVE_BUTTON);
        clickElement(driver, CustomerInfoPageUI.SAVE_BUTTON);
    }

    public String getTextboxValue(String idTextbox) {
        return getAttributeValueByID(driver, CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID, "value", idTextbox);

    }

    public void checkNewLetterCheckbox() {
        waitForElementVisible(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
        checkNativeCheckbox(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);

    }

    public void uncheckNewLetterCheckbox() {
        waitForElementVisible(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
        uncheckNativeCheckbox(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
    }


    public boolean isNewLetterChecked() {
        return isElementSelected(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
    }

    public String getUpdatedInfoMessage(String message) {
        return notification.getNotificationMessage(message);

    }

    public LoginPageObject clickLogOutLink() {
        header.account.clickLogoutLink();
        return PageGenerator.getLoginPage(driver);
    }

    public String getErrorMessage(String fieldName) {
        waitForElementVisible(driver, CustomerInfoPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);
        return getElementText(driver, CustomerInfoPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);

    }

    public String getErrorMessage() {
        waitForElementVisible(driver, CustomerInfoPageUI.EXISTING_EMAIL_MESSAGE);
        return getElementText(driver, CustomerInfoPageUI.EXISTING_EMAIL_MESSAGE);

    }

    public void verifyUserInformation(Map<String, String> expectedDataMap) {
        for (Map.Entry<String, String> entry : expectedDataMap.entrySet()) {
            String field = entry.getKey();
            String value = entry.getValue();
            switch (field.toLowerCase()) {
                case "gender":
                    Assert.assertEquals(getGenderValue(value.toLowerCase()), value);
                    break;
                case "newsletter":
                    if (value.equalsIgnoreCase("subscribed")) {
                        Assert.assertTrue(isNewLetterChecked());
                    } else if (value.equalsIgnoreCase("unsubscribed")){
                        Assert.assertFalse(isNewLetterChecked());
                    } else {
                        throw new CucumberException("Invalid parameter");
                    }
                    break;
                case "email":
                    Assert.assertEquals(getTextboxValue(field), value);
                default:
                    Assert.assertEquals(getTextboxValue(field), value);
            }
        }
    }
}
