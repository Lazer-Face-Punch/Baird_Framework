package com.booj.base;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.booj.pageObject.AccountLogin;

public class FailTest extends BaseSuper {
	/* WebDriver driver; */
	AccountLogin objLogin; 
	/* BaseTestSub objSetup; */
	/*test*/

	@Test
	public void test_Account_Login_Page_Appear_Correct_Fail() throws Exception {
		// Create login page object
		objLogin = new AccountLogin(driver);

		objLogin.loginToBairdAccount("brenden@activewebsite.com", "active");
		
		//Returns welcome title and asserts true
		String loginWelcomeTitle = objLogin.getLoginWelcome();
		System.out.println("Login Title:" + loginWelcomeTitle);
		
		Assert.assertTrue(loginWelcomeTitle.equals("Welcome, brenden thornsberr"));
		
	
	}

}
