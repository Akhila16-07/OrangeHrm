package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import testBase.BaseClass;

public class BasePage extends BaseClass{

	    public BasePage(WebDriver driver) {
	       this.driver=driver;
	       PageFactory.initElements(driver, this);
	    }
  
}
