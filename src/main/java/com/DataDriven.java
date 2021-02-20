package com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataDriven {
	public static Workbook wb;
	public static Sheet sheet;
	public static int row;
	public static int cell;
	public static Object[][] getData(String filepath,String sheetname) throws Exception
	{
		FileInputStream fi=new FileInputStream(filepath);
		wb=WorkbookFactory.create(fi);
		sheet=wb.getSheet(sheetname);
		row=sheet.getLastRowNum();
		cell=sheet.getRow(0).getLastCellNum();
		System.out.println("no of rows are:"+row);
		System.out.println("no of cells are:"+cell);
		Object[][] object=new Object[row][1];
		for (int i = 1; i <=row; i++) {
			HashMap<String, String>hash=new HashMap<String, String>();
			for (int j = 0; j < cell; j++) {
				
			
			DataFormatter format=new DataFormatter();
			String key=format.formatCellValue(sheet.getRow(0).getCell(j));
			String value=format.formatCellValue(sheet.getRow(i).getCell(j));
			
			
			hash.put(key, value);
			}
			object[i-1][0]=hash;
		}
		return object;
		
	}
			
}
