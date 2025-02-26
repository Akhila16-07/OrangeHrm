package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.ActionUtility;

public class TC004_Admin extends BaseClass {

	ActionUtility action = new ActionUtility();
	
	public void admin() throws Exception {
		HomePage hp = new HomePage(driver);
		//Thread.sleep(3000);
		if(!hp.getSystemUserDisplay()) {
			hp.getAdmin();
		}else {
			System.out.println("User is in admin page only");
		}
	}
	
	
	@Test(groups={"Regression","Master"},priority=1)
	public void addSystemUsers() throws Exception {
		
		HomePage hp = new HomePage(driver);
		admin();
		Thread.sleep(2000);
		hp.getAddUserButton();
		Thread.sleep(3000);
		hp.selectUserRole();
		hp.selectEmployeeName("Orange ","Orange Test");
		hp.selectStatus();
		hp.enterUsername(userName);
		hp.enterPassword("Akhireddy@07");
		hp.clickSave();
		action.isSuccessBannerDisplayed();
	}
	
	@Test(groups={"Regression","Master"},priority=2)
	public void searchSystemUsers() throws Exception {
		
		HomePage hp = new HomePage(driver);
		Thread.sleep(5000);
		
		if(hp.getSystemUserDisplay()) {
			hp.getUserName();
			hp.getUserRole();
			hp.selectEmployeeName("Orange ","Orange Test");
			hp.getStatusDropDown();
			hp.getSearchUser();
		}
	}
	
	@Test(groups={"Regression","Master"},priority=3)
	public void jobCategory() throws Exception {
		HomePage hp = new HomePage(driver);
			admin();
			Thread.sleep(2000);
			hp.getJob();
			hp.getJobTitle();
			Thread.sleep(2000);
			hp.getAddBtn();
			Thread.sleep(2000);
			hp.getAddJobTitle("Tester "+faker.number().digit());
			hp.getJobSaveBtn();
			action.isSuccessBannerDisplayed();
		
	}
	
//	@Test(groups={"Regression","Master"},priority=4)
	public void payGrades() throws Exception {
		HomePage hp = new HomePage(driver);
			admin();
			Thread.sleep(2000);
			hp.getJob();
			hp.getPayGrades();
			Thread.sleep(1000);
			hp.getAddBtn();
			Thread.sleep(1000);
			hp.getNameInput(faker.name().name());
			hp.getSaveBtn();
			Thread.sleep(2000);
		//	hp.getSaveBtn();
			//action.isSuccessBannerDisplayed();
	}
	
	@Test(groups={"Regression","Master"},priority=5)
	public void empStatus() throws Exception {
		HomePage hp = new HomePage(driver);
			admin();
			Thread.sleep(2000);
			hp.getJob();
			hp.getEmpStatus();
			Thread.sleep(2000);
			hp.getAddBtn();
			Thread.sleep(1000);
			hp.getempNameInput(faker.name().firstName());
			hp.getSaveBtn();
			action.isSuccessBannerDisplayed();
	}
	
	@Test(groups={"Regression","Master"},priority=6)
	public void jobCategories() throws Exception {
		HomePage hp = new HomePage(driver);
			admin();
			Thread.sleep(2000);
			hp.getJob();
			hp.getJobCategory();
			Thread.sleep(2000);
			hp.getAddBtn();
			Thread.sleep(1000);
			hp.getNameInput(faker.name().firstName());
			hp.getSaveBtn();
			action.isSuccessBannerDisplayed();
	}
	
	@Test(groups={"Regression","Master"},priority=7)
	public void workShifts() throws Exception {
		HomePage hp = new HomePage(driver);
			admin();
			Thread.sleep(2000);
			hp.getJob();
			hp.getWorkShifts();
			Thread.sleep(2000);
			hp.getAddBtn();
			Thread.sleep(1000);
			hp.getShiftName(faker.name().firstName());
			hp.getSaveBtn();
			action.isSuccessBannerDisplayed();
	}
	
	@Test(groups= {"Regression","Master"},priority=8)
	public void qualification() throws Exception {
		HomePage hp = new HomePage(driver);
		admin();
		Thread.sleep(2000);
		hp.getQualification();
		hp.getSkills();
		Thread.sleep(2000);
		hp.getAddBtn();
		Thread.sleep(2000);	
		hp.getSkillName("java1" +faker.number().digit());
		hp.getJobSaveBtn();
		action.isSuccessBannerDisplayed();
	}
}
