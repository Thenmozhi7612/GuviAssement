Feature: Loan Request Functionality

  Scenario: Submit loan request with valid details
    Given user is on the loan request page
    When user submits loan request with amount "5000", down payment "1000", and from account "12345"
    Then loan status message should be displayed

  Scenario: Submit loan request with missing information
    Given user is on the loan request page
    When user submits loan request with empty fields
    Then loan status message should not be displayed

  Scenario: Submit loan request exceeding limit
    Given user is on the loan request page
    When user submits loan request with amount "9999999", down payment "10", and from account "12345"
    Then user should see loan rejection message

  Scenario: Verify loan request status and result
    Given user is on the loan request page
    When user submits loan request with amount "2000", down payment "500", and from account "12345"
    Then user should see loan status as approved or rejected