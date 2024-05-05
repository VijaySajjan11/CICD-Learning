package VijayKumar.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import VijayKumar.AbstractComponents.AbstractComponenet;

public class LandingPage extends AbstractComponenet{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver= driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(xpath="//input[@id='userEmail']")
	WebElement userEmail;
	
	@FindBy(xpath = "//input[@id='userPassword']")
	WebElement passwordele;
	
	@FindBy(xpath="//input[@id= 'login']")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	
	
	public ProductCatalogue loginApplication(String Email, String Password ) {
		userEmail.sendKeys(Email);
		passwordele.sendKeys(Password);
		submit.click();
		ProductCatalogue productcatalogue = new ProductCatalogue(driver);
		return productcatalogue;
		
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}
	
	public void goTo(){
		driver.get("https://rahulshettyacademy.com/client/");
		
		
	}

}
