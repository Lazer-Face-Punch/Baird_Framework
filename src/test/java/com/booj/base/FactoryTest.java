package com.booj.base;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.booj.driver.Driver2;
import com.booj.pageObject.AccountLoginFactory;
import com.booj.utilities.PDFListener;

/*@Listeners(value=ReporterListener.class)*/
//@Listeners(PDFListener.class)
public class FactoryTest  extends BaseSuperFactory {

	/*@Test(enabled = false)
	public void test_Account_Login_Page_Appear_Correct_Fail() throws Exception {
		//System.out.println("Fail Thread id = " + Thread.currentThread().getId());
		// Create login page object
		AccountLoginFactory.loginInitialize();
		// Login to account
		AccountLoginFactory.loginToBairdAccount("Brenden@activewebsite.com",
				"active");
		// Returns welcome title and asserts true
		Assert.assertTrue(AccountLoginFactory.getLoginWelcome().equals(
				"Welcome, brenden thornsberry"));
		// Logout and Verify
		AccountLoginFactory.logoutOfBairdAccount();
		
	}

	@Test(enabled = false)
	public void test_Account_Login_Page_Appear_Correct_Pass() throws Exception {
		//System.out.println("Pass Thread id = " + Thread.currentThread().getId());
		// Create login page object
		AccountLoginFactory.loginInitialize();
		// Login to account
		AccountLoginFactory.loginToBairdAccount("Brenden@activewebsite.com",
				"active");
		// Returns welcome title and asserts true
		Assert.assertTrue(AccountLoginFactory.getLoginWelcome().equals(
				"Welcome, brenden thornsberry"));
		// Logout and Verify
		AccountLoginFactory.logoutOfBairdAccount();
		

	}*/
	
	@Test(enabled = true)
	public void test_Login_Button_Present_Pass() throws InterruptedException{
		
		AccountLoginFactory.loginInitialize();
		
		Assert.assertTrue(AccountLoginFactory.verifyLoginButton());

		Thread.sleep(5000);
		
	}
	
	@Test(enabled = true)
	public void test_Login_Button_Present_Fail() throws InterruptedException{
		
		AccountLoginFactory.loginInitialize();
		
		Assert.assertFalse(AccountLoginFactory.verifyLoginButton());
		Thread.sleep(5000);
	}
}
