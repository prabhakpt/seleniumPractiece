package com.automation.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class SelectedLabel {
	WebDriver driver;
	BrowserEvents events = new BrowserEvents();
	
	@Before
	public void createDriever(){
		driver = events.createDriver("firefox");
	}
	
	@Test
	public void testSelectedLabel() throws InterruptedException{
		
		driver.get("file:///E:/automation/test_apps/dropdown_test.html");
		Thread.sleep(2000);
		
		System.out.println("Selected Lable :"+BrowserEvents.getselectedLabel("id", "SelectID_One"));
		
	}
	
	public void testSelectedLabelText(){
		driver.get("file:///E:/automation/test_apps/dropdown_test.html");
		try {
			Thread.sleep(2000);
			System.out.println("Selected Label Text:"+BrowserEvents.getselectedLabelText("id", "SelectID_One"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@After
	public void closeDriver(){
		events.closeDriver();
	}
}
