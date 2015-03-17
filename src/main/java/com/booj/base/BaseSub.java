package com.booj.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseSub {
	protected WebDriver driver;

	/* public String baseUrl = "http://bairdwarner.com/"; */

	/*
	 * public BaseTestSub(WebDriver driver){ this.driver = driver; }
	 */

	@BeforeMethod
	public void setUpBeforeTestClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.bairdwarner.com/");

	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();
		System.out.println("Browser Close");
	}
	/*
	 * @BeforeSuite public void setUpBairdHomePage(){
	 * this.setUpBeforeTestClass(); }
	 */
}
