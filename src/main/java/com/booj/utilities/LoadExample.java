package com.booj.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoadExample {
	public static void main(String[] args) {
        FirefoxProfile profile = new FirefoxProfile();

        File firebug = new File (System.getProperty("user.dir") + ("\\src\\main\\resources\\firebug-2.0.8-fx.xpi"));
        File netExport = new File (System.getProperty("user.dir") + ("\\src\\main\\resources\\netExport-0.9b7.xpi"));

        try
        {
            profile.addExtension(firebug);
            profile.addExtension(netExport);
        }
        catch (IOException err)
        {
            System.out.println(err);
        }

        // Set default Firefox preferences
        profile.setPreference("app.update.enabled", false);

        String domain = "extensions.firebug.";

        // Set default Firebug preferences
        profile.setPreference(domain + "currentVersion", "2.0.8");
        profile.setPreference(domain + "allPagesActivation", "on");
        profile.setPreference(domain + "defaultPanelName", "net");
        profile.setPreference(domain + "net.enableSites", true);

        // Set default NetExport preferences
        profile.setPreference(domain + "netexport.alwaysEnableAutoExport", true);
        profile.setPreference(domain + "netexport.showPreview", false);
        profile.setPreference(domain + "netexport.defaultLogDir", "C:\\Downloads\\_har\\");

        WebDriver driver = new FirefoxDriver(profile);

        try
        {
            // Wait till Firebug is loaded
            Thread.sleep(5000);

            // Load test page
            driver.get("http://five.james.ent.lan/admin/login");
            driver.findElement(By.id("email")).clear();
            driver.findElement(By.id("email")).sendKeys("admin@example.com");
            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys("password");
            driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
            driver.findElement(By.className("navbar-brand")).click();
            new WebDriverWait(driver, 15).until(ExpectedConditions
        			.visibilityOfElementLocated(By.xpath("//a[@href='/admin/contacts']"))).click();
            //driver.findElement(By.xpath("//a[@href='/admin/contacts']")).click();
            new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@aria-controls='collapse-managed_by']"))).click();
            //driver.findElement(By.xpath("//div[@id='heading-managed_by']/h4/a/i")).click();
            driver.findElement(By.cssSelector("i.fa.fa-square-o")).click();
            driver.findElement(By.linkText("End Current Session")).click();
            // Wait till HAR is exported
            Thread.sleep(10000);
        }
        catch (InterruptedException err)
        {
            System.out.println(err);
        }

        driver.quit();
    }
}
