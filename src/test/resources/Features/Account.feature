Feature: Account Functionality

  Scenario: View checking account summary
    Given the user is logged in to the account module
    When the user selects the checking account from the summary
    Then the checking account balance and recent transactions should be displayed

  Scenario: View savings account summary
    Given the user is logged in to the account module
    When the user selects the savings account from the summary
    Then the savings account balance and recent transactions should be displayed

  Scenario: Validate updated balance after a successful transaction
    Given the user is logged in to the account module
    And the user notes the current balance
    When the user performs a successful funds transfer of amount "500"
    Then the balance should update accordingly

  Scenario: View full transaction history
    Given the user is logged in to the account module
    When the user navigates to the full transaction history
    Then the complete list of past transactions should be displayed

  Scenario: Validate transaction entry fields
    Given the user is logged in to the account module
    When the user views the transaction history
    Then each transaction should display date, amount, and type

  Scenario Outline: Filter transactions by date range
    Given the user is logged in to the account module
    When the user filters transactions from "<fromDate>" to "<toDate>"
    Then only transactions within that date range should be displayed

    Examples:
      | fromDate   | toDate     |
      | 01-01-2024 | 31-01-2024 |
      | 01-07-2024 | 31-07-2024 |

  Scenario Outline: Sort transactions by <field> in <order> order
    Given the user is logged in to the account module
    When the user sorts transactions by "<field>" in "<order>" order
    Then the transactions should be sorted by "<field>" in "<order>" order

    Examples:
      | field | order     |
      | date  | ascending |
      | date  | descending|
      | amount| ascending |
      | amount| descending|