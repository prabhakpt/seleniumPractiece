package com.automation.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class A {
 public static WebDriver driver;

 public static void main(String a[]) throws InterruptedException
 {

	 driver=new FirefoxDriver();
 

  driver.get("https://paytm.com/");
  driver.manage().window().maximize();
  driver.findElement(By.linkText("Log In / Sign Up")).click();
  Thread.sleep(5000);
  driver.switchTo().frame(2);
  driver.findElement(By.id("input_0")).sendKeys("Ramesh");
 }


}