@Login
Feature:  Login feature
  Background:
    Given I am on the login page
    Scenario Outline: Login_01: Log in with invalid data
      When I log in with "<email>" and "<password>"
      And I clicks login button
      Then I should see the "<message>"
      Examples:
        |case				|email					|password	  |message|
        |EmptyData			|            			|             |Please enter your email|
        |InvalidEmail		|hoa   		            |123456789    |Please enter a valid email address.|
        |NonRegisterEmail	|men@gmail.com   		|123456789    |Login was unsuccessful. Please correct the errors and try again.\nNo customer account found|
        |EmptyPassword		|hoa@gmail.com          |             |Login was unsuccessful. Please correct the errors and try again.\nNo customer account found|
        |InvalidPassword	|hoa@gmail.com   		|1234         |Login was unsuccessful. Please correct the errors and try again.\nNo customer account found|



#    Scenario Outline: Login_01: Log in with invalid data
#    When user enters "<email>" and "<password>"
#    And user clicks login button
#    Then a message "<message>" is displayed at "<position>"
#    Examples:
#    |email           |password     |message                                                                                  |position       |
#    |                |             |Please enter your email                                                                  |email          |
#    |phuong          |123456789    |Please enter a valid email address.                                                      |email          |
#    |hoa@gmail.com   |123456789    |Login was unsuccessful. Please correct the errors and try again.No customer account found|summary        |
#    |hoa@gmail.com   |             |Login was unsuccessful. Please correct the errors and try again.No customer account found|summary        |
#    |hoa@gmail.com   |1234         |Login was unsuccessful. Please correct the errors and try again.No customer account found|summary        |


    Scenario: Login_02: Log in with valid email and password
    When I log in with "admin@yourstore.com" and "admin"
    And I clicks login button
    Then I should be navigated to HomePage
