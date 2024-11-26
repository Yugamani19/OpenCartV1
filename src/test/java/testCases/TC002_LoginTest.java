package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups ={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("** Starting TC002 Login Test");
		
		try
		{
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetPage = map.isMyAccountPageExists();
		
		logger.info("** meessage validation **");
		
		Assert.assertEquals(targetPage, true,"Login Failed");
		
		//Assert.assertTrue(targetPage);
		logger.info("** meessage validation completed**");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		
		logger.info("** TC002_Login Test Finished");
		
	}

}
