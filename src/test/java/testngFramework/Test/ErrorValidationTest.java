package testngFramework.Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import testngFramework.TestComponents.BaseTest;
import testngProjectFramwork.pageobjects.Cartpage;
import testngProjectFramwork.pageobjects.ProductCataloge;

public class ErrorValidationTest extends BaseTest {

	@Test(groups = {"errorHandiling"})
	public void loginErrorValidation() throws IOException, InterruptedException 
	{

		String ProductName = "ZARA COAT 3";

 landingpage.loginApplication("Test@testframework.com", "Test@123");
	Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
		
		
	}

	@Test
	public void productErrorValidation() throws IOException, InterruptedException 
	{

		String ProductName = "ZARA COAT 3";
		
	

		ProductCataloge productcataloge = landingpage.loginApplication("Test@testframework.com", "Test@123");

		List<WebElement> products = productcataloge.getproductlist();

		productcataloge.addProductToCart(ProductName);

		Cartpage cartpage = productcataloge.addToCart();

		Boolean match = cartpage.VerifyProductDisplay(ProductName);
		Assert.assertTrue(match);
	}
	
	
}
