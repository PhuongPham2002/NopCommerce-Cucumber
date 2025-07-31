package dataTest.dataObject;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CustomerData {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private String companyName;
    private String cityName;
    private String oldPassword;
    private String newPassword;
    private String gender;
    private  String address1;
    private  String address2;
    private  String postalCode;
    private  String phoneNumber;
    private  String faxNumber;
    private  String countryName;
    private  String state_province;

    private CustomerData(){}


    public static Builder builder(){
        return new Builder();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String companyName;
        private String cityName;
        private String oldPassword;
        private String newPassword;
        private String gender;
        private  String address1;
        private  String address2;
        private  String postalCode;
        private  String phoneNumber;
        private  String faxNumber;
        private  String countryName;
        private  String state_province;


        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;

        }
        public Builder lastName (String lastName){
            this.lastName = lastName;
            return this;
        }
        public Builder emailAddress (String emailAddress){
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder companyName (String companyName){
            this.companyName = companyName;
            return this;
        }
        public Builder cityName (String cityName){
            this.cityName = cityName;
            return this;
        }

        public Builder oldPassword (String oldPassword){
            this.oldPassword = oldPassword;
            return this;
        }

        public Builder newPassword (String newPassword){
            this.newPassword = newPassword;
            return this;
        }
        public Builder gender (String gender){
            this.gender = gender;
            return this;
        }

        public Builder address1 (String address1){
            this.address1 = address1;
            return this;
        }
        public Builder address2 (String address2){
            this.address2 = address2;
            return this;
        }
        public Builder postalCode (String postalCode){
            this.postalCode = postalCode;
            return this;
        }
        public Builder phoneNumber (String phoneNumber){
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder faxNumber (String faxNumber){
            this.faxNumber = faxNumber;
            return this;
        }
        public Builder countryName (String countryName){
            this.countryName = countryName;
            return this;
        }
        public Builder state_province (String state_province){
            this.state_province = state_province;
            return this;
        }
        public CustomerData build(){
            CustomerData data = new CustomerData();
            data.firstName = this.firstName;
            data.lastName = this.lastName;
            data.emailAddress = this.emailAddress;
            data.companyName = this.companyName;
            data.cityName = this.cityName;
            data.oldPassword = this.oldPassword;
            data.newPassword = this.newPassword;
            data.gender = this.gender;
            data.address1 = this.address1;
            data.address2 = this.address2;
            data.postalCode = this.postalCode;
            data.phoneNumber = this.phoneNumber;
            data.faxNumber = this.faxNumber;
            data.countryName = this.countryName;
            data.state_province = this.state_province;
            return data;

        }


    }




}
