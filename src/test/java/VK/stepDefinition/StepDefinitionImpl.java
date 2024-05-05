package VK.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import VijayKumar.PageObjects.CartPage;
import VijayKumar.PageObjects.CheckoutPage;
import VijayKumar.PageObjects.ConfirmationPage;
import VijayKumar.PageObjects.LandingPage;
import VijayKumar.PageObjects.ProductCatalogue;
import VijayKumarSeleniumPractice.TestComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {
	
	public LandingPage landingPage;
	ProductCatalogue productcatalogue;
	ConfirmationPage confirmationPage;
	List<WebElement> products;
	@Given("I landed on Ecommerce application")
	public void I_landed_on_Ecommerce_application() throws IOException {
		landingPage = launchApplication();
		
		
	}
	@Given("^Logged with username(.+) and password (.+)$")
	public void Logged_with_username_and_password_(String username, String password){
		 productcatalogue = landingpage.loginApplication(username,password);
		
	}
	
	@When ("^I add the product (.+) from cart$")
	public void When_I_add_the_product_from_cart(String product) throws InterruptedException {
     products = productcatalogue.getProductList();
		
		productcatalogue.addProductToCart(product);
		

	}
	@When("^Checkout (.+) and submit the order$")
	
	public void Checkout_and_submit_the_order(String product) {
		CartPage cartPage= productcatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(product);
		
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		 confirmationPage = checkoutPage.submitOrder();
		
	}

	@Then("{string} Message is displayed on confirmation page")
	public void Message_displayed_on_confirmation_page(String string) {
        String Confirmmessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(Confirmmessage.equalsIgnoreCase(string));
		driver.close();
	}

}
