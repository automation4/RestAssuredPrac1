package utils;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static Object[][] getExcelData(String condition) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\Book1.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        int totalrows = sheet.getPhysicalNumberOfRows();
        int totalcell = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[1][1];
        for (int i = 0; i < totalrows; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < totalcell; j++) {
                XSSFCell cell = row.getCell(i);
                if (cell.getStringCellValue().equalsIgnoreCase(condition)) {
                    data[i][j] = cell.getRow().getCell(j+1);
                    j++;
                }
                break;
            } }
        return data;
    }

    @Test
    public static void getExcelData1() throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\Book1.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("Sheet1");
        int totalrows = sheet.getPhysicalNumberOfRows();
        int totalcell = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[1][1];
        for (int i = 0; i < totalrows; i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < totalcell; j++) {
                XSSFCell cell = row.getCell(i);
                if (cell.getStringCellValue().equalsIgnoreCase("samplepayload1")) {
                        data[i][j] = cell.getRow().getCell(j+1);
                        j++;
                    }
                    break;
                } }


        for(int i=0;i<totalrows;i++){
            for(int j=0;j<data[i].length;j++){
                System.out.println(data[i][j]);
            }
        }
    }
}

