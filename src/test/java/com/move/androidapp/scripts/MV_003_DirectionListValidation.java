package com.move.androidapp.scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.move.androidapp.init.InitializePages;
import com.move.androidapp.init.TestDataCoulmns;
import com.move.androidapp.lib.BaseLib;
import com.move.androidapp.report.MyExtentListners;

public class MV_003_DirectionListValidation extends BaseLib  implements TestDataCoulmns{

	
	
	@Test(enabled=true,priority=1,description=" Test case : Login As Guest User and Search Address and Validate Direction List")
	
	public void tc_003_testGuestSearchDirection() throws Exception{
		// assign category
		MyExtentListners.test.assignCategory("Guest-Login-Direction");
		// write testcase name in report
		MyExtentListners.test.info(MarkupHelper.createLabel(
				" Test case : Login As Guest User", ExtentColor.AMBER));
		//initialise the page class
		InitializePages init=new InitializePages(gv.aDriver);
		// Navigate to Login Screen
		init.directionListPage.validateStartAndDestLocInNavigation("MV_001", gv.aDriver);
		init.navigationPage.validateDirectionStripText(init.directionListPage.dirList, gv.aDriver);
	}
	
	@AfterMethod
	public void reset(){
		//gv.aDriver.resetApp();
	}
	
}
