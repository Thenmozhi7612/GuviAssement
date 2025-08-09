package com.parabank.stepdefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.parabank.driver.DriverFactory;
import com.parabank.pages.FundTransferPage;
import com.parabank.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FundTransferStepDefinition {
	
	WebDriver driver;
    LoginPage loginPage;
    FundTransferPage transferPage;

    @Given("the user is logged in for fund transfer")
    public void the_user_is_logged_in_for_fund_transfer() {
        driver = DriverFactory.initDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login("validUser", "validPass", false);

        transferPage = new FundTransferPage(driver);
        transferPage.navigateToTransferPage();
    }

    @When("the user transfers {string} from account {string} to account {string}")
    public void the_user_transfers_from_account_to_account(String amount, String fromAcc, String toAcc) {
        transferPage.transferFunds(fromAcc, toAcc, amount);
    }

    @Then("the transfer should be successful")
    public void the_transfer_should_be_successful() {
        assertTrue(transferPage.isTransferSuccessful());
    }

    @Then("the account balances should be updated accordingly")
    public void the_account_balances_should_be_updated_accordingly() {
        assertTrue(transferPage.areBalancesUpdated());
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String errorMsg) {
        assertTrue(transferPage.getErrorMessage().contains(errorMsg));
    }

    @When("the user submits the fund transfer form without entering required fields")
    public void the_user_submits_fund_transfer_form_without_required_fields() {
        transferPage.submitEmptyTransferForm();
    }

    @Then("validation errors should be shown")
    public void validation_errors_should_be_shown() {
        assertTrue(transferPage.areValidationErrorsVisible());
    }

    @When("the user tries to transfer {string} from and to the same account {string}")
    public void the_user_tries_to_transfer_from_and_to_same_account(String amount, String accNum) {
        transferPage.transferFunds(accNum, accNum, amount);
    }

    @Then("a confirmation message should be displayed")
    public void a_confirmation_message_should_be_displayed() {
        assertTrue(transferPage.isConfirmationMessageDisplayed());
    }

    @Then("the account balances should reflect the transfer")
    public void the_account_balances_should_reflect_the_transfer() {
        assertTrue(transferPage.areBalancesUpdated());
    }

}
