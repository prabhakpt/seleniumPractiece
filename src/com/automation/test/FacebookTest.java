package com.automation.test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FacebookTest {
	public WebDriver driver;
	
	@Before
	public void createDriver(){
		System.out.println("Creating the driver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void facebookLogin(){
		
		try {
			driver.get("https://www.facebook.com/");
			Thread.sleep(3000);
			
			/* verifying with wrong password
			 * 
			 * driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("pass")).clear();
			driver.findElement(By.id("email")).sendKeys("prabha25k@gmail.com");
			driver.findElement(By.id("pass")).sendKeys("test");
			driver.findElement(By.id("u_0_v")).click();
			Thread.sleep(3000);
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Please re-enter your password"));*/
			
			//driver.navigate().back(); loginbutton
			driver.findElement(By.id("email")).clear();
			driver.findElement(By.id("email")).sendKeys("prabha25k@gmail.com");
			driver.findElement(By.id("pass")).sendKeys("Prabha4");
			driver.findElement(By.id("u_0_v")).click();
			Thread.sleep(3000);
			Assert.assertFalse(driver.findElement(By.tagName("body")).getText().contains("The password that you've entered is incorrect."));
			
			//log out
			driver.findElement(By.id("pageLoginAnchor")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Log Out")).click();
			
			Thread.sleep(6000);	
			
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Sign Up"));
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	
	@After
	public void closeDriver(){
		System.out.println("Closing the driver");
		driver.quit();
	}
}
