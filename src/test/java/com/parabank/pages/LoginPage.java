package com.parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	 private WebDriver driver;

	    @FindBy(name = "username")
	    WebElement emailField;
	    
	    @FindBy(name = "password")
	    WebElement passwordField;
	    
	    @FindBy(css = "input[type='submit']")
	    WebElement loginBtn;
	    
	    @FindBy(css = ".error") 
	    WebElement errorMsg;
	    
	    @FindBy(id = "remember-me")
	    WebElement rememberCheckbox; // if exists

	    public LoginPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    public void goTo() {
	        driver.get("https://parabank.parasoft.com/parabank/index.html");
	    }

	    public void login(String email, String pwd, boolean rememberMe) {
	        emailField.clear();
	        emailField.sendKeys(email);
	        passwordField.clear();
	        passwordField.sendKeys(pwd);
	        if (rememberMe) rememberCheckbox.click();
	        loginBtn.click();
	    }

	    public String getErrorMessage() {
	        return errorMsg.isDisplayed() ? errorMsg.getText() : "";
	    }

	    public boolean isPasswordMasked() {
	        return "password".equals(passwordField.getAttribute("type"));
	    }

}
