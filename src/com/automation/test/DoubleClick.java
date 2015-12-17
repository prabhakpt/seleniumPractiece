package com.automation.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.automation.utilities.BrowserEvents;

public class DoubleClick {

	WebDriver driver;
	
	@Before
	public void loadDriver(){
		driver = BrowserEvents.createDriver("firefox");
	}
	
	@Test
	public void doubleclick() throws InterruptedException{
		driver.get("http://api.jquery.com/dblclick/");
		driver.manage().window().maximize();
		Thread.sleep(4000);
		JavascriptExecutor scroll =  (JavascriptExecutor)driver;
		//scroll.executeScript("window.scrollBy(50, 2300)","");
		scroll.executeScript("scroll(0,2600)", "");
		Thread.sleep(5000);
		
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		
		System.out.println("created aciton for driver");
		actions.doubleClick(driver.findElement(By.xpath("//body/div"))).perform();
		System.out.println("double click on frame");
		Thread.sleep(3000);
		System.out.println("Text in body frame 0 : "+driver.findElement(By.tagName("body")).getText());
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("db1"));
		
		Thread.sleep(5000);
	}
	
	@After
	public void closeDriver(){
		BrowserEvents.closeDriver();
	}
}
