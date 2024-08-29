package testngFramework.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testngFramework.TestComponents.BaseTest;
import testngProjectFramwork.pageobjects.Cartpage;
import testngProjectFramwork.pageobjects.CheckOutPage;
import testngProjectFramwork.pageobjects.ConfirmationPage;
import testngProjectFramwork.pageobjects.OrderPage;
import testngProjectFramwork.pageobjects.ProductCataloge;

public class SubmitOrderTest extends BaseTest {

	String ProductName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })

	public void submitOrder(HashMap<String,String>input)
			throws IOException, InterruptedException {
		ProductCataloge productcataloge = landingpage.loginApplication(input.get("email"), input.get("password"));
//Bijoy@test123.in,Test@123
		List<WebElement> products = productcataloge.getproductlist();

		productcataloge.addProductToCart(input.get("product"));

		Cartpage cartpage = productcataloge.addToCart();

		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage CheckOutPage = cartpage.gotocheckout();
		CheckOutPage.selectCountry("India");

		ConfirmationPage ConfirmationPage = CheckOutPage.submitorder();

		String ConfirmMessage = ConfirmationPage.getconfiramtionMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
		// ZARA COAT 3 --is available in the orderhistory

		ProductCataloge productcataloge = landingpage.loginApplication("Test@testframework.com", "Test@123");

		OrderPage orderpage = productcataloge.goToOrderpage();
		Assert.assertTrue(orderpage.VerifyOrderDisplay(ProductName));
		
	}

	/*
	 * @DataProvider 
	 * public Object[][] getData() { return new Object[][]
	 * {{"Test@testframework.com","Test@123","ZARA COAT 3"},{"Bijoy@test123.in",
	 * "Test@123","IPHONE 13 PRO"} } ; }
	 */
	/*
	HashMap<String,String>map=new HashMap<String,String>();
	map.put("email", "Test@testframework.com");
	map.put("password", "Test@123");
	map.put("product", "ZARA COAT 3");
	
	
	HashMap<String,String>map1=new HashMap<String,String>();
map1.put("email", "Bijoy@test123.in");
map1.put("password","Test@123");
map1.put("product","IPHONE 13 PRO");
*/

	
	
	@DataProvider 
	public Object[][] getData() throws IOException
	{
		

		
		List<HashMap<String,String>>data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//testngFramework//data//PurchaseOrder.json") ;
	return new Object[][] {{data.get(0)},{data.get(1)}}; 
	
	}
	
	
}
	
	
	


