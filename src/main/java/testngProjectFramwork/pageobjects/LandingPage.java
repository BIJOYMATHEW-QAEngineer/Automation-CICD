package testngProjectFramwork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testngProjectFramwork.abstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver)// this is a constructor. it is used to run first method in a class.in the main method Syntax is public classname.
	{
		//Initialization 
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//WebElement UserEmail=	driver.findElement(By.id("userEmail"));
//pageFactor -- @FindBy (to use page object

@FindBy(id="userEmail")
WebElement UserEmail;
//driver.findElement(By.id("userPassword"))
@FindBy(id="userPassword")
WebElement USERPassword;
//	driver.findElement(By.id("login"))
@FindBy(id="login")
WebElement Submit;
@FindBy(xpath="//div[@aria-label='Incorrect email or password.']")
WebElement errorMessage;

//create actions for above page object

public ProductCataloge loginApplication(String email,String password)
{
	UserEmail.sendKeys(email);
	USERPassword.sendKeys(password);
	Submit.click();
	ProductCataloge productcataloge = new ProductCataloge(driver);
	return productcataloge;
}

public String getErrorMessage()
{
	waitForWebElementToAppear(errorMessage);
	return errorMessage.getText();
}

public void goToURL()
{
	driver.get("https://rahulshettyacademy.com/client");
}


}
