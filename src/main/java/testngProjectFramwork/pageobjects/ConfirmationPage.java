package testngProjectFramwork.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testngProjectFramwork.abstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;
	//driver.findElement(By.cssSelector(".hero-primary"))
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getconfiramtionMessage()
	{
		return confirmationMessage.getText();
		
		
	}
}
