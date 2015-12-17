package com.automation.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class IsTextPresent {
	WebDriver driver;
	BrowserEvents events = new BrowserEvents();
	
	@Before
	public void createDriever(){
		driver = BrowserEvents.createDriver("firefox");
	}
	
	@Test
	public void istextPresent() throws InterruptedException{
		driver.get("http://www.flipkart.com/mens-footwear/sports-shoes/pr?sid=osp%2Ccil%2C1cu");
		//Thread.sleep(1000);
		
		BrowserEvents.waitForTextPresent(10000,"Puma Carlos Running Shoes");
		
		Thread.sleep(4000);
	}
	
	@After
	public void closeDriver(){
		BrowserEvents.closeDriver();
	}
}
