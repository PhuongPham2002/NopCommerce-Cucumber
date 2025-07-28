
@UpdateEmail
Feature: MyAccount

  Background:
    Given The user access "Nopcommerce Webpage"
  Scenario: MyAccount_01: Update email with valid email
    Given The user is on the login page
    And The user log in with "admin@yourstore.com" and "admin123"
    And The user navigate to the My Account page
    And The user has the following registered information
      | Email | admin@yourstore.com |
    When The user navigates to "customer info" tab
    And The user updates the following user information
      | Email | unregistered email |
    Then The user should see the update info success message "The customer info has been updated successfully."
    And The user should see the following updated information
      | Email | unregistered email |

  Scenario: MyAccount_02: Update email address and login successfully with new email address
    # Nên dùng Then cho data đc fill sẵn khi register hay dùng Given (trc When)
    # Gherkin có phải follow UI flow ko? ví dụ như trường hợp Given này m k thấy k theo UI
    Given The user is on the login page
    And The user log in with "admin@yourstore.com" and "admin123"
    And The user navigate to the My Account page
    And The user has the following registered information
      | Email | admin@yourstore.com |
    When The user navigates to "customer info" tab
    And The user updates the following user information
      | Email | unregistered email |
    Then The user should see the update info success message "The customer info has been updated successfully."
    And The user should see the following updated information
      | Email | unregistered email |
    When The user log out from the webpage
    And The user log in with "email" and "password"
    Then The user should be navigated to HomePage
      # Test scenario này thì đã bao gồm scenario 1 nữa không ?




