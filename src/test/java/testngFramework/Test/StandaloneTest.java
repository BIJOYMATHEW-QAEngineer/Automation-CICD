package testngFramework.Test;

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

import testngProjectFramwork.pageobjects.LandingPage;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String ProductName="ZARA COAT 3";
	WebDriver driver = new ChromeDriver(); 
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
		
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingpage =new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("Test@testframework.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@123");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
	List<WebElement>products=	driver.findElements(By.cssSelector(".mb-3"));
	
	WebElement prod=products.stream().filter(product->
	product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
	List<WebElement>Cartproducts=driver.findElements(By.cssSelector(".cartSection h3"));
	
Boolean match=	Cartproducts.stream().anyMatch(Cartproduct->Cartproduct.getText().equalsIgnoreCase(ProductName));
	
Assert.assertTrue(match);

//driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();

//driver.findElement(By.cssSelector("div[class='cartSection removeWrap'] button[class='btn btn-primary']"));
driver.findElement(By.xpath("(//button[normalize-space()='Buy Now'])[1]")).click();
Actions a =new Actions(driver);
a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-item"))); 
//driver.findElement(By.xpath("(//i[@class='fa fa-search'])[2]")).click();
driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();
/*until(
ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='your-button-class']"))
);
*/
//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='btnn action__submit']")));
//placeOrderButton.click();

//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@class, 'action__submit')]")));
//driver.findElement(By.xpath("//a[@class='btnn action__submit']")).click();
//WebElement placeOrder=driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"));
WebElement placeOrder=driver.findElement(By.cssSelector(".action__submit"));
JavascriptExecutor js = (JavascriptExecutor)driver;
js.executeScript("arguments[0].scrollIntoView();", placeOrder);
//wait.until(ExpectedConditions.elementToBeClickable(placeOrder));
//wait.until(ExpectedConditions.elementToBeSelected(placeOrder));

Thread.sleep(2000);
placeOrder.click();
String ConfirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
driver.quit();



	}
}
