package dataObjects;

import com.poiji.annotation.ExcelCellName;
import com.poiji.annotation.ExcelSheet;
import lombok.Getter;
import lombok.Setter;

@ExcelSheet("RegisterData")
@Getter
public class RegisterTestData {
    @ExcelCellName("firstName")
    private String firstName;
    @ExcelCellName("lastName")
    private String lastName;
    @ExcelCellName("Email")
    private String emailAddress;
    @ExcelCellName("CompanyName")
    private String companyName;
    @ExcelCellName("Password")
    private String password;
    @ExcelCellName("Gender")
    private String gender;
    @ExcelCellName("Confirm password")
    private String confirmPassword;

    //public RegisterTestData(){}


    private RegisterTestData(){}

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private String firstName;
        private String lastName;
        private String emailAddress;
        private String companyName;
        private String password;
        private String gender;
        private String confirmPassword;



        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public Builder emailAddress(String emailAddress){
            this.emailAddress = emailAddress;
            return this;
        }
        public Builder companyName(String companyName){
            this.companyName = companyName;
            return this;
        }

        public Builder password (String password){
            this.password = password;
            return this;
        }

        public Builder gender (String gender){
            this.gender = gender;
            return this;
        }

        public Builder confirmPassword (String confirmPassword){
            this.confirmPassword = confirmPassword;
            return this;
        }


        public RegisterTestData build(){
            RegisterTestData registerTestData = new RegisterTestData();
            registerTestData.firstName = this.firstName;
            registerTestData.lastName = this.lastName;
            registerTestData.emailAddress = this.emailAddress;
            registerTestData.companyName = this.companyName;
            registerTestData.password = this.password;
            registerTestData.confirmPassword = this.confirmPassword;
            registerTestData.gender = this.gender;

            return registerTestData;

        }


    }

//    public RegisterTestData(String firstName, String lastName, String emailAddress,String password,String confirmPassword,String gender) {
//        this.firstName = firstName;
//        this.gender = gender;
//        this.lastName = lastName;
//        this.emailAddress = emailAddress;
//        this.password = password;
//
//    }
//    public RegisterTestData(String firstName, String lastName, String emailAddress,String password,String confirmPassword) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.emailAddress = emailAddress;
//        this.password = password;
//        this.confirmPassword = confirmPassword;
//
//    }
//
//    public RegisterTestData(String firstName, String lastName, String emailAddress, String companyName, String password, String confirmPassword,String gender) {
//        this.firstName = firstName;
//        this.gender = gender;
//        this.lastName = lastName;
//        this.emailAddress = emailAddress;
//        this.companyName = companyName;
//        this.password = password;
//        this.confirmPassword = confirmPassword;
//    }

    @Override
    public String toString() {
        return String.format("Email='%s'",emailAddress);
    }

}
