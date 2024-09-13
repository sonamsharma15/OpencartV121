package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//Each pageObject contains::to achieve reusability

//Constructor of this class with driver as parameter which is initialised in BasePage class.
//Use super() call to invoke parent class constructor.

//Locators::Using @FindBy(Locator) annotation to get the webelement

//Action methods::public method to perform action on each webelement

public class HomePage extends BasePage {

	// Constructor
	public HomePage(WebDriver driver) {
		super(driver);

	}

	// Locators
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement linkMyaccount;

	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement linkLogin;

	
	
	// Action methods:
	public void clickMyAccount() {

		linkMyaccount.click();
	}

	public void clickRegister() {

		linkRegister.click();
	}
	
	public void clickLogin() {
		linkLogin.click();
	}

}
