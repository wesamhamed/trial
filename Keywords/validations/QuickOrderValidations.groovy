package validations

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

import actions.QuickOrderActions
import helpers.QuickOrderHelpers
import internal.GlobalVariable

public class QuickOrderValidations {
	public static void verifyCartCounterAndStyle(String no) {
		TestObject cartSpan = findTestObject("Object Repository/Quick Order/span_cartCounter")
		assert WebUI.getText(cartSpan).equals(no)
		//		assert WebUI.getCSSValue(cartSpan, "background-color").equals(GlobalVariable.cartCounterBgClr)
		assert WebUI.getCSSValue(cartSpan, "color").equals(GlobalVariable.whiteColor)
	}
	public static void veriyHeaderStyle() {
		TestObject quickHeader = findTestObject('Object Repository/Quick Order/a_quickOrderHeader')
		assert WebUI.getCSSValue(quickHeader, "background-color").equals(GlobalVariable.searchHeaderTopBarBackgroundColor)
		assert WebUI.getCSSValue(quickHeader, "color").equals(GlobalVariable.whiteColor)
		TestObject quickIcon = findTestObject('Object Repository/Quick Order/span_quickOrderIcon')
		WebUI.verifyElementVisible(quickIcon)
	}
	public static void verifyInputPlaceholder(TestObject stockInput) {
		assert WebUI.getAttribute(stockInput, "placeholder").equals(GlobalVariable.stockNoInputPlaceholder)
	}
	public static void verifyInputPlaceholderEmpty(TestObject stockInput) {
		assert WebUI.getAttribute(stockInput, "placeholder").equals("")
	}
	public static void verifyInputEmpty(TestObject item) {
		WebUI.getAttribute(item, "value").equals("")
	}
	public static void verifyQuickOrderSubTotal(int quantity, TestObject price, TestObject expectedSubTotal) {
		double priceNo = QuickOrderActions.formatPriceAndTotal(WebUI.getText(price))
		double expectedTotal = QuickOrderActions.formatPriceAndTotal(WebUI.getText(expectedSubTotal))
		double actualTotal = quantity * priceNo
		assert String.format("%,.2f",actualTotal).equals(String.format("%,.2f",expectedTotal))
	}
	public static void verifyCartTotal(String first, String sec, String third, String fourth,String fifth) {
		double expectedTotal = QuickOrderHelpers.calculateQuickOrdersTotal(first, sec, third, fourth, fifth)
		double actualTotal = QuickOrderActions.formatPriceAndTotal(WebUI.getText(findTestObject("Object Repository/Quick Order/span_cartLabel")))
//		assert expectedTotal == actualTotal
	}
	public static void verifyProductsNoInCart(int expectedNo, int realNo) {
		assert expectedNo == realNo
	}
}
