
package com.move.androidapp.pages;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.move.androidapp.init.TestDataCoulmns;
import com.move.androidapp.lib.BaseLib;
import com.move.androidapp.lib.GenericLib;
import com.move.androidapp.util.MobileActionUtil;

import io.appium.java_client.android.AndroidDriver;

public class DirectionListsPage implements TestDataCoulmns {

	// Android driver instace creation
	AndroidDriver driver;
	public Set<String> dirList=new LinkedHashSet<String>();

	public DirectionListsPage(AndroidDriver driver) {

		// Allocate global driver reference to local driver
		this.driver = driver;
		// Initialize Ajax page initialisation
		PageFactory.initElements(driver, this);
	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Direction Lists Screen Page Objects
	 */

	// Bottom Sheet Controller Button
	@FindBy(id = "com.mmi.maps:id/bottom_sheet_controller_button")
	private WebElement eleBottomSheetControllerBtn;

	// Direction List Img
	@FindBy(xpath = "//android.widget.TextView[@text='Direction List']/..")
	private WebElement eleDirectionListImg;

	// Your Current Location Starting from here Text
	@FindBy(xpath = "//android.widget.TextView[@text='Starting from here']/..//android.widget.TextView[@text='Your current location']")
	private WebElement eleStratingLocTxt;

	// Destination Arrive
	@FindBy(xpath = "//android.widget.TextView[@text='You will arrive at your destination']")
	private WebElement eleDestinationLocTxt;

	// Direction Item Text List

	@FindBys({
		
		@FindBy(id = "com.mmi.maps:id/item_direction_Text")
	})
	private List<WebElement> eleDirectionListTextsLst;
	
	// Destination Arrive Place
	@FindBy(xpath = "//android.widget.TextView[@text='You will arrive at your destination']/..//android.widget.TextView[1]")
	private WebElement eleDestinationLocPlaceTxt;

	// Destination Arrive Place
	@FindBy(id = "com.mmi.maps:id/save_offline_textview")
	private WebElement eleShowOnMapBtn;

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Methods to verify Destination Location
	 *
	 */

	public void validateStartAndDestLocInNavigation(String sTestCaseId, AndroidDriver driver) throws Exception {

		String[] sData = GenericLib.toReadExcelDataWithNull(GenericLib.sTestDataPath, SEARCH_SHEET, sTestCaseId);

		MobileActionUtil.waitForElement(eleBottomSheetControllerBtn, driver, " Bottom Sheet Controller Button", 10);
		MobileActionUtil.clickElement(eleBottomSheetControllerBtn, driver, " Bottom Sheet Controller Button ");
		MobileActionUtil.clickElement(eleDirectionListImg, driver, " Direction List Image ");
		MobileActionUtil.isEleDisplayed(eleStratingLocTxt, driver, " Starting From Here | Your Current Location ");

		
		boolean flag = true;
		int i = 1;
		while (flag) {

			for(int j=0;j<eleDirectionListTextsLst.size();j++){
				
				dirList.add(eleDirectionListTextsLst.get(j).getText().trim());
			}
			if (MobileActionUtil.isEleDisplayed(eleDestinationLocTxt, driver, 1, 1)) {

				String actDestLocPlaceTxt = MobileActionUtil.gettext(eleDestinationLocPlaceTxt, driver,
						" Arrive At Your Destination ");
				MobileActionUtil.verifyEqualsText("You Will Arrive At Your Destination ", actDestLocPlaceTxt.trim(),
						sData[BaseLib.gv.iPlace].trim());
				flag = false;
				break;
			} else {
				
				MobileActionUtil.swipeBottomToTop(1, driver, .70, .30);
				
			}

		}
		Iterator<String> itr=dirList.iterator();
		while (itr.hasNext()) {
			
			System.out.println(itr.next().toString());
		}
		
		MobileActionUtil.clickElement(eleShowOnMapBtn, driver, " Show On Map Button");

	}
	
	

}
