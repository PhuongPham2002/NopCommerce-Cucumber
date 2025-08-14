@MyAccount
Feature: MyAccount

  Background:
    Given The user information is updated via API
      | FirstName | Rosie               |
      | LastName  | Nguyen              |
      | Email     | admin@yourstore.com |
    And The user access "Nopcommerce Webpage"
    And The user is on the login page
    And The user log in with "email" and "password"
    And The user navigate to the My Account page
    And The user navigates to "customer info" tab

  Scenario: MyAccount_01: Update customer Info
    When The user updates the following user information
      | FirstName  | Hau        |
      | LastName   | Ho         |
      | Company    | FPT        |
      | Gender     | Male       |
      | NewsLetter | Subscribed |
    Then The user should see the update info success message "The customer info has been updated successfully."
    And The user should see the following updated information
      | FirstName  | Hau        |
      | LastName   | Ho         |
      | Company    | FPT        |
      | Gender     | Male       |
      | NewsLetter | Subscribed |

  Scenario: MyAccount_02: Update user info with empty required field: firstname, lastname and email data
    When The user updates the following user information
      | FirstName |  |
      | LastName  |  |
      | Email     |  |
    Then The user should see the error message on the "Customer Info" page
      | FirstName | First name is required. |
      | LastName  | Last name is required.  |
      | Email     | Email is required.      |

  Scenario Outline: MyAccount_03: Update user info with invalid email
    When The user updates the following user information
      | Email | <invalidEmail> |
    And The user should see the error message on the "Customer Info" page
      | Email | <error message> |
    Examples:
      | invalidEmail | error message                       |
      | phuong       | Please enter a valid email address. |
      | phuong@      | Please enter a valid email address. |
      | phuong.com   | Please enter a valid email address. |

  Scenario: MyAccount_04: Update user info with existing email address
    When The user updates the following user information
      | Email | phuong@gmail.com |
    Then The user should see the error message on the "Customer Info" page
      | Email | The e-mail address is already in use |

    # Cách thử 2 sử dụng random
  Scenario: MyAccount_01: Update customer Info
    When The user access "Nopcommerce Webpage"
    And The user is on the login page
    And The user log in with "email" and "password"
    And The user navigate to the My Account page
    And The user navigates to "customer info" tab
    And The user updates the following user information random
      | FirstName  | randomFirstName  |
      | LastName   | randomLastName   |
      | Company    | randomCompany    |
      | Gender     | randomGender     |
      | NewsLetter | randomSubscribed |
    Then The user should see the update info success message "The customer info has been updated successfully."
    And The user should see the following updated information random
      | FirstName  | randomFirstName  |
      | LastName   | randomLastName   |
      | Company    | randomCompany    |
      | Gender     | randomGender     |
      | NewsLetter | randomSubscribed |