package actions.components.MyAccountSideBar;
import commons.base.BasePage;
import interfaces.componentUI.myAccountSideBar.AddressesPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AddressesComponent extends BasePage {
    WebDriver driver;
    public AddressesComponent(WebDriver driver){
        super(driver);
    }

    public void updateCustomerAddresses() {
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getFirstName(), "FirstName");
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getLastName(), "LastName");
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getEmailAddress(), "Email");
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getCompanyName(), "Company");
//        selectDropdownOptionByID("CountryId",customerData.getCountryName());
//        selectDropdownOptionByID("StateProvinceId",customerData.getState_province());
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getCityName(), "City");
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getAddress1(), "Address1");
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getAddress2(), "Address2");
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getPostalCode(), "ZipPostalCode");
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getPhoneNumber(), "PhoneNumber");
//        enterTextboxByID(AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID, customerData.getFaxNumber(), "FaxNumber");
//        clickSaveButton();
    }

    public void assertAddressesInfoAfterAdding() {
        List<WebElement> allAddressesInfo = getListElement(AddressesPageUI.LIST_ADDRESSES_INFO_TEXT);
        List<String> allActualValue = new ArrayList<>();

        for (WebElement addressesInfo:allAddressesInfo){
           String actualValue = addressesInfo.getText().trim();
           allActualValue.add(actualValue);
       }
//        Assert.assertTrue(allActualValue.contains(customerData.getFirstName()+" "+customerData.getLastName()));
//        Assert.assertTrue(allActualValue.contains("Fax number: "+customerData.getFaxNumber()));
//        Assert.assertTrue(allActualValue.contains(customerData.getCompanyName()));
//        Assert.assertTrue(allActualValue.contains(customerData.getAddress2()));
//        Assert.assertTrue(allActualValue.contains(customerData.getAddress1()));
//        Assert.assertTrue(allActualValue.contains("Email: "+ customerData.getEmailAddress()));
//        Assert.assertTrue(allActualValue.contains(customerData.getCityName()));
//        Assert.assertTrue(allActualValue.contains(customerData.getPostalCode()));
//        Assert.assertTrue(allActualValue.contains(customerData.getState_province()));
    }


    public String getAddingInfoSuccessfulMessage() {
        waitForElementVisible(AddressesPageUI.ADDRESSES_INFO_SUCCESSFUL_UPDATE);
        return getElementText(AddressesPageUI.ADDRESSES_INFO_SUCCESSFUL_UPDATE);
    }

    public void clickAddNewButton() {
        waitForElementClickable(AddressesPageUI.ADD_NEW_BUTTON);
        clickElement(AddressesPageUI.ADD_NEW_BUTTON);
    }
    public void selectDropdownOptionByID(String idDropdownValue, String option){
        waitForTextToBePresentInElement(AddressesPageUI.ADDRESSES_DROPDOWN_BY_ID, option, idDropdownValue);
        waitForElementClickable(AddressesPageUI.ADDRESSES_DROPDOWN_BY_ID, idDropdownValue);
        selectDropdownOption(option, AddressesPageUI.ADDRESSES_DROPDOWN_BY_ID, idDropdownValue);
    }
    public void clickSaveButton(){
        waitForElementClickable(AddressesPageUI.SAVE_BUTTON);
        clickElement(AddressesPageUI.SAVE_BUTTON);
    }
}
