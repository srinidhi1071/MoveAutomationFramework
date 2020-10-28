
package com.move.androidapp.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.move.androidapp.init.TestDataCoulmns;
import com.move.androidapp.lib.BaseLib;
import com.move.androidapp.lib.GenericLib;
import com.move.androidapp.report.MyExtentListners;
import com.move.androidapp.util.MobileActionUtil;

import io.appium.java_client.android.AndroidDriver;

public class DirectionPage implements TestDataCoulmns {

	// Android driver instace creation
	AndroidDriver driver;

	public DirectionPage(AndroidDriver driver) {

		// Allocate global driver reference to local driver
		this.driver = driver;
		// Initialize Ajax page initialisation
		PageFactory.initElements(driver, this);
	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Direction Screen Page Objects
	 */

	// Current Location Text
	@FindBy(xpath = "//android.widget.TextView[@text='Your current location']")
	private WebElement eleCurrLocTxt;

	// Destination Location Text
	@FindBy(xpath = "(//android.widget.TextView[@resource-id='com.mmi.maps:id/direction_stop_label'])[2]")
	private WebElement eleDestinationTxt;

	// Direction Time Text
	@FindBy(id = "com.mmi.maps:id/direction_time_text_view")
	private WebElement eleDirectionTimeTxt;

	// Direction Distance Text
	@FindBy(id = "com.mmi.maps:id/direction_distance_text_view")
	private WebElement eleDirectionDistTxt;

	// Arrival Time Text
	@FindBy(id = "com.mmi.maps:id/direction_eta_text_view")
	private WebElement eleArrivalTimeTxt;

	// Start Button
	@FindBy(id = "com.mmi.maps:id/start_layout")
	private WebElement eleStartBtn;

	// Direction List Button
	@FindBy(id = "com.mmi.maps:id/direction_list_textview")
	private WebElement eleDirectionListBtn;

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Methods to verify Destination Location
	 *
	 */

	public void verifyDestinationLocation(String sTestCaseId, AndroidDriver driver) throws Exception {

		String[] sData = GenericLib.toReadExcelDataWithNull(GenericLib.sTestDataPath, SEARCH_SHEET, sTestCaseId);

		MobileActionUtil.waitForElement(eleCurrLocTxt, driver, " Your current location", 10);

		String actCurrLoc = MobileActionUtil.gettext(eleCurrLocTxt, driver, " Your current location ");
		String actDestLoc = MobileActionUtil.gettext(eleDestinationTxt, driver, " Destination Location ");

		MobileActionUtil.verifyEqualsText(" Current Place ", actCurrLoc.trim(), "Your current location");
		MobileActionUtil.verifyEqualsText(" Destination Place ", actDestLoc.trim(), sData[BaseLib.gv.iPlace].trim());

	}
	
	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Methods to validate Searched Place
	 *
	 */

	public void verifyDirectionScreen(String sTestCaseId, AndroidDriver driver) throws Exception {

		String[] sData = GenericLib.toReadExcelDataWithNull(GenericLib.sTestDataPath, SEARCH_SHEET, sTestCaseId);

		MobileActionUtil.waitForElement(eleStartBtn, driver, " Start Button", 10);
		
		String destTime=MobileActionUtil.gettext(eleDirectionTimeTxt, driver, " Direction Time ");
		String dirDistance=MobileActionUtil.gettext(eleDirectionDistTxt, driver, " Direction Distance Kilometer ");
		String arrivalTime=MobileActionUtil.gettext(eleArrivalTimeTxt, driver, " Arriaval Time ");
		
		MyExtentListners.test.info(destTime + " is the destination time");
		MyExtentListners.test.info(dirDistance + " is the Distance in Kilometer ");
		MyExtentListners.test.info(arrivalTime + " is the Arriaval time");
		
		MobileActionUtil.clickElement(eleStartBtn, driver, " Start Button ");
		
	}
	
	
}
