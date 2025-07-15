@MyAccount
Feature: MyAccount

  Background:
    Given The user access Nopcommerce Webpage "https://localhost:59579/"
    And The user log in with a valid account
    And The user navigate to the My Account page

  Scenario: MyAccount_01: Update customer Info
    When The user navigates to customer info tab
    And The user updates the following user information
      | FirstName  | Rosie        |
      | LastName   | Nguyen       |
      | Company    | AI Corp      |
      | Gender     | Female       |
      | NewsLetter | Subscribed   |
    Then The user should see the update info success message "The customer info has been updated successfully."
    And The user should see the following updated information
      | FirstName  | Rosie        |
      | LastName   | Nguyen       |
      | Company    | AI Corp      |
      | Gender     | Female       |
      | NewsLetter | Subscribed   |

    Scenario: MyAccount_02: Update email address with valid value
