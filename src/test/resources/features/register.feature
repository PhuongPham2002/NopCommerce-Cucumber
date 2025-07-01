@Register
Feature: Register
  Background:
    Given I am on the register page
  Scenario: Register_01: Register with empty data
    When I fill in register form with data:
      | FirstName |  |
      | LastName  |  |
      | Email     |  |
      | Password  |  |
      | Confirm Password | |
    And I click register button
    Then I should see the empty data error messages:
      | FirstName |First name is required. |
      | LastName |Last name is required.|
      | EmailAddress |Email is required. |
      | Password |Password is required. |

    Scenario: Register_02: Register with invalid password
      When I fill in register form with data:
        | FirstName |Phuong|
        | LastName  |Pham|
        | Email     |phuong@gmail.com|
        | Password  |1234|
        | Confirm Password |1234|
      And I click register button
      Then I should see the invalid password error messages "Password must meet the following rules: must have at least 6 characters and not greater than 64 characters"

    Scenario: Register_03: Register with mismatchPassword
      When I fill in register form with data:
        | FirstName |Phuong|
        | LastName  |Pham|
        | Email     |phuong@gmail.com|
        | Password  |123456789|
        | Confirm Password |1234|
      And I click register button
      Then I should see the mismatch password error messages "The password and confirmation password do not match."

      Scenario Outline: Register_04: Register with invalid email
        When I enter "<FirstName>","<LastName>","<Email>","<Password>","<ConfirmPassword>" in register form
        And I click register button
        Then I should see the invalid email error message "<error message>"
        Examples:
          |FirstName|LastName|Email|Password|ConfirmPassword|error message|
          |Phuong|Pham|phuong|123456789|123456789|Please enter a valid email address.|
          |Phuong|Pham|phuong@|123456789|123456789|Please enter a valid email address.|
          |Phuong|Pham|phuong.com|123456789|123456789|Please enter a valid email address.|


#        Scenario: Register_05: Register with existed email
#          Given my account already existed
#          Given I fill in register form with data:
#            | FirstName |Phuong|
#            | LastName  |Pham|
#            | Email     |nhan@gmail.com|
#            | Password  |123456789|
#            | Confirm Password |123456789|
#          And I click register button
#          And I should see the register successful messages "Your registration completed"
#
#          When I fill in register form with data:
#            | FirstName |Phuong|
#            | LastName  |Pham|
#            | Email     |nhan@gmail.com|
#            | Password  |123456789|
#            | Confirm Password |123456789|
#          And I click register button
#          Then I should see the error messages "The specified email already exists"

        Scenario: Register_06: Register with valid email
          When I fill in register form with data:
            | FirstName |Phuong|
            | LastName  |Pham|
            | Email     |[random]|
            | Password  |123456789|
            | Confirm Password |123456789|
          And I click register button
          Then I should see the register successful messages "Your registration completed"


