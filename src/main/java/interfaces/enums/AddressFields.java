package interfaces.enums;

import lombok.Getter;
import lombok.Setter;

public enum AddressFields {
    FIRST_NAME("FirstName","First name"),
    LAST_NAME("LastName","Last name"),
    EMAIL("Email","Email address"),
    CITY("City", "City name"),
    FIRST_ADDRESS("Address1","First address"),
    POSTAL_CODE("ZipPostalCode","Zip / postal code"),
    PHONE_NUMBER("PhoneNumber","Phone number"),
    COUNTRY("CountryId","Country name"),
    STATE_PROVINCE("StateProvinceId","State/Province name");


   @Setter @Getter
    private String idDynamics;
    private String fieldName;



    AddressFields(String idDynamics, String fieldName){
        this.idDynamics = idDynamics;
        this.fieldName = fieldName;

    }

    public static AddressFields fieldsName(String fieldName) {
        for (AddressFields field : AddressFields.values()) {
            if (field.fieldName.equals(fieldName.trim())) {
                return field;}
            }
        throw new RuntimeException("Invalid fieldName: " + fieldName);
        }

    public String getLocator(String rawLocator){
        return String.format(rawLocator,idDynamics);
    }
}
