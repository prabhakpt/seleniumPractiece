package com.automation.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utilities.BrowserEvents;

public class GoogleDropdowncount {
	WebDriver driver;
	BrowserEvents events = new BrowserEvents();
	
	@Before
	public void createDriver(){
		driver = events.createDriver("firefox");
	}
	
	@Test
	public void countDropDowns() throws InterruptedException, IOException{
		driver.get("https://www.google.co.in/?gfe_rd=cr&ei=EetWVuyTOcPU8AeQqYeACw&gws_rd=ssl");
		Thread.sleep(2000);
		// creating file
		File file = new File("googleText.txt");
		BufferedWriter write = new BufferedWriter(new FileWriter(file));
		
		driver.findElement(By.id("lst-ib")).sendKeys("apple");
		String xpath = "//div[@class='gstl_0 sbdd_a']/div[2]/div/div/ul/li";
		int xpahtSize = BrowserEvents.getXpahtCount(xpath);
		System.out.println("xpathsize"+xpahtSize);
		for(int i=1;i<=xpahtSize;i++){
			String value = driver.findElement(By.xpath(xpath+"["+i+"]")).getText();
			write.write(value);
			System.out.println("value"+i+":"+value);
			write.newLine();
		}
		
		write.close();
	}
	
	@After
	public void closeDriver(){
		events.closeDriver();
	}
}
