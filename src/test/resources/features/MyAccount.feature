@MyAccount
Feature: MyAccount

  Background:
    Given The user access "Nopcommerce Webpage"
    And The user is on the login page
    And The user log in with "admin@yourstore.com" and "admin123"
    And The user navigate to the My Account page

  Scenario: MyAccount_01: Update customer Info
    # Given này có phải note cả các trường chưa có thông tin không? có dụ company, subscribed or not? gender
    Given The user has the following registered information
      | FirstName | Rosie  |
      | LastName  | Nguyen |
    When The user navigates to "customer info" tab
    And The user updates the following user information
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
# Có nên có bước reset data trước khi chạy scenario thứ 2 không?
  Scenario: MyAccount_02: Update user info with empty required field: firstname, lastname and email data
    Given The user has the following registered information
    # với các case ko update đc thì có ần thêm step này cho rox ràng ko?)
      | FirstName | Rosie               |
      | LastName  | Nguyen              |
      | Email     | admin@yourstore.com |
    When The user navigates to "customer info" tab
    And The user updates the following user information
      | FirstName |  |
      | LastName  |  |
      | Email     |  |
    Then The user should see the error message on the "Customer Info" page
      | FirstName | First name is required. |
      | LastName  | Last name is required.  |
      | Email     | Email is required.      |

  Scenario Outline: MyAccount_03: Update user info with invalid email
    Given The user has the following registered information
      | FirstName | Rosie               |
      | LastName  | Nguyen              |
      | Email     | admin@yourstore.com |
    When The user navigates to "customer info" tab
    And The user updates the following user information
    # Chỉ test mỗi trường email có ok ko?
    # Khi nhập email xong, move out ra khỏi textbox là hiển thị validate message
      | Email | <invalidEmail> |
    And The user should see the error message on the "Customer Info" page
      | Email | <error message> |
    Examples:
      | invalidEmail | error message                       |
      | phuong       | Please enter a valid email address. |
      | phuong@      | Please enter a valid email address. |
      | phuong.com   | Please enter a valid email address. |

Scenario: MyAccount_04: Update user info with existing email address
  Given The user has the following registered information
    | FirstName | Rosie               |
    | LastName  | Nguyen              |
    | Email     | admin@yourstore.com |
  When The user navigates to "customer info" tab
  And The user updates the following user information
    | Email | phuong@gmail.com |
  Then The user should see the error message on the "Customer Info" page
    | Email | The e-mail address is already in use |
