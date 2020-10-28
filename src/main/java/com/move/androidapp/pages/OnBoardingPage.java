
package com.move.androidapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.move.androidapp.util.MobileActionUtil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class OnBoardingPage {

	// Android driver instace creation
	AndroidDriver driver;

	public OnBoardingPage(AndroidDriver driver) {

		// Allocate global driver reference to local driver
		this.driver = driver;
		// Initialize Ajax page initialisation
		PageFactory.initElements(driver, this);
	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: OnBoarding Screen Page Objects
	 */

	// OnBoarding Next Button
	@FindBy(id = "com.mmi.maps:id/activity_onboarding_btn_next")
	private WebElement eleOnBrdNextBtn;

	// Login Home Heading Text
	@FindBy(id = "	com.mmi.maps:id/heading_login_register")
	private WebElement eleLoginHeaderTxt;

	
	
	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Methods to navigate to the Login Screen
	 * 
	 * @Action: Click on Next Button Until Reaches the Login Screen
	 */

	public void navigateToLoginScreen(AndroidDriver driver) throws Exception {
		
		while ((MobileActionUtil.isEleDisplayed(eleOnBrdNextBtn, driver, 2, 1))) {

			MobileActionUtil.clickElement(eleOnBrdNextBtn, driver, " OnBoarding Next Button");
		}
	}

}
