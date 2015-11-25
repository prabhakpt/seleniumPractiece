package com.ie.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;


public class IETest {
	WebDriver driver;
	BrowserEvents events = new BrowserEvents();
	
	@Before
	public void startDriver(){
		driver  = events.createDriver("ie");
	}
	
	@Test
	public void testIE(){
		
		try{
			driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1#identifier");
			//Thread.sleep(5000);
			
			driver.findElement(By.id("Email")).sendKeys("prabha.kpr25@gmail.com");
			driver.findElement(By.id("next")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.id("Passwd")).sendKeys("Pra4@sho");
			driver.findElement(By.id("signIn")).click();
			Thread.sleep(11000);
			
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gb_Q gb_R gb_S gb_le']")).getText().contains("k"));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	@After
	public void closeDriver(){
		events.closeDriver();
	}
	
}
