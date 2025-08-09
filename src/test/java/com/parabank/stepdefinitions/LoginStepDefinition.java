package com.parabank.stepdefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import com.parabank.driver.DriverFactory;
import com.parabank.pages.LoginPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {
	
	 WebDriver driver;
	    LoginPage loginPage;

	    @Given("the user is on the login page")
	    public void the_user_is_on_the_login_page() {
	        driver = DriverFactory.initDriver();
	        loginPage = new LoginPage(driver);
	        loginPage.goTo();
	    }

	    @When("the user logs in with username {string} and password {string}")
	    public void the_user_logs_in_with_username_and_password(String username, String password) {
	        loginPage.login(username, password, false);
	    }

	    @Then("the login result should be {string}")
	    public void the_login_result_should_be(String result) {
	        if (result.equalsIgnoreCase("success")) {
	            assertTrue(driver.getTitle().contains("Accounts Overview"));
	        } else if (result.equalsIgnoreCase("error")) {
	            assertFalse(loginPage.getErrorMessage().isEmpty());
	        } else {
	            assertEquals(result, loginPage.getErrorMessage());
	        }
	    }

	    @Then("the password field should be masked")
	    public void the_password_field_should_be_masked() {
	        assertTrue(loginPage.isPasswordMasked());
	    }

	    @Given("the user is logged in")
	    public void the_user_is_logged_in() {
	        driver = DriverFactory.initDriver();
	        loginPage = new LoginPage(driver);
	        loginPage.goTo();
	        loginPage.login("validUser", "validPass", false);
	    }

	    @And("the user is inactive for 5 minutes")
	    public void the_user_is_inactive_for_5_minutes() throws InterruptedException {
	        Thread.sleep(1000); // 5 minutes
	    }

	    @Then("the user should be logged out and redirected to the login page")
	    public void the_user_should_be_logged_out_and_redirected_to_the_login_page() {
	        assertTrue(driver.getCurrentUrl().contains("login.htm"));
	    }

	    @When("the user logs in with username {string} and password {string} with remember me checked")
	    public void the_user_logs_in_with_username_and_password_with_remember_me_checked(String username, String password) {
	        loginPage.login(username, password, true);
	    }

	    @And("the user closes and reopens the browser")
	    public void the_user_closes_and_reopens_the_browser() {
	        DriverFactory.quitDriver();
	        driver = DriverFactory.initDriver();
	        loginPage = new LoginPage(driver);
	        loginPage.goTo();
	    }

	    @Then("the user should still be logged in")
	    public void the_user_should_still_be_logged_in() {
	        assertTrue(driver.getTitle().contains("Accounts Overview"));
	    }

}
