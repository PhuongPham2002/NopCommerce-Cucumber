package data.helpers;

import commons.helpers.CommonHelper;
import dataObjects.CustomerData;

public class CustomerDataHelper {
    public final static String FIRSTNAME ="Automation";
    public final static String LASTNAME ="FC";
    public final static String EMAIL_ADDRESS = CommonHelper.generateUniqueEmail();
    public final static String COMPANY_NAME="Automation FC";
    public final static String CITY_NAME="Ha Noi";
    public final static String OLD_PASSWORD ="123456789";
    public final static String NEW_PASSWORD ="3456789012";
    public final static String GENDER = "Female";
    public final static String ADDRESS1="123/40 Le Lai";
    public final static String ADDRESS2 ="234/05 Hai Phong";
    public final static String POSTAL_CODE ="550000";
    public final static String PHONE_NUMBER = "0123456789";
    public final static String FAX_NUMBER="0987654321";
    public final static String COUNTRY_NAME="Vietnam";
    public final static String STATE_PROVINCE ="Hà Nội";

    public static CustomerData updateCustomerInfo(){
        return CustomerData.builder()
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .gender(GENDER)
                .emailAddress(EMAIL_ADDRESS)
                .companyName(COMPANY_NAME).build();

    }
    public static CustomerData updateCustomerAddress(){
        return CustomerData.builder()
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .emailAddress(EMAIL_ADDRESS)
                .companyName(COMPANY_NAME)
                .countryName(COUNTRY_NAME)
                .state_province(STATE_PROVINCE)
                .cityName(CITY_NAME)
                .address1(ADDRESS1)
                .address2(ADDRESS2)
                .postalCode(POSTAL_CODE)
                .phoneNumber(PHONE_NUMBER)
                .faxNumber(FAX_NUMBER).build();
    }
    public static CustomerData updatePassword(){
        return CustomerData.builder()
                .oldPassword(OLD_PASSWORD)
                .newPassword(NEW_PASSWORD).build();
    }

    public static CustomerData loginWithOldPassword(){
        return CustomerData.builder()
                .emailAddress(EMAIL_ADDRESS)
                .oldPassword(OLD_PASSWORD).build();
    }

}
