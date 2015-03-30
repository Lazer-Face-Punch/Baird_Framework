package com.booj.driver;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.session.SessionManager;
import org.openqa.selenium.Dimension;

public class Driver2 {
	private static String instance = "";
	
	//private static ExtWebDriver Instance=null;
	

	

	public static void Initialize()  {
	
	
	String Instance = SessionManager.getInstance().getNewSession("client","client.properties");
	

		
			//SessionManager.getInstance().getNewSession("client", "client.properties");
		 //WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
		//Instance = (RemoteWebDriver) WebDriverFactory.getDriver(DesiredCapabilities.firefox());
		//Instance.manage().window().setPosition(new Point(0, 0));
	Instance.manage().window().setSize(new Dimension(1024, 768));
		//Instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	instance = Instance;
		
	}

	public static void tearDown() throws Exception {
		System.out.println("\nBrowser close");
		Instance.quit();
	}
}
