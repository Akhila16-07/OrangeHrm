package testcases;

import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;


public class TC002_Logout extends BaseClass{

	
	@Test(groups={"Sanity","Master"})	
	public void logout() throws InterruptedException {
		
		//logger.info("********Starting of TC002_Logout********");
		HomePage hp = new HomePage(driver);
		boolean targetPage= hp.isDashboardExists();
		if(targetPage==true) {
			System.out.println("Already login ");
			hp.getLogOut();
		}else {
			System.out.println("User not yet login so Login Now");
			Thread.sleep(3000);
			LoginPage login1 = new LoginPage(driver);
			login1.enterUsername(p.getProperty("username"));
			login1.enterPassword(p.getProperty("password"));
			login1.clickLogin();
			/*Thread.sleep(5000);
			if(targetPage==true) {
				hp.getLogOut();
			}*/
		//logger.info("********Ending of TC002_Logout********");
		}
	}
}
