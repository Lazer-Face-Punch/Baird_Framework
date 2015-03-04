package com.booj.base;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.jfree.util.Log;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.booj.PageObject.AccountLoginStatic;
import com.booj.driver.Driver;

public class BaseSuperStatic {

	private static Logger Log = Logger.getLogger(BaseSuperStatic.class.getName());
	
	@BeforeMethod
	
	public void setupBeforeTestMethod() throws Exception{
		DOMConfigurator.configure("log4j.xml");
		Driver.Initialize();
		Log.info("New Driver Instantiated");
	}
	
	
	@AfterMethod
	
	public void tearDownAfterTestMethod() throws Exception{
		DOMConfigurator.configure("log4j.xml");
		Driver.tearDown();
		Log.info("Driver Closed");
	}
}
