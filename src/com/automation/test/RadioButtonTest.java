package com.automation.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class RadioButtonTest {
WebDriver driver;
	
	BrowserEvents browserEventUtilities = new BrowserEvents();

	@Before
	public void initiateDriver(){
		driver = browserEventUtilities.createDriver("firefox");
	}
	
	@Test
	public void contrlMouseClick() throws InterruptedException{
		driver.get("file:///E:/automation/test_apps/radioButtontest.html");
		Thread.sleep(3000);
		BrowserEvents.isRadioButtonSelected("xpath", "//input[@value='male']");
		
		Thread.sleep(3000);
		
		BrowserEvents.isRadioButtonSelected("xpath", "//input[@value='female']");
		Thread.sleep(3000);
		
		BrowserEvents.selectRadioButton("xpath", "//input[@value='female']");
		
	}
	
	@After
	public void closeDriver(){
		browserEventUtilities.closeDriver();
	}
}
