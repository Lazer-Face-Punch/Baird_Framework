package com.booj.base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.booj.PageObject.AccountLoginTest;

public abstract class BaseTestSuper {
	protected WebDriver driver;
	protected AccountLoginTest objLogin;

	/* protected String baseUrl = "http://www.bairdwarner.com"; */

	
	@Parameters({"browser"})
	@BeforeMethod
	public void setUpBeforeTestClass(@Optional String browser, Method method) {
		System.out.println("Running Test: " + method.getName());
	if (browser.equalsIgnoreCase("Firefox")){
		driver = new FirefoxDriver();
	}
	else if (browser.equalsIgnoreCase("HtmlUnit")){
		driver = new HtmlUnitDriver();
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");

        java.util.logging.Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF); 
        java.util.logging.Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
	
	}
	System.out.println("Thread id = " + Thread.currentThread().getId());
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.bairdwarner.com");
	}

	/*
	 * @BeforeTest public void setUp() throws Exception{ driver = new
	 * FirefoxDriver(); driver.manage().timeouts().implicitlyWait(10,
	 * TimeUnit.SECONDS); driver.get(baseUrl);
	 * 
	 * }
	 * 
	 * @AfterTest public void closebrowser() throws Exception {
	 * System.out.println("\nBrower close"); driver.quit(); }
	 */

	/*
	 * void setUpBeforeTestMethod(){ driver.get(baseUrl); }
	 */

	/* abstract void tearDownAfterTestMethod(); */

	/* abstract void tearDownAfterTestClass(); */

	/*
	 * @BeforeTest public void baseSetup() throws Exception {
	 * 
	 * DesiredCapabilities caps = DesiredCapabilities.firefox();
	 * caps.setCapability("version", "33"); caps.setCapability("name", "baird");
	 * driver = new RemoteWebDriver(new
	 * URL("http://localhost:4444/wd/hub"),caps); }
	 */

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		System.out.println("method name:" + result.getMethod().getMethodName());
		System.out.println("\nBrower close");
		driver.quit();
	}
}
