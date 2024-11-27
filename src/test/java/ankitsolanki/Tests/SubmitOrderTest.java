package ankitsolanki.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ankitsolanki.TestComponents.BaseTest;
import ankitsolanki.pageobjects.CartPage;
import ankitsolanki.pageobjects.CheckoutPage;
import ankitsolanki.pageobjects.ConfirmationPage;
import ankitsolanki.pageobjects.LandingPage;
import ankitsolanki.pageobjects.OrdersPage;
import ankitsolanki.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckoutPage();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage=checkoutPage.confirmOrder();
		String confirmMessage=confirmationPage.ConfirmMessage();
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("ankit.solanki@appfoster.com", "App@12345");
		OrdersPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "ankit.solanki@appfoster.com");
//		map.put("password", "App@12345");
//		map.put("product","ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "solankiankit66@gmail.com");
//		map1.put("password", "Rahul@123");
//		map1.put("product","ADIDAS ORIGINAL");
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//ankitsolanki//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
