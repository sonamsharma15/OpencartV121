package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegistrationPage;
import testBase.BaseClass;

//

public class TC001_AccountRegistrationTest extends BaseClass {

	
	@Test(groups = {"Regression","Master"})
	public void verify_account_registration() {

		logger.info("*******starting TC001_AccountRegistrationTest *********");//Log4j2

		try {
		HomePage hp = new HomePage(driver);

		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");

		hp.clickRegister();
		logger.info("Clicked on Register Link");

		RegistrationPage rp = new RegistrationPage(driver);
		rp.setFirstName(randomString().toUpperCase());
		rp.setLastName(randomString().toUpperCase());
		rp.setEmail(randomString() + "@gmail.com");// randomly generated string for Email.
		rp.setTelephone(randomNumber());

		String pswd = randomAlphaNumeric();// call this method 2 times will generate 2 different pswd..so error
		rp.setPassword(pswd);
		rp.setConfirmPswd(pswd);

		rp.ClickPrivacyPolicy();
		rp.clickContinue();

		logger.info("Validating Expected Message");
		String confmsg = rp.getConfirmationMsg();
		
		if (confmsg.equals("Your Account Has Been Created!")) {
		//if (confmsg.equals("Your Account Has Been Created!^^^^")) {
			Assert.assertTrue(true);
		}

		else {
			
			  logger.error("Test is failed");
		      logger.debug("Debug logs");
		      Assert.assertTrue(false);
			
		}
		//Assert.assertEquals(confmsg, "Your Account Has Been Created!!!!!!!!!!");
		}
		catch(Exception e)
		{	
		  
		    Assert.fail();
			
		}

		logger.info("*******finished TC001_AccountRegistrationTest *********");//Log4j2
	}

}
