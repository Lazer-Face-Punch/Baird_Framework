package com.booj.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class V2 {
WebDriver driver = new FirefoxDriver();
	
@Test

public void scenario(){
	
	
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
}
}
