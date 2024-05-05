package VijayKumar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VijayKumar.AbstractComponents.AbstractComponenet;

public class CartPage extends AbstractComponenet{
	WebDriver driver;
	
	@FindBy(xpath= "//div[@class='cartSection']/h3")
	List<WebElement> cartProducts;
	
	@FindBy(xpath = "//li[@class='totalRow']/button")
	WebElement checkoutEle;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	public Boolean verifyProductDisplay(String Productname) {
		
		Boolean match = cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(Productname));
		return match;
		
	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		return new CheckoutPage(driver);
		
	}

}
