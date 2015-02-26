package com.booj.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

public class BaseTestSub extends BaseTestSuper {
	protected WebDriver driver;

	/* public String baseUrl = "http://bairdwarner.com/"; */

	/*
	 * public BaseTestSub(WebDriver driver){ this.driver = driver; }
	 */

	@BeforeTest
	public void setUpBeforeTestClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.bairdwarner.com/");

	}

	/*
	 * @BeforeSuite public void setUpBairdHomePage(){
	 * this.setUpBeforeTestClass(); }
	 */
}
