Feature:  Login feature
  Background:
    Given user is on the login page

  Scenario Outline: Login_01: Log in with invalid data
    When user enters "<email>" and "<password>"
    And user clicks login button
    Then a message "<message>" is displayed at "<position>"
    Examples:
    |email           |password     |message                                                                                  |position       |
    |                |             |Please enter your email                                                                  |email          |
    |phuong          |123456789    |Please enter a valid email address.                                                      |email          |
    |hoa@gmail.com   |123456789    |Login was unsuccessful. Please correct the errors and try again.No customer account found|summary        |
    |hoa@gmail.com   |             |Login was unsuccessful. Please correct the errors and try again.No customer account found|summary        |
    |hoa@gmail.com   |1234         |Login was unsuccessful. Please correct the errors and try again.No customer account found|summary        |


  Scenario: Login_02: Log in with valid email and password
    When user enters valid email and valid password
    And user clicks login button
    Then my account link should be visible
