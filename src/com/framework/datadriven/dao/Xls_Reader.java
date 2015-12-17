package com.framework.datadriven.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class Xls_Reader {
	
	static InputStream inputStream; 
	static HSSFWorkbook workbook;
	
	// creating instance for excel file.
	public static void getInstanceOfExcel(String excelPath){
		try {
			
			inputStream = new FileInputStream(new File(excelPath));
			workbook = new HSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Caused by:"+e.getCause());
		}
	}
	/**
	 * Read data from excel sheet with specific rowNumber and sheet name
	 * 
	 * @param rowNumber
	 * @param sheetName
	 */
	public static void readTheRow(int rowNumber,String sheetName){
		HSSFSheet sheet = workbook.getSheet(sheetName);
		HSSFRow row = sheet.getRow(rowNumber);
		Iterator<Cell> cellItr =  row.cellIterator();
		System.out.println("creating two dimentional array with size: "+ row.getLastCellNum());
		Object[][] userInfo = new Object[1][row.getLastCellNum()];
		
		int i=0;
		while(cellItr.hasNext()){
			userInfo[0][i] = cellItr.next().getStringCellValue();
			i++;
		}
	}
	
	/**
	 * Get all data in a sheet in tow dimensional array object
	 * 
	 * @param sheetName
	 * @return 
	 */
	public static Object[][] getSheetData(String sheetName){
		HSSFSheet sheet = workbook.getSheet(sheetName);
		System.out.println("created instance for sheet.");
		
		// adding 1 to the x axis size of array to make sure not to braeak in between
		Object[][] userInfo = new Object[sheet.getLastRowNum()+1][];
		
		int sizeofRows = sheet.getLastRowNum();
		System.out.println("Number of rows in a sheet:"+sizeofRows);
		
		for(int rowNum=0;rowNum<sizeofRows;rowNum++){
			HSSFRow row = sheet.getRow(rowNum);
			Iterator<Cell> cellItr =  row.cellIterator();
			System.out.println("creating two dimentional array with size: "+ row.getLastCellNum());
			int i=0;
			while(cellItr.hasNext()){
				userInfo[rowNum][i] = cellItr.next().getStringCellValue();
				i++;
			}
		
		}
		return userInfo;
	}
	
	public static void readExcel(){
		String currentFilePath = System.getProperty("user.dir");
		System.out.println("current folder path is"+currentFilePath+"/excel_files/gmail_user.xls");
		File file = new File(currentFilePath + "/excel_files/gmail_user.xls");
		try {
			inputStream= new FileInputStream(file);
			workbook = new HSSFWorkbook(inputStream);
			System.out.println("work book object"+workbook);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
