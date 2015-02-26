package com.booj.PageObject;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/*import pageObject.BaseTestSub;*/

public class AccountLoginTest {
	private static Logger Log = Logger.getLogger(AccountLoginTest.class.getName());
	
	WebDriver driver;
	By loginPopup = By.linkText("Log In");
	/* By popupBox = By.id("account_login_form"); */
	By email = By
			.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-email");
	By password = By
			.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-pass");
	By login = By
			.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > input[name=\"Submit\"]");
	
	@FindBy(className = "account-user-name")
	WebElement welcomeText;

	public AccountLoginTest(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	

	/*
	 * public void setup(){ BaseTestSub Test = new BaseTestSub();
	 * Test.setUpBeforeTestClass(); }
	 */

	/*
	 * public void setUpBeforeTestClass(){
	 * 
	 * driver = new FirefoxDriver();
	 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * driver.get("http://www.bairdwarner.com/");
	 * 
	 * }
	 */

	// Open Login page popup
	public void clickLoginPopup() {
		driver.findElement(loginPopup).click();
	}

	// Set user name into Text Box
	public void setUserName(String strUserName) {
		driver.findElement(email).sendKeys(strUserName);
	}

	// Set Password
	public void setPassword(String strPassword) {
		driver.findElement(password).sendKeys(strPassword);
	}

	// Click Login Button
	public void clickLogin() {
		driver.findElement(login).click();
	}
	
	// Verify Welcome Text
	public String getLoginWelcome()  {

		new WebDriverWait(driver, 10)
		.until(ExpectedConditions.visibilityOf(welcomeText));
		return welcomeText.getText();
		
		
	}

	/**
	 * 
	 * This POM method will be exposed in test case to login in the application
	 * 
	 * @param strUserName
	 * 
	 * @param strPasword
	 * 
	 * @return
	 * @throws Exception 
	 */

	public void loginToBairdAccount(String strUserName, String strPassword){
		DOMConfigurator.configure("log4j.xml");

		/* this.setUpBeforeTestClass(); */

		// Click account menu item
		this.clickLoginPopup();
		Log.info("Click account menu item");
		
		// Target Popup
		/* this.targetPopup(); */

		// Fill user name
		this.setUserName(strUserName);
		Log.info("Fill user name");
		
		// Fill password
		this.setPassword(strPassword);
		Log.info("Fill password");
		
		// Click Login Button
		this.clickLogin();
		Log.info("Click login button");
		
		//Verify Welcome Text
		this.getLoginWelcome();
		Log.info("Verify welcome text");
	}
}