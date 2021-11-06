package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.util.SystemOutLogger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public static void main(String args[]) throws IOException {

		//Processing using ArrayList
		//ArrayList<String> getLst=new ArrayList<String>();
		//getLst=getData("Test Case2");
		//Regular approach to print array list
		/*for(int i=0;i<getLst.size();i++) {
			System.out.println(getLst.get(i));
		}*/
		//Lamda expression to print array list
		//getLst.forEach(tstCaseName -> System.out.println(tstCaseName));
		//getLst.forEach(System.out::println);

		//Processing using HashMap
		HashMap<String, String> getMap=new HashMap<String, String>();
		getMap=getData("Test Case2");
		//Print key and value using lamda functions
		getMap.forEach((key, value) -> System.out.println(key + " : " + value));
		//Print only keys
		getMap.forEach((key, value) -> System.out.println(key));
		//Print only values
		getMap.forEach((key, value) -> System.out.println(value));
	}

	//public static ArrayList<String, String> getData(String tstCaseName) throws IOException {
	public static HashMap<String, String> getData(String tstCaseName) throws IOException {
		ArrayList<String> dataLst=new ArrayList<String>();
		HashMap<String, String> dataMap=new HashMap<String, String>();
		FileInputStream fis = new FileInputStream("./src/test/java/resources/testData.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		int sheets=wb.getNumberOfSheets();

		for (int i=0;i<sheets;i++) {

			if(wb.getSheetName(i).equalsIgnoreCase("Data")) {
				XSSFSheet sht=wb.getSheetAt(i);
				//Identify Test Case Name column by scanning the 1st row
				Iterator<Row> rows=sht.rowIterator();					//Contains all rows in a sheet. Sheet is a collection of rows
				Row fstRow=rows.next();									//Cursor focus on first row
				Iterator<Cell> fstRowCels=fstRow.cellIterator();		//Contains all cells in a row. Row is a collection of cells
				int tstCaseNameColInd=0;
				int colInd=0;
				//Traversing each cell in first row
				while(fstRowCels.hasNext()) {
					Cell celVal=fstRowCels.next();
					if(celVal.getStringCellValue().equalsIgnoreCase("Test Case Name")) {
						tstCaseNameColInd=colInd;			//Get Test Case Name Column number
					}
					colInd++;
				}
				//System.out.println(tstCaseNameColInd);

				//Find test case scenario using Test Case Name column number
				int celInd=0;
				while(rows.hasNext()) {
					Row curRow=rows.next();
					if(curRow.getCell(tstCaseNameColInd).getStringCellValue().equalsIgnoreCase(tstCaseName)) {
						Iterator<Cell> curCel=curRow.cellIterator();		//All cells in current row
						while(curCel.hasNext()) {
							Cell curCelVal=curCel.next();
							if(curCelVal.getCellType()==CellType.STRING) {
								//dataLst.add(curCel.next().getStringCellValue());
								dataMap.put(fstRow.getCell(celInd).getStringCellValue(), curCelVal.getStringCellValue());
							}
							if(curCelVal.getCellType()==CellType.NUMERIC) {
								//dataLst.add(curCel.next().getStringCellValue());
								dataMap.put(fstRow.getCell(celInd).getStringCellValue(), String.valueOf(curCelVal.getNumericCellValue()));
							}

							celInd++;
						}
					}
				}

			}
		}
		wb.close();
		//return dataLst;
		return dataMap;

	}

}
