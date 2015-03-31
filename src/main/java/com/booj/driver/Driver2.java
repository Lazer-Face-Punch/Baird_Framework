package com.booj.driver;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.session.SessionManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Proxy;

public class Driver2 {
	//private static instance;
	//String Proxy1 = "localhost:8080";
	//proxy = new Proxy();
	
	private ExtWebDriver driver;
	
	private Driver2(){
		
	}
	private static Driver2 instance = new Driver2();
	
	public static Driver2 getInstance(){
	
		return instance;
	}

	

	protected void Driver3() throws Exception { 
	
	
	driver = SessionManager.getInstance().getNewSession("client","client.properties");
	

		
			//SessionManager.getInstance().getNewSession("client", "client.properties");
		 //WebDriverFactory.setMode(WebDriverFactoryMode.THREADLOCAL_SINGLETON);
		//Instance = (RemoteWebDriver) WebDriverFactory.getDriver(DesiredCapabilities.firefox());
		//Instance.manage().window().setPosition(new Point(0, 0));
	//Driver2.getInstance().getDriver().manage().window().setSize(new Dimension(1024, 768));
		//Instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//instance = Instance;
	//return driver;
		
	}
	
	public ExtWebDriver getDriver()
	{
		return driver;
		
		
	}
	
	/*public static void driverTest(){
		Driver2 obj1 = new Driver2();
		Driver2 obj;
		obj = obj1.Initialize();
	}*/

	public void tearDown() throws Exception {
		System.out.println("\nBrowser close");
		Driver2.getInstance().getDriver().quit();
	}
}
