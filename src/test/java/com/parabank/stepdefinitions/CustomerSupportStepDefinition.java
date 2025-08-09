package com.parabank.stepdefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.parabank.driver.DriverFactory;
import com.parabank.pages.LoginPage;
import com.parabank.pages.SupportPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CustomerSupportStepDefinition {
	
	WebDriver driver;
    LoginPage loginPage;
    SupportPage supportPage;

    @Given("the user is logged into the system")
    public void the_user_is_logged_into_the_system() {
        driver = DriverFactory.initDriver();
        loginPage = new LoginPage(driver);
        loginPage.goTo();
        loginPage.login("validUser", "validPass", false);
    }

    @When("the user clicks on the customer support link")
    public void the_user_clicks_on_the_customer_support_link() {
        supportPage = new SupportPage(driver);
        supportPage.openSupportForm();
    }

    @Then("the support form should be displayed")
    public void the_support_form_should_be_displayed() {
        assertTrue(supportPage.isSupportFormDisplayed());
    }

    @Given("the user is on the customer support form")
    public void the_user_is_on_the_customer_support_form() {
        supportPage = new SupportPage(driver);
        supportPage.openSupportForm();
        assertTrue(supportPage.isSupportFormDisplayed());
    }

    @When("the user fills the form with valid details")
    public void the_user_fills_the_form_with_valid_details() {
        supportPage.fillForm("John Doe", "john@example.com", "1234567890", "Need help with account.");
        supportPage.submitForm();
    }

    @Then("a success message should be shown")
    public void a_success_message_should_be_shown() {
        assertTrue(supportPage.getSuccessMessage().contains("Thank you"));
    }

    @When("the user submits the form without entering details")
    public void the_user_submits_the_form_without_entering_details() {
        supportPage.submitForm();
    }

    @Then("appropriate validation messages should be displayed")
    public void appropriate_validation_messages_should_be_displayed() {
        assertTrue(supportPage.isValidationErrorDisplayed());
    }

    @When("the user fills the form and submits it")
    public void the_user_fills_the_form_and_submits_it() {
        supportPage.fillForm("Jane Smith", "jane@example.com", "9876543210", "Inquiry about loan.");
        supportPage.submitForm();
    }

    @Then("the confirmation message should be displayed")
    public void the_confirmation_message_should_be_displayed() {
        assertTrue(supportPage.getSuccessMessage().contains("Thank you"));
    }

}
