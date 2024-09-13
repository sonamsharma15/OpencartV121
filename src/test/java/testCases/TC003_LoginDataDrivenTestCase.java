package testCases;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;



import utilities.DataProviders;//important as its from utility class

public class TC003_LoginDataDrivenTestCase extends BaseClass {
	
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups = "Datadriven")//getting dataprovider from different class
	public void verify_loginDDT(String username,String password,String expectedResult) throws InterruptedException {
		
		logger.info("************starting TC003_loginDDT*********************");
		
		
		try {
		//homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//loginpage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(username);
		lp.setPassword(password);
		lp.clickLogin();
		
		//Myaccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();//true means login to targetPage successful
		
		

		/*Data is valid-->login success--->TC pass-->logout  as logged in to app next page.
		               -->login failed-->TC fail-->as its in same loginPage

		  Data is invalid-->login success--->TC fail-->logout as logged in apps next page
       		             -->login failed-->TC pass-->as its in same loginPage*/

		//data valid
		if (expectedResult.equalsIgnoreCase("Valid")) {
			
			if (targetPage==true) {
				macc.clickLogout();
				Assert.assertTrue(true);//passed TC
				
			}else {
				Assert.assertTrue(false);//failed Tc and no need to do logout as its in loginPage only
			}
			
		}
		
		//data invalid
		if(expectedResult.equalsIgnoreCase("Invalid")) {
			if(targetPage==true) {//target page is coming means  login successfula and we need to logout also
				macc.clickLogout();
				Assert.assertTrue(false);//failed TC
				
			}else {
				Assert.assertTrue(true);//passed TC as no targetPage reached
				
			}
			
		}
		}catch(Exception e){
			
			Assert.fail();
		}
		
		Thread.sleep(3000);
		logger.info("************finihed TC003_loginDDT*********************");
		
		
	}
	

}
