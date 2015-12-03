package com.automation.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.automation.utilities.BrowserEvents;
import com.excel.testing.ReadAndWriteToExcelUsingPOI;

public class FlipkartTest {
	
	String className = this.getClass().getSimpleName();
	
	WebDriver driver;
	
	BrowserEvents driverUtilities = new BrowserEvents();
	
	@Before
	public void initiateDriver(){
		driver = driverUtilities.createDriver("firefox");
	}
	
	@Test
	public void flipkartTest() throws InterruptedException{
		boolean isTestSuccess = false;
		try{
			driver.get("http://www.flipkart.com/");
			Thread.sleep(10000);
			driver.manage().window().maximize();
			Thread.sleep(5000);
			
			WebElement element = driver.findElement(By.xpath("//div[@id='fk-mainhead-id']/div[2]/div/div/ul/li/a/span"));
			
			Actions action = new Actions(driver);
			Actions hoverRegister = action.moveToElement(element);
			hoverRegister.perform();
			
			driver.findElement(By.linkText("Lenovo")).click();
			Thread.sleep(5000);
			
			driver.findElement(By.linkText("Lenovo P1m")).click();
			Thread.sleep(3000);
	
			driver.findElement(By.cssSelector("img.banner.fk-display-block")).click();
			Thread.sleep(5000);
			
			driver.findElement(By.xpath("//div[@id='fk-mainbody-id']/div/div[7]/div/div[3]/div/div/div[5]/div[2]/div[2]/a/div/div")).click();
			Thread.sleep(4000);
			
			driver.findElement(By.name("pincode")).sendKeys("560016");
			driver.findElement(By.xpath("//input[@value='Check']")).click();
			Thread.sleep(5000);
			
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Available with"));
			
			driver.findElement(By.xpath("//input[@value='Buy Now']")).click();
			Thread.sleep(10000);
			
			driver.findElement(By.id("email")).sendKeys(BrowserEvents.generateEmail());
			driver.findElement(By.xpath("//input[@value='Continue']")).click();
			Thread.sleep(3000);
			
			driver.findElement(By.name("mobile")).sendKeys("8722214333");
			driver.findElement(By.xpath("//input[@value='Continue']")).click();
			Thread.sleep(2000);
			isTestSuccess = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
		finally{
			if(isTestSuccess)
				ReadAndWriteToExcelUsingPOI.updateDataWithTestCaseResults(className, "success");
			else
				ReadAndWriteToExcelUsingPOI.updateDataWithTestCaseResults(className, "failed");
		}
		
	}
	
	@After
	public void closeDriver(){
		driverUtilities.closeDriver();
	}
}
