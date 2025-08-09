package com.parabank.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionHistoryPage {

	 WebDriver driver;

	@FindBy(css = "table#transactionTable tbody tr")
	List<WebElement> rows;
	@FindBy(id = "fromDate")
	WebElement fromDate;
	@FindBy(id = "toDate")
	WebElement toDate;
	@FindBy(css = "button#filter")
	WebElement filterBtn;
	@FindBy(css = "th.date")
	WebElement dateHeader;
	@FindBy(css = "th.amount")
	WebElement amountHeader;

	public TransactionHistoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int getRowCount() {
		return rows.size();
	}

	public void filterByDate(String from, String to) {
		fromDate.clear();
		fromDate.sendKeys(from);
		toDate.clear();
		toDate.sendKeys(to);
		filterBtn.click();
	}

	public void sortByDate() {
		dateHeader.click();
	}

	public void sortByAmount() {
		amountHeader.click();
	}

	// Example: return date/amount/type for row
	public Transaction getRow(int idx) {
		WebElement r = rows.get(idx);
		String date = r.findElement(By.cssSelector("td.date")).getText();
		String amount = r.findElement(By.cssSelector("td.amount")).getText();
		String type = r.findElement(By.cssSelector("td.type")).getText();
		return new Transaction(date, amount, type);
	}

	public static class Transaction {
		public String date, amount, type;

		public Transaction(String d, String a, String t) {
			date = d;
			amount = a;
			type = t;
		}
	}

}
