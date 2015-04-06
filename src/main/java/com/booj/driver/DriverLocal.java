package com.booj.driver;

import org.finra.jtaf.ewd.ExtWebDriver;

public class DriverLocal {
 private static ExtWebDriver webDriver = null;

public static ExtWebDriver getWebDriver() {
	return webDriver;
}

public static void setWebDriver(ExtWebDriver driver) {
	DriverLocal.webDriver = driver;
}

	

}
