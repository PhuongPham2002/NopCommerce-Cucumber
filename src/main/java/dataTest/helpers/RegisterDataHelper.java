package dataTest.helpers;

import commons.helpers.CommonHelper;
import dataTest.dataObject.RegisterTestData;

public class
RegisterDataHelper {
    public final static String FIRST_NAME="Phuong";
    public final static String LAST_NAME="Pham";
    public final static String COMPANY_NAME="AI Automation";
    public final static String PASSWORD="123456789";
    public final static String GENDER="Female";
    public final static String CONFIRM_PASSWORD ="123456789";

    public static RegisterTestData provideValidRequiredFields(){
        String emailAddress= getUniqueEmailAddress();
        return RegisterTestData.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .emailAddress(emailAddress)
                .password(PASSWORD)
                .confirmPassword(CONFIRM_PASSWORD).build();
    }

    public static RegisterTestData provideValidRequiredFieldsAndGenderSelection(){
        String emailAddress= getUniqueEmailAddress();
        return RegisterTestData.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .emailAddress(emailAddress)
                .password(PASSWORD)
                .confirmPassword(CONFIRM_PASSWORD)
                .gender(GENDER).build();
    }


    public static String getUniqueEmailAddress(){
       return CommonHelper.generateUniqueEmail();
    }

    public static RegisterTestData provideExistedEmailData(String existedEmail){
        return RegisterTestData.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .emailAddress(existedEmail)
                .password(PASSWORD)
                .confirmPassword(CONFIRM_PASSWORD)
                .gender(GENDER).build();

    }

}
