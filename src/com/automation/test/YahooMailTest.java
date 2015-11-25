package com.automation.test;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class YahooMailTest {
	WebDriver driver;
	
	@Before
	public void loadDriver(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void yahooLogin() throws InterruptedException{
		driver.get("http://yahoomail.com/");
		Thread.sleep(11000);
		
		
		driver.findElement(By.xpath("//*[contains(text(),'Compose')]")).click();
		Thread.sleep(3000);
		//driver.findElement("")
	}
	
	@Before
	public void closeDriver(){
		driver.quit();
	}
}
