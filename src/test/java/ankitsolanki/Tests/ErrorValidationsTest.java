package ankitsolanki.Tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ankitsolanki.TestComponents.BaseTest;
import ankitsolanki.TestComponents.Retry;
import ankitsolanki.pageobjects.CartPage;
import ankitsolanki.pageobjects.CheckoutPage;
import ankitsolanki.pageobjects.ConfirmationPage;
import ankitsolanki.pageobjects.LandingPage;
import ankitsolanki.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("ankit.solanki@appfoster.com", "App12345");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());


	}
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException
	{
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("solankiankit66@gmail.com", "Rahul@123");
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);


	}

}
