package com.parabank.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SupportPage {
	
	private WebDriver driver;

    @FindBy(linkText = "Contact Us")
    WebElement contactUsLink;

    @FindBy(name = "name")
    WebElement nameInput;

    @FindBy(name = "email")
    WebElement emailInput;

    @FindBy(name = "phone")
    WebElement phoneInput;

    @FindBy(name = "message")
    WebElement messageInput;

    @FindBy(css = "input[type='submit']")
    WebElement sendButton;

    @FindBy(id = "supportResponse")
    WebElement supportResponseMessage;

    public SupportPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void openSupportForm() {
        contactUsLink.click();
    }

    public boolean isSupportFormDisplayed() {
        return messageInput.isDisplayed();
    }

    public void fillForm(String name, String email, String phone, String message) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        phoneInput.sendKeys(phone);
        messageInput.sendKeys(message);
    }

    public void submitForm() {
        sendButton.click();
    }

    public String getSuccessMessage() {
        return supportResponseMessage.getText();
    }

    public boolean isValidationErrorDisplayed() {
        // Placeholder: Replace with actual validation logic
        return driver.getPageSource().contains("This field is required");
    }

}
