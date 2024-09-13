package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@SuppressWarnings("unused")
public class RegistrationPage extends BasePage {

	//constructor
	public RegistrationPage(WebDriver driver) {
		super(driver);
		
	}
	
	//locators
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstrName;
	
	@FindBy(xpath = "//input[@id='input-lastname']") 
	WebElement txtLastName;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']") 
	WebElement txtTelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']") 
	WebElement txtConfirmPassword;
	
	@FindBy(xpath = "//input[@name='agree']") 
	WebElement clickPolicy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']") 
	WebElement msgConfirmation;
	
	
	//Action methods:
	public void setFirstName(String fname) {
		txtFirstrName.sendKeys(fname);
		
	}
	
	public void setLastName(String lname) {
         txtLastName.sendKeys(lname);
	
	}
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	
	}
	public void setTelephone(String telephn) {
		txtTelephone.sendKeys(telephn);
	
	}
	
	public void setPassword(String pswd) {
		txtPassword.sendKeys(pswd);
	
	}
	public void setConfirmPswd(String pswd) {
		txtConfirmPassword.sendKeys(pswd);
	
	}
	public void ClickPrivacyPolicy() {
		clickPolicy.click();
	
	}
	
	public void clickContinue() {
		
		//Way1:
		btnContinue.click();
		
		//If the above method throws Exception as 'ElementInterceptableException'
		//So use below ways:
		
		//Way2 :
		//Actions act=new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//Way3:
		//JavascriptExecutor js=(JavascriptExecutor) driver;
		//js.executeScript("argument[0].click()", btnContinue);
		
		//way4:
		//btnContinue.sendKeys(Keys.RETURN);
		
		//way5:
		//WebDriverWait mywait=new WebDriverWait(driver, Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
		
	}
	
	public String getConfirmationMsg() {
		try {
			
			return msgConfirmation.getText();
		
		} catch (Exception e) {
			
			return e.getMessage();
		}
		

		
	}
	
	
}
