@Register
Feature: Register

  Background:
    Given The user access Nopcommerce Webpage "https://localhost:59579/"
    Given The user is on the register page

  Scenario: Register_01: Register with empty data
    When The user register a new account with the following information
      | FirstName        |  |
      | LastName         |  |
      | Email            |  |
      | Password         |  |
      | ConfirmPassword |  |
    Then The user should see the following register error message
      | FirstName       | First name is required. |
      | LastName        | Last name is required.  |
      | Email           | Email is required.      |
      | ConfirmPassword | Password is required.   |
    # Web Nopcommerce hiển thị mỗi error message cho trường confirm password, ko có cho password =.=

  Scenario: Register_02: Register with invalid password
    When The user register a new account with the following information
      | FirstName        | Phuong           |
      | LastName         | Pham             |
      | Email            | phuong@gmail.com |
      | Password         | 1234             |
      | ConfirmPassword | 1234             |
    Then The user should see the register error message "Password must meet the following rules: must have at least 6 characters and not greater than 64 characters" at "password" field

  Scenario: Register_03: Register with mismatchPassword
    When The user register a new account with the following information
      | FirstName        | Phuong           |
      | LastName         | Pham             |
      | Email            | phuong@gmail.com |
      | Password         | 123456789        |
      | ConfirmPassword | 1234             |
    Then The user should see the register error message "The password and confirmation password do not match." at "confirm password" field

  Scenario Outline: Register_04: Register with invalid email
    When The user register a new account with the following information
      | FirstName        | <FirstName>       |
      | LastName         | <LastName>        |
      | Email            | <Email>           |
      | Password         | <Password>        |
      | ConfirmPassword | <ConfirmPassword> |
    Then The user should see the register error message "<error message>" at "email" field
    Examples:
      | FirstName | LastName | Email      | Password  | ConfirmPassword | error message                       |
      | Phuong    | Pham     | phuong     | 123456789 | 123456789       | Please enter a valid email address. |
      | Phuong    | Pham     | phuong@    | 123456789 | 123456789       | Please enter a valid email address. |
      | Phuong    | Pham     | phuong.com | 123456789 | 123456789       | Please enter a valid email address. |


  Scenario: Register_06: Register with valid email
    When The user register a new account with the following information
      | FirstName        | Phuong    |
      | LastName         | Pham      |
      | Email            | [random]  |
      | Password         | 123456789 |
      | ConfirmPassword | 123456789 |
    Then I should see the register successful messages "Your registration completed"


