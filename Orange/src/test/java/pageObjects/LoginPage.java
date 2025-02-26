package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testBase.BaseClass;

public class LoginPage extends BaseClass{

	  public LoginPage(WebDriver driver) {
	       this.driver=driver;
	       PageFactory.initElements(driver, this);
	    }
	  
	 @FindBy(xpath = "//h5[text()='Login']")
	 WebElement login;
	 public boolean getLogin() {
		 try {
			 return login.isDisplayed();
		 }catch(Exception e) {
			 return false;

		 }
	 }
    // Locators using @FindBy annotation
    @FindBy(xpath = "//input[@name='username']")
    WebElement usernameField;

  //input[@name="password"]
    @FindBy(xpath = "//input[@name='password']")
     WebElement passwordField;
    
    @FindBy(xpath = "//button[contains(@class, 'orangehrm-login-button')]")
     WebElement loginButton;
    
    @FindBy(className  = "a.oxd-brand")
     WebElement loginConfirmation;
    
    // Methods to perform actions on the login page
    public void enterUsername(String username) {
        usernameField.sendKeys(username);
    }
    
    public void enterPassword(String password) {
    	passwordField.clear();
        passwordField.sendKeys(password);
    }
    
    public void clickLogin() {
        loginButton.click();
    }
    public String getLoginConfirmation() {
    	try {    	
    		return(loginConfirmation.getText());
    	}catch (Exception e) {
			return(e.getMessage());
		}
    }
    
}
