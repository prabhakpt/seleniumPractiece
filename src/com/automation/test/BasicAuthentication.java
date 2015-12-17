package com.automation.test;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BasicAuthentication {
	WebDriver driver;
	
	@Before
	public void getBroser() throws InterruptedException{
		driver = new FirefoxDriver();
		Thread.sleep(2000);
		driver.manage().window().maximize();
		
		
	}
	
	@Test
	public void authentication() throws InterruptedException{
		Thread.sleep(5000);
		driver.get("http://prabha25k:Prabha143kpt@www.gmail.com");
		Thread.sleep(10000);
		ChromeOptions options = new ChromeOptions();
		DesiredCapabilities caps = new DesiredCapabilities();
		driver.close();
	}
}
