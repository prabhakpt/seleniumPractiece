package com.automation.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BrowserEvents {

	private static WebDriver driver;

	public WebDriver createDriver(String driverName) {
		if (driverName.equals("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			System.out.println("FireFox Driver Started..");
			return driver;
		} else if (driverName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir")
							+ "\\src\\config\\chromedriver.exe");
			driver = new ChromeDriver();
			return driver;
		} else if (driverName.equals("ie")) {
			System.setProperty("webdriver.ie.driver",
					System.getProperty("user.dir")
							+ "\\src\\config\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			return driver;
		}
		return null;

	}

	public void closeDriver() {
		System.out.println("Closing Driver");
		driver.quit();
	}

	public static void takeScreenShotOnfailure(String filename) {
		File image = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(image, new File(System.getProperty("user.dir")
					+ "//screenshots_on_failure//" + filename + "_"
					+ dataTime("MMddhhmmss") + ".jpg"));
		} catch (Throwable e) {
			e.printStackTrace();

		}
	}

	public static String dataTime(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat date = new SimpleDateFormat(dateFormat);

		return date.format(cal.getTime());
	}

	public static String generateEmail() {
		String userId = "prabha";
		String date = new SimpleDateFormat("yyyyMMddHHmmssSSS")
				.format(new Date());
		System.out.println("user name is:" + (userId + date));
		return (userId + date) + "@gmail.com";
	}

	public static void waitForElementPresent(int maxWaitTime,
			String identifyBy, String locator) throws InterruptedException {

		long totalWaitTime = 0;
		// boolean isPresent = false;
		
		while (!isElementPresent(identifyBy, locator)) {
			long time = 1000;
			totalWaitTime = totalWaitTime + time;
			Thread.sleep(1000);
			if (totalWaitTime >= maxWaitTime) {
				break;
			}
		}
	}
	
	public static boolean isElementPresentStatus(int maxWaitTime, String identifyBy, String locator) throws InterruptedException {

			long totalWaitTime = 0;
			boolean isPresent = isElementPresent(identifyBy, locator);
			while (!isPresent) {
				System.out.println("Element not present..");
				long time = 1000;
				totalWaitTime = totalWaitTime + time;
				Thread.sleep(1000);
				isPresent = isElementPresent(identifyBy, locator);
				if (totalWaitTime >= maxWaitTime) {
					break;
				}
			}
			return isPresent;
		}
	

	public static boolean isElementPresent(String identifyBy, String locator) {

		// Use findElements as it does not throw an exception if the element doesn't exist

		try {
			if (identifyBy.equalsIgnoreCase("xpath")) {
				if (driver.findElements(By.xpath(locator)).size() > 0) {
					return true;
				}
			}
			if (identifyBy.equalsIgnoreCase("link")) {
				if (driver.findElements(By.linkText(locator)).size() > 0) {
					return true;
				}
			}
			if (identifyBy.equalsIgnoreCase("id")) {
				if (driver.findElements(By.id(locator)).size() > 0) {
					return true;
				}
			}
			if (identifyBy.equalsIgnoreCase("name")) {
				if (driver.findElements(By.name(locator)).size() > 0) {
					return true;
				}
			}
			if (identifyBy.equalsIgnoreCase("css")) {
				if (driver.findElements(By.cssSelector(locator)).size() > 0) {
					return true;
				}
			}
		} catch (Exception ex) {
			System.out.println("Exception occured..");
			// ex.printStackTrace();

		}
		System.out.println("Returning false...");
		return false;

	}

	public static String getTooltip(String identityBy, String locator) {
		// //article[@id='post-542']/div/header/div/div/span/a
		String title = null;
		if (getWebElement(identityBy, locator) != null) {
			title = getWebElement(identityBy, locator).getAttribute("title");
		}

		return title;

	}

	public static WebElement getWebElement(String identifyBy, String locator) {
		WebElement webElement = null;
		if (identifyBy.equalsIgnoreCase("xpath")) {
			webElement = driver.findElement(By.xpath(locator));
		} else if (identifyBy.equalsIgnoreCase("id")) {
			webElement = driver.findElement(By.id(locator));
		} else if (identifyBy.equalsIgnoreCase("name")) {
			webElement = driver.findElement(By.name(locator));
		} else if (identifyBy.equalsIgnoreCase("css")) {
			webElement = driver.findElement(By.cssSelector(locator));
		} else if (identifyBy.equalsIgnoreCase("link")) {
			webElement = driver.findElement(By.linkText(locator));
		} else if (identifyBy.equalsIgnoreCase("partialLinkText")) {
			webElement = driver.findElement(By.partialLinkText(locator));
		}
		return webElement;
	}

	/*
	 * public static void waitForElementPresent(int maxWaitTime, String
	 * identifyBy, String string) throws InterruptedException {
	 * 
	 * long totalWaitTime = 0; while (!isElementPresent(identifyBy, string)) {
	 * long time = 1000; totalWaitTime = totalWaitTime + time;
	 * Thread.sleep(1000); if (totalWaitTime >= maxWaitTime) { break; }
	 * 
	 * } }
	 */

	public static String getselectedLabel(String identifyBy, String locator)
			throws InterruptedException {
		waitForElementPresent(10000, identifyBy, locator);

		if (identifyBy.equalsIgnoreCase("xpath")) {
			Select selectbox = new Select(driver.findElement(By.xpath(locator)));
			String option1 = selectbox.getFirstSelectedOption().getText();
			return option1;

		} else if (identifyBy.equalsIgnoreCase("id")) {

			Select selectbox = new Select(driver.findElement(By.id(locator)));
			String option1 = selectbox.getFirstSelectedOption().getText();
			return option1;

		} else if (identifyBy.equalsIgnoreCase("name")) {
			Select selectbox = new Select(driver.findElement(By.name(locator)));
			String option1 = selectbox.getFirstSelectedOption().getText();
			return option1;
		}

		else
			return null;
	}

	public static String getselectedLabelText(String identifyBy, String locator)
			throws InterruptedException {
		waitForElementPresent(10000, identifyBy, locator);
		if (identifyBy.equalsIgnoreCase("xpath")) {
			String val = getValue("xpath", locator);
			return getText("xpath", "//option[@value='" + val + "']");
			/*
			 * Select selectbox1 = new Select(driver.findElement(By
			 * .xpath("selectWithLabelsOnly"))); String option1 =
			 * selectbox1.getFirstSelectedOption().getText(); return option1;
			 */
		} else if (identifyBy.equalsIgnoreCase("id")) {

			Select selectbox1 = new Select(driver.findElement(By.id(locator)));
			String option1 = selectbox1.getFirstSelectedOption().getText();
			return option1;
		} else if (identifyBy.equalsIgnoreCase("name")) {
			Select selectbox1 = new Select(driver.findElement(By.name(locator)));
			String option1 = selectbox1.getFirstSelectedOption().getText();
			return option1;
		}

		else
			return null;
	}

	public static String getValue(String identifyBy, String locator)
			throws InterruptedException {
		waitForElementPresent(10000, identifyBy, locator);
		return getWebElement(identifyBy, locator).getAttribute("value");

	}

	public static String getText(String identifyBy, String locator)
			throws InterruptedException {
		waitForElementPresent(10000, identifyBy, locator);
		return getWebElement(identifyBy, locator).getText();

	}

	public static int getXpahtCount(String xPath) {

		return driver.findElements(By.xpath(xPath)).size();
	}

	public static void waitForTextPresent(int maxWaitTime, String string)
			throws InterruptedException {

		long totalWaitTime = 0;
		int i = 1;
		while (!isTextPresent(string)) {
			long time = 1000;
			System.out.println(i + "Second");
			totalWaitTime = totalWaitTime + time;
			Thread.sleep(1000);
			i++;
			if (totalWaitTime >= maxWaitTime) {
				break;
			}

		}
	}

	public static boolean isTextPresent(String text) {
		boolean isTextExist = driver.findElement(By.tagName("body")).getText()
				.contains(text);
		System.out.println("isText present:" + isTextExist);
		return isTextExist;
	}

	public static void mouseoverByxPath(String menu)
			throws InterruptedException {
		// waitForElementPresent(10000,menu);
		WebElement webElement = driver.findElement(By.xpath(menu));// Menu

		Actions builder = new Actions(driver);
		Actions hoverOverRegistrar = builder.moveToElement(webElement);
		hoverOverRegistrar.perform();

		Thread.sleep(2000);

	}

	public static void mouseoverByWebElement(WebElement webElement)
			throws InterruptedException {
		// waitForElementPresent(10000,menu);
		// WebElement webElement = driver.findElement(By.xpath(menu));// Menu

		Actions builder = new Actions(driver);
		Actions hoverOverRegistrar = builder.moveToElement(webElement);
		hoverOverRegistrar.perform();

		Thread.sleep(2000);

	}

	public static void mouseOverByIdentityTypeLocator(String identifyBy, String locator) throws InterruptedException {
		
		WebElement webElement = null;
		try{
			if (identifyBy.equalsIgnoreCase("xpath")) {
				webElement = driver.findElement(By.xpath(locator));
				System.out.println("Executed"+ identifyBy +"without exception");
			} else if (identifyBy.equalsIgnoreCase("id")) {
				webElement = driver.findElement(By.id(locator));
			} else if (identifyBy.equalsIgnoreCase("name")) {
				webElement = driver.findElement(By.name(locator));
			}else if(identifyBy.equalsIgnoreCase("link")){
				webElement = driver.findElement(By.linkText(locator));
			}
		}catch(Exception ex){
			System.out.println("Exception occured..");
		}
		if(webElement != null){
			Actions builder = new Actions(driver);
			Actions hoverOverRegistrar = builder.moveToElement(webElement);
			hoverOverRegistrar.perform();
		}
		Thread.sleep(2000);
	}

	public static void mouseoverandclick(String identifyBy,String locator)
			throws InterruptedException {
		WebElement mouseOverElement = findElement(identifyBy,locator);
		Actions builder = new Actions(driver);
		Actions hoverOverRegistrar = builder.moveToElement(mouseOverElement);
		hoverOverRegistrar.perform();
		mouseOverElement.click();
		//Thread.sleep(2000);

	}
	
	public static WebElement findElement(String identifyBy,String locator){
		try{
			if (identifyBy.equalsIgnoreCase("xpath")) {
				return driver.findElement(By.xpath(locator));
				
			} else if (identifyBy.equalsIgnoreCase("id")) {
				return driver.findElement(By.id(locator));
			} else if (identifyBy.equalsIgnoreCase("name")) {
				return driver.findElement(By.name(locator));
			}else if(identifyBy.equalsIgnoreCase("link")){
				return driver.findElement(By.linkText(locator));
			}
		}catch(Exception ex){
			System.out.println("Exception occured..");
		}
		
		return null;
	}
	
	public static void pressEnter(String identifyBy,String locator ){
		try{
			if (identifyBy.equalsIgnoreCase("xpath")) {
				driver.findElement(By.xpath(locator)).sendKeys(Keys.ENTER);
				
			} else if (identifyBy.equalsIgnoreCase("id")) {
				driver.findElement(By.id(locator)).sendKeys(Keys.ENTER);
			} else if (identifyBy.equalsIgnoreCase("name")) {
				driver.findElement(By.name(locator)).sendKeys(Keys.ENTER);
			}else if(identifyBy.equalsIgnoreCase("link")){
				driver.findElement(By.linkText(locator)).sendKeys(Keys.ENTER);
			}else{
				System.out.println("Nothing identfyBy is matched with existing conditions please add condition for:"+identifyBy);
			}
			
		}catch(Exception ex){
			System.out.println("Exception occured..");
		}
	}
	
	public static void enterText(String identifyBy, String locator,
			String text) {
		try {
			getWebElement(identifyBy, locator).sendKeys(text);
			System.out.println("Entered text..");
		} catch (Throwable e) {
			takeScreenShotOnfailure("Textbox not found");// "+datetime("MMddhhmmss")
			System.out.println(e.getMessage());
			Assert.assertTrue(e.getMessage(), false);
		}
	}
	public static void main(String[] args) {
		generateEmail();
	}

}
