package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;

public class XLUtils {

    public static FileInputStream fi;
    public static Workbook wb;
    public static Sheet ws;
    public static Row row;
    public static Cell cell;

    public static int getrowCount(String xlfile,String xlsheet) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        int rowcount = ws.getLastRowNum()-ws.getFirstRowNum();
        return rowcount;
    }
    public static String getStringCellData(String xlfile,String xlsheet,int rownum,int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);

        String data;
        try {
            cell = row.getCell(colnum);
            data = cell.getStringCellValue();
        }catch (Exception e)
        {
            data = "";
        }
        return data;
    }

    public static double getNumericCellValue(String xlfile,String xlsheet,int rownum,int colnum) throws IOException {
        fi = new FileInputStream(xlfile);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlsheet);
        row = ws.getRow(rownum);

        double data;
        try {
            cell = row.getCell(colnum);
            data = cell.getNumericCellValue();
        }catch (Exception e)
        {
            data = 0.0;
        }
        return data;
    }

    public static void man() throws IOException {
        String data = "C:\\Users\\lenovo\\IdeaProjects\\Mobile\\src\\test\\java\\Sheet.xlsx";
        String sheet = "Sheet1";

        int rowcount = XLUtils.getrowCount(data,sheet);
        String username;
        String password;
        for (int i=1;i<=rowcount;i++)
        {

        }
            username = String.valueOf(XLUtils.getNumericCellValue(data,sheet,1,0));
            password = XLUtils.getStringCellData(data,sheet,1,1);
        }
    }

