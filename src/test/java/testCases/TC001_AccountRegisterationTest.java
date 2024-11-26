package testCases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegisterationTest extends BaseClass{
	
	
	
	@Test (groups={"Regression","Master"})
	public void verify_account_registeration()
	{
		logger.info("** TC1 Started **");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		logger.info("** Home Page **");
		
		AccountRegisterationPage arp = new AccountRegisterationPage(driver);
		logger.info("** Registeration Page **");
		arp.setFirstName(randomString().toUpperCase());
		arp.setLastName(randomString().toUpperCase());
		arp.setEmail(randomString()+"@gmail.com");
		arp.setPhNo(randomNumber());
		String password = randomAlphanumeric();
		arp.setPassword(password);
		arp.setConfirmPassword(password);
		arp.clickAgree();
		logger.info("** Agreed **");
		arp.clickContinue();
		logger.info("** Continued **");
		
		logger.info("** Message Validation **");
		String cnfrm_msg = arp.getConfirmationMsg();
		if(cnfrm_msg.equals("Your Account Has Been Created!"))
		{
			AssertJUnit.assertTrue(true);
		}
		else {
			logger.error("Test Failed");
			logger.debug("Debug Logs..");
			AssertJUnit.assertTrue(false);
		}
		//Assert.assertEquals(cnfrm_msg, "Your Account Has Been Created!!!");
		}
		
		catch(Exception e)
		{
			
			AssertJUnit.fail();
		}
		
		logger.info("** Test Completed **");
		
	}
	
	

}
