/***********************************************************************
* @author 			:		Srinivas Hippargi
* @description		: 		Application Indepent Methods
* @methods 			: 		getConfigValue(),setConfigValue(),toReadExcelData(),setCellExeclData().
*/
package com.move.androidapp.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GenericLib {
	public final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	public static String sFile;
	public static String sDirPath = System.getProperty("user.dir");
	public static String sConfigPath = sDirPath + "/src/main/resources/config/config.xlsx";
	public static String sUserCredFile = sDirPath + "/src/test/resources/propertyfiles/user_cred.properties";
	public static String sTestDataPath = sDirPath + "/src/test/resources/testdata/app/app_testdata.xlsx";


	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: To read the basic environment settings data from config file
	 * based on Property file value
	 */
	public static String getProprtyValue(String sFile, String sKey) {
		Properties prop = new Properties();
		String sValue = null;
		try {
			InputStream input = new FileInputStream(sFile);
			prop.load(input);
			sValue = prop.getProperty(sKey);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sValue;
	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: To read the basic environment settings data from config file
	 */
	public static Properties getPropertyFile(String sFile) {
		Properties prop = new Properties();
		String sValue = null;
		try {
			InputStream input = new FileInputStream(sFile);
			prop.load(input);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description:To set the basic environment settings data from config file
	 */
	public static void setPropertyValue(String sFile, String sKey, String sValue) {
		Properties prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(new File(sFile));
			prop.load(fis);
			fis.close();
			FileOutputStream fos = new FileOutputStream(new File(sFile));
			prop.setProperty(sKey, sValue);
			prop.store(fos, "Updated  file with " + "Key " + sKey + " and Value " + sValue);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @author: Srinivas Hippargi
	 * 
	 * Description:To read test data from excel sheet based on TestcaseID
	 */
	public static String[] toReadExcelData(String sFilepath, String sSheet, String sTestCaseID) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(sTestCaseID)) {
					int iCellNum = sht.getRow(i).getPhysicalNumberOfCells();
					sData = new String[iCellNum];
					for (int j = 0; j < iCellNum; j++) {
						sData[j] = sht.getRow(i).getCell(j).getStringCellValue();
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	/*
	 * @author: Srinivas Hippargi
	 * 
	 * Description:To read test data from excel sheet based on TestcaseID
	 */
	public static String[] toReadColumnData(String sFilepath, String sSheet, int coloumn) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 1; i <= iRowNum; i++) {
				sData = new String[iRowNum];
				sData[i - 1] = sht.getRow(i).getCell(coloumn).getStringCellValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}
	
	/*
	 * @author: Srinivas Hippargi
	 * 
	 * Description:To read test data from excel sheet based on TestcaseID
	 */
	public static String[] readTestCaseIds(String sFilepath, String sSheet) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			sData = new String[iRowNum];
			for (int i = 1; i <= iRowNum; i++) {
				sData[i - 1] = sht.getRow(i).getCell(0).getStringCellValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	/*
	 * @author: Srinivas Hippargi
	 * 
	 * Description:To read test data from excel sheet based on MilkAnalyzer
	 */
	public static String[] toReadMilkAnalyzerData(String sFilepath, String sSheet, String milkAnalyzerType,
			String configType) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(milkAnalyzerType)) {
					if (sht.getRow(i).getCell(1).toString().equals(configType)) {
						int iCellNum = sht.getRow(i).getPhysicalNumberOfCells();
						sData = new String[iCellNum];
						for (int j = 0; j < iCellNum; j++) {
							sData[j] = sht.getRow(i).getCell(j).getStringCellValue();
						}
						break;
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	/*
	 * @author: Srinivas Hippargi
	 * 
	 * Description: Method to read data based on row header | TEST_CASE_NO
	 */

	public static int getColumnIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.toReadExcelData(filepath, sSheet, "TEST_CASE_NO");
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}

	/*
	 * @author: Srinivas Hippargi
	 * 
	 * Description: Method to read data based on row header | Farmer Id
	 */

	public static int readFarmerTempHeadings(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.toReadExcelData(filepath, sSheet, "Farmer Id");
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}
	
	/*
	 * @author: Srinivas Hippargi
	 * 
	 * Description: Method to read data based on row header | S No
	 */

	public static int getReportColumnIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.toReadExcelData(filepath, sSheet, "S No");
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}

	/*
	 * @author: Srinivas Hippargi
	 * 
	 * Description: Method to read data based on row header | MILK_ANALYZER
	 */

	public static int getConfigurationIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.toReadExcelData(filepath, sSheet, "MILK_ANALYZER");
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}

	/*
	 * @author: Srinivas Hippargi Description: Method to read data based on row
	 * header
	 */

	public static int getHeaderColumnIndex(String filepath, String sSheet, String colName) {
		String[] firstRow = GenericLib.toReadExcelData(filepath, sSheet, "SI No");
		int index = 0;
		for (int i = 0; i < firstRow.length; i++) {
			if (firstRow[i].equalsIgnoreCase(colName)) {
				index = i;
			}
		}
		return index;
	}

	/*
	 * @author: Srinivas Hippargi Description:Method is used to set data in
	 * excel sheet
	 */

	public static void setCellData(String filePath, String sSheet, String sTestCaseID, String columnName, String value)
			throws Exception {
		int columnNumber = getColumnIndex(filePath, sSheet, columnName);
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			// logger.info("----------Sheet " + sSheet);
			int lastRowNum = sht.getLastRowNum();
			for (int i = 0; i <= lastRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(sTestCaseID)) {
					Row rowNum = sht.getRow(i);
					Cell cell = rowNum.getCell(columnNumber);
					if (cell == null) {
						cell = rowNum.createCell(columnNumber);
						cell.setCellValue(value);
					} else {
						cell.setCellValue(value);
					}
					break;
				}
			}
			FileOutputStream fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		} catch (Exception e) {
			throw (e);
		}
	}

	public static void executeBatchCommmand(String command) {
		try {
			String line;
			ArrayList<String> deviceUDID = new ArrayList<String>();
			Process p = Runtime.getRuntime().exec("cmd /c " + command);

			BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((line = bri.readLine()) != null) {
				// logger.info(line);
				deviceUDID.add(line);
			}
			bri.close();
			while ((line = bre.readLine()) != null) {
				logger.info("" + line);
			}

			bre.close();
			p.waitFor();

		} catch (Exception err) {
			err.printStackTrace();
		}
	}

	/*
	 * @author: Description: To split and return the array
	 */
	public static String[] getSplittedArray(String str, String splitChar) {
		return str.split(splitChar);
	}

	/*
	 * @author: Description: Extract the string based on previous and next
	 * strings and occurrences
	 */
	public static String getString(String str, String startStr, int startOccurance, String endStr, int endOccurance) {
		return str.substring(str.indexOf(startStr, startOccurance) + startStr.length(),
				str.indexOf(endStr, endOccurance));
	}

	/*
	 * @author: Description: Extract the string based on previous and next
	 * strings
	 */
	public static String getString(String str, String startStr, String endStr) {
		return str.substring(str.indexOf(startStr) + startStr.length(), str.indexOf(endStr));
	}
	
	/*
	 * @author: Srinivas Hippargi
	 * 
	 * Description:To read test data from excel sheet based on TestcaseID
	 */
	public static String[] toReadExcelDataWithNull(String sFilepath, String sSheet, String sTestCaseID) {
		String sData[] = null;
		try {
			FileInputStream fis = new FileInputStream(sFilepath);
			Workbook wb = (Workbook) WorkbookFactory.create(fis);
			Sheet sht = wb.getSheet(sSheet);
			int iRowNum = sht.getLastRowNum();
			for (int i = 0; i <= iRowNum; i++) {
				if (sht.getRow(i).getCell(0).toString().equals(sTestCaseID)) {
					int iCellNum = sht.getRow(i).getPhysicalNumberOfCells();
					sData = new String[iCellNum];
					for (int j = 0; j < iCellNum; j++) {
						String value=sht.getRow(i).getCell(j).getStringCellValue();
						if(value.equals("") || value ==null || value.equals(" ")) {
							value="novalue";
						}
						sData[j] = value;
					}
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sData;
	}

	
	
	
}
