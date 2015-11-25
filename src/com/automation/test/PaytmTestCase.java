package com.automation.test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.TestNG.ExitCodeListener;

public class PaytmTestCase {
	public WebDriver driver;
	@Before
	public void getDriver(){
		driver = new FirefoxDriver(); // creating firefox driver
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	
	@Test
	public void testFirstwd()throws Exception{
		driver.get("https://paytm.com/");
		//Thread.sleep(230000);
		driver.manage().window().maximize();
		System.out.println("is text exist"+driver.findElement(By.linkText("Log In / Sign Up")).getText());
		driver.findElement(By.linkText("Log In / Sign Up")).click();
		Thread.sleep(5000);
		driver.switchTo().frame(2);
		driver.findElement(By.xpath("//input[@id='input_0']")).sendKeys("prabha25k@gmail.com");
		
		driver.findElement(By.xpath("//*[contains(text(),'T&C')]")).click();
		Thread.sleep(3000);
		// getting list of windows
		Set<String> listOfWindows= driver.getWindowHandles();
		System.out.println("windows size is:"+listOfWindows.size());
		
		// getting only second window
		String secondWindow = getSecondWindow(listOfWindows);
		System.out.println("second window is:"+secondWindow);
		driver.switchTo().window(secondWindow);
		Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Your Agreement to these Terms and Conditions for availing Service"));
		Thread.sleep(2000);
		driver.close();
		
		for(String window:listOfWindows){
			driver.switchTo().window(window);
			driver.switchTo().frame(2);
			driver.findElement(By.xpath("//*[@id='input_1']")).sendKeys("Prabha143");
			driver.findElement(By.xpath("//form[@id='loginForm']//div//md-content//button")).click();
			Thread.sleep(3000);
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Please enter valid Username and Password."));
			break;
			
		}
	}
	

	private String getSecondWindow(Set<String> listOfWindows) {
		String window = null;
		for(String windows:listOfWindows){
			window = windows;
		}
		return window;
	}


	@After
	public void closeDriver(){
		driver.quit();
	}
}
