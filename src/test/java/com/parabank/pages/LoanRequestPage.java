package com.parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoanRequestPage {
	 WebDriver driver;

    @FindBy(linkText = "Request Loan")
    WebElement requestLoanLink;

    @FindBy(id = "amount")
    WebElement loanAmountInput;

    @FindBy(id = "downPayment")
    WebElement downPaymentInput;

    @FindBy(id = "fromAccountId")
    WebElement fromAccountDropdown;

    @FindBy(css = "input[type='submit']")
    WebElement applyNowButton;

    @FindBy(css = ".title + p")
    WebElement loanStatusMessage;

    public LoanRequestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoanPage() {
        requestLoanLink.click();
    }

    public void submitLoanRequest(String amount, String downPayment, String fromAccount) {
        loanAmountInput.clear();
        loanAmountInput.sendKeys(amount);
        downPaymentInput.clear();
        downPaymentInput.sendKeys(downPayment);
        new org.openqa.selenium.support.ui.Select(fromAccountDropdown).selectByVisibleText(fromAccount);
        applyNowButton.click();
    }

    public boolean isLoanStatusDisplayed() {
        return loanStatusMessage.isDisplayed();
    }

    public String getLoanStatusMessage() {
        return loanStatusMessage.getText();
    }

    public void submitEmptyLoanRequest() {
        loanAmountInput.clear();
        downPaymentInput.clear();
        applyNowButton.click();
    }

}
