package com.booj.driver;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.openqa.selenium.WebDriver;

public class TestSessionInformation {

    private ExtWebDriver driver;
    private String sessionId;
    //private TestProperties testProperties;

    public ExtWebDriver getDriver() {
        return driver;
    }

    public void setDriver(ExtWebDriver driver) {
        this.driver = driver;
    }

    public String getSessionId() {
        return driver.getSessionId();
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

  /*  public TestProperties getTestProperties() {
        return testProperties;
    }

    public void setTestProperties(TestProperties testProperties) {
        this.testProperties = testProperties;
    }*/
}
