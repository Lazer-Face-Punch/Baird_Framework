package com.booj.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/*import pageObject.BaseTestSub;*/
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

 public class AccountLogin {
	
	WebDriver driver;
	By loginPopup = By.linkText("Log In");
	/*By popupBox = By.id("account_login_form");*/
	By email = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-email");
	By password = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > div.form-group > #wua-pass");
	By login = By.cssSelector("div.fancybox-inner > #account_login_form > #account_login_ajax > fieldset.clearfix > input[name=\"Submit\"]");
	@FindBy(className="account-user-name")
	WebElement welcomeText;
	
	
	

 

	
public AccountLogin(WebDriver driver){ 
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

//Open Login page popup
public void clickLoginPopup(){
	driver.findElement(loginPopup).click();
}


//Set user name into Text Box
public void setUserName(String strUserName){
	driver.findElement(email).sendKeys(strUserName);
}

//Set Password
public void setPassword(String strPassword){
	driver.findElement(password).sendKeys(strPassword);
}
//Click Login Button
public void clickLogin(){
	driver.findElement(login).click();
}

//Verify logged in Welcome/Name
public String getLoginWelcome(){
	new WebDriverWait(driver, 15)
	.until(ExpectedConditions.visibilityOf(welcomeText));
	return welcomeText.getText();
}


/**

 * This POM method will be exposed in test case to login in the application

 * @param strUserName

 * @param strPasword

 * @return

 */


public void loginToBairdAccount(String strUserName, String strPassword){
	
	/*this.setUpBeforeTestClass();*/
	/*this.setUp();*/
	//Click account menu item
	this.clickLoginPopup();
	
	//Target Popup
	/*this.targetPopup();*/
	
	//Fill user name
	this.setUserName(strUserName);
	
	//Fill password
	this.setPassword(strPassword);
	//Click Login Button
	this.clickLogin();
	
	//Verify Welcome Text
	this.getLoginWelcome();
	
}
}