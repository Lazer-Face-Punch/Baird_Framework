package com.booj.base;



import org.finra.jtaf.ewd.ExtWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.booj.driver.Driver3;
import com.booj.driver.Driver4;
import com.booj.driver.DriverLocal;

public class Trythis {

	@BeforeMethod
	public void Before() throws Exception{
	//Driver3.removeAllSessions();
	Driver3.beforeMethod();
	}
	
	@AfterMethod
	public void After() throws Exception{
		Driver3.afterMethod();;
		
	}
	
	@Test
	public void Baird() throws Exception {
		
		String session = DriverLocal.getWebDriver().getSessionId();
		System.out.println(session);
		DriverLocal.getWebDriver().get("http://www.bairdwarner.com/");
		//ExtWebDriver ewd = SessionManager.getInstance().setSessionFactory(new Driver3()).getCurrentSession();
		//Driver3.getObj().getWebDriver().get("http://bairdwarner.com");
		System.out.println( DriverLocal.getWebDriver().getTitle());
		
		
	}
	@Test(enabled = true)
	public void Geek() throws Exception {
	
		String session = DriverLocal.getWebDriver().getSessionId();
		System.out.println(session);
		DriverLocal.getWebDriver().get("http://www.murney.com/");
		//ExtWebDriver ewd = SessionManager.getInstance().setSessionFactory(new Driver3()).getCurrentSession();
		//Driver3.getObj().getWebDriver().get("http://murney.com");
		System.out.println( DriverLocal.getWebDriver().getTitle());
		
		
	}
	
	/*@Test
	public void trying() throws Exception{
		String url = Driver3.newDriver().getCurrentUrl();
		Assert.assertEquals(url, "google.com");
	}*/
}
