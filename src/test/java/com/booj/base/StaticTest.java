package com.booj.base;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.booj.PageObject.AccountLoginStatic;



public class StaticTest extends BaseSuperStatic {

	
	@Test
	public void test_Account_Login_Page_Appear_Correct_Pass() throws Exception {
		// Create login page object
		AccountLoginStatic.loginInitialize();
		//Login to account
		AccountLoginStatic.loginToBairdAccount("Brenden@activewebsite.com", "active");
		//Returns welcome title and asserts true
		Assert.assertTrue(AccountLoginStatic.getLoginWelcome().equals("Welcome, brenden thornsberry"));
		
		
	}
}
