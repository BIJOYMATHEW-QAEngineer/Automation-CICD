package testngProjectFramwork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import testngProjectFramwork.abstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {

	WebDriver driver;
	// driver.findElement(By.cssSelector("[placeholder='Select Country']"
	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;
	// driver.findElement(By.cssSelector(".action__submit"))
	@FindBy(xpath = "//a[contains(text(),'Place Order')]")
	//@FindBy(xpath="//a[normalize-space()='Place Order']")
	WebElement submit;
	// driver.findElement(By.xpath("(//button[@type='button'])[2]"))
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectcountry;
	

	public CheckOutPage(WebDriver driver) {
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void selectCountry(String countryName) throws InterruptedException {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		Thread.sleep(1500);
		selectcountry.click();
		Thread.sleep(2000);

	}
	public ConfirmationPage submitorder() throws InterruptedException {

		WebElement placeOrder = submit;
	
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
	
		js.executeScript("arguments[0].scrollIntoView();",placeOrder);
		
		//js.executeScript("document.documentElement.scrollTop = document.documentElement.scrollHeight;");
		// or
		//js.executeScript("document.body.scrollTop = document.body.scrollHeight;");
		Thread.sleep(2000);
		placeOrder.click();

		return new ConfirmationPage(driver);

	}

}
