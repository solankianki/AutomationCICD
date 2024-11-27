package ankitsolanki.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ankitsolanki.AbstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productsName;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutEle;
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match = productsName.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}

}
