package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ExcelReader{
    public ExcelReader() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Users\\lenovo\\Documents\\Sheet.xlsx");
            XSSFWorkbook wb = new XSSFWorkbook(fis);

            Sheet sheet = wb.getSheet("Sheet1");
            Row row = sheet.getRow(1);
            Cell c1 = row.getCell(0);
            Cell c2 = row.getCell(1);
            double username = c1.getNumericCellValue();
            String password = c2.getStringCellValue();

            fis.close();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
