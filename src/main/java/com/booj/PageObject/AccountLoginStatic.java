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

import com.booj.driver.Driver;

public class AccountLoginStatic {
	/*static WebDriver driver;*/
	static By loginPopup = By.linkText("Log In");
	/*By popupBox = By.id("account_login_form");*/
	static By email = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-email");
	static By password = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-pass");
	static By login = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > input[name=\"Submit\"]");
	@FindBy(className="account-user-name")static WebElement welcomeText;

	
	private static Logger Log = Logger.getLogger(AccountLoginStatic.class.getName());
	
	

 

	
public AccountLoginStatic(WebDriver driver){ 
	/*this.driver = driver;*/
	PageFactory.initElements(driver, this);
	driver.get("http://www.bairdwarner.com");
}

/*public void setUpBeforeTestClass(){
	 
	  driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.bairdwarner.com/");
		
}*/
/*public static void setUp() {
	Driver.Instance.get("http://www.bairdwarner.com");
}*/	

public static AccountLoginStatic Login;
	


public static AccountLoginStatic getLogin() {
	return Login;
}


public static void setLogin(AccountLoginStatic login) {
	Login = login;
}

//Initializes Page
public static void loginInitialize(){
	Login = new AccountLoginStatic(Driver.Instance);
}

//Open Login page popup
public static void clickLoginPopup(){
	Driver.Instance.findElement(loginPopup).click();
}


//Set user name into Text Box
public static void setUserName(String strUserName){
	Driver.Instance.findElement(email).sendKeys(strUserName);
}

//Set Password
public static void setPassword(String strPassword){
	Driver.Instance.findElement(password).sendKeys(strPassword);
}
//Click Login Button
public static void clickLogin(){
	Driver.Instance.findElement(login).click();
}

//Verify logged in Welcome/Name
public static String getLoginWelcome(){
	new WebDriverWait(Driver.Instance, 15)
	.until(ExpectedConditions.visibilityOf(welcomeText));
	return welcomeText.getText();
}



/**
 *  This POM method will be exposed in test case to login in the application
 * @param strUserName
 * @param strPassword
 */
public static void loginToBairdAccount(String strUserName, String strPassword){
	DOMConfigurator.configure("log4j.xml");
	
	//Click account menu item
	AccountLoginStatic.clickLoginPopup();
	Log.info("Click account menu item");
	
	//Target Popup
	/*this.targetPopup();*/
	
	//Fill user name
	AccountLoginStatic.setUserName(strUserName);
	Log.info("Fill user name");
	//Fill password
	AccountLoginStatic.setPassword(strPassword);
	Log.info("Fill password");
	//Click Login Button
	AccountLoginStatic.clickLogin();
	Log.info("Click login button");
	
	//Verify Welcome Text
	AccountLoginStatic.getLoginWelcome();
	Log.info("Verify Welcome Text");
	
}
}
