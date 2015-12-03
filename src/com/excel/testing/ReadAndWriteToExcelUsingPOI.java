package com.excel.testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

import com.automation.utilities.BrowserEvents;
import com.framework.datadriven.snapdeal.pojo.SnapdealUser;

public class ReadAndWriteToExcelUsingPOI {
	static HSSFWorkbook workBook = null;
	static InputStream file;
	StringBuilder filePath = new StringBuilder();
	
	//filePath. "E:/automation/docs/testingResults/";
	
	public ReadAndWriteToExcelUsingPOI(String filePath){
		this.filePath.append("E:/automation/docs/testingResults/");
		this.filePath.append(filePath);
		this.filePath.append(".xls");
		System.out.println("final filepath is:"+this.filePath.toString());
	}
	
	public void createWorkBook(){
		
		try {
			file = new FileInputStream(new File(this.filePath.toString()));
			workBook = new HSSFWorkbook(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readAllSheets() {
		try {
			
			int sheetsCount = workBook.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < sheetsCount; sheetIndex++) {
				
				HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
				String sheetName = sheet.getSheetName();
				System.out.println("Sheet Name:"+sheetName);
				int rows = sheet.getLastRowNum();
				System.out.println("Number of rows" + rows);
				Iterator<Row> rowIterator = sheet.rowIterator();
				
				// return number of rows and columns in the sheet.
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					System.out.println("Row number is :"+row.getRowNum());
					int rowNumber = row.getRowNum();
					System.out.println("Number of cells in a row:"+row.getPhysicalNumberOfCells());
					Iterator<Cell> cell = row.cellIterator();
					while (cell.hasNext()) {
						if(rowNumber==0){
							row.createCell(row.getRowNum()+2,rowNumber).setCellValue("Results");
						}
						Cell eachCell = cell.next();
						DataFormatter dateFormatter = new DataFormatter();
						System.out.print(""+ dateFormatter.formatCellValue(eachCell)+"\t");
					}
					System.out.println(" ");
				}
			}
			file.close();
			FileOutputStream fileOut = new FileOutputStream(new File("E:/automation/docs/testingResults/output.xls"));
			workBook.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDataWithTestCaseResults(String ClassName,String status){
		try {
			/*InputStream file = new FileInputStream(new File("E:/automation/docs/testingResults/output.xls"));
			HSSFWorkbook workBook = new HSSFWorkbook(file);*/
			int sheetsCount = workBook.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < sheetsCount; sheetIndex++) {

				HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
				String sheetName = sheet.getSheetName();
				System.out.println("Sheet Name:"+sheetName);
				int rows = sheet.getLastRowNum();
				System.out.println("Number of columns:"+sheet.getRow(0).getPhysicalNumberOfCells());
				int numberOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
				System.out.println("Number of rows" + rows);
				
				Iterator<Row> rowIterator = sheet.rowIterator();

				// return number of rows and columns in the sheet.
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					System.out.println("Row number is :"+row.getRowNum());
					int rowNumber = row.getRowNum();
					System.out.println("Number of cells in a row:"+row.getPhysicalNumberOfCells());
					if(row.getCell(0).toString().equals(ClassName)){
						System.out.println("index 0 value:"+((row.getRowNum())+2));
						System.out.println("index 1 vlaue:"+rowNumber);
						System.out.println("updating in column:"+numberOfColumns);
						// updating cell
						row.createCell((numberOfColumns-1)).setCellValue(status);
					}
				}
			}
			
			file.close();
			FileOutputStream fileOut = new FileOutputStream(new File("E:/automation/docs/testingResults/output.xls"));
			workBook.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	//@SuppressWarnings("null")
	public static Object[][] getEntireCellDataAsTwoDimentionalArrayObject(String sheetName) {
		Object[][] snapDeslExcelData = null;
		try {
			HSSFSheet sheet = workBook.getSheet(sheetName);
			int rows = getRowCountOfSheet(sheetName);
			int columns = getColumnCountOfSheet(sheetName);
			System.out.println("columns and rows available is:"+columns+"--"+rows);
			if (rows<=0) {
				snapDeslExcelData =new Object[1][0];
				return snapDeslExcelData;
			}
			snapDeslExcelData = new Object[rows][columns];
			for(int rowNum=1;rowNum<=rows;rowNum++){
				for(int colNum=0;colNum<columns;colNum++){
					HSSFRow row = sheet.getRow(rowNum);
					Cell cell = row.getCell(colNum);
					DataFormatter dataFormatter = new DataFormatter();
					String value = dataFormatter.formatCellValue(cell);
					snapDeslExcelData[rowNum-1][colNum]= value;
				}
			}
			
			return snapDeslExcelData;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error due to :"+e.getMessage()+"Error causedby:"+e.getCause());
		}
		System.out.println("snapDeslExcelData data" +snapDeslExcelData.toString());
		return snapDeslExcelData;
	}

	public static int getRowCountOfSheet(String sheet){
		return workBook.getSheet(sheet).getLastRowNum();
	}
	
	public static int getColumnCountOfSheet(String sheet){
		HSSFSheet hSSFSheet = workBook.getSheet(sheet);
		return (hSSFSheet.getLastRowNum()<=0?0:(hSSFSheet.getRow(0).getLastCellNum()));
	}
	
	// this method needs to be change
	public static void generateSnapdealDummyExcelData(){
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet excelSheet = workBook.createSheet("snapdealusers");
		Iterator<SnapdealUser> userIterator = generateTestData().iterator();
		int rowCount = 0;
		while(userIterator.hasNext()){
			SnapdealUser user = userIterator.next();
			if(rowCount == 0){
				HSSFRow row = excelSheet.createRow(rowCount);
				int columnCount = 0;
				
				 HSSFCell cell0 = row.createCell(columnCount);
				 cell0.setCellValue("Email");
				 
				 HSSFCell cell1 = row.createCell(++columnCount);
				 cell1.setCellValue("Password");
				 
				 HSSFCell cell2 = row.createCell(++columnCount);
				 cell2.setCellValue("Confirm Password");
				 
				 HSSFCell cell3 = row.createCell(++columnCount);
				 cell3.setCellValue("MobileNumber");
				 
				 HSSFCell cell4 = row.createCell(++columnCount);
				 cell4.setCellValue("Results");
				 
				 rowCount++;
			}else{
				HSSFRow row = excelSheet.createRow(rowCount);
				int columnCount = 0;
				
				 HSSFCell cell0 = row.createCell(columnCount);
				 System.out.println("setting cell value:"+user.getEmail());
				 cell0.setCellValue(user.getEmail());
				 
				 HSSFCell cell1 = row.createCell(++columnCount);
				 System.out.println("setting cell value:"+user.getPassword());
				 cell1.setCellValue(user.getPassword());
				 
				 HSSFCell cell2 = row.createCell(++columnCount);
				 System.out.println("setting cell value:"+user.getConfirmPassword());
				 cell2.setCellValue(user.getConfirmPassword());
				 
				 HSSFCell cell3 = row.createCell(++columnCount);
				 System.out.println("setting cell value:"+user.getMobileNumb()+"");
				 cell3.setCellValue(Integer.parseInt(user.getMobileNumb()));
				 rowCount++;
			}
		}
		try {
			FileOutputStream output = new  FileOutputStream(new File("E:/automation/docs/testingResults/snapdeal/user.xls"));
			workBook.write(output);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("file path is not available");
		}
	}
	
	public static List<SnapdealUser> generateTestData(){
		List<SnapdealUser> snapDealUsersList = new ArrayList<SnapdealUser>();
		
		for(int i=0;i<5;i++){
			SnapdealUser user = new SnapdealUser();
			user.setEmail(BrowserEvents.generateEmail());
			String password = BrowserEvents.dataTime("MMddhhmmss");
			user.setPassword("pra"+password);
			user.setConfirmPassword("pra"+password);
			user.setMobileNumb(BrowserEvents.dataTime("MMddhhmmss"));
			snapDealUsersList.add(user);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return snapDealUsersList;
	}
	
	public static void updateSnapdealDataWithTestCaseResults(String email,String status){
		try {
			/*InputStream file = new FileInputStream(new File("E:/automation/docs/testingResults/snapdeal/user.xls"));
			HSSFWorkbook workBook = new HSSFWorkbook(file);*/
			int sheetsCount = workBook.getNumberOfSheets();
			for (int sheetIndex = 0; sheetIndex < sheetsCount; sheetIndex++) {

				HSSFSheet sheet = workBook.getSheetAt(sheetIndex);
				String sheetName = sheet.getSheetName();
				System.out.println("Sheet Name:"+sheetName);
				int rows = sheet.getLastRowNum();
				System.out.println("Number of columns:"+sheet.getRow(0).getPhysicalNumberOfCells());
				int numberOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
				System.out.println("Number of rows" + rows);
				
				Iterator<Row> rowIterator = sheet.rowIterator();

				// return number of rows and columns in the sheet.
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					System.out.println("Row number is :"+row.getRowNum());
					int rowNumber = row.getRowNum();
					System.out.println("Number of cells in a row:"+row.getPhysicalNumberOfCells());
					if(row.getCell(0).toString().equals(email)){
						System.out.println("index 0 value:"+((row.getRowNum())+2));
						System.out.println("index 1 vlaue:"+rowNumber);
						System.out.println("updating in column:"+numberOfColumns);
						// updating cell
						row.createCell((numberOfColumns-1)).setCellValue(status);
					}
				}
			}
			
			file.close();
			FileOutputStream fileOut = new FileOutputStream(new File("E:/automation/docs/testingResults/snapdeal/user.xls"));
			workBook.write(fileOut);
			fileOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//readAllSheets();
		/*ReadAndWriteToExcelUsingPOI xcelInfo = new ReadAndWriteToExcelUsingPOI("input");
		xcelInfo.createWorkBook();
		//updateDataWithTestCaseResults("Snapdeal","success");
		Object[][] object = getEntireCellDataAsTwoDimentionalArrayObject("Script_results");
		System.out.println("object length:"+object.length);*/
		
		//generateDummyExcelData();
		//new ReadAndWriteToExcelUsingPOI("user").createWorkBook();

		updateSnapdealDataWithTestCaseResults("prabha20151202195358070@gmail.com","failure");
	}
}
