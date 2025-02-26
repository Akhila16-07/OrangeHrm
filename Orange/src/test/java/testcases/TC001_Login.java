package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC001_Login extends BaseClass {
	
	
	@Test(groups={"Sanity","Master"})
	public void login() throws InterruptedException {
		LoginPage login1 = new LoginPage(driver);
		Thread.sleep(10000);
		if(login1.getLogin()) {
	//	logger.info("********Starting of TC001_Login********");
		
		login1.enterUsername(p.getProperty("username"));
		login1.enterPassword(p.getProperty("password"));
		login1.clickLogin();
		Thread.sleep(5000);
	
		
	     // Validate login success
		HomePage hp = new HomePage(driver);
		boolean targetPage= hp.isDashboardExists();
		//System.out.println(targetPage);

		Assert.assertTrue(targetPage);
	    System.out.println("Login sucessfully");
//		logger.info("********Ending of TC001_Login********");
		}else {
			System.out.println("page is not loaded yet");
		}
	}	  
}


