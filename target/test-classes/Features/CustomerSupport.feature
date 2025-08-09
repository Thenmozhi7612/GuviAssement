Feature: Customer Support

  Scenario: Access and open customer support form
    Given the user is logged into the system
    When the user clicks on the customer support link
    Then the support form should be displayed

  Scenario: Submit form with valid user information
    Given the user is on the customer support form
    When the user fills the form with valid details
    Then a success message should be shown

  Scenario: Submit form with empty fields
    Given the user is on the customer support form
    When the user submits the form without entering details
    Then appropriate validation messages should be displayed

  Scenario: Verify form submission success message
    Given the user is on the customer support form
    When the user fills the form and submits it
    Then the confirmation message should be displayed