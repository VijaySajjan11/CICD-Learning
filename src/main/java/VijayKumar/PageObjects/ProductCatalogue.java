package VijayKumar.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import VijayKumar.AbstractComponents.AbstractComponenet;

public class ProductCatalogue extends AbstractComponenet{
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By products1 = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(products1);
		return products;
	}
	
	public WebElement getProductByName(String Productname) {
		 WebElement prod = products.stream().filter(product->
		 product.findElement(By.cssSelector("b")).getText().equals(Productname)).findFirst().orElse(null);
		 return prod;
	}
	
	public void addProductToCart(String Productname) throws InterruptedException {
		WebElement prod = getProductByName(Productname);
		 prod.findElement(addToCart).click();
		 waitForElementToAppear(toastMessage);
		 waitForElementToDisappear(spinner);
	}

}
