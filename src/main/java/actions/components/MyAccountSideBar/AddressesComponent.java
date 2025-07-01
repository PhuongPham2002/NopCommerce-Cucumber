package actions.components.MyAccountSideBar;
import commons.base.BasePage;
import dataTest.dataObject.CustomerData;
import interfaces.componentUI.myAccountSideBar.AddressesPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class AddressesComponent extends BasePage {
    WebDriver driver;
    public AddressesComponent(WebDriver driver){
        this.driver=driver;
    }

    public void updateCustomerAddresses(CustomerData customerData){
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getFirstName(),"FirstName");
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getLastName(),"LastName");
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getEmailAddress(),"Email");
        enterTextboxByID(driver,AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getCompanyName(),"Company");;
        selectDropdownOptionByID("CountryId",customerData.getCountryName());
        selectDropdownOptionByID("StateProvinceId",customerData.getState_province());
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getCityName(),"City");
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getAddress1(),"Address1");
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getAddress2(),"Address2");
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getPostalCode(),"ZipPostalCode");
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getPhoneNumber(),"PhoneNumber");
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,customerData.getFaxNumber(),"FaxNumber");
        clickSaveButton();
    }

    public void assertAddressesInfoAfterAdding(CustomerData customerData){
       List<WebElement> allAddressesInfo= getListElement(driver,AddressesPageUI.LIST_ADDRESSES_INFO_TEXT);
        List<String> allActualValue = new ArrayList<>();

        for (WebElement addressesInfo:allAddressesInfo){
           String actualValue = addressesInfo.getText().trim();
           allActualValue.add(actualValue);
       }
        Assert.assertTrue(allActualValue.contains(customerData.getFirstName()+" "+customerData.getLastName()));
        Assert.assertTrue(allActualValue.contains("Fax number: "+customerData.getFaxNumber()));
        Assert.assertTrue(allActualValue.contains(customerData.getCompanyName()));
        Assert.assertTrue(allActualValue.contains(customerData.getAddress2()));
        Assert.assertTrue(allActualValue.contains(customerData.getAddress1()));
        Assert.assertTrue(allActualValue.contains("Email: "+ customerData.getEmailAddress()));
        Assert.assertTrue(allActualValue.contains(customerData.getCityName()));
        Assert.assertTrue(allActualValue.contains(customerData.getPostalCode()));
        Assert.assertTrue(allActualValue.contains(customerData.getState_province()));
    }


    public String getAddingInfoSuccessfulMessage() {
        waitForElementVisible(driver,AddressesPageUI.ADDRESSES_INFO_SUCCESSFUL_UPDATE);
        return getElementText(driver,AddressesPageUI.ADDRESSES_INFO_SUCCESSFUL_UPDATE);
    }

    public void clickAddNewButton() {
        waitForElementClickable(driver,AddressesPageUI.ADD_NEW_BUTTON);
        clickElement(driver,AddressesPageUI.ADD_NEW_BUTTON);
    }
    public void selectDropdownOptionByID(String idDropdownValue, String option){
        waitForTextToBePresentInElement(driver,AddressesPageUI.ADDRESSES_DROPDOWN_BY_ID,option,idDropdownValue);
        waitForElementClickable(driver, AddressesPageUI.ADDRESSES_DROPDOWN_BY_ID, idDropdownValue);
        selectDropdownOption(driver,option, AddressesPageUI.ADDRESSES_DROPDOWN_BY_ID, idDropdownValue);
    }
    public void clickSaveButton(){
        waitForElementClickable(driver,AddressesPageUI.SAVE_BUTTON);
        clickElement(driver,AddressesPageUI.SAVE_BUTTON);
    }
}
