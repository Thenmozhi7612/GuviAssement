package com.parabank.stepdefinitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.parabank.driver.DriverFactory;
import com.parabank.pages.LoanRequestPage;
import com.parabank.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoanRequestStepDefinition {
	
	 WebDriver driver;
	    LoginPage loginPage;
	    LoanRequestPage loanRequestPage;

	    @Given("user is on the loan request page")
	    public void user_is_on_the_loan_request_page() {
	        driver = DriverFactory.initDriver();
	        loginPage = new LoginPage(driver);
	        loginPage.goTo();
	        loginPage.login("validUser", "validPass", false);
	        loanRequestPage = new LoanRequestPage(driver);
	        loanRequestPage.navigateToLoanPage();
	    }

	    @When("user submits loan request with amount {string}, down payment {string}, and from account {string}")
	    public void user_submits_loan_request(String amount, String downPayment, String fromAccount) {
	        loanRequestPage.submitLoanRequest(amount, downPayment, fromAccount);
	    }

	    @Then("loan status message should be displayed")
	    public void loan_status_should_be_displayed() {
	        assertTrue(loanRequestPage.isLoanStatusDisplayed());
	    }

	    @When("user submits loan request with empty fields")
	    public void user_submits_loan_request_with_empty_fields() {
	        loanRequestPage.submitEmptyLoanRequest();
	    }

	    @Then("loan status message should not be displayed")
	    public void loan_status_message_should_not_be_displayed() {
	        assertFalse(loanRequestPage.isLoanStatusDisplayed());
	    }

	    @Then("user should see loan rejection message")
	    public void user_should_see_loan_rejection_message() {
	        String status = loanRequestPage.getLoanStatusMessage().toLowerCase();
	        assertTrue(status.contains("denied") || status.contains("rejected"));
	    }

	    @Then("user should see loan status as approved or rejected")
	    public void user_should_see_loan_status_as_approved_or_rejected() {
	        String message = loanRequestPage.getLoanStatusMessage().toLowerCase();
	        assertTrue(message.contains("approved") || message.contains("denied") || message.contains("rejected"));
	    }

}
