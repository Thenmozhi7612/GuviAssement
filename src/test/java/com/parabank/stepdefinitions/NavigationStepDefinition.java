package com.parabank.stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import com.parabank.driver.DriverFactory;
import com.parabank.pages.NavigationPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class NavigationStepDefinition {
	
	private WebDriver driver = DriverFactory.getDriver();
    private NavigationPage navPage = new NavigationPage(driver);

    // 1. Navigate through all main navigation links

    @Given("the user is logged into the application")
    public void the_user_is_logged_into_the_application() {
        navPage.loginWithValidCredentials(); // This should log in with valid test credentials
    }

    @When("the user clicks on the Home link")
    public void the_user_clicks_on_the_home_link() {
        navPage.clickHomeLink();
    }

    @Then("the Home page should be displayed")
    public void the_home_page_should_be_displayed() {
        Assert.assertTrue(navPage.isHomePageVisible());
    }

    @When("the user clicks on the Accounts link")
    public void the_user_clicks_on_the_accounts_link() {
        navPage.clickAccountsLink();
    }

    @Then("the Accounts Overview page should be displayed")
    public void the_accounts_overview_page_should_be_displayed() {
        Assert.assertTrue(navPage.isAccountsOverviewVisible());
    }

    @When("the user clicks on the Transfer Funds link")
    public void the_user_clicks_on_the_transfer_funds_link() {
        navPage.clickTransferFundsLink();
    }

    @Then("the Transfer Funds page should be displayed")
    public void the_transfer_funds_page_should_be_displayed() {
        Assert.assertTrue(navPage.isTransferFundsPageVisible());
    }

    @When("the user clicks on the Request Loan link")
    public void the_user_clicks_on_the_request_loan_link() {
        navPage.clickLoanRequestLink();
    }

    @Then("the Loan Request page should be displayed")
    public void the_loan_request_page_should_be_displayed() {
        Assert.assertTrue(navPage.isLoanRequestPageVisible());
    }

    @When("the user clicks on the Contact Us link")
    public void the_user_clicks_on_the_contact_us_link() {
        navPage.clickContactUsLink();
    }

    @Then("the Customer Care page should be displayed")
    public void the_customer_care_page_should_be_displayed() {
        Assert.assertTrue(navPage.isContactPageVisible());
    }

    // 2. Verify logo redirect

    @When("the user clicks on the site logo")
    public void the_user_clicks_on_the_site_logo() {
        navPage.clickLogo();
    }

    @Then("the user should be redirected to the Home page")
    public void the_user_should_be_redirected_to_the_home_page() {
        Assert.assertTrue(navPage.isHomePageVisible());
    }

    // 3. Validate visibility and clickability of buttons

    @Given("the user is on the relevant application pages")
    public void the_user_is_on_the_relevant_application_pages() {
        navPage.navigateToAllRelevantPages(); // Custom method to iterate through pages if needed
    }

    @Then("the Submit, Transfer, and Apply Now buttons should be visible and clickable")
    public void the_buttons_should_be_visible_and_clickable() {
        Assert.assertTrue(navPage.areAllMajorButtonsClickable());
    }

    // 4. Font and alignment

    @Given("the user visits different application pages")
    public void the_user_visits_different_application_pages() {
        navPage.navigateToAllRelevantPages();
    }

    @Then("the font style and alignment should be consistent")
    public void the_font_style_and_alignment_should_be_consistent() {
        Assert.assertTrue(navPage.isFontAndLayoutConsistent());
    }

    // 5. Alerts and popup

    @Given("the user triggers an action that causes an alert")
    public void the_user_triggers_an_action_that_causes_an_alert() {
        navPage.triggerAlertPopup();
    }

    @Then("a proper alert message should be displayed")
    public void a_proper_alert_message_should_be_displayed() {
        Assert.assertTrue(navPage.isAlertMessageCorrect());
    }
}
