package com.framework.datadriven.snapdeal.scripts;

import static com.automation.utilities.BrowserEvents.clickByLocator;
import static com.automation.utilities.BrowserEvents.closeDriver;
import static com.automation.utilities.BrowserEvents.createDriver;
import static com.automation.utilities.BrowserEvents.enterText;
import static com.automation.utilities.BrowserEvents.openUrl;
import static com.automation.utilities.BrowserEvents.mouseOverByIdentityTypeLocator;
import static com.automation.utilities.BrowserEvents.takeScreenShotOnfailure;
import static com.automation.utilities.BrowserEvents.verifyForTagName;
import static com.automation.utilities.XcelUtilities.getParamsObject;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.excel.testing.ReadAndWriteToExcelUsingPOI;
import com.framework.datadriven.snapdeal.pojo.SnapDealItem.BuyItem;

@RunWith(Parameterized.class)
public class SnapDealPurchaseTestNG {
	
	String className = this.getClass().getSimpleName();
	
	String email;
	String password;
	String confirmPassword;
	String mobileNumber;
	String result;
	
	/*public SnapDealPurchaseTestNG(String email,String password, String confirmPassword,String mobileNumber,String result){
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.mobileNumber = mobileNumber;
		this.result = result;
	}*/
	
	/*@Parameters
	public static Collection<Object[]> getSnapdealData(){
		return Arrays.asList(getParamsObject());
	}*/
	
	@DataProvider(name="snapdealData")
	public Object[][] createData(){
		return getParamsObject();
	}
	
	@BeforeMethod
	public void createDriever(){
		createDriver(BuyItem.BROWSER);
	}
	
	@Test(dataProvider="snapdealData")
	public void snapdealFlow(String email,String password, String confirmPassword,String mobileNumber,String result){
		boolean isTestSuccess = false;
		//createDriver(BuyItem.BROWSER);
		try{
			openUrl(BuyItem.URL);
			
			mouseOverByIdentityTypeLocator(BuyItem.COMPUTERS_GAMES[0],BuyItem.COMPUTERS_GAMES[1]);// is preferred to use.
			mouseOverByIdentityTypeLocator(BuyItem.XPATH_IDENTIFY,BuyItem.STORAGE);
			clickByLocator(BuyItem.LINK_IDENTIFY,BuyItem.TB_LINK);

			// Select item among many items
			clickByLocator(BuyItem.XPATH_IDENTIFY, BuyItem.TB_DEVICE);
			
			// checking for delivery address
			enterText(BuyItem.ID_IDENTIFY,BuyItem.PIN_CHECK,"560016");
			clickByLocator(BuyItem.ID_IDENTIFY, BuyItem.PIN_CHECK_BUTTON);

			// clicking add to cart
			clickByLocator(BuyItem.ID_IDENTIFY,BuyItem.ADD_TO_CART);
			clickByLocator(BuyItem.XPATH_IDENTIFY,BuyItem.CLOSE_CART);
			
			clickByLocator(BuyItem.ID_IDENTIFY,BuyItem.BUY_BUTTON);
			
			// registering in snapdeal
			enterText(BuyItem.ID_IDENTIFY,BuyItem.USERNAME,email);
			
			clickByLocator(BuyItem.ID_IDENTIFY,BuyItem.LOGIN_CONTINUE);
			enterText(BuyItem.ID_IDENTIFY,BuyItem.PASSWORD,password);
			enterText(BuyItem.ID_IDENTIFY,BuyItem.CONFIRM_PASSWORD,confirmPassword);
			clickByLocator(BuyItem.ID_IDENTIFY,BuyItem.REGISTER);
			
			// personal dtails
			enterText(BuyItem.ID_IDENTIFY,"fullName","prabhakar");
			enterText(BuyItem.ID_IDENTIFY,"address","No.37, bsv reddy layout, ramamurthinagar");
			enterText(BuyItem.ID_IDENTIFY,"nearestLandmark","Universal gim road");
			enterText(BuyItem.ID_IDENTIFY,"mobile","8722214333");
			clickByLocator(BuyItem.ID_IDENTIFY,"home-mobile");
			
			clickByLocator(BuyItem.ID_IDENTIFY,"delivery-modes-continue");
			Thread.sleep(2000);
			
			Assert.assertTrue(verifyForTagName("body","Review Order"));
			Thread.sleep(3000);
			
			isTestSuccess = true;
			
			
		}catch(InterruptedException ex){
			
			ex.printStackTrace();
		}catch(NoSuchElementException elementEx ){
			// static method access directly using class name
			takeScreenShotOnfailure("element_not_found");
			String cause = elementEx.getCause().getMessage();
			System.out.println("cause of exception:"+cause);
		}
		finally{
			if(isTestSuccess)
				ReadAndWriteToExcelUsingPOI.updateSnapdealDataWithTestCaseResults(email, "Success");
			else
				ReadAndWriteToExcelUsingPOI.updateSnapdealDataWithTestCaseResults(email, "Failed");
		}
	}
	
	
	@AfterMethod
	public void closeSnapdealDriver(){
		closeDriver();
	}
	
	
}
