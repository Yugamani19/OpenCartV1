package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook wb;
	public XSSFSheet ws;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtils (String path)
	{
		this.path=path;
	}
	
	
	public  int getRowCount(String sheetName) throws IOException
	{
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fis.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetName, int rownum) throws IOException
	{
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		int cellCount = row.getLastCellNum();
		wb.close();
		fis.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName, int rownum, int colnum) throws IOException
	{
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		
		try {
		 //data = cell.toString(); 
		 DataFormatter formatter = new DataFormatter();
		 data = formatter.formatCellValue(cell);
		}
		catch(Exception e)
		{
			data ="";
		}
		
		wb.close();
		fis.close();
		return data;
		}
	
	public void setCellData(String sheetName, int rownum, int colnum, String data) throws IOException
	{
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fos = new FileOutputStream(path);
		wb.write(fos);
		wb.close();
		fos.close();
		fis.close();
	}
	
	public void fillGreenColour(String sheetName,int rownum, int colnum) throws IOException
	{
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos = new FileOutputStream(sheetName);
		wb.write(fos);
		wb.close();
		fis.close();
		fos.close();
	}
	
	public void fillRedColour(String sheetName, int rownum, int colnum) throws IOException
	{
		fis = new FileInputStream(path);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheetName);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		cell.setCellStyle(style);
		fos = new FileOutputStream(sheetName);
		wb.write(fos);
		wb.close();
		fos.close();
		fis.close();
	}
	
	
	

}
