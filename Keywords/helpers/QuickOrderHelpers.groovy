package helpers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.text.DecimalFormat

import org.openqa.selenium.Keys
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

import actions.GeneralActions
import actions.MiniCartActions
import actions.QuickOrderActions
import internal.GlobalVariable
import validations.GeneralValidations
import validations.MiniCartValidations
import validations.QuickOrderValidations

public class QuickOrderHelpers {
	
	public static List<Integer> cartItem = new ArrayList<>();
	
		public static List<WebElement> quantities;
		public static List<WebElement> titles;
		public static List<WebElement> prices;
		public static List<WebElement> totals;
		public static List<WebElement> stocksNotify;
		public static List<WebElement> images;
		public static List<WebElement> sku;
		public static List<QuickOrder1> products;
	/***
	 * navigation to quick order page: verify visibility, hover link, verify style, click the link
	 * @author wesam hamad
	 */
	public static void navigateToQuickOrderPage() {
		TestObject quickOrderLink = findTestObject("Object Repository/Quick Order/a_quickOrder")
		WebUI.verifyElementVisible(quickOrderLink)
		GeneralActions.hoverItem(quickOrderLink)
		GeneralValidations.verifyColorChangeOnHover(findTestObject("Object Repository/Quick Order/i_quickOrderIcon"),GlobalVariable.quickOrderIconColor)
		QuickOrderActions.clickQuickOrderLink()
	}
	
	/***
	 * navigation to quick order page, verify url, title, header
	 * @author wesam hamad
	 */
	public static void navigationAndVerifyToQuickOrder() {
		QuickOrderHelpers.navigateToQuickOrderPage()

		TestObject quickOrderHeader = findTestObject("Object Repository/Quick Order/span_quickOrderHeader")
		GeneralHelpers.verifyNavigationToPage(GlobalVariable.quickOrderPageTitle, quickOrderHeader,
				GlobalVariable.quickOrderHeader, GlobalVariable.quickOrderUrl)
	}
	/***
	 * verify quick order header style and non visibility of addToCart Button
	 * @author wesam hamad
	 */
	public static void headerAndTableStyle() {
		QuickOrderValidations.veriyHeaderStyle()
		TestObject quickOrderTableRows = findTestObject("Object Repository/Quick Order/tr_allQuickOrderRows")

		GeneralValidations.verifyBackgroundColor(quickOrderTableRows,GlobalVariable.grayClr)
		TestObject addToCartBtn = findTestObject("Object Repository/Quick Order/button_addToCart")
		WebUI.verifyElementNotPresent(addToCartBtn, GlobalVariable.globalTimeOut)

	}
	/***
	 * filling stock number and verify value is reflected
	 * @author wesam hamad
	 */
	public static void fillingVerifyingStockNo() {
		TestObject firstStockNo = findTestObject("Object Repository/Quick Order/input_0quickOrderStock")
		WebUI.waitForElementPresent(firstStockNo, 5)
		TestObject secStockNo = findTestObject("Object Repository/Quick Order/input_1quickOrderStock")
		TestObject thirdStockNo = findTestObject("Object Repository/Quick Order/input_2quickOrderStock")
		TestObject fourthStockNo = findTestObject("Object Repository/Quick Order/input_3quickOrderStock")
		TestObject fivthStockNo = findTestObject("Object Repository/Quick Order/input_4quickOrderStock")

		QuickOrderHelpers.fillStockNoQuickOrder(firstStockNo, GlobalVariable.firstStockNo)
		TestObject addToCartBtn = findTestObject("Object Repository/Quick Order/button_addToCart")
		WebUI.verifyElementVisible(addToCartBtn)
		QuickOrderHelpers.fillStockNoQuickOrder(secStockNo, GlobalVariable.secStockNo)
		QuickOrderHelpers.fillStockNoQuickOrder(thirdStockNo, GlobalVariable.thirdStockNo)
		QuickOrderHelpers.fillStockNoQuickOrder(fourthStockNo, GlobalVariable.fourthStockNo)
		QuickOrderHelpers.fillStockNoQuickOrder(fivthStockNo, GlobalVariable.fifthStockNo)
	}
	/***
	 * filling stock number, verify placeholder, focus the input, verify it's style, reflected value
	 * @author wesam hamad
	 */
	public static void fillStockNoQuickOrder(TestObject stockInput, String value) {
		QuickOrderValidations.verifyInputPlaceholder(stockInput)
		QuickOrderValidations.verifyInputEmpty(stockInput)
		GeneralActions.focusItem(stockInput)
		//	assert WebUI.verifyEqual(WebUI.getCSSValue(stockInput, 'box-shadow'), GlobalVariable.checkOutInputShadow)
		//	assert WebUI.verifyEqual(WebUI.getCSSValue(stockInput, 'border-color'), GlobalVariable.checkOutinputborderColor)
		WebUI.sendKeys(stockInput, value+ Keys.ENTER)
		GeneralValidations.verifyInputValue(stockInput, value)
	}
	
	/***
	 * verify visibility of Product Details when filling stock number
	 * @author wesam hamad
	 */
	public static void visibilityOfProductDetails() {
		QuickOrderHelpers.getProductsSelectorsFromTable()

		for(int i=0; i<=4; i++) {
			TestObject Title = WebUI.convertWebElementToTestObject(titles[i])
			TestObject StocksNotify = WebUI.convertWebElementToTestObject(stocksNotify[i])
			TestObject Imgs = WebUI.convertWebElementToTestObject(images[i])
			TestObject Prices = WebUI.convertWebElementToTestObject(prices[i])

			WebUI.verifyElementPresent(Title, GlobalVariable.elementVisibilityTimeOut)
			WebUI.verifyElementPresent(Imgs, GlobalVariable.elementVisibilityTimeOut)
			WebUI.verifyElementPresent(Prices, GlobalVariable.elementVisibilityTimeOut)
			WebUI.verifyElementText(StocksNotify, "In Stock!")
		}
	}
	/***
	 * get Products Selectors From quick order Table
	 * @author wesam hamad
	 */
	public static void getProductsSelectorsFromTable() {
		quantities = WebUI.findWebElements(findTestObject("Object Repository/Quick Order/input_quantities"),GlobalVariable.webElementTimeOut)
		titles = WebUI.findWebElements(findTestObject("Object Repository/Quick Order/a_quickOrderTitle"),GlobalVariable.webElementTimeOut)
		prices = WebUI.findWebElements(findTestObject("Object Repository/Quick Order/div_quickOrderPrice"),GlobalVariable.webElementTimeOut)
		totals = WebUI.findWebElements(findTestObject("Object Repository/Quick Order/span_quickOrderTotal"),GlobalVariable.webElementTimeOut)
		stocksNotify = WebUI.findWebElements(findTestObject("Object Repository/Quick Order/div_quickOrderStock"),GlobalVariable.webElementTimeOut)
		images = WebUI.findWebElements(findTestObject("Object Repository/Quick Order/img_quickOrderProductImg"),GlobalVariable.webElementTimeOut)
		sku = WebUI.findWebElements(findTestObject("Object Repository/Quick Order/input_tabkeSku"),GlobalVariable.webElementTimeOut)
	}
	/***
	 * filling Quantity inputs with random value for each product
	 * @author wesam hamad 
	 */
	public static void fillingQuanitiesInputs() {
		for(int i=0; i<=4; i++) {
			TestObject Quantity = WebUI.convertWebElementToTestObject(quantities[i])
			QuickOrderHelpers.fillQuantityQuickOrder(Quantity)
		}
	}
	/***
	 * filling Quantity input with random value, verify reflected value
	 * @author wesam hamad
	 */
	public static void fillQuantityQuickOrder(TestObject quantityInput) {
		int randomQuantity = (int) (Math.random() * 50 + 1)
		WebUI.sendKeys(quantityInput, Keys.chord(Keys.BACK_SPACE) +randomQuantity+Keys.TAB )
		GeneralValidations.verifyInputValue(quantityInput, randomQuantity.toString())
	}
	/***
	 * store added Products Details on list And Verify Total is right
	 * @author wesam hamad
	 */
	public static void storeProductsDetailsAndVerifyTotal() {
		products = new ArrayList<QuickOrder1>();
		for(int i=0; i<=4; i++) {
			TestObject Quantity = WebUI.convertWebElementToTestObject(quantities[i])
			TestObject Price = WebUI.convertWebElementToTestObject(prices[i])
			TestObject Total = WebUI.convertWebElementToTestObject(totals[i])
			TestObject Title = WebUI.convertWebElementToTestObject(titles[i])
			TestObject Img = WebUI.convertWebElementToTestObject(images[i])
			TestObject StocksNotify = WebUI.convertWebElementToTestObject(stocksNotify[i])
			TestObject Sku = WebUI.convertWebElementToTestObject(sku[i])
			products.add(new QuickOrder1(WebUI.getAttribute(Quantity,"value"),WebUI.getText(Price), WebUI.getText(Total),WebUI.getText(Title),
					WebUI.getAttribute(Img,"src"),WebUI.getText(StocksNotify),WebUI.getAttribute(Sku,"value")))
			String name = WebUI.getText(Title)
			String qyt = WebUI.getAttribute(Quantity, 'value')
			String price = WebUI.getText(Price).replaceAll("[^0-9\\.]","")

			String sku = WebUI.getAttribute(Sku,"value")

			cartItem.add(GeneralHelperFunctions.makeListOfItems(name, price, sku, qyt))

			QuickOrderHelpers.verifyQuickOrderTotal(Price, Total, Quantity)
		}
		GlobalVariable.cartItems = cartItem
	}
	/***
	 * calculate and check the total number is right
	 * @author wesam hamad
	 */
	public static void verifyQuickOrderTotal(TestObject price, TestObject expectedSubTotal, TestObject quantityInput) {
		int quantity = Integer.parseInt(WebUI.getAttribute(quantityInput, "value"))
		QuickOrderValidations.verifyQuickOrderSubTotal(quantity, price, expectedSubTotal)
	}
	/***
	 * navigation to shopping cart page, verify hearder, titl, url
	 * @author wesam hamad 
	 */
	public static void navigationAndVerifyingShoppingCart() {
		QuickOrderHelpers.navigateToAddToCartPage()
		TestObject shopCartHeader = findTestObject("Object Repository/Shopping Cart/h1_shopCartHeader")
		WebUI.waitForElementPresent(shopCartHeader, 5)

		GeneralHelpers.verifyNavigationToPage(GlobalVariable.shoppingCartPageTitle, shopCartHeader, GlobalVariable.shopCartHeader,
				GlobalVariable.shoppingCartUrl)
	}
	/***
	 * navigation to shopping cart page: hover button, verify style, click the link
	 * @author wesam hamad
	 */
	public static void navigateToAddToCartPage() {
		TestObject addToCartBtn = findTestObject('Object Repository/Quick Order/button_addToCart')

		GeneralActions.hoverItem(addToCartBtn)
		//assert WebUI.getCSSValue(container, 'box-shadow').equals('rgba(0, 0, 0, 0.3) 0px 0px 10px 2px')

		QuickOrderActions.clickAddToCartBtn()
	}
	/***
	 * verify Cart Total is right And Rows No as expected
	 * @author wesam hamad
	 */
	public static void verifyCartTotalAndRowsNo(int no) {
		QuickOrderValidations.verifyCartTotal(products[0].getQuantity(), products[1].getQuantity(), products[2].getQuantity(),
				products[3].getQuantity(), products[4].getQuantity())

		List<WebElement> productsNoCartTr = WebUI
				.findWebElements(findTestObject("Object Repository/Quick Order/tr_productsInCart")
				,GlobalVariable.webElementTimeOut)
		QuickOrderValidations.verifyProductsNoInCart(no, productsNoCartTr.size())
	}
	/***
	 * calculate the total number of all products totals
	 * @author wesam hamad
	 */
	public static double calculateQuickOrdersTotal(String first, String sec, String third, String fourth,String fifth) {
		DecimalFormat format = new DecimalFormat("#.##")
		double firstTotal = QuickOrderActions.formatPriceAndTotal(first)
		double secTotal = QuickOrderActions.formatPriceAndTotal(sec)
		double thirdTotal = QuickOrderActions.formatPriceAndTotal(third)
		double fourthTotal = QuickOrderActions.formatPriceAndTotal(fourth)
		double fifthTotal = QuickOrderActions.formatPriceAndTotal(fifth)
		return Double.parseDouble(format.format(firstTotal + secTotal + thirdTotal + fourthTotal + fifthTotal));
	}
	/***
	 * hover On Mini Cart Header and verify values
	 * @author Wesam hamad
	 */
	public static void hoverOnMiniCartHeader() {
		MiniCartActions.hoverOnMiniCartLink()
		MiniCartValidations.verifyHoverStyleOnMiniCartLink()
		MiniCartValidations.verifyMiniCartTotals()
		MiniCartValidations.miniCartItemCount()
		QuickOrderHelpers.verifyMiniCartAddedProductRefelectDetails()
	}
	public static void verifyMiniCartAddedProductRefelectDetails() {
		List<WebElement> prodPrice = WebUI.findWebElements(findTestObject('Mini Cart/span_miniCartProductPrice'), 1)
		List<WebElement> prodQyt = WebUI.findWebElements(findTestObject('Mini Cart/td_miniCartTableQuantityTableData'), 1)
		List<WebElement> prodSKU = WebUI.findWebElements(findTestObject('Object Repository/Mini Cart/span_productSKU'), 1)
		List<WebElement> prodName = WebUI.findWebElements(findTestObject('Object Repository/Mini Cart/a_productName'), 1)

		List cartItem = GlobalVariable.cartItems
		boolean flag = false


		for(int idx = 0; idx < prodPrice.size(); idx++) {
			String prc = prodPrice.get(idx).getAttribute('innerText').replaceAll("[^0-9\\.]","");
			String qt = prodQyt.get(idx).getAttribute('innerText');
			String sku = prodSKU.get(idx).getAttribute('innerText').split("#")[1].trim();
			String name = prodName.get(idx).getAttribute('innerText');
			println("sku: "+sku)
			for(int i = 0; i < cartItem.size(); i++) {
				println("compare2: "+cartItem[i][2])
				if(name.contains(cartItem[i][0]) && sku.contains(cartItem[i][2])) {
					flag = true
					break
				}
			}
		}

		assert flag : "Verify Added Product To MiniCart Refelected"
	}
	
}
public class QuickOrder1{
	private String quantity;
	private String price;
	private String total;
	private String title;
	private String img;
	private String stocksNotify;
	private String sku;

	public quickOrder(String quantity,String price, String total, String title,String img ,String stocksNotify,String sku) {
		this.quantity = quantity;
		this.price = price;
		this.total = total;
		this.title = title;
		this.img = img;
		this.stocksNotify = stocksNotify;
		this.sku =sku;
	}
	public String getQuantity() {
		return this.quantity;
	}
}
