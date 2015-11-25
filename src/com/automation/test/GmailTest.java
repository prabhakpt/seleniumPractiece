package com.automation.test;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GmailTest {
	WebDriver driver;
	
	@Before
	public void createDriver(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void loginTest(){
		
		try {
			driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1#identifier");
			Thread.sleep(5000);
			
			driver.findElement(By.id("Email")).sendKeys("prabha.kpr25@gmail.com");
			driver.findElement(By.id("next")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.id("Passwd")).sendKeys("Pra4@sho");
			driver.findElement(By.id("signIn")).click();
			Thread.sleep(11000);
			// asserting to confirm the value exist
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gb_Q gb_R gb_S gb_le']")).getText().contains("k"));
			
			// starting composing the mail
			driver.findElement(By.xpath("//*[contains(text(),'COMPOSE')]")).click();
			Thread.sleep(5000);
			driver.findElement(By.className("vO")).sendKeys("krishna@neevtech.com");
			//driver.findElement(By.className("aB gQ pE")).click();
			//driver.findElement(By.className(className))
			driver.findElement(By.className("aoT")).sendKeys("Test mail from prabha");
			Thread.sleep(2000);
			
			//System.out.println("Text is:"+driver.findElement(By.xpath("//iframe[@tabindex='1']")).getText());
			
			driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).click();
			driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys("Hi Krishna, \n\n It is working now.. Hurrey. :) \n\n Regards, \n Prabhakar K");
			
			//driver.switchTo().frame(2);
			//driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf']")).sendKeys("Hi Krishna");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//td[@class='gU Up']//div//div[@class='T-I J-J5-Ji aoO T-I-atl L3']")).click();
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@After
	public void closeDriver(){
		System.out.println("Closing Driver");
		driver.quit();
	}
}
