Feature: Funds Transfer Functionality

  Scenario: Transfer funds between valid internal accounts
    Given the user is logged in for fund transfer
    When the user transfers "$500" from account "12345" to account "67890"
    Then the transfer should be successful
    And the account balances should be updated accordingly

  Scenario: Transfer funds with insufficient balance
    Given the user is logged in for fund transfer
    When the user transfers "$100000" from account "12345" to account "67890"
    Then an error message "Insufficient balance." should be displayed

  Scenario: Transfer funds with empty fields
    Given the user is logged in for fund transfer
    When the user submits the fund transfer form without entering required fields
    Then validation errors should be shown

  Scenario: Attempt to transfer to the same account
    Given the user is logged in for fund transfer
    When the user tries to transfer "$200" from and to the same account "12345"
    Then an error message "Cannot transfer to the same account." should be displayed

  Scenario: Transfer large or decimal amount
    Given the user is logged in for fund transfer
    When the user transfers "$12345.67" from account "12345" to account "67890"
    Then the transfer should be successful

  Scenario: Validate transfer confirmation and updated balances
    Given the user is logged in for fund transfer
    When the user transfers "$300" from account "12345" to account "67890"
    Then a confirmation message should be displayed
    And the account balances should reflect the transfer