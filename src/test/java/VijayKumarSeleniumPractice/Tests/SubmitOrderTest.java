package VijayKumarSeleniumPractice.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import VijayKumar.PageObjects.CartPage;
import VijayKumar.PageObjects.CheckoutPage;
import VijayKumar.PageObjects.ConfirmationPage;
import VijayKumar.PageObjects.LandingPage;
import VijayKumar.PageObjects.OrderPage;
import VijayKumar.PageObjects.ProductCatalogue;
import VijayKumarSeleniumPractice.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String Productname = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		//Launching and Login the Application
		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"),input.get("password"));
		
		//Wait and get list of Element
		List<WebElement> products = productcatalogue.getProductList();
		
		productcatalogue.addProductToCart(input.get("Productname"));

		CartPage cartPage= productcatalogue.goToCartPage();
		Boolean match = cartPage.verifyProductDisplay(input.get("Productname"));
		
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String Confirmmessage = confirmationPage.getConfirmationMessage();
		
		Assert.assertTrue(Confirmmessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		
	}
	
	
	@Test(dependsOnMethods="submitOrder")
	public void orderHistoryTest() {
		ProductCatalogue productcatalogue = landingpage.loginApplication("vijaykumarsajjan32@gmail.com", "Sajjan@77");
		OrderPage orderpage = productcatalogue.goToOrderPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(Productname));
		
	}

    @DataProvider
    public Object[][] getData() throws IOException {
    
    	
    	List<HashMap<String,String>> data =  getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\VijayKumarSeleniumPractice\\Data\\DataReader.json");
    	
	return new Object[][] {{data.get(0)},{data.get(1)}};
	
}
	/*
	HashMap<String,String> map = new HashMap<String,String>();
	map.put("email","vijaykumarsajjan32@gmail.com" );
	map.put("password", "Sajjan@77");
	map.put("Productname", "ZARA COAT 3");
	
	HashMap<String,String> map1 = new HashMap<String,String>();
	map1.put("email","anshika@gmail.com" );
	map1.put("password", "Iamking@000");
	map1.put("Productname", "ADIDAS ORIGINAL"); */
	

}
