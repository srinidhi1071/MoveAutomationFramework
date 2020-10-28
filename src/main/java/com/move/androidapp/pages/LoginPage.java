
package com.move.androidapp.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.move.androidapp.util.MobileActionUtil;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage {

	// Android driver instace creation
	AndroidDriver driver;

	public LoginPage(AndroidDriver driver) {

		// Allocate global driver reference to local driver
		this.driver = driver;
		// Initialize Ajax page initialisation
		PageFactory.initElements(driver, this);
	}

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Login Screen Page Objects
	 */

	// Facebook Container
	@FindBy(id = "com.mmi.maps:id/container_facebook")
	private WebElement eleFacebbokCtnr;

	// Google Container
	@FindBy(id = "com.mmi.maps:id/container_google")
	private WebElement eleGoogleCtnr;

	// Skip Login Button
	@FindBy(id = "com.mmi.maps:id/text_view_skip_login")
	private WebElement eleSkipLoginBtn;

	// Login With Email Tab
	@FindBy(id = "com.mmi.maps:id/btn_login_with_email")
	private WebElement eleLoginWithEmailIdTab;

	// Login With Mobile Number Tab
	@FindBy(id = "com.mmi.maps:id/btn_login_with_mobile")
	private WebElement eleLoginWithMobileNoTab;

	// Forgot Pass Link Button
	@FindBy(id = "com.mmi.maps:id/text_view_forgot_pass")
	private WebElement eleForgotPasswordLnkBtn;

	// Forgot Pass Link Button
	@FindBy(id = "com.mmi.maps:id/btn_iot_activation")
	private WebElement eleActivateDeviceBtn;

	

	/*
	 * @author:Srinivas Hippargi
	 * 
	 * Description: Method to erify login Page components are displayed
	 * 
	 * @Action: Verify Facebook Container, Google Container, Skip Button, Login
	 * With Email/Mobile Tab
	 */

	public void _verifyLoginScreen(AndroidDriver driver) throws Exception {

		MobileActionUtil.isEleDisplayed(eleFacebbokCtnr, driver, " Facebook Container");
		MobileActionUtil.isEleDisplayed(eleGoogleCtnr, driver, " Google Container");
		MobileActionUtil.isEleDisplayed(eleSkipLoginBtn, driver, " Skip Button");
		MobileActionUtil.isEleDisplayed(eleLoginWithEmailIdTab, driver, " Login With Email Tab");
		MobileActionUtil.isEleDisplayed(eleLoginWithMobileNoTab, driver, " Login With Mobile Tab");
		MobileActionUtil.isEleDisplayed(eleForgotPasswordLnkBtn, driver, " Forgot Password Link Button");
		MobileActionUtil.isEleDisplayed(eleActivateDeviceBtn, driver, " Activate Device Button");
		MobileActionUtil.clickElement(eleSkipLoginBtn, driver, " Skip Button");
	}

}
