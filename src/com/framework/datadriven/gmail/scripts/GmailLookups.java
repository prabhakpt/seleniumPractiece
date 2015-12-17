package com.framework.datadriven.gmail.scripts;

import static com.automation.utilities.BrowserEvents.closeDriver;
import static com.automation.utilities.BrowserEvents.createDriver;
import static com.automation.utilities.BrowserEvents.openUrl;
import static com.framework.datadriven.gmail.utilities.GmainInfo.browser;
import static com.framework.datadriven.gmail.utilities.GmainInfo.composeButton;
import static com.framework.datadriven.gmail.utilities.GmainInfo.gmailUrl;
import static com.framework.datadriven.gmail.utilities.GmainInfo.mailSubject;
import static com.framework.datadriven.gmail.utilities.GmainInfo.mailTextBox;
import static com.framework.datadriven.gmail.utilities.GmainInfo.sendButton;
import static com.framework.datadriven.gmail.utilities.GmainInfo.signOutButton;
import static com.framework.datadriven.gmail.utilities.GmainInfo.signOutIcon;
import static com.framework.datadriven.gmail.utilities.GmainInfo.toAddress;
import static com.framework.datadriven.gmail.utilities.GmainInfo.verifyOnLandingPage;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.automation.utilities.BrowserEvents;
import com.excel.testing.ReadAndWriteToExcelUsingPOI;
import com.framework.datadriven.gmail.utilities.GmainInfo.Step1;
import com.framework.datadriven.gmail.utilities.GmainInfo.Step2;

@RunWith(Parameterized.class)
public class GmailLookups {
	
	String id;
	String email;
	String password;
	String to_email;
	String to_password;
	
	public GmailLookups(String id,String email,String password,String to_email,String to_password){
		this.id =id;
		this.email = email;
		this.password = password;
		this.to_email = to_email;
		this.to_password = to_password;
	}
	
	@Before
	public void createGmailDriver(){
		createDriver(browser);
	}
	
	@Parameters
	public static List<Object[]> getUserInfo(){
		ReadAndWriteToExcelUsingPOI.readProjectInnerFile();
		System.out.println("created instance for exel");
		return Arrays.asList(ReadAndWriteToExcelUsingPOI.getEntireCellDataAsTwoDimentionalArrayObject("gmailUsers"));
	}
	
	/*@Test
	public void gmailTests() throws InterruptedException{
		
		openUrl(gmailUrl);
		
		BrowserEvents.enterText(Step1.userid[0], Step1.userid[1], Step1.userid[2]);
		BrowserEvents.clickByLocator(Step1.userIdNext[0], Step1.userIdNext[1]);
		
		BrowserEvents.enterText(Step2.password[0], Step2.password[1], Step2.password[2]);
		BrowserEvents.clickByLocator(Step2.loginButton[0], Step2.loginButton[1]);
		Thread.sleep(4000);
		
		// asserting to confirm the value exist
		Assert.assertTrue(BrowserEvents.getText(verifyOnLandingPage[0], verifyOnLandingPage[1]).contains("k"));
		Thread.sleep(5000);
		
		BrowserEvents.clickByLocator(composeButton[0], composeButton[1]);
		BrowserEvents.clickByLocator(toAddress[0], toAddress[1]);
		BrowserEvents.enterText(toAddress[0], toAddress[1],toAddress[2]);
		BrowserEvents.enterText(mailSubject[0], mailSubject[1], mailSubject[2]);
		
		BrowserEvents.clickByLocator(mailTextBox[0], mailTextBox[1]);
		BrowserEvents.enterText(mailTextBox[0], mailTextBox[1],mailTextBox[2]);
		
		BrowserEvents.clickByLocator(sendButton[0], sendButton[1]);
		Thread.sleep(4000);
		
		BrowserEvents.clickByLocator(signOutIcon[0], signOutIcon[1]);
		Thread.sleep(3000);
		BrowserEvents.clickByLocator(signOutButton[0], signOutButton[1]);
		Thread.sleep(6000);
		
		Assert.assertTrue(BrowserEvents.getText("tagName", "body").contains("prabha25k@gmail.com"));
	}
*/
	@Test
	public void verifyEmail() throws InterruptedException{
		BrowserEvents.openUrl(gmailUrl);
		
		BrowserEvents.enterText(Step1.userid[0], Step1.userid[1], to_email);
		BrowserEvents.clickByLocator(Step1.userIdNext[0], Step1.userIdNext[1]);
		
		BrowserEvents.enterText(Step2.password[0], Step2.password[1], to_password);
		BrowserEvents.clickByLocator(Step2.loginButton[0], Step2.loginButton[1]);
		Thread.sleep(4000);
		
		// asserting to confirm the value exist
		Assert.assertTrue(BrowserEvents.getText(verifyOnLandingPage[0], verifyOnLandingPage[1]).contains("prabha"));
		Thread.sleep(5000);
		
		Assert.assertTrue(BrowserEvents.getText("tagName", "body").contains("k prabha"));
		System.out.println("yes got mail");
		Thread.sleep(5000);
		BrowserEvents.clickByLocator("partialLinkText", "Test mail to prabha25k");
		Thread.sleep(5000);
	}
	
	@After
	public void closeGmailDriver(){
		closeDriver();
	}
}
