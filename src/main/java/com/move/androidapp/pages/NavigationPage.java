
package com.move.androidapp.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.move.androidapp.init.TestDataCoulmns;
import com.move.androidapp.lib.BaseLib;
import com.move.androidapp.lib.GenericLib;
import com.move.androidapp.report.MyExtentListners;
import com.move.androidapp.util.MobileActionUtil;

import io.appium.java_client.android.AndroidDriver;

public class NavigationPage implements TestDataCoulmns {

	// Android driver instace creation
	AndroidDriver driver;

	public NavigationPage(AndroidDriver driver) {

		// Allocate global driver reference to local driver
		this.driver = driver;
		// Initialize Ajax page initialisation
		PageFactory.initElements(driver, this);
	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Navigation Screen Page Objects
	 */

	// Searched Place Name Text
	@FindBy(id = "com.mmi.maps:id/place_name")
	private WebElement eleSearchedPlaceNameTxt;

	// Searched Address Text
	@FindBy(id = "com.mmi.maps:id/place_address")
	private WebElement eleSearchedPlaceAddressTxt;

	// Place ELocation
	@FindBy(id = "com.mmi.maps:id/place_eloc")
	private WebElement eleSearchedPlaceELocLnk;

	// Direction Button
	@FindBy(id = "com.mmi.maps:id/place_directions_button")
	private WebElement eleDirectionBtn;

	// Navigation Strip Right Image Button
	@FindBy(id = "com.mmi.maps:id/navigation_strip_right_image_button")
	private WebElement eleNavigationRightBtn;

	// Navigation Strip Text
	@FindBy(id = "com.mmi.maps:id/navigation_strip_text")
	private WebElement eleNavigationStripTxt;

	// Navigation Strip Text
	@FindBy(id = "com.mmi.maps:id/centerButto")
	private WebElement eleRecenterBtn;

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Methods to validate Searched Place
	 *
	 */

	public void validateSearchedPlace(String sTestCaseId, AndroidDriver driver) throws Exception {

		String[] sData = GenericLib.toReadExcelDataWithNull(GenericLib.sTestDataPath, SEARCH_SHEET, sTestCaseId);

		MobileActionUtil.waitForElement(eleDirectionBtn, driver, " Direction Button", 10);
		MobileActionUtil.waitForElement(eleSearchedPlaceNameTxt, driver, "  Place Name", 10);
		MobileActionUtil.waitForElement(eleSearchedPlaceNameTxt, driver, " Address Text", 10);
		
		
		String actPlaceName = MobileActionUtil.gettext(eleSearchedPlaceNameTxt, driver, " Place Name");
		String actAddress = MobileActionUtil.gettext(eleSearchedPlaceAddressTxt, driver, " Address Text");

		MobileActionUtil.verifyEqualsText(" Searched Place ", actPlaceName.trim(), sData[BaseLib.gv.iPlace].trim());
		MobileActionUtil.verifyEqualsText(" Searched Address ", actAddress.trim(), sData[BaseLib.gv.iAddress].trim());

	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Method to click on direction button to start navigation
	 *
	 */

	public void clickOnDirectionBtn(AndroidDriver driver) throws Exception {

		if (MobileActionUtil.isEleDisplayed(eleSearchedPlaceELocLnk, driver, 2, 2)) {

			MobileActionUtil.isEleDisplayed(eleSearchedPlaceELocLnk, driver, eleSearchedPlaceELocLnk.getText().trim());
		}

		MobileActionUtil.clickElement(eleDirectionBtn, driver, " Direction Button");

	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Method to validate directions strip text
	 *
	 */

	public void validateDirectionStripText(Set<String> dirList, AndroidDriver driver) throws Exception {

		List<String> list = new ArrayList<String>();

		// push each element in the set into the list
		for (String t : dirList) {
			list.add(t);
		}

		for (int i = 1; i < list.size(); i++) {

			String currRoute = MobileActionUtil.gettext(eleNavigationStripTxt, driver, " Next Navigation Strip ");
			MobileActionUtil.verifyEqualsText(" Next Navigation Strip", currRoute.trim(), list.get(i).toString());
			MobileActionUtil.clickElement(eleNavigationRightBtn, driver, " Navigation Strip Next Button ");
		}
		
		
		if(MobileActionUtil.isEleDisplayed(eleRecenterBtn, driver, 1, 1)){
			
		}else{
			MyExtentListners.test.fail(MarkupHelper.createLabel("Current Location is not Your Starting From Here", ExtentColor.RED));
			MyExtentListners.test.addScreenCaptureFromPath(MobileActionUtil.capture(driver, " Re-center Button"));
			Assert.fail();
		}
		//MobileActionUtil.clickElement(eleRecenterBtn, driver, " Recenter Button");
		/*String currRoute = MobileActionUtil.gettext(eleNavigationStripTxt, driver, " Next Navigation Strip ");
		MobileActionUtil.verifyEqualsText(" Re-Centered Location", currRoute.trim(), " Your Location Starts Here");
		MyExtentListners.test.addScreenCaptureFromPath(MobileActionUtil.capture(driver, " Re-center Location"));*/

	}

}
