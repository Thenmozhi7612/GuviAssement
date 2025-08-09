package com.parabank.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.parabank.utils.ConfigReader;

public class DriverFactory {
	
	 private static WebDriver driver;

	    public static WebDriver initDriver() {
	        String browser = System.getProperty("browser");
	        if (browser == null) {
	            browser = ConfigReader.get("browser"); // Fallback to config.properties
	        }

	        switch (browser.toLowerCase()) {
	            case "firefox":
	                driver = new FirefoxDriver();
	                break;
	            case "edge":
	                driver = new EdgeDriver();
	                break;
	            case "chrome":
	            default:
	                driver = new ChromeDriver();
	                break;
	        }

	        driver.manage().window().maximize();
	        return driver;
	    }

	    public static WebDriver getDriver() {
	        return driver;
	    }

	    public static void quitDriver() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}
