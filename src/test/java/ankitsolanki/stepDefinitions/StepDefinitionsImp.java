package ankitsolanki.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import ankitsolanki.TestComponents.BaseTest;
import ankitsolanki.pageobjects.CartPage;
import ankitsolanki.pageobjects.CheckoutPage;
import ankitsolanki.pageobjects.ConfirmationPage;
import ankitsolanki.pageobjects.LandingPage;
import ankitsolanki.pageobjects.ProductCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsImp extends BaseTest {

	public ConfirmationPage confirmationPage;
	public ProductCatalogue productCatalogue;
	public LandingPage landingPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String password)
	{
		 productCatalogue=landingPage.loginApplication(username,password);
	}
	
	 @When("^I add Product (.+) to cart$")
	 public void I_add_product_to_cart(String productName) throws InterruptedException
	 {
		 //List<WebElement> products=productCatalogue.getProductList();
			productCatalogue.addProductToCart(productName);
	 }
	 
	 @When("^Checkout (.+) and submit the order$")
	 public void Checkout_and_submit_the_order(String productName) throws InterruptedException
	 {
		 CartPage cartPage=productCatalogue.goToCartPage();
			
			Boolean match=cartPage.VerifyProductDisplay(productName);
			Assert.assertTrue(match);
			CheckoutPage checkoutPage=cartPage.goToCheckoutPage();
			checkoutPage.selectCountry("India");
			confirmationPage=checkoutPage.confirmOrder();
	 }
	 @Then("{string} message is displayed on ConfirmationPage")
	 public void message_displayed_ConfirmationPage(String string)
	 {
		 String confirmMessage=confirmationPage.ConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	 }
	 @Then("{string} message is displayed")
	 public void message_is_displayed(String string)
	 {
		 Assert.assertEquals(string, landingPage.getErrorMessage());
		 driver.close();
	 }
}




