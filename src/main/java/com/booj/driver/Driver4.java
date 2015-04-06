package com.booj.driver;

import java.util.HashMap;
import java.util.Map;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.impl.DefaultExtWebDriver;
import org.finra.jtaf.ewd.impl.DefaultSessionFactory;
import org.finra.jtaf.ewd.session.SessionFactory;
import org.finra.jtaf.ewd.session.SessionManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Driver4 {

    public static String url = "http://www.bairdwarner.com";
    
	//public ExtWebDriver ewd=null;

	
    @BeforeMethod
    public void setup() throws Exception {
      SessionManager.getInstance().setSessionFactory( new DefaultSessionFactory());
      
    ExtWebDriver  ewd =  SessionManager.getInstance().getNewSession(false);
        System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + ewd.hashCode());
    }

    @AfterMethod
    public void teardown() throws Exception {
        ExtWebDriver  ewd =  SessionManager.getInstance().getNewSession(false);

    	ewd.quit();
        SessionManager.getInstance().removeSession(ewd);
    }
   
    @Test
	public void Baird() throws Exception {
        ExtWebDriver  ewd =  SessionManager.getInstance().getNewSession(false);

		String session = ewd.getSessionId();
		System.out.println(session);
		ewd.get("http://www.bairdwarner.com/");
		//ExtWebDriver ewd = SessionManager.getInstance().setSessionFactory(new Driver3()).getCurrentSession();
		//Driver3.getObj().getWebDriver().get("http://bairdwarner.com");
		System.out.println(ewd.getTitle());
		
		
	}
	@Test(enabled = true)
	public void Geek() throws Exception {
	    ExtWebDriver  ewd =  SessionManager.getInstance().getNewSession(false);

		String session = ewd.getSessionId();
		System.out.println(session);
		ewd.get("http://www.murney.com/");
		//ExtWebDriver ewd = SessionManager.getInstance().setSessionFactory(new Driver3()).getCurrentSession();
		//Driver3.getObj().getWebDriver().get("http://murney.com");
		System.out.println(ewd.getTitle());
		
		
	}
	class TestFactory implements SessionFactory {

		public TestFactory() {

		}

		@Override
		public void cleanup(Map<String, String> options) throws Exception {
			// TODO Auto-generated method stub

		}

		@Override
		public Map<String, String> createDefaultOptions() {
			Map<String, String> m = new HashMap<String, String>();
			m.put("javascriptClickOn", "true");
			return m;
		}

		@Override
		public DesiredCapabilities createCapabilities(Map<String, String> options) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public WebDriver createInnerDriver(Map<String, String> options, DesiredCapabilities capabilities) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ExtWebDriver createNewSession(Map<String, String> options, WebDriver wd) {
			ExtWebDriver ewd = new DefaultExtWebDriver();
			if (options.get("javascriptClickOn") != null) {
				if (options.get("javascriptClickOn").equals("true")) {
					ewd.setClickMode(true);
				}
			}
			return ewd;
		}

	}
}