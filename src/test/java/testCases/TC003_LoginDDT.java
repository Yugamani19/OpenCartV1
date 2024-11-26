package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid -- login success -- test pass - logout
 * Data is valid -- login failed -- test fail
 * Data is invalid -- login success -- test fail - logout
 * Data is invalid - login failed -- test pass
 */

public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider = "LoginData", dataProviderClass=DataProviders.class, groups={"Datadriven","Master"}) //data provider from different class
	
	public void verify_loginDDT(String email, String password, String exp)
	{
		logger.info("*** starting TC003 LoginDDT***");
		
		try
		{
	
	HomePage hp = new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	LoginPage lp = new LoginPage(driver);
	lp.setEmail(email);
	lp.setPassword(password);
	lp.clickLogin();
	
	MyAccountPage map = new MyAccountPage(driver);
	boolean targetPage = map.isMyAccountPageExists();
	
	if(exp.equalsIgnoreCase("Valid"))
	{
		if(targetPage == true)
		{
			map.clickLogout();
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	
	if(exp.equalsIgnoreCase("Invalid"))
	{
		if(targetPage == true)
		{
			map.clickLogout();
			Assert.assertTrue(false);
		}
		else
		{
			Assert.assertTrue(true);
		}
	}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	logger.info("*** finished TC003 LoginDDT***");
	
	}

}
