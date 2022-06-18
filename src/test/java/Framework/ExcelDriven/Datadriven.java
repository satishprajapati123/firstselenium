package Framework.ExcelDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Datadriven {

	public ArrayList<String> getdata(String testcasename) throws IOException {
		/*
		 * First we need to use the fileinputstream method to read the data from sheet.
		 * Then create object of XSSFworkbook class and pass object fis into that.
		 */
		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("/Users/satishprajapati/Documents/Book1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		// Get No. of sheets present in the excel
		int sheetcounts = workbook.getNumberOfSheets();

		for (int i = 0; i < sheetcounts; i++) {
			// Traverse through for loop and after that compare with sheet name with the
			// help of If statement.
			if (workbook.getSheetName(i).equalsIgnoreCase("TestData")) {

				XSSFSheet sheet = workbook.getSheetAt(i);

				// Step1 : Identity Testcase column from excelsheet by scanning whole first Row.

				Iterator<Row> rows = sheet.iterator(); // Sheet is collections of rows
				Row firstrow = rows.next();

				// Step 2 : Identity Testcase column from excelsheet by scanning whole first
				// Row.

				Iterator<Cell> column = firstrow.cellIterator(); // Rows is collections of cells

				int k = 0, column1 = 0;
				while (column.hasNext()) {
					Cell value = column.next();
					if (value.getStringCellValue().equalsIgnoreCase("Testcase")) {
						// Desired column
						column1 = k;
					}
					k++;
				}

				System.out.println(column1);

				// hasnext() method tell us that next cells is present or not.

				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column1).getStringCellValue().equalsIgnoreCase(testcasename)) {
						// After you grab purchase Testcase Row then Pull all the data of that row and
						// feed into test.
						
						
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							
							Cell c = cv.next();
								
								if(c.getCellType()==CellType.STRING)
								{
							
									a.add(((Cell) cv).getStringCellValue());
								}
								else
								{
									
									a.add(NumberToTextConverter.toText(((Cell) cv).getNumericCellValue()));
								}
							
							// System.out.println();
							// Print all the data present in the purchase row. Need to store in Arraylist.

						}
					}
				}
			}

			// return a;
		}
		return a;

	}

	public static void main(String args[]) throws IOException {

	}
}

/*
 * Identity Testcase column from excelsheet by scanning whole first Row. Once
 * Column is identified then scan the entire Testcase column to get the purchase
 * testcase. After you grab purchase Testcase Row then Pull all the data of that
 * row and feed into test.
 */