/***********************************************************************
* @author 				:		Srinivas Hippargi
* @description			: 		Implemented Application Precondition and Postconditions
* @Variables			: 	  	Declared and Initialised AndroidDriver and WebDriver, Instance for GlobalVariables Page
* @BeforeSuiteMethod	: 		DB connection for xyz
* @BeforeTest			: 		Desired Capabilities for launching app and launching portal		
*/

package com.move.androidapp.lib;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.move.androidapp.init.GlobalVariables;
import com.move.androidapp.report.MyExtentListners;
import com.move.androidapp.util.MobileActionUtil;
import com.paulhammant.ngwebdriver.NgWebDriver;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

public class BaseLib{

	public static GlobalVariables gv = new GlobalVariables();

	/*
	 * Read Parameters from Jenkins
	 */

	static {

		if (gv.sUDID != null) {
			gv.iPort = Integer.parseInt(System.getProperty("PORT"));
			gv.sUDID = System.getProperty("UDID");
			gv.sVersion = System.getProperty("VERSION");
			gv.sDeviceName = System.getProperty("DEVICENAME");
			gv.browser=System.getProperty("CHROME");
			
		} else {

			int rowCount = ExcelLibrary.getExcelRowCount(GenericLib.sConfigPath, "config");
			System.out.println(" Total Row Count ============> " + rowCount);
			ArrayList<String> deviceCount = new ArrayList<String>();
			int runStatus = GenericLib.getHeaderColumnIndex(GenericLib.sConfigPath, "config", "Run Status");
			for (int i = 1; i <= rowCount; i++) {

				if (ExcelLibrary.getExcelData(GenericLib.sConfigPath, "config", i, runStatus).equalsIgnoreCase("Yes")) {
					// System.out.println(i);
					deviceCount.add(ExcelLibrary.getExcelData(GenericLib.sConfigPath, "config", i, runStatus));
				}
			}
			System.out.println(deviceCount.size());

			if (String.valueOf(deviceCount.size()).equalsIgnoreCase("1")) {
				for (int i = 1; i <= rowCount; i++) {

					if (ExcelLibrary.getExcelData(GenericLib.sConfigPath, "config", i, runStatus)
							.equalsIgnoreCase("Yes")) {

						int port = GenericLib.getHeaderColumnIndex(GenericLib.sConfigPath, "config", "Port");
						int udid = GenericLib.getHeaderColumnIndex(GenericLib.sConfigPath, "config", "Device UDID");
						int devName = GenericLib.getHeaderColumnIndex(GenericLib.sConfigPath, "config", "Device Name");
						int devVersion = GenericLib.getHeaderColumnIndex(GenericLib.sConfigPath, "config",
								"Device Version");
						int browserType = GenericLib.getHeaderColumnIndex(GenericLib.sConfigPath, "config", "Browser");
						gv.iPort = Integer
								.parseInt(ExcelLibrary.getExcelData(GenericLib.sConfigPath, "config", i, port).trim());
						gv.sUDID = ExcelLibrary.getExcelData(GenericLib.sConfigPath, "config", i, udid).trim();
						gv.sDeviceName = ExcelLibrary.getExcelData(GenericLib.sConfigPath, "config", i, devName).trim();

						gv.sVersion = ExcelLibrary.getExcelData(GenericLib.sConfigPath, "config", i, devVersion).trim();
						gv.browser= ExcelLibrary.getExcelData(GenericLib.sConfigPath, "config", i, browserType).trim();

					}
				}
			} else {

				System.out.println("************PLEASE SELECT ONE DEVICE IN CONFIG******************");
			}
		}
	}

	/*
	 * This method initializes the database variables that requires to connect to
	 * the database before suite
	 */

	@BeforeSuite
    public void before_suite() throws Exception {
		
		
      }
	
	/**
	 * Description : This Function launch the app based on capabilities provided by
	 * testng.xml file
	 * 
	 * @param port
	 * @param UDID
	 * @param version
	 * @param deviceName
	 * @throws Exception
	 */
	@BeforeTest
	public void _LaunchApp() throws Exception {
		
	
		/** Launch App **/
		
		gv.capabilities = new DesiredCapabilities();
		gv.capabilities.setCapability("platformName", "Android");
		gv.capabilities.setCapability("platformVersion",gv.sVersion);
		gv.capabilities.setCapability("deviceName", gv.sDeviceName);
		gv.capabilities.setCapability("UDID", gv.sUDID);
		gv.capabilities.setCapability("appPackage", "com.mmi.maps");
		gv.capabilities.setCapability("appActivity", "com.mmi.maps.ui.activities.HomeScreenActivity");
		gv.capabilities.setCapability("fullReset", false);
		gv.capabilities.setCapability("noReset", false);
		gv.capabilities.setCapability("skipUnlock ", true);
		gv.capabilities.setCapability("appWaitDuration", 180000);
		gv.capabilities.setCapability("deviceReadyTimeout", 20);
		gv.capabilities.setCapability("intentAction", "android.intent.action.MAIN");
		gv.capabilities.setCapability("noSign", true);
		gv.capabilities.setCapability("autoGrantPermissions", true);
		gv.capabilities.setCapability("autoAcceptAlerts", true);
		gv.capabilities.setCapability("newCommandTimeout", 30000);
		System.out.println("http://127.0.0.1:"+gv.iPort+"/wd/hub");
		gv.aDriver = new AndroidDriver(new URL("http://127.0.0.1:"+gv.iPort+"/wd/hub"), gv.capabilities);
		System.out.println("----------------appium driver initialised-------------------");
		MobileActionUtil.waitForIdleTimeout();
		
	}

	@AfterSuite
	public void OracleCloseConnection() throws Exception {
		
		//gv.aDriver.quit();
		System.out.println("----------DB Connection Closed---------");
//			String sDate = MyExtentListners.getTimeStamp();
//			String excelDir = GenericLib.sDirPath + "/Reports" + "/Excel";
//			String fileName = "/Excel_Report_" + sDate + ".xlsx";

		String sFile = GenericLib.sDirPath + "/excelreport.properties";
		String fileName = GenericLib.getProprtyValue(sFile, "excelreport.file.name") + ".xlsx";
		String excelDir = GenericLib.getProprtyValue(sFile, "excelreport.outputdir");
		System.out.println("Excel File Name " + fileName);

	}

}
