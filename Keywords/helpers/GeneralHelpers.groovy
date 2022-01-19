package helpers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import actions.Navigations
import internal.GlobalVariable
import validations.GeneralValidations

public class GeneralHelpers {

	/***
	 * initialise the website
	 * @author wesam hamad
	 * add verification that pageTitle is not empty
	 */
	public static void initScenario() {
		WebUI.openBrowser('');
		WebUI.maximizeWindow()

		Navigations.navigateToHomePage()
		WebUI.waitForPageLoad(GlobalVariable.pageLoadTimeOut)
		GeneralValidations.verifyCurrentPageTitleIsNotEmpty()
		GeneralValidations.verifyCurrentPageTitleValue(GlobalVariable.homePageTitle)
		GeneralValidations.verifyCurrentPageURL(GlobalVariable.baseUrl)
	}
	public static void verifyNavigationToPage(String pageTitle, TestObject header ,String pageHeader, String url) {
		GeneralValidations.verifyCurrentPageTitleValue(pageTitle)
		GeneralValidations.verifyCurrentPageURL(url)
		GeneralValidations.verifyAnyHeading(header, pageHeader)
	}
}
