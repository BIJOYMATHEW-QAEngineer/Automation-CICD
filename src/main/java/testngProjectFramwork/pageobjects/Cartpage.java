package testngProjectFramwork.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testngProjectFramwork.abstractComponent.AbstractComponent;

public class Cartpage extends AbstractComponent {

	WebDriver driver;

	// List<WebElement>Cartproducts=driver.findElements(By.cssSelector(".cartSection
	// h3"));

	@FindBy(css = ".cartSection h3")
	List<WebElement> Cartproducts;
//driver.findElement(By.xpath("(//button[normalize-space()='Buy Now'])[1]")).click();
	@FindBy(xpath="//button[contains(text(),'Buy Now')]")
	WebElement checkoutEle;
	
	public Cartpage(WebDriver driver) {
		// Initialization
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean VerifyProductDisplay(String ProductName) {
		Boolean match = Cartproducts.stream()
				.anyMatch(Cartproduct -> Cartproduct.getText().equalsIgnoreCase(ProductName));
		return match;
	}
	
	public CheckOutPage gotocheckout()
	{
		checkoutEle.click();
		return new CheckOutPage(driver);
	}
	
	
}
