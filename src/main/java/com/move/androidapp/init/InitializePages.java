package com.move.androidapp.init;

import com.move.androidapp.pages.DirectionListsPage;
import com.move.androidapp.pages.DirectionPage;
import com.move.androidapp.pages.HomePage;
import com.move.androidapp.pages.LoginPage;
import com.move.androidapp.pages.NavigationPage;
import com.move.androidapp.pages.OnBoardingPage;

import io.appium.java_client.android.AndroidDriver;

public class InitializePages {

	/** APP CLASSES **/

	public OnBoardingPage onboarding=null;
	public LoginPage loginPage=null;
	public HomePage homePage=null;
	public NavigationPage navigationPage=null;
	public DirectionPage directionPage=null;
	public DirectionListsPage directionListPage=null;
	
	
	/** APP CLASSES INITIALISATION**/
	
	
	public InitializePages(AndroidDriver driver) {

		loginPage=new LoginPage(driver);
		onboarding=new OnBoardingPage(driver);
		homePage=new HomePage(driver);
		navigationPage=new NavigationPage(driver);
		directionPage=new DirectionPage(driver);
		directionListPage=new DirectionListsPage(driver);
	}
	
}
