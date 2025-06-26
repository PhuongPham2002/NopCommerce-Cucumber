package actions.components.MyAccountSideBar;

import commons.base.BasePage;
import dataObjects.CustomerData;
import interfaces.componentUI.myAccountSideBar.CustomerInfoPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CustomerInfoComponent extends BasePage {
    WebDriver driver;

    public CustomerInfoComponent(WebDriver driver) {
        this.driver = driver;
    }
    public void updateCustomerInformation(CustomerData customerData){

        checkGenderRadio(customerData.getGender().toLowerCase());
        enterTextboxByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,customerData.getFirstName(),"FirstName");
        enterTextboxByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,customerData.getLastName(),"LastName");
        enterTextboxByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,customerData.getEmailAddress(),"Email");
        enterTextboxByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,customerData.getCompanyName(),"Company");
        clickSaveButton();
    }

    public void checkGenderRadio(String gender) {
        waitForElementVisible(driver, CustomerInfoPageUI.GENDER_RADIO,gender);
        checkCheckboxOrRadio(driver,CustomerInfoPageUI.GENDER_RADIO,gender);
    }
    public String getGenderValue(String gender){
        waitForElementVisible(driver,CustomerInfoPageUI.GENDER_RADIO,gender);
        return getElementText(driver,CustomerInfoPageUI.GENDER_RADIO,gender);
    }

    public void clickSaveButton() {
        waitForElementClickable(driver,CustomerInfoPageUI.SAVE_BUTTON);
        clickElement(driver,CustomerInfoPageUI.SAVE_BUTTON);
    }
    public void assertUpdatedCustomerInfo(CustomerData customerData){
        Assert.assertEquals(getGenderValue("female"),customerData.getGender());
        Assert.assertEquals(getAttributeValueByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"value","FirstName"),customerData.getFirstName());
        Assert.assertEquals(getAttributeValueByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"value","LastName"),customerData.getLastName());
        Assert.assertEquals(getAttributeValueByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"value","Email"),customerData.getEmailAddress());
        Assert.assertEquals(getAttributeValueByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"value","Company"),customerData.getCompanyName());

    }

}
