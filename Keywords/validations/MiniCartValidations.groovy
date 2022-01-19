package validations

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import org.openqa.selenium.WebElement

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

import internal.GlobalVariable

public class MiniCartValidations {
	/**
	 * Verify hover effect style on mini cart link
	 * @author wesam hamad
	 */
	public static void verifyHoverStyleOnMiniCartLink() {
		TestObject miniCart = findTestObject('Mini Cart/a_miniCartLink')
		assert WebUI.getCSSValue(miniCart, 'background-color').equals(GlobalVariable.whiteColor)

		TestObject cartIcon = findTestObject('Mini Cart/i_cartIcon')
		assert WebUI.getCSSValue(miniCart, 'color').equals(GlobalVariable.purpleColor)
	}
	/**
	 * Verify miniCart total price
	 * @author wesam hamad
	 */
	public static void verifyMiniCartTotals() {
		List<WebElement> prodPrice = WebUI.findWebElements(findTestObject('Mini Cart/span_miniCartProductPrice'), 1)
		List<WebElement> prodQyt = WebUI.findWebElements(findTestObject('Mini Cart/td_miniCartTableQuantityTableData'), 1)
		List<WebElement> prodTotal = WebUI.findWebElements(findTestObject('Mini Cart/td_miniCartTotalPrice'), 1)

		Float totalOfTotal = 0

		for(int idx = 0; idx < prodPrice.size(); idx++) {
			String prc = prodPrice.get(idx).getAttribute('innerText').replaceAll("[^0-9\\.]","");
			String qt = prodQyt.get(idx).getAttribute('innerText').replaceAll("[^0-9\\.]","");
			String total = prodTotal.get(idx).getAttribute('innerText').replaceAll("[^0-9\\.]","");

			totalOfTotal += Float.parseFloat(total)

			assert total.contains(String.format("%.2f", (Float.parseFloat(prc) * Integer.parseInt(qt))))
		}

		TestObject miniCartTotal = findTestObject('Mini Cart/span_miniCartSubTotal')
		assert WebUI.getText(miniCartTotal).replace('$', '').replace(',', '').contains(String.format("%.2f", totalOfTotal))
	}
	/**
	 * Verify mini cart items count
	 * @author wesam hamad
	 */
	public static void miniCartItemCount() {
		List<WebElement> productRow = WebUI.findWebElements(findTestObject('Mini Cart/tr_miniCartTableRow'), 1)
		TestObject counterBadge = findTestObject('Mini Cart/span_cartCounterPadge')

		assert WebUI.getText(counterBadge).contains(productRow.size().toString())
	}
}
