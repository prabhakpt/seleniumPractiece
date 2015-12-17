package com.automation.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class AlertTest {
	WebDriver driver;
	
	@Before
	public void initiateDriver(){
		driver = BrowserEvents.createDriver("firefox");
	}
	
	@Test
	public void contrlMouseClick() throws InterruptedException{
		driver.get("file:///E:/automation/test_apps/alerttest.html");
		
		driver.findElement(By.id("check")).click();
		Thread.sleep(2000);
		BrowserEvents.alertText();
		
		BrowserEvents.alertAccept();
		
		driver.findElement(By.id("check")).click();
		Thread.sleep(3000);
		BrowserEvents.alertReject();
		
	}
	
	@After
	public void closeDriver(){
		BrowserEvents.closeDriver();
	}
}
