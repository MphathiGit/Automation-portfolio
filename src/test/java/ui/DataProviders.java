package ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


public class DataProviders  {



      /*
       * Data provider reads excel file conaining travel dates in sets, then passes this data as a multiDim array
       * to the Test method calling it.
       */

      @DataProvider(name = "excelDates")
      public Object[][] provideDate() throws IOException{

    	     File excelFile = new File("./Data.xlsx");
    	     FileInputStream pipe = new FileInputStream(excelFile);
    	     XSSFWorkbook workbook = new XSSFWorkbook(pipe);

    	     XSSFSheet sheet = workbook.getSheetAt(0);
    	     int numOfRows = sheet.getPhysicalNumberOfRows() ;
    	     int numOfCells = sheet.getRow(0).getLastCellNum();
    	     System.out.println("orig numRow>>: "+numOfRows);
    	     System.out.println("orig numCells>>: "+numOfCells);

             Object[][] data = new Object[numOfRows][numOfCells];
             CellType cellDatatype;

      for (int row = 0; row < numOfRows; row++) {
    	   for(int cell = 0; cell < numOfCells; cell++) {
    		   cellDatatype = sheet.getRow(row).getCell(cell).getCellType();
    		  switch (cellDatatype) {
			     case STRING:
			    		 data[row][cell] = (sheet.getRow(row).getCell(cell).getStringCellValue());
			    		 break;
			     case NUMERIC:
    				    data[row][cell] = (sheet.getRow(row).getCell(cell).getNumericCellValue()+"").replace(".0", "");
			            break;
			     default:
				        break;
			}

    	  }
      }
          pipe.close();
          workbook.close();

		 return data;
      }








	}


