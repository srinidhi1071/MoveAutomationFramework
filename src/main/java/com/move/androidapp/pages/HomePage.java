
package com.move.androidapp.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.move.androidapp.init.TestDataCoulmns;
import com.move.androidapp.lib.BaseLib;
import com.move.androidapp.lib.GenericLib;
import com.move.androidapp.util.MobileActionUtil;

import io.appium.java_client.android.AndroidDriver;

public class HomePage implements TestDataCoulmns {

	// Android driver instace creation
	AndroidDriver driver;

	public HomePage(AndroidDriver driver) {

		// Allocate global driver reference to local driver
		this.driver = driver;
		// Initialize Ajax page initialisation
		PageFactory.initElements(driver, this);
	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Home Screen Page Objects
	 */

	// Okay Button
	@FindBy(id = "com.mmi.maps:id/ok_btn")
	private WebElement eleOkayBtn;

	// Home Search Text Box
	@FindBy(id = "com.mmi.maps:id/fragment_home_search_txt")
	private WebElement eleHomeSearchTxtBx;

	// Home Search Icon
	@FindBy(id = "com.mmi.maps:id/fragment_home_voice_search")
	private WebElement eleHomeSearchIcn;

	// AR button
	@FindBy(id = "com.mmi.maps:id/ar_button")
	private WebElement eleARBtn;

	// Current Location Button
	@FindBy(id = "com.mmi.maps:id/current_location_button")
	private WebElement eleCurrLocBtn;

	// Direction Button
	@FindBy(id = "com.mmi.maps:id/direction_button")
	private WebElement eleDirLocBtn;

	// Search Auto List Item
	@FindBys({ @FindBy(id = "com.mmi.maps:id/auto_list_icon") })
	private List<WebElement> eleSeachAutoIconLst;

	// Search Auto List Item
	@FindBys({ @FindBy(id = "com.mmi.maps:id/auto_list_item") })
	private List<WebElement> eleSearchAutoList;

	// Search Auto List Item Address
	@FindBys({ @FindBy(id = "com.mmi.maps:id/auto_list_item_address") })
	private List<WebElement> eleSearchAutoItemAddLst;

	// Search Auto List Item Address
	@FindBys({ @FindBy(id = "com.mmi.maps:id/text_view_distance") })
	private List<WebElement> eleDistanceLst;

	// Allow Button
	@FindBy(id = "com.android.packageinstaller:id/permission_allow_button")
	private WebElement eleAllowBtn;

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Methods to validate existing/created customer in farmers
	 * screen
	 * 
	 * @Action: Enter username and password , click on signin button
	 */

	public void searchAddress(String sTestCaseId, AndroidDriver driver) throws Exception {

		String[] sData = GenericLib.toReadExcelDataWithNull(GenericLib.sTestDataPath, SEARCH_SHEET, sTestCaseId);
		String placeName = "";
		String address = "";
		String distance = "";

		if (MobileActionUtil.isEleDisplayed(eleOkayBtn, driver, 2, 1)) {

			MobileActionUtil.clickElement(eleOkayBtn, driver, " Okay Button");
			MobileActionUtil.clickElement(eleOkayBtn, driver, " Okay Button");
			MobileActionUtil.clickElement(eleAllowBtn, driver, " Allow Button");
		}
		
		//MobileActionUtil.isImageExist(" Current Location Marker", GenericLib.sDirPath + "/images/currlocimg.png");
		MobileActionUtil.type(eleHomeSearchTxtBx, sData[BaseLib.gv.iPlace], sData[BaseLib.gv.iPlace], driver);
		MobileActionUtil.waitForElement(eleSearchAutoList.get(0), driver, " Searched Place Auto Suggestions", 10);
		for (int i = 0; i < eleSearchAutoList.size(); i++) {

			placeName = MobileActionUtil.gettext(eleSearchAutoList.get(i), driver, " Place");

			if (sData[BaseLib.gv.iPlace].trim().equalsIgnoreCase(placeName.trim())) {

				address = MobileActionUtil.gettext(eleSearchAutoItemAddLst.get(i), driver, "Address");
				distance = MobileActionUtil.gettext(eleDistanceLst.get(i), driver, "Distance");
				MobileActionUtil.verifyEqualsText(" Searched Place ", placeName.trim(),
						sData[BaseLib.gv.iPlace].trim());
				MobileActionUtil.verifyEqualsText(" Searched Place ", address.trim(),
						sData[BaseLib.gv.iAddress].trim());
				MobileActionUtil.verifyEqualsText(" Searched Place ", distance.trim(),
						sData[BaseLib.gv.iDistance].trim());
				MobileActionUtil.clickElement(eleSearchAutoList.get(i), driver, placeName);
				break;
			}

		}

	}

}
