package com.booj.driver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {
	/* WebDriver driver; */
	public static WebDriver Instance;

	public static WebDriver getInstance() {
		return Instance;
	}

	public static void setInstance(WebDriver instance) {
		Instance = instance;
	}

	public static void Initialize() throws Exception {
		Instance = new FirefoxDriver();
		Instance.manage().window().setPosition(new Point(0, 0));
		Instance.manage().window().setSize(new Dimension(1024, 768));
		Instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	public static void tearDown() throws Exception {
		System.out.println("\nBrowser close");
		Driver.Instance.quit();
	}
}