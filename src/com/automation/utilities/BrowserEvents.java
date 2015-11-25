package com.automation.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserEvents {
	
	WebDriver driver;
	
	public WebDriver createDriver(String driverName){
		if(driverName.equals("firefox")){
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("FireFox Driver Started..");
			return driver;	
		}else if(driverName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\config\\chromedriver.exe");
			driver = new ChromeDriver();
			return driver;
		}else if(driverName.equals("ie")){
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\config\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			return driver;
		}
		return null;
		
	}
	
	public void closeDriver(){
		System.out.println("Closing Driver");
		driver.quit();
	}
}
