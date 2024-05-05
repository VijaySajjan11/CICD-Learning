package VijayKumarSeleniumPractice.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import VijayKumar.PageObjects.CartPage;
import VijayKumar.PageObjects.CheckoutPage;
import VijayKumar.PageObjects.ConfirmationPage;
import VijayKumar.PageObjects.LandingPage;
import VijayKumar.PageObjects.ProductCatalogue;
import VijayKumarSeleniumPractice.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation() throws IOException, InterruptedException {
		//String Productname = "ZARA COAT 3";
		//Launching and Login the Application
		landingpage.loginApplication("vijaykumarsajjan32@gmail.com", "Sajjan");
		//landingpage.getErrorMessage();
		Assert.assertEquals("Incorrect email password.", landingpage.getErrorMessage());

	}
	

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		String Productname = "ZARA COAT 3";
		//Launching and Login the Application
		ProductCatalogue productcatalogue = landingpage.loginApplication("vijaykumarsajjan32@gmail.com", "Sajjan@77");
		
		//Wait and get list of Element
		List<WebElement> products = productcatalogue.getProductList();
		
		productcatalogue.addProductToCart(Productname);

		CartPage cartPage= productcatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(Productname);
		
		Assert.assertFalse(match);

}
}