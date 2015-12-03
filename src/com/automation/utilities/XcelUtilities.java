package com.automation.utilities;

import com.excel.testing.ReadAndWriteToExcelUsingPOI;

public class XcelUtilities {
	static String filePath;
	public XcelUtilities(String filePath){
		XcelUtilities.filePath = filePath;
	}
	
	public static void generateDummyExcelData(){
		
	}
	
	public static Object[][] getParamsObject(){
		ReadAndWriteToExcelUsingPOI readExcel = new ReadAndWriteToExcelUsingPOI("snapdeal/user");
		ReadAndWriteToExcelUsingPOI.generateSnapdealDummyExcelData();
		readExcel.createWorkBook();
		return ReadAndWriteToExcelUsingPOI.getEntireCellDataAsTwoDimentionalArrayObject("snapdealusers");
	}
}
