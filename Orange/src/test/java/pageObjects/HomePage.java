package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BaseClass;
import utilities.ActionUtility;


public class HomePage  {
	
	public WebDriver driver;
	
	  public HomePage(WebDriver driver) {
		  this.driver=driver;
	      PageFactory.initElements(driver, this);
	    }
	  
	  ActionUtility action =new ActionUtility();
	  
    @FindBy(xpath = "//div[contains(@class,'header-title')]")
    WebElement dashBoard;

    public boolean isDashboardExists() {
        try {
            Thread.sleep(2000);
            System.out.println(dashBoard.getText());
            return dashBoard.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Profile
    @FindBy(xpath = "//div[contains(@class, 'userarea')]")
    WebElement profile;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    WebElement logOut;

    public void getLogOut() throws InterruptedException {
        Thread.sleep(5000);
        profile.click();
        logOut.click();
    }

    // Locators using @FindBy annotation
    @FindBy(xpath = "//span[contains(@class, 'oxd-main-menu-item--name') and text()='Admin']")
   WebElement admin;

    public void getAdmin() throws Exception {
        admin.click();
    }

    @FindBy(xpath = "//span[contains(@class, 'oxd-topbar-body-nav-tab-item') and text()='User Management ']")
    WebElement userManagement;

    public void getUserManagement() {
        userManagement.click();
    }

    @FindBy(xpath = "//i[contains(@class,'oxd-button-icon')]")
    WebElement addUserButton;
    
    public void getAddUserButton() {
    	addUserButton.click();
    }
    

    @FindBy(xpath = "//label[text()='User Role']/following::div[contains(@class, 'oxd-select-text-input')]")
    WebElement userRoleDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span[text()='Admin']")
    WebElement selectAdmin;

 //   @FindBy(xpath = "//label[text()='Employee Name']/following::input")
    //WebElement employeeNameInput;
    
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    WebElement employeeNameInput;

    
    @FindBy(xpath = "//div[contains(@class, 'oxd-autocomplete-dropdown')]//span")
    List<WebElement> suggestionsList;

    
    @FindBy(xpath = "//div[contains(@class, 'oxd-autocomplete-dropdown')]//span")
    WebElement suggestions;
    
    @FindBy(xpath = "//label[text()='Status']/following::div[contains(@class, 'oxd-select-text-input')]")
    WebElement statusDropdown;

    @FindBy(xpath = "//div[@role='listbox']//span[text()='Disabled']")
    WebElement selectDisabled;

    @FindBy(xpath = "//label[text()='Username']/following::input")
    WebElement usernameInput;

    @FindBy(xpath = "//label[text()='Password']/following::input[@type='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//label[text()='Confirm Password']/following::input[@type='password']")
    WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[@type='submit' and contains(., 'Save')]")
    WebElement saveButton;
    
    
    // Methods to interact with elements

    public void selectUserRole() {
        userRoleDropdown.click();
        selectAdmin.click();
    }

//    public void enterEmployeeName(String name) {
//        employeeNameInput.sendKeys(name);
//    }
    
    // Method to type in the employee name and select from suggestions
    public void selectEmployeeName(String nameToType, String nameToSelect) throws Exception {
        employeeNameInput.sendKeys(nameToType);  // Type in the input field

        // Wait until suggestions are visible
        action.waitAndVisibility(suggestions);
        Thread.sleep(2000);
        // Iterate through suggestions and select the matching one
        for (WebElement suggestion : suggestionsList) {
            if (suggestion.getText().equalsIgnoreCase(nameToSelect)) {
            	action.scrollIntoView(suggestion);
            	action.CombinedClick(suggestion);
                break;
            }
        }
    }

    public void selectStatus() {
        statusDropdown.click();
        selectDisabled.click();
    }

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
    	//Faker faker =new Faker();
    	//String pwd =faker.internet().password(7, 10, true, true, true)+number;
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(password);
    }

    public void clickSave() {
        saveButton.click();
    }
    
    //********************************************************************************//
    
    @FindBy(xpath = "//h5[text()='System Users']")
    public WebElement systemUsers;
    
    public boolean getSystemUserDisplay() {
        try {
            Thread.sleep(2000);
            return systemUsers.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    
 // Username input field
    @FindBy(xpath = " //label[text()='Username']/following::input[@class='oxd-input oxd-input--active']")
    public WebElement username;
    
    public void getUserName() {
    	System.out.println("username"+BaseClass.userName);
    	username.sendKeys(BaseClass.userName);
    }

    
    
    // User Role dropdown
    @FindBy(xpath = "//label[text()='User Role']/following::div[contains(@class, 'oxd-select-wrapper')]")
    public WebElement userRole;
    
    @FindBy(xpath = "//div[@role='listbox']//span[text()='Admin']")
    public WebElement userAdmin;
    
    public void getUserRole() throws InterruptedException {
    	userRole.click();
    	Thread.sleep(1000);	
    	//action.scrollIntoView(userAdmin);
    	userAdmin.click();
    }

    // Employee Name input field
    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    public WebElement employeeName;
    
    public void getEmpName() throws Exception {
    	
//    	employeeName.sendKeys(BaseClass.empName);
//    	action.scrollIntoView(employeeName);
//    	action.javascriptClick(employeeName);
    }
    

    // Status dropdown
    @FindBy(xpath = "//label[text()='Status']/following::div[contains(@class, 'oxd-select-wrapper')]")
    public WebElement statusDrop;
    
 // Status dropdown
    @FindBy(xpath = "//div[@role='listbox']//span[text()='Disabled']")
    public WebElement statusDropDisabled;
    
    public void getStatusDropDown() throws InterruptedException {
    	action.scrollUp();
    	statusDrop.click();
    	Thread.sleep(1000);
    	statusDropDisabled.click();
    	Thread.sleep(2000);
    }
    
    @FindBy(xpath = "//button[text()=' Search ']")
    public WebElement searchUser;
    
    public void getSearchUser() throws Exception {
    	action.javascriptClick(searchUser);
    //	searchUser.click();
    }
//*******************************************************************************

    //job category
    
    @FindBy(xpath = "//span[@class='oxd-topbar-body-nav-tab-item' and contains(text(),'Job')]")
    public WebElement job;
    
    public void getJob() {
    	job.click();
    }
    
    @FindBy(xpath = "//a[contains(text(),'Job Titles')]")
    public WebElement jobTitle;
    
    public void getJobTitle() {
    	jobTitle.click();
    }
    
    @FindBy(css  = "button.oxd-button--secondary")
    public WebElement addBtn;
    
    public void getAddBtn() {
    	addBtn.click();
    }
    
    @FindBy(xpath = "//label[text()='Job Title']/following::input[1]")
    public WebElement addJobTitle;
    
    public void getAddJobTitle(String jobName) {
    	addJobTitle.sendKeys(jobName);
    }
    
    @FindBy(xpath = "//button[text()=' Save ']")
    public WebElement jobSaveBtn;
    
    public void getJobSaveBtn() throws Exception {
    	action.javascriptClick(jobSaveBtn);
    }
    ///@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    
    @FindBy(xpath = "//a[contains(text(),'Pay Grades')]")
    public WebElement payGrades;
    
    public void getPayGrades() {
    	payGrades.click();
    }
    
    @FindBy(xpath = "//label[text()='Name']/following::input[1]")
    public WebElement nameInput;
    
    public void getNameInput(String name) {
    	nameInput.sendKeys(name);
    }
    
    @FindBy(xpath = " //button[text()=' Save ']")
    public WebElement saveBtn;
    
    public void getSaveBtn() {
    	saveBtn.click();
    }
    
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44
    
    @FindBy(xpath = "//a[contains(text(),'Employment Status')]")
    public WebElement empStatus;
    
    public void getEmpStatus() {
    	empStatus.click();
    }
    
    @FindBy(xpath = "//label[text()='Name']/following::input[1]")
    public WebElement empnameInput;
    
    public void getempNameInput(String name) {
    	empnameInput.sendKeys(name);
    }
    
    @FindBy(xpath = "//a[contains(text(),'Job Categories')]")
    public WebElement jobCategory;
    
    public void getJobCategory() {
    	jobCategory.click();
    }
    
    @FindBy(xpath = "//a[contains(text(),'Work Shifts')]")
    public WebElement workShifts;
    
    public void getWorkShifts() {
    	workShifts.click();
    }
    
    @FindBy(xpath = "//label[text()='Shift Name']/following::input[1]")
    public WebElement shiftName;
    
    public void getShiftName(String name) {
    	shiftName.sendKeys(name);
    }
    
   //**************************************************************
    
    @FindBy(xpath = "//span[contains(@class,'-nav-tab-item' )and contains(text(),'Qualifications ')]")
    public WebElement qualification;
    
    public void getQualification() {
    	qualification.click();
    }
    
    @FindBy(xpath = "//a[contains(@class,'-nav-tab-link' )and contains(text(),'Skills')]")
    public WebElement skills;
    
    public void getSkills() {
    	skills.click();
    }
    
    @FindBy(xpath = "//label[text()='Name']/following::input[1]")
    public WebElement skillName;
    
    public void getSkillName(String name) {
    	skillName.sendKeys(name);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    @FindBy(xpath = "//button[@title='Assign Leave']")
    WebElement assignLeave;

    public void getAssignLeave() throws InterruptedException {
    	System.out.println("driver " +driver);
    	
    	//JavascriptExecutor js = (JavascriptExecutor) driver;
    	//js.executeScript("window.scrollBy(0,400)");
    	//js.executeScript("arguments[0].click();",assignLeave);
    	Thread.sleep(5000);
    	driver.findElement(By.xpath("//button[@title='Assign Leave']")).click();
    	//assignLeave.click();
    }
}
