package com.booj.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.booj.driver.RemoteDriver;
import com.booj.utilities.Email;
import com.booj.utilities.ScreenShot;

public class BaseRemote extends ScreenShot {
	String nodeURL;
	private static Logger Log = Logger.getLogger(BaseSuperStatic.class
			.getName());

	@BeforeMethod
	public void setupBeforeTestMethod() throws MalformedURLException {
		nodeURL = "http://localhost:4444/wd/hub";
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.ANY);
		RemoteDriver.Instance = new RemoteWebDriver(new URL(nodeURL),
				capability);
		System.out.println("Thread id = " + Thread.currentThread().getId());
	}

	@AfterMethod
	public void tearDownAfterTestMethod() throws Exception {
		DOMConfigurator.configure("log4j.xml");
		RemoteDriver.tearDown();
		Log.info("Browser Closed");
		Email.EmailPDFAfterSuite();
	}

	// After complete execution send pdf report by email

}