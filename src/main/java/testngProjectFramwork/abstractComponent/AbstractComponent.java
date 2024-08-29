package testngProjectFramwork.abstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testngProjectFramwork.pageobjects.Cartpage;
import testngProjectFramwork.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartHeader;
	
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));
		
	}
	
	public Cartpage addToCart()
	{
		cartHeader.click();
		Cartpage cartpage = new Cartpage(driver);
		return cartpage;
	}
	
	public OrderPage goToOrderpage()
	{
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait  = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	
}
