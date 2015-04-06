package com.booj.driver;

import java.util.HashMap;
import java.util.Map;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.impl.DefaultSessionFactory;
import org.finra.jtaf.ewd.session.SessionFactory;
import org.finra.jtaf.ewd.session.SessionManager;
import org.testng.annotations.AfterMethod;

public class Driver3 {
	
public static ExtWebDriver createInstance() throws Exception{
	ExtWebDriver driver = null;
	Map<String, String> properties = new HashMap<String,String>();
	properties.put("client", "client.properties");
	
		//SessionManager.getInstance().setSessionFactory(new DefaultSessionFactory());
		driver = SessionManager.getInstance().getNewSession(properties, true);
		return driver;
	
	
	//return driver;
}

public static void beforeMethod() throws Exception{
	ExtWebDriver driver = Driver3.createInstance();
	DriverLocal.setWebDriver(driver);
	System.out.println(driver.getSessionId());
	System.out.println("Thread id = " + Thread.currentThread().getId());
}

public static void removeAllSessions() {
	boolean sessionsClear = false;
	while (!sessionsClear) {
		Map<String, ExtWebDriver> sessions = SessionManager.getInstance().getSessions();
		if (SessionManager.getInstance().getSessions().size() == 0) {
			sessionsClear = true;
		} else {
			ExtWebDriver driver = sessions.values().iterator().next();
			SessionManager.getInstance().removeSession(driver);
		}
	}
	SessionManager.getInstance().setSessionFactory(new DefaultSessionFactory());
}

public static void afterMethod(){
	ExtWebDriver driver = DriverLocal.getWebDriver();
	driver.quit();
	SessionManager.getInstance().removeSession(driver);
}
	

	/*@AfterMethod
	public static void tearDown() throws Exception {
		System.out.println("\nBrowser close");
		 Driver3.getObj().quit();
        //SessionManager.getInstance().removeSession(Instance);
*/	}
	

