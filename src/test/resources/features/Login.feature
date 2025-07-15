Feature:  Login feature

  Background:
    Given The user access Nopcommerce Webpage "https://localhost:59579/"
    Given  The user is on the login page

  Scenario Outline: Login_01: Log in with invalid password and unregister email
    When The user log in with "<email>" and "<password>"
    Then The user should see the message "<message>"
    Examples:
      | case             | email         | password  | message                                                                                     |
      | NonRegisterEmail | men@gmail.com | 123456789 | Login was unsuccessful. Please correct the errors and try again.\nNo customer account found |
      | EmptyPassword    | hoa@gmail.com |           | Login was unsuccessful. Please correct the errors and try again.\nNo customer account found |
      | InvalidPassword  | hoa@gmail.com | 1234      | Login was unsuccessful. Please correct the errors and try again.\nNo customer account found |

  Scenario: Login_02: Log in with invalid email
    When The user enter "hoa" into email field and move to "password" field
    Then The user should see the message "Please enter a valid email address."

  Scenario: Login_03: Log in with empty data
    When The user log in with "" and ""
    Then The user should see the message "Please enter your email"

  Scenario: Login_04: Log in with valid email and password
    When The user log in with "email" and "password"
    Then The user should be navigated to HomePage
