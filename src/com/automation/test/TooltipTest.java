package com.automation.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class TooltipTest {
	WebDriver driver;
	BrowserEvents events = new BrowserEvents();
	
	@Before
	public void createDriever(){
		driver = events.createDriver("firefox");
	}
	
	@Test
	public void testTooltip() throws InterruptedException{
		driver.get("http://webdesignledger.com/11-excellent-solutions-for-creating-tooltips");
		Thread.sleep(2000);
		
		System.out.println("Tooltip title :"+BrowserEvents.getTooltip("xpath", "//article[@id='post-542']/div/header/div/div/span/a"));
		
	}
	
	@After
	public void closeDriver(){
		events.closeDriver();
	}
}
