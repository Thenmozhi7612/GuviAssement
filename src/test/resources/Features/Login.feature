Feature: Login Functionality

  Scenario Outline: Login attempt with various credentials
    Given the user is on the login page
    When the user logs in with username "<username>" and password "<password>"
    Then the login result should be "<result>"

    Examples:
      | username        | password     | result                      |
      | Thenu       	| PASSW123     | success                     |
      | Prasad       	| wrongPass    | Invalid credentials.        |
      | 12Dacw	        | anyPass      | User does not exist.        |
      |                 |              | error                       |
      | invalidEmail    | somePass     | error                       |

  Scenario: Verify password field masks input
    Given the user is on the login page
    Then the password field should be masked

  Scenario: Verify session timeout due to inactivity
    Given the user is logged in
    And the user is inactive for 5 minutes
    Then the user should be logged out and redirected to the login page

  Scenario: Verify "Remember Me" functionality
    Given the user is on the login page
    When the user logs in with username "Validuser" and password "validPass" with remember me checked
    And the user closes and reopens the browser
    Then the user should still be logged in
    
    
    