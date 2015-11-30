package com.automation.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class IsElementPresent {
	WebDriver driver;
	BrowserEvents events = new BrowserEvents();
	
	@Before
	public void createDriever(){
		driver = events.createDriver("chrome");
	}
	
	@Test
	public void isElementPresent() throws InterruptedException{
		driver.get("http://www.snapdeal.com/");
		
		BrowserEvents.mouseOverByIdentityTypeLocator("xpath","//div[@id='leftNavNemu']/div/div/ul/li[2]/a");
		
		BrowserEvents.mouseOverByIdentityTypeLocator("xpath","//div[@id='leftNavNemu']/div/div/ul/li[2]/div/ul/li[3]/a/span");
		
		BrowserEvents.mouseoverandclick("link","4G Mobiles");
		
		System.out.println("Is element present"+BrowserEvents.isElementPresentStatus(10000,"xpath","//div[@id='628016512272']/div/div[2]/div[3]/a/p"));
		
		BrowserEvents.getWebElement("xpath","//div[@id='628016512272']/div/div[2]/div[3]/a/p").click();
		
		Thread.sleep(3000);
		
		driver.navigate().back();
		
		Thread.sleep(4000);
		
		if(BrowserEvents.isElementPresentStatus(10000,"name","keyword")){
			// send keys
			BrowserEvents.enterText("name", "keyword", "iphone");
			Thread.sleep(2000);
			BrowserEvents.pressEnter("xpath", "//div[@id='sdHeader']/div[4]/div/div[2]/button");
		}
		
		Thread.sleep(4000);
		
		
	}
	
	@After
	public void closeDriver(){
		events.closeDriver();
	}
}
