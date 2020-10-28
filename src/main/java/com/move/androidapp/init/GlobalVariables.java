package com.move.androidapp.init;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.move.androidapp.lib.GenericLib;
import com.paulhammant.ngwebdriver.NgWebDriver;

import io.appium.java_client.android.AndroidDriver;

public class GlobalVariables implements TestDataCoulmns{

	public AndroidDriver aDriver;
	public DesiredCapabilities capabilities;
	public String browser;
	public int iPort;
	public String sUDID;
	public String sVersion;
	public String sDeviceName;
	public String sAutomationName;
	public String sChillingCenter;

	// Property Files Values
	public String sManager_Username=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "MANAGER_USERNAME");
	public String sManager_Password=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "MANAGER_PASSWORD");
	public String sOperator_Username=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "OPERATOR_USERNAME");
	public String sOperator_Password=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "OPERATOR_PASSWORD");
	public String sChilling_Center=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "CHILLING_CENTER");
	public String sCollection_Center=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "COLLECTION_CENTER");
	public String sSocietyCode=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "SOCIETY_CODE");
	
	
	
	// Portal Credentials 
	
	public String sPortal_Url=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "PORTAL_URL");
	public String sPortal_Username=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "PORTAL_USERNAME");
	public String sPortal_Password=GenericLib.getProprtyValue(GenericLib.sUserCredFile, "PORTAL_PASSWORD");
	
	// Search Sheet Columns
	 public int iPlace=GenericLib.getColumnIndex(GenericLib.sTestDataPath, SEARCH_SHEET, PLACE);
	 public int iAddress=GenericLib.getColumnIndex(GenericLib.sTestDataPath, SEARCH_SHEET, ADDRESS);
	 public int iDistance=GenericLib.getColumnIndex(GenericLib.sTestDataPath, SEARCH_SHEET, DISTANCE);

	
	
}
