Feature: Navigation and UI Testing

  Scenario: Navigate through all main navigation links
    Given the user is logged into the application
    When the user clicks on the Home link
    Then the Home page should be displayed
    When the user clicks on the Accounts link
    Then the Accounts Overview page should be displayed
    When the user clicks on the Transfer Funds link
    Then the Transfer Funds page should be displayed
    When the user clicks on the Request Loan link
    Then the Loan Request page should be displayed
    When the user clicks on the Contact Us link
    Then the Customer Care page should be displayed

  Scenario: Verify logo link redirects to Home page
    Given the user is logged into the application
    When the user clicks on the site logo
    Then the user should be redirected to the Home page

  Scenario: Validate visibility and clickability of all major buttons
    Given the user is on the relevant application pages
    Then the Submit, Transfer, and Apply Now buttons should be visible and clickable

  Scenario: Verify consistent font and alignment across pages
    Given the user visits different application pages
    Then the font style and alignment should be consistent

  Scenario: Validate alert boxes and popup messages
    Given the user triggers an action that causes an alert
    Then a proper alert message should be displayed