package cucumberFramework.StepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import testngFramework.TestComponents.BaseTest;
import testngProjectFramwork.pageobjects.Cartpage;
import testngProjectFramwork.pageobjects.CheckOutPage;
import testngProjectFramwork.pageobjects.ConfirmationPage;
import testngProjectFramwork.pageobjects.LandingPage;
import testngProjectFramwork.pageobjects.ProductCataloge;

public class StepDefinitionImplementation extends BaseTest {

	public LandingPage landingPage;
	public ProductCataloge productcataloge;
	public ConfirmationPage ConfirmationPage;

	@Given("I landed on the Ecommerse Page")
	public void I_landed_on_the_Ecommerse_Page() throws IOException, InterruptedException {

		landingPage = launchApplication();
	}

	@Given("^Logged in with username (.+) and password (.+)$")
	public void Logged_in_with_username_and_password(String username, String password) {
		productcataloge = landingpage.loginApplication(username, password);

	}

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productcataloge.getproductlist();

		productcataloge.addProductToCart(productName);

	}

	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) throws InterruptedException {
		Cartpage cartpage = productcataloge.addToCart();

		Boolean match = cartpage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckOutPage CheckOutPage = cartpage.gotocheckout();
		CheckOutPage.selectCountry("India");

		ConfirmationPage = CheckOutPage.submitorder();

	}

	@Then("{string} message displayed on the Confirmationpage")
	public void message_displayed_on_the_Confirmationpage(String string) {
		String ConfirmMessage = ConfirmationPage.getconfiramtionMessage();
		Assert.assertTrue(ConfirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string1)
	{
		Assert.assertEquals(string1, landingpage.getErrorMessage());
		driver.close();
	}
}
