package com.excel.testing;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ReadExcelData {
	static String className;
	public void getClassName(){
		className = this.getClass().getSimpleName();
	}
	
	public static void readExcel(){
		try {
			Workbook workBook =  Workbook.getWorkbook(new FileInputStream("E:/automation/docs/scripts.xls"));
			
			String[] sheetNames = workBook.getSheetNames();
			
			for(int sheetIndex=0;sheetIndex < sheetNames.length;sheetIndex++){
				Sheet sheet = workBook.getSheet(sheetNames[sheetIndex]);
				//return number of rows and columns in the sheet.
				int noOfRows = sheet.getRows();
				int noOfCols = sheet.getColumns();
				
				for(int i=0;i < noOfRows;i++){
					for(int j=0;j < noOfCols;j++){
						System.out.print(sheet.getCell(j, i).getContents()+"\t");
					}
					System.out.println("");
				}
			}
			
			System.out.println("Class name:"+className);
			
			//workBook.getSheet("Script_results");
		} catch (BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void writeToExcel(){
		try {
			WritableWorkbook newWorkBook = Workbook.createWorkbook(new FileOutputStream("temp.xls"));
			WritableSheet sheet = newWorkBook.createSheet("sheet_results",0);
			
			Label label = new Label(0,1,"hello");
			sheet.addCell(label);
			
			newWorkBook.write();
			newWorkBook.close();
			
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void main(String[] args) {
		/*new ReadExcelData().getClassName();
		ReadExcelData.readExcel();*/
		ReadExcelData.writeToExcel();
	}
}
