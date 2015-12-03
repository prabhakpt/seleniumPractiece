package com.automation.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class ControlKeys {
	WebDriver driver;
	BrowserEvents events = new BrowserEvents();
	
	@Before
	public void createDriever(){
		driver = events.createDriver("chrome");
	}
	
	@Test
	public void istextPresent() throws InterruptedException{
		driver.get("http://www.snapdeal.com/");
		Thread.sleep(4000);
		System.out.println("ready to work on search box...");
		driver.findElement(By.name("keyword")).click();
		driver.findElement(By.name("keyword")).sendKeys("Prabhakar k hello");
		
		System.out.println("started on control key");
		//BrowserEvents.waitForTextPresent(10000,"Puma Carlos Running Shoes");
		BrowserEvents.controlEvents("name","keyword", "a");
		System.out.println("strating control c");
		BrowserEvents.controlEvents("name","keyword", "c");
		
		Thread.sleep(4000);
	}
	
	@After
	public void closeDriver(){
		events.closeDriver();
	}
}
