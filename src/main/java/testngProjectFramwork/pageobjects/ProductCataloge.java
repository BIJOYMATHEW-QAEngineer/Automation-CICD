package testngProjectFramwork.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testngProjectFramwork.abstractComponent.AbstractComponent;

public class ProductCataloge extends AbstractComponent {

	WebDriver driver;

	public ProductCataloge(WebDriver driver)// this is a constructor. it is used to run first method in a class.in the
											// main method Syntax is public classname.
	{
		// Initialization
		super(driver);

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//List<WebElement>products=	driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;
//driver.findElement(By.cssSelector(".ng-animating"))
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getproductlist() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String ProductName) {
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	
	
	public void addProductToCart(String ProductName) throws InterruptedException {
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
	  Thread.sleep(2000);
	//	waitForElementToDisappear(spinner);
	}

	
}
