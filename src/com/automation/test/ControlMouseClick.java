package com.automation.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class ControlMouseClick {
	WebDriver driver;
	
	BrowserEvents browserEventUtilities = new BrowserEvents();

	@Before
	public void initiateDriver(){
		driver = browserEventUtilities.createDriver("firefox");
	}
	
	@Test
	public void contrlMouseClick() throws InterruptedException{
		driver.get("http://www.google.com/");
		Thread.sleep(4000);
		System.out.println("ready to work on search box...");
		//driver.findElement(By.id("gs_htif0")).click();
		driver.findElement(By.xpath("//div[3]/div/input")).sendKeys("Prabhakar");
		
		BrowserEvents.pressEnter("name", "btnG");
		
		//link=Prabhakar - Wikipedia, the free encyclopedia
		Thread.sleep(3000);
		BrowserEvents.controlMouseClick("link","Prabhakar - Wikipedia, the free encyclopedia");
		
		Thread.sleep(4000);
	}
	
	@After
	public void closeDriver(){
		browserEventUtilities.closeDriver();
	}
}
