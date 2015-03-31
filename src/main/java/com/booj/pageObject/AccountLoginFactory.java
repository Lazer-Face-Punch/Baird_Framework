package com.booj.pageObject;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.finra.jtaf.ewd.ExtWebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.booj.driver.Driver2;
//import com.booj.Driver2.getInstance().getDriver().Driver2.getInstance().getDriver();

public class AccountLoginFactory  {
	//private static ExtWebDriver Instance = null;
	
	 //static WebDriver2.getInstance().getDriver() Driver2.getInstance().getDriver();
	public static By loginButton = By.linkText("Log In");
	/* By popupBox = By.id("account_login_form"); */
	private static By email = By
			.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-email");
	private static By password = By
			.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-pass");
	private static By login = By
			.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > input[name=\"Submit\"]");
	@FindBy(className = "account-user-name")
	private static WebElement welcomeText;
	// @FindBy(linkText="Log Out") private static WebElement logout;
	private static By logout = By.linkText("Logout");

	private static Logger Log = Logger.getLogger(AccountLoginFactory.class
			.getName());
	

	public AccountLoginFactory() {
		 //BaseSuperFactory.Driver2.getInstance().getDriver() = Driver2.getInstance().getDriver(); 
		 PageFactory.initElements(Driver2.getInstance().getDriver(), this);
		 //System.out.println("Page Object Thread id = " + Thread.currentThread().getId());
		//Driver2.getInstance().getDriver().get("http://www.bairdwarner.com");
		
	}

	/*
	 * public void setUpBeforeTestClass(){
	 * 
	 * Driver2.getInstance().getDriver() = new FirefoxDriver2.getInstance().getDriver()();
	 * Driver2.getInstance().getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	 * Driver2.getInstance().getDriver().get("http://www.bairdwarner.com/");
	 * 
	 * }
	 */
	/*
	 * public static void setUp() {
	 * Driver2.getInstance().getDriver()..get("http://www.bairdwarner.com"); }
	 */

	protected static AccountLoginFactory Obj;

	protected static AccountLoginFactory getObj() {
		return Obj;
	}

	protected static void setLogin(AccountLoginFactory obj) {
		Obj = obj;
	}

	// Initializes Page
	public static void loginInitialize() {
		Obj = new AccountLoginFactory();
	}

	// Open Login page popup
	public static void clickLoginPopup() {
		Driver2.getInstance().getDriver().findElement(loginButton).click();
	}

	// Set user name into Text Box
	public static void setUserName(String strUserName) {
		Driver2.getInstance().getDriver().findElement(email).sendKeys(strUserName);
	}

	// Set Password
	public static void setPassword(String strPassword) {
		Driver2.getInstance().getDriver().findElement(password).sendKeys(strPassword);
	}

	// Click Login Button
	public static void clickLogin() {
		Driver2.getInstance().getDriver().findElement(login).click();
	}

	// Verify logged in Welcome/Name
	public static String getLoginWelcome() {
		new WebDriverWait(Driver2.getInstance().getDriver(), 15).until(ExpectedConditions
				.visibilityOf(welcomeText));
		return welcomeText.getText();
	}

	// Logout
	public static void clickLogout() {
		Driver2.getInstance().getDriver().findElement(logout).click();
	}

	// Verify logged out
	public static void verifyLoggedOut() {
		new WebDriverWait(Driver2.getInstance().getDriver(), 15).until(ExpectedConditions
				.visibilityOfElementLocated(loginButton));
	}
	
	public static boolean verifyLoginButton(){
		Driver2.getInstance().getDriver().findElement(loginButton).isDisplayed();
		return true;
	}

	/**
	 * This POM method will be exposed in test case to login in the application
	 * 
	 * @param strUserName
	 * @param strPassword
	 */
	public static void loginToBairdAccount(String strUserName,
			String strPassword) {
		DOMConfigurator.configure("log4j.xml");

		// Click account menu item
		AccountLoginFactory.clickLoginPopup();
		Log.info("Click account menu item");

		// Target Popup
		/* this.targetPopup(); */

		// Fill user name
		AccountLoginFactory.setUserName(strUserName);
		Log.info("Fill user name");
		// Fill password
		AccountLoginFactory.setPassword(strPassword);
		Log.info("Fill password");
		// Click Login Button
		AccountLoginFactory.clickLogin();
		Log.info("Click login button");

		// Verify Welcome Text
		AccountLoginFactory.getLoginWelcome();
		Log.info("Verify Welcome Text");

	}

	public static void logoutOfBairdAccount() {

		// Logout
		AccountLoginFactory.clickLogout();
		Log.info("Click Logout");

		// Verify Logout
		AccountLoginFactory.verifyLoggedOut();
		Log.info("Verified Log Out");
	}
}
