package com.automation.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
//import org.junit.Test;
import org.testng.annotations.Test;

public class MultipleTests {
	
	@BeforeClass
	public void beforeClassTest(){
		System.out.println("before Class Test");
	}
	
	@BeforeMethod
	public void beforeTest(){
		System.out.println("Running before test");
	}
	
	@Test(priority=3)
	public void firstTest(){
		System.out.println("this is first test");
	}
	
	@Test(priority=2)
	public void secondTest(){
		System.out.println("this is second test");
	}
	
	@Test(priority=1)
	public void priorityTest(){
		System.out.println("Priority test");
	}
	
	@AfterMethod
	public void afterTest(){
		System.out.println("this is after test");
	}
	
	@AfterClass
	public void afterClassTest(){
		System.out.println("after class test...");
		System.out.println("\n\n");
	}
}
