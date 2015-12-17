package com.automation.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.automation.utilities.BrowserEvents;
import com.excel.testing.ReadAndWriteToExcelUsingPOI;

public class Snapdeal {
	WebDriver driver;
	BrowserEvents events = new BrowserEvents();
	
	String className = this.getClass().getSimpleName();
	
	@Before
	public void createDriever(){
		driver = BrowserEvents.createDriver("firefox");
	}
	
	@Test
	public void snapdealFlow(){
		boolean isTestSuccess = false;
		try{
			driver.get("http://www.snapdeal.com/");
			Thread.sleep(2000);
			driver.manage().window().maximize();
			
			WebElement element = driver.findElement(By.xpath("//div[@id='leftNavNemu']/div/div/ul/li[3]/a/span"));
			//WebElement element = driver.findElement(By.linkText("Computers, Office & Gaming"));
			Actions action = new Actions(driver);
			Actions hoverOnSnapdeal = action.moveToElement(element);
			hoverOnSnapdeal.perform();
			WebElement childElement = driver.findElement(By.xpath("//div[@id='leftNavNemu']/div/div/ul/li[3]/div/ul/li[3]/a/span"));
			//WebElement childElement = driver.findElement(By.linkText("Storage"));
			Actions hoverOnStorage = action.moveToElement(childElement);
			hoverOnStorage.perform();
			
			driver.findElement(By.linkText("2 TB")).click();
			Thread.sleep(5000);
			/*// check is element present
			events.waitForElementPresent(10000, "link", "Transcend H3P 2 TB External Hard Disk");*/
			//scroll to the element
			/*WebElement itemElement = driver.findElement(By.linkText("Transcend H3P 2 TB External Hard Disk"));
			Actions hoveOnItem = action.moveToElement(itemElement);
			hoveOnItem.perform();
			// click on the element
			driver.findElement(By.linkText("Transcend H3P 2 TB External Hard Disk")).click();*/
			
			driver.findElement(By.xpath("//div[@id='2025234329']/div/div[2]/div/div/a/img")).click();
			Thread.sleep(3000);
			
			//String productOverviewName = driver.findElement(By.xpath("//div[@id='productOverview']/div[2]/div/div/div/h1")).getText();
			
			// checking for delivary address
			driver.findElement(By.id("pincode-check")).sendKeys("560016");
			driver.findElement(By.id("pincode-check-bttn")).click();
			Thread.sleep(4000);
			// clicking add to cart
			driver.findElement(By.id("add-cart-button-id")).click();
			Thread.sleep(4000);
			
			// checking for text exists in tag
			//if(driver.findElement(By.tagName("body")).getText().contains("Item Available: Delivery"))
			// closing cart default value
			//driver.findElement(By.xpath("//div[@id='cartModal']/div[2]/div/div/div[2]/span/i")).click();
			driver.findElement(By.xpath("//div[2]/div/div/div[2]/span/i")).click();
			Thread.sleep(3000);
			// verifying cart data
			/*driver.findElement(By.id("cartHeader")).click();//
			//String cartValue = driver.findElement(By.linkText("WD 2TB My Book Desktop Storage External Hard Drive")).getText();
			// Assetting the values.
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains(productOverviewName));
			
			
			// again closing the cart model
			driver.findElement(By.cssSelector("span.icon-font-grey-size24.close-popup-icon > i.sd-icon.sd-icon-delete-sign")).click();*/
			
			
			driver.findElement(By.id("buy-button-id")).click();
			
			Thread.sleep(4000);
			
			// registering in snapdeal
			driver.findElement(By.id("username")).sendKeys(BrowserEvents.generateEmail());
			driver.findElement(By.id("login-continue")).click();
			
			Thread.sleep(2000);
			driver.findElement(By.id("w_password")).sendKeys("prabhaKpt@1");
			driver.findElement(By.id("w_password-repeat")).sendKeys("prabhaKpt@1");
			driver.findElement(By.id("register-done")).click();
			Thread.sleep(3000);
			
			// personal dtails
			driver.findElement(By.id("fullName")).sendKeys("prabhakar");
			driver.findElement(By.id("address")).sendKeys("No.37, bsv reddy layout, ramamurthinagar");
			driver.findElement(By.id("nearestLandmark")).sendKeys("Universal gim road");
			driver.findElement(By.id("mobile")).sendKeys("8722214333");
			driver.findElement(By.id("home-mobile")).click();
			driver.findElement(By.id("delivery-modes-continue")).click();
			Thread.sleep(2000);
			
			// review order
			Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Review Order"));
			
			Thread.sleep(3000);
			isTestSuccess = true;
			
			
		}catch(InterruptedException ex){
			
			ex.printStackTrace();
		}catch(NoSuchElementException elementEx ){
			// static method access directly using class name
			BrowserEvents.takeScreenShotOnfailure("element_not_found");
			String cause = elementEx.getCause().getMessage();
			System.out.println("cause of exception:"+cause);
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
		BrowserEvents.closeDriver();
	}
}
