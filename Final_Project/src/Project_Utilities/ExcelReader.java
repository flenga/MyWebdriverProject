package Project_Utilities;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ExcelReader 
{
	/* The worksheet to read in Excel file*/
	public static Sheet wrksheet;

	/* The Excel file to read */

	public static Workbook wrkbook = null;
	/*Store the column data */
	public static Hashtable<String, Integer> dict = new Hashtable<String, Integer>();

	/* Create a Constructor
	 * @param ExcelSheetPath
	 * @throws BiffException
	 * @throws WeblivException 
	 * */
	public ExcelReader(String ExcelSheetPath) throws IOException, BiffException
	{
// Initialize
		try 
		{
			wrkbook = Workbook.getWorkbook(new File(ExcelSheetPath));
			wrksheet = wrkbook.getSheet("LogInData");
		}
		catch (IOException e) 
		{
			throw new IOException();
		}
	}

	/*
	 * Returns the Number of Rows
	 * @return Rows
	 */

	public static int RowCount() 
	{
		return wrksheet.getRows();
	}

	/*
	 * Returns the Cell value by taking row and Column values as argument
	 * @param column
	 * @param row
	 * @return Cell contents
	 */
	public static String ReadCell(int column, int row) 
	{
		return wrksheet.getCell(column, row).getContents();
	}
	/* Create Column Dictionary to hold all the Column Names*/

	public static void ColumnDictionary()
	{

		// Iterate through all the columns in the Excel sheet and store the

		// value in Hashtable

		for (int col = 0; col < wrksheet.getColumns(); col++) 
		{
			dict.put(ReadCell(col, 0), col);
		}
	}

	/*
	 * Read Column Names
	 * @param colName
	 * @return value
	 */

	public static int GetCell(String colName)
	{
		try 
		{
			int value;
			value = ((Integer) dict.get(colName)).intValue();
			return value;
		} 
		catch (NullPointerException e) 
		{
			return (0);
		}
	}
}
