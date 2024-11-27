package ankitsolanki.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ankitsolanki.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {
	WebDriver driver;
public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}

//	driver.findElement(By.cssSelector("[placeholder*='Select Country']")
	@FindBy(css=".action__submit")
	WebElement submit;

	@FindBy(css="[placeholder*='Select Country']")
	WebElement country;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName)
	{
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		WaitForElementToAppear(results);
		List<WebElement> options=driver.findElements(By.cssSelector(".ta-item"));
		for (WebElement option : options) 
		{
			if(option.getText().equalsIgnoreCase(countryName))
			{
				option.click();
				break;
			}
		}
	}
	public ConfirmationPage confirmOrder() throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,550)", "");
		
		Thread.sleep(1000);
		submit.click();
		return new ConfirmationPage(driver);
	}
}
