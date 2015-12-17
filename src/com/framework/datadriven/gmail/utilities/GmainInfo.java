package com.framework.datadriven.gmail.utilities;

public class GmainInfo {
	public static String browser = "firefox";
	public static String gmailUrl ="www.gmail.com";
	
	// After landing on Home page
	public static String[] verifyOnLandingPage = {"xpath","//div[@id='gb']/div/div/div/div/span"};
	public static String[] composeButton = {"xpath","//*[contains(text(),'COMPOSE')]"};
	public static String[] toAddress = {"name","to","prabha25k@gmail.com"};
	public static String[] mailSubject = {"name","subjectbox","Test mail to prabha25k"};
	public static String[] mailTextBox = {"xpath","//td[2]/div[2]/div","Hi Krishna, \n\n It is working now.. Hurrey. :) \n\n Regards, \n Prabhakar K"};
	public static String[] sendButton = {"xpath","//td/div/div/div[4]/table/tbody/tr/td/div/div[2]"};
	
	public static String[] signOutIcon = {"xpath","//div/a/span"};
	public static String[] signOutButton = {"link","Sign out"};
	
	public static String[] getMail = {"xpath","(//span[@name='k prabha'])[2]"};
	
	public static class Step1{
		public static String[] userid ={"id","Email","prabha.kpr25@gmail.com"}; 
		public static String[] userIdNext = {"id","next"}; 
	}
	
	public static class Step2{
		public static String[] password = {"id","Passwd","Pra4@sho"};
		public static String[] loginButton = {"id","signIn"};
	}
}
