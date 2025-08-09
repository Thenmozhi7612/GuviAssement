package com.parabank.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class AccountPage {

	private WebDriver driver;

	@FindBy(css = "#accountTable")
	WebElement accountSummaryTable;
	@FindBy(css = ".account a")
	List<WebElement> accountLinks;
	@FindBy(id = "transactionTable")
	WebElement transactionTable;

	@FindBy(id = "type")
	WebElement transactionTypeDropdown;
	@FindBy(id = "fromDate")
	WebElement fromDateInput;
	@FindBy(id = "toDate")
	WebElement toDateInput;
	@FindBy(id = "searchBtn")
	WebElement searchButton;

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void goToAccountSummary() {
		driver.findElement(By.linkText("Accounts Overview")).click();
	}

	public boolean isSummaryDisplayed() {
		return accountSummaryTable.isDisplayed();
	}

	public void selectFirstAccount() {
		if (!accountLinks.isEmpty()) {
			accountLinks.get(0).click();
		}
	}

	public boolean isAccountDetailsDisplayed() {
		return driver.getPageSource().contains("Account Details");
	}

	public void goToTransactionPage(String accountNumber) {
		driver.get("https://parabank.parasoft.com/parabank/activity.htm?id=" + accountNumber);
	}

	public boolean isTransactionHistoryVisible() {
		return transactionTable.isDisplayed();
	}

	public void searchTransactionByType(String type) {
		new Select(transactionTypeDropdown).selectByVisibleText(type);
		searchButton.click();
	}

	public boolean verifyTransactionType(String type) {
		List<WebElement> rows = transactionTable.findElements(By.cssSelector("tbody tr"));
		for (WebElement row : rows) {
			String transactionType = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
			if (!transactionType.equalsIgnoreCase(type)) {
				return false;
			}
		}
		return true;
	}

	public void searchTransactionByDateRange(String fromDate, String toDate) {
		fromDateInput.clear();
		fromDateInput.sendKeys(fromDate);
		toDateInput.clear();
		toDateInput.sendKeys(toDate);
		searchButton.click();
	}

	public boolean areTransactionsWithinDateRange() {
		List<WebElement> rows = transactionTable.findElements(By.cssSelector("tbody tr"));
		if (rows.isEmpty())
			return false;

		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		try {
			Date from = sdf.parse(fromDateInput.getAttribute("value"));
			Date to = sdf.parse(toDateInput.getAttribute("value"));

			for (WebElement row : rows) {
				String dateStr = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
				Date txnDate = sdf.parse(dateStr);
				if (txnDate.before(from) || txnDate.after(to)) {
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ✅ NEW: Select Checking Account
	public void selectCheckingAccount() {
		for (WebElement link : accountLinks) {
			if (link.getText().toLowerCase().contains("checking")) {
				link.click();
				break;
			}
		}
	}

	// ✅ NEW: Select Savings Account
	public void selectSavingsAccount() {
		for (WebElement link : accountLinks) {
			if (link.getText().toLowerCase().contains("savings")) {
				link.click();
				break;
			}
		}
	}

	// ✅ NEW: Check if balance is visible
	public boolean isBalanceVisible() {
		try {
			WebElement balance = driver
					.findElement(By.xpath("//*[contains(text(), 'Available Balance') or contains(text(), 'Balance')]"));
			return balance.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	// ✅ NEW: Get Current Balance (assumes presence of balance in specific location)
	public double getCurrentBalance() {
		try {
			WebElement balance = driver
					.findElement(By.xpath("//*[contains(text(), 'Available Balance')]/following-sibling::td"));
			String balanceText = balance.getText().replace("$", "").replace(",", "").trim();
			return Double.parseDouble(balanceText);
		} catch (Exception e) {
			return 0.0;
		}
	}
	
	public boolean isRecentTransactionsVisible() {
	    List<WebElement> rows = transactionTable.findElements(By.cssSelector("tbody tr"));
	    return !rows.isEmpty(); // True if at least one recent transaction is visible
	}
	
	public boolean areTransactionsInDateRange(String fromDate, String toDate) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH);
	    LocalDate from = LocalDate.parse(fromDate, formatter);
	    LocalDate to = LocalDate.parse(toDate, formatter);

	    List<WebElement> rows = transactionTable.findElements(By.cssSelector("tbody tr"));

	    for (WebElement row : rows) {
	        String dateText = row.findElement(By.cssSelector("td:nth-child(1)")).getText();
	        LocalDate transactionDate = LocalDate.parse(dateText, formatter);

	        if (transactionDate.isBefore(from) || transactionDate.isAfter(to)) {
	            return false; // found a transaction outside the range
	        }
	    }
	    return true; // all transactions are within range
	}

	// ✅ NEW: Perform dummy transfer (simulate successful transaction)
	public void transferFunds(String amount) {
		driver.findElement(By.linkText("Transfer Funds")).click();
		WebElement amountField = driver.findElement(By.id("amount"));
		WebElement fromAccount = driver.findElement(By.id("fromAccountId"));
		WebElement toAccount = driver.findElement(By.id("toAccountId"));
		WebElement transferBtn = driver.findElement(By.cssSelector("input[type='submit']"));

		amountField.clear();
		amountField.sendKeys(amount);
		new Select(fromAccount).selectByIndex(0);
		new Select(toAccount).selectByIndex(1);
		transferBtn.click();
	}
	
	public void navigateToTransactionHistory() {
	    // Assuming the account details page has a link or button to transaction history
	    // Otherwise, this might just ensure you're on the right tab/page
	    // Example: If the transaction history is already visible after selecting the account:
	    // Do nothing — or just verify presence

	    // If you need to click a tab or link to get to transaction history:
	    try {
	        WebElement transactionTab = driver.findElement(By.linkText("Transaction History")); // adjust as needed
	        if (transactionTab.isDisplayed()) {
	            transactionTab.click();
	        }
	    } catch (NoSuchElementException e) {
	        System.out.println("Transaction History link not found, assuming already on correct page.");
	    }
	}

	// ✅ NEW: Validate each transaction row contains Date, Amount, Type
	public boolean areAllTransactionsFormattedCorrectly() {
		List<WebElement> rows = transactionTable.findElements(By.cssSelector("tbody tr"));
		for (WebElement row : rows) {
			List<WebElement> cols = row.findElements(By.tagName("td"));
			if (cols.size() < 3)
				return false;
			String date = cols.get(0).getText().trim();
			String amount = cols.get(1).getText().trim();
			String type = cols.get(2).getText().trim();
			if (date.isEmpty() || amount.isEmpty() || type.isEmpty())
				return false;
		}
		return true;
	}
	
	public boolean areTransactionsInDateRange() {
	    String fromDate = "01-01-2024";
	    String toDate = "01-31-2024";
	    return areTransactionsInDateRange(fromDate, toDate);
	}
	
	public void filterByDateRange(String fromDate, String toDate) {
	    fromDateInput.clear();
	    fromDateInput.sendKeys(fromDate);
	    toDateInput.clear();
	    toDateInput.sendKeys(toDate);
	    searchButton.click();
	}

	// ✅ NEW: Sort transactions by date or amount in ascending/descending order
	public void sortTransactions(String field, String order) {
		String columnSelector = "";
		switch (field.toLowerCase()) {
		case "date":
			columnSelector = "th:nth-child(1)";
			break;
		case "amount":
			columnSelector = "th:nth-child(2)";
			break;
		default:
			return;
		}

		WebElement columnHeader = transactionTable.findElement(By.cssSelector(columnSelector));
		columnHeader.click(); // first click = ascending
		if (order.equalsIgnoreCase("descending")) {
			columnHeader.click(); // second click = descending
		}
	}

	// ✅ NEW: Check if sorted
	public boolean areTransactionsSorted(String field, String order) {
		List<WebElement> rows = transactionTable.findElements(By.cssSelector("tbody tr"));
		List<String> values = new ArrayList<>();

		int colIndex = field.equalsIgnoreCase("date") ? 0 : 1;
		for (WebElement row : rows) {
			values.add(row.findElements(By.tagName("td")).get(colIndex).getText().trim());
		}

		
		if (field.equalsIgnoreCase("amount")) {
			List<Double> doubleVals = new ArrayList<>();
			for (String val : values) {
				doubleVals.add(Double.parseDouble(val.replace("$", "").replace(",", "")));
			}
			List<Double> sortedDoubles = new ArrayList<>(doubleVals);
			if (order.equalsIgnoreCase("ascending")) {
				Collections.sort(sortedDoubles);
			} else {
				Collections.sort(sortedDoubles, Collections.reverseOrder());
			}
			return doubleVals.equals(sortedDoubles);
		} else {
			// date
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			List<Date> dates = new ArrayList<>();
			for (String dateStr : values) {
				try {
					dates.add(sdf.parse(dateStr));
				} catch (ParseException e) {
					return false;
				}
			}
			List<Date> sortedDates = new ArrayList<>(dates);
			if (order.equalsIgnoreCase("ascending")) {
				Collections.sort(sortedDates);
			} else {
				Collections.sort(sortedDates, Collections.reverseOrder());
			}
			return dates.equals(sortedDates);
		}
	}
}
