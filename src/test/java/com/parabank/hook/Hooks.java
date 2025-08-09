package com.parabank.hook;

import com.parabank.driver.DriverFactory;

import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	
	 @Before
	    public void setUp() {
	        DriverFactory.initDriver();
	    }

	    @After
	    public void tearDown() {
	        DriverFactory.quitDriver();
	    }

}
