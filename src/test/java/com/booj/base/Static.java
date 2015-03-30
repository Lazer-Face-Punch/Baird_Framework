package com.booj.base;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.booj.pageObject.AccountLoginStatic;
import com.booj.utilities.PDFListener;

/*@Listeners(value=ReporterListener.class)*/
@Listeners(PDFListener.class)
public class Static extends BaseSuperStatic {

	@Test(enabled = true)
	public void test_Account_Login_Page_Appear_Correct_Fail() throws Exception {

		// Create login page object
		AccountLoginStatic.loginInitialize();
		// Login to account
		AccountLoginStatic.loginToBairdAccount("Brenden@activewebsite.com",
				"active");
		// Returns welcome title and asserts true
		Assert.assertTrue(AccountLoginStatic.getLoginWelcome().equals(
				"Welcome, brenden thornsberr"));
		// Logout and Verify
		AccountLoginStatic.logoutOfBairdAccount();
	}

	@Test(enabled = true)
	public void test_Account_Login_Page_Appear_Correct_Pass() throws Exception {
		// Create login page object
		AccountLoginStatic.loginInitialize();
		// Login to account
		AccountLoginStatic.loginToBairdAccount("Brenden@activewebsite.com",
				"active");
		// Returns welcome title and asserts true
		Assert.assertTrue(AccountLoginStatic.getLoginWelcome().equals(
				"Welcome, brenden thornsberry"));
		// Logout and Verify
		AccountLoginStatic.logoutOfBairdAccount();

	}
}
