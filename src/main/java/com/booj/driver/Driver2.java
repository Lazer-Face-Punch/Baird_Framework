package com.booj.driver;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.session.SessionManager;


public class Driver2 extends SessionManager {
	public Driver4 createMyClass(){
		return new Driver4(new ExtWebDriver());
	}
}
