package com.parabank.stepdefinitions;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.parabank.driver.DriverFactory;
import com.parabank.pages.AccountPage;
import com.parabank.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AccountStepDefinition {
	
	WebDriver driver;
    LoginPage loginPage;
    AccountPage accountPage;
    double initialBalance;

    @Given("the user is logged in to the account module")
    public void the_user_is_logged_in_to_the_account_module() {
        driver = DriverFactory.initDriver();
        loginPage = new LoginPage(driver);
        accountPage = new AccountPage(driver);
        loginPage.goTo();
        loginPage.login("validUser", "validPass", false);
    }

    @When("the user selects the checking account from the summary")
    public void the_user_selects_the_checking_account() {
        accountPage.selectCheckingAccount();
    }

    @Then("the checking account balance and recent transactions should be displayed")
    public void checking_account_details_should_be_displayed() {
        assertTrue(accountPage.isBalanceVisible());
        assertTrue(accountPage.isRecentTransactionsVisible());
    }

    @When("the user selects the savings account from the summary")
    public void the_user_selects_the_savings_account() {
        accountPage.selectSavingsAccount();
    }

    @Then("the savings account balance and recent transactions should be displayed")
    public void savings_account_details_should_be_displayed() {
        assertTrue(accountPage.isBalanceVisible());
        assertTrue(accountPage.isRecentTransactionsVisible());
    }

    @And("the user notes the current balance")
    public void user_notes_current_balance() {
        initialBalance = accountPage.getCurrentBalance();
    }

    @When("the user performs a successful funds transfer of amount {string}")
    public void user_performs_successful_transaction(String amount) {
        accountPage.transferFunds(amount);
    }

    @Then("the balance should update accordingly")
    public void balance_should_update() {
        double updatedBalance = accountPage.getCurrentBalance();
        assertNotEquals(initialBalance, updatedBalance);
    }

    @When("the user navigates to the full transaction history")
    public void user_navigates_to_transaction_history() {
        accountPage.navigateToTransactionHistory();
    }

    @Then("the complete list of past transactions should be displayed")
    public void full_transaction_list_should_be_displayed() {
        assertTrue(accountPage.isTransactionHistoryVisible());
    }

    @When("the user views the transaction history")
    public void user_views_transaction_history() {
        accountPage.navigateToTransactionHistory();
    }

    @Then("each transaction should display date, amount, and type")
    public void validate_transaction_fields() {
        assertTrue(accountPage.areAllTransactionsFormattedCorrectly());
    }

    @When("the user filters transactions from {string} to {string}")
    public void user_filters_by_date_range(String fromDate, String toDate) {
        accountPage.filterByDateRange(fromDate, toDate);
    }

    @Then("only transactions within that date range should be displayed")
    public void only_filtered_transactions_should_be_shown() {
        assertTrue(accountPage.areTransactionsInDateRange());
    }

    @When("the user sorts transactions by {string} in {string} order")
    public void user_sorts_transactions(String field, String order) {
        accountPage.sortTransactions(field, order);
    }

    @Then("the transactions should be sorted by {string} in {string} order")
    public void transactions_should_be_sorted_correctly(String field, String order) {
        assertTrue(accountPage.areTransactionsSorted(field, order));
    }

}
