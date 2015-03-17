package com.booj.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteDriver {

	public static RemoteWebDriver Instance;
	
	
	public static RemoteWebDriver getInstance() {
		return Instance;
	}

	public static void setInstance(RemoteWebDriver instance) {
		Instance = instance;
	}
	
	public static void tearDown() throws Exception{
		System.out.println("\nBrowser close");
		RemoteDriver.Instance.quit();
	}
}
