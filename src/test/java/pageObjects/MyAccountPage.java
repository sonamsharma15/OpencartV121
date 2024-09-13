package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	//locators:
	@FindBy(xpath="//h2[normalize-space()='My Account']") //MyAccount page heading
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")//added for DDT of login page.
	WebElement linkLogout;
	
	
	//Action method:
	
	//check the presence of MyAccount page heading::
	public boolean isMyAccountPageExists() {
		
		try {
			return msgHeading.isDisplayed();
			
		} catch (Exception e) {
			
			return false;
		}
		
	}
	
	public void clickLogout() {
		
		linkLogout.click();
		
	}
	
}
