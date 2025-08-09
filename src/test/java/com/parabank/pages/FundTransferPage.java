package com.parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FundTransferPage {


	WebDriver driver;

	@FindBy(linkText = "Transfer Funds")
	WebElement transferFundsLink;
	@FindBy(id = "amount")
	WebElement amountInput;
	@FindBy(id = "fromAccountId")
	WebElement fromAccountDropdown;
	@FindBy(id = "toAccountId")
	WebElement toAccountDropdown;
	@FindBy(css = "input[type='submit']")
	WebElement transferButton;

	@FindBy(css = ".error")
	WebElement errorMessage;
	@FindBy(css = ".confirmation")
	WebElement confirmationMessage;

	public FundTransferPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToTransferPage() {
		transferFundsLink.click();
	}

	public void transferFunds(String fromAcc, String toAcc, String amount) {
		amountInput.clear();
		amountInput.sendKeys(amount);
		new Select(fromAccountDropdown).selectByVisibleText(fromAcc);
		new Select(toAccountDropdown).selectByVisibleText(toAcc);
		transferButton.click();
	}

	public boolean isTransferSuccessful() {
		return confirmationMessage.isDisplayed();
	}

	public boolean areBalancesUpdated() {
		// Placeholder logic
		return true;
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

	public void submitEmptyTransferForm() {
		amountInput.clear();
		new Select(fromAccountDropdown).selectByIndex(0);
		new Select(toAccountDropdown).selectByIndex(0);
		transferButton.click();
	}

	public boolean areValidationErrorsVisible() {
		return errorMessage.isDisplayed();
	}

	public boolean isConfirmationMessageDisplayed() {
		return confirmationMessage.isDisplayed();
	}
}
