package testcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*data is valid - login success -test pass -logout
 * data is valid - login failed - test fail
 * 
 * 
 * data is invalid -login success - test fail -logout
 * data is invalid - login failed - test pass
*/

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven") //dataprovider in different pack and class.
	public void verify_loginDDT(String email,String pwd,String exp) throws InterruptedException {
		
		//logger.info("******Starting Tc003LoginDDT***********");
		
		Thread.sleep(5000);
	
		LoginPage login1 = new LoginPage(driver);
		login1.enterUsername(email);
		login1.enterPassword(pwd);
		login1.clickLogin();
		Thread.sleep(5000);
		
		// Validate login success
		HomePage hp = new HomePage(driver);
		boolean targetPage= hp.isDashboardExists();
		System.out.println("Result at homepage login "+targetPage);
		if(exp.equalsIgnoreCase("Valid")) {
			System.out.println("valid");
			if(targetPage==true) 
			{			
				hp.getLogOut();
				System.out.println("logout");
				Assert.assertTrue(true);
				
			}else 
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("invalid")) 
		{
			if(targetPage==true) {
				hp.getLogOut();
				Assert.assertTrue(false);			
			}else 
			{
				Assert.assertTrue(true);
			}
		}
//		}catch(Exception e) {
//			Assert.fail();
//		}
		
		
	//	logger.info("******* Finished TC003 loginDDT*******");
	}
}
