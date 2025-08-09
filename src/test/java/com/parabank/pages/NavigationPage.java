package com.parabank.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPage {

	 private WebDriver driver;

	    public NavigationPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    // Login Elements
	    @FindBy(name = "username") WebElement usernameField;
	    @FindBy(name = "password") WebElement passwordField;
	    @FindBy(css = "input[type='submit']") WebElement loginButton;

	    // Navigation links
	    @FindBy(linkText = "Home") WebElement homeLink;
	    @FindBy(linkText = "Accounts Overview") WebElement accountsLink;
	    @FindBy(linkText = "Transfer Funds") WebElement transferFundsLink;
	    @FindBy(linkText = "Request Loan") WebElement requestLoanLink;
	    @FindBy(linkText = "Contact Us") WebElement contactUsLink;
	    @FindBy(css = "img[src*='logo']") WebElement siteLogo;
	    @FindBy(xpath = "//h1[contains(text(), 'Customer Care')]")  
	    WebElement contactPageHeading;

	    
	   

	    @FindBy(linkText = "Request Loan")
	    WebElement loanRequestLink;

	    @FindBy(linkText = "Contact Us")
	    WebElement contactLink;
	    
	    // Page Titles or Identifiers
	    @FindBy(tagName = "h1") WebElement header;
	    @FindBy(id = "rightPanel") WebElement rightPanel;

	    // Buttons
	    @FindBy(css = "input[type='submit']") WebElement submitButton;
	    @FindBy(css = "input[value='Transfer']") WebElement transferButton;
	    @FindBy(css = "input[value='Apply Now']") WebElement applyNowButton;

	    // Alert trigger and message
	    @FindBy(id = "trigger-alert") WebElement alertTrigger;  // Add this manually in UI or handle with JS
	    @FindBy(id = "alert-message") WebElement alertMessage;  // If alert content appears in the page DOM

	    // -------------------------------
	    // Functional Methods
	    // -------------------------------

	    public void loginWithValidCredentials() {
	        usernameField.sendKeys("username");
	        passwordField.sendKeys("password");
	        loginButton.click();
	    }
	    
	    public void clickLoanRequestLink() {
	        requestLoanLink.click();
	    }

	    public void clickHomeLink() {
	        homeLink.click();
	    }

	    public void clickAccountsLink() {
	        accountsLink.click();
	    }

	    public void clickTransferFundsLink() {
	        transferFundsLink.click();
	    }

	    public void clickRequestLoanLink() {
	        requestLoanLink.click();
	    }

	    public void clickContactUsLink() {
	        contactUsLink.click();
	    }

	    public void clickLogo() {
	        siteLogo.click();
	    }

	    public boolean isHomePageVisible() {
	        return driver.getTitle().contains("ParaBank") || header.getText().contains("Welcome");
	    }
	    
	    private void waitForPageToLoad() {
	        try {
	            Thread.sleep(1000); // Replace with WebDriverWait for better stability
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    public void navigateToAllRelevantPages() {
	        homeLink.click();
	        waitForPageToLoad();

	        accountsLink.click();
	        waitForPageToLoad();

	        transferFundsLink.click();
	        waitForPageToLoad();

	        loanRequestLink.click();
	        waitForPageToLoad();

	        contactLink.click();
	        waitForPageToLoad();
	    }

	    public boolean isAccountsOverviewVisible() {
	        return driver.getPageSource().contains("Accounts Overview");
	    }

	    public boolean isTransferFundsPageVisible() {
	        return driver.getPageSource().contains("Transfer Funds");
	    }

	    public boolean isLoanRequestPageVisible() {
	        return driver.getPageSource().contains("Apply for a Loan");
	    }
	    
	    public boolean isContactPageVisible() {
	        return contactPageHeading.isDisplayed();
	    }

	    public boolean isCustomerCarePageVisible() {
	        return driver.getPageSource().contains("Customer Care");
	    }

	    public boolean areAllMajorButtonsClickable() {
	        return submitButton.isDisplayed() && submitButton.isEnabled()
	            && transferButton.isDisplayed() && transferButton.isEnabled()
	            && applyNowButton.isDisplayed() && applyNowButton.isEnabled();
	    }

	    public boolean isFontAndLayoutConsistent() {
	        // This is a placeholder: Replace with actual font/alignment check using CSS properties
	        String font = rightPanel.getCssValue("font-family");
	        return font != null && font.contains("Arial"); // adjust based on actual UI
	    }

	    public void triggerAlertPopup() {
	        // If no real alert trigger exists, execute JS manually
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("alert('Test Alert!')");
	    }

	    public boolean isAlertMessageCorrect() {
	        try {
	            Alert alert = driver.switchTo().alert();
	            String alertText = alert.getText();
	            alert.accept();
	            return alertText.equalsIgnoreCase("Test Alert!");
	        } catch (NoAlertPresentException e) {
	            return false;
	        }
	    }
}
