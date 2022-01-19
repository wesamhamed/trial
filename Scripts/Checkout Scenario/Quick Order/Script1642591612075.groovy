import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import helpers.GeneralHelpers
import helpers.QuickOrderHelpers
import internal.GlobalVariable
import validations.QuickOrderValidations

import org.openqa.selenium.Keys as Keys

GeneralHelpers.initScenario()

//-------------------quick order------------------------
QuickOrderHelpers.navigationAndVerifyToQuickOrder()
QuickOrderValidations.verifyCartCounterAndStyle("0")

//--------------filling product stock number inputs ----------
QuickOrderHelpers.headerAndTableStyle()
QuickOrderHelpers.fillingVerifyingStockNo()

//---------------filling quantity inputs and verify total----------

QuickOrderHelpers.visibilityOfProductDetails()
QuickOrderHelpers.fillingQuanitiesInputs()

//------------------store 5 products details & verify the total is right ----------------

QuickOrderHelpers.storeProductsDetailsAndVerifyTotal()

//-----------------navigation to shopping cart and verify it--------------------------------
QuickOrderHelpers.navigationAndVerifyingShoppingCart()

//-----------------verify cart counter and cart total same with products table----------

QuickOrderValidations.verifyCartCounterAndStyle("5")
QuickOrderHelpers.verifyCartTotalAndRowsNo(5)

//-----------------verify cart counter and cart total inside mini cart ---------------

QuickOrderHelpers.hoverOnMiniCartHeader()



WebUI.closeBrowser()

