package actions.components.MyAccountSideBar;

import actions.components.NotificationBarComponent;
import commons.base.BasePage;
import dataTest.dataObject.CustomerData;
import interfaces.componentUI.myAccountSideBar.CustomerInfoPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.sql.DriverManager;
import java.util.Map;

public class CustomerInfoComponent extends BasePage {
    WebDriver driver;
    NotificationBarComponent notification;

    public CustomerInfoComponent(WebDriver driver) {
        this.driver = driver;
        this.notification = new NotificationBarComponent(driver);
    }

    public void checkGenderRadio(String gender) {
        waitForElementVisible(driver, CustomerInfoPageUI.GENDER_RADIO,gender);
        checkCheckboxOrRadio(driver,CustomerInfoPageUI.GENDER_RADIO,gender);
    }
    public String getGenderValue(String gender){
        waitForElementVisible(driver,CustomerInfoPageUI.GENDER_RADIO_VALUE,gender);
        return getElementText(driver,CustomerInfoPageUI.GENDER_RADIO_VALUE,gender);
    }

    public void clickSaveButton() {
        waitForElementClickable(driver,CustomerInfoPageUI.SAVE_BUTTON);
        clickElement(driver,CustomerInfoPageUI.SAVE_BUTTON);
    }
    public String getTextboxValue(String idTextbox){
      return getAttributeValueByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"value",idTextbox);

    }

    public void checkNewLetterCheckbox() {
        waitForElementVisible(driver,CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
        checkNativeCheckbox(driver,CustomerInfoPageUI.NEWSLETTER_CHECKBOX);

    }

    public void uncheckNewLetterCheckbox() {
        waitForElementVisible(driver,CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
        uncheckNativeCheckbox(driver,CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
    }


    public boolean isNewLetterChecked() {
        return isElementSelected(driver,CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
    }
    public String getUpdatedInfoMessage(String message){
        return notification.getNotificationMessage(message);

    }
}
