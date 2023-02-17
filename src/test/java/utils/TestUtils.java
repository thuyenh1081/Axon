package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestUtils {
    public static final long WAIT = 120;
    public static final String driverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
            + File.separator + "resources" + File.separator + "drivers" + File.separator+ "chromedriver";
    public static final String excelFileFolder = System.getProperty("user.dir") + File.separator + "target"
            + File.separator + "Reports";

    public void readExcel(String filePath,String fileName,String sheetName) throws IOException{

        //Create an object of File class to open xlsx file

        File file =    new File(filePath+"\\"+fileName);

        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook book = null;

        //Find the file extension by splitting file name in substring  and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if(fileExtensionName.equals(".xlsx")){

            //If it is xlsx file then create object of XSSFWorkbook class

            book = new XSSFWorkbook(inputStream);

        }

        //Check condition if the file is xls file

        else if(fileExtensionName.equals(".xls")){

            //If it is xls file then create object of HSSFWorkbook class

            book = new HSSFWorkbook(inputStream);

        }

        //Read sheet inside the workbook by its name

        Sheet sheet = book.getSheet(sheetName);

        //Find number of rows in excel file

        int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();

        //Create a loop over all the rows of excel file to read it

        for (int i = 0; i < rowCount+1; i++) {

            Row row = sheet.getRow(i);

            //Create a loop to print cell values in a row

            for (int j = 0; j < row.getLastCellNum(); j++) {

                //Print Excel data in console

                System.out.print(row.getCell(j).getStringCellValue()+"|| ");

            }

            System.out.println();
        }


    }
    public static void writeExcel(String filePath, String fileName, String sheetName, int startRowNumber,
                                  List<List<String>> dataToWrite)
            throws IOException {

        //Create an object of File class to open xlsx file

//        File file =    new File(filePath+"\\"+fileName);
        File file = new File(filePath + "/" + fileName);
        //Create an object of FileInputStream class to read excel file

        FileInputStream inputStream = new FileInputStream(file);

        Workbook book = null;

        //Find the file extension by splitting  file name in substring and getting only extension name

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        //Check condition if the file is xlsx file

        if (fileExtensionName.equals(".xlsx")) {

            //If it is xlsx file then create object of XSSFWorkbook class

            book = new XSSFWorkbook(inputStream);

        }

        //Check condition if the file is xls file

        else if (fileExtensionName.equals(".xls")) {

            //If it is xls file then create object of XSSFWorkbook class

            book = new HSSFWorkbook(inputStream);

        }

        //Read excel sheet by sheet name

        Sheet sheet = book.getSheet(sheetName);

        //Get the current count of rows in excel file

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();

        //Get the last row from the sheet

        Row row = sheet.getRow(sheet.getLastRowNum());

        //Create a new row and append it at last of sheet

        for(int i=0; i< dataToWrite.size(); i++ )
        {
            rowCount = rowCount + 1;
            Row newRow = sheet.createRow(rowCount);

        //Create a loop over the cell of newly created Row

            for (int j = 0; j < row.getLastCellNum(); j++) {

                //Fill data in row

                Cell cell = newRow.createCell(j);

                cell.setCellValue(dataToWrite.get(i).get(j));
            }
         }
        //Close input stream

        inputStream.close();

        //Create an object of FileOutputStream class to create write data in excel file

        FileOutputStream outputStream = new FileOutputStream(file);

        //write data in the excel file

        book.write(outputStream);

        //close output stream

        outputStream.close();

    }

}
