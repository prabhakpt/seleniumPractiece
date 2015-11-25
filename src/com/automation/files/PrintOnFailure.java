package com.automation.files;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.automation.utilities.BrowserEvents;

public class PrintOnFailure {
	BrowserEvents utils = new BrowserEvents();
	
	WebDriver driver = new FirefoxDriver();
	
	@Before
	public void startDriver(){
		driver = utils.createDriver("firefox");
	}
	
	@Test
	public void printOnFailure() throws InterruptedException{
		try{
			driver.get("https://accounts.google.com/ServiceLogin?service=mail&passive=true&rm=false&continue=https://mail.google.com/mail/&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1#identifier");
			Thread.sleep(5000);
			
			driver.findElement(By.id("Email")).sendKeys("");
			driver.findElement(By.id("next")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.id("Passwd1")).sendKeys("Pra4@ho");
			driver.findElement(By.id("signIn")).click();
			Thread.sleep(11000);
			
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='gb_Q gb_R gb_S gb_le']")).getText().contains("k"));
		}catch(Exception ex){
			takeScreenShot("elementNotFount");
			System.out.println(ex.getMessage());
		}
	}
	
	private void takeScreenShot(String fileName) {
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"//onfailure//"+fileName+".jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@After
	public void closeDriver(){
		utils.closeDriver();
	}
}
