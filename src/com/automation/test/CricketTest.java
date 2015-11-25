package com.automation.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class CricketTest {
	WebDriver driver;
	
	BrowserEvents browserEventUtilities = new BrowserEvents();

	@Before
	public void initiateDriver(){
		driver = browserEventUtilities.createDriver("firefox");
	}
	
	@Test
	public void testCricket() throws InterruptedException{
		driver.get("file:///E:/automation/test_apps/cricket.html");
		Thread.sleep(3000);
		int trSize = driver.findElements(By.xpath("//table/tbody/tr")).size();
		
		for(int i=1;i<=trSize;i++){
			String name = driver.findElement(By.xpath("//html/body/table/tbody/tr["+i+"]/td[2]")).getText();
			if(name.equals("Dhoni")){
				System.out.println("Score of"+name+":"+driver.findElement(By.xpath("//html/body/table/tbody/tr["+i+"]/td[3]")).getText());
			}
		}
	}
	
	@After
	public void closeDriver(){
		browserEventUtilities.closeDriver();
	}
}
