package utilities;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.github.javafaker.Faker;

import testBase.BaseClass;

public class ActionUtility extends BaseClass {

	JavascriptExecutor js = (JavascriptExecutor) driver;
	  String username;
	 public void scrollIntoView(WebElement element) {
	            js.executeScript("arguments[0].scrollIntoView(true);", element);	       
	    }
	 
	 public  void javascriptClick(WebElement element) throws Exception {				
		 Thread.sleep(1000);			
		 js.executeScript("arguments[0].click();",element);		
	}
	 

	 public  void CombinedClick(WebElement locatorName) throws Exception {	
	    try {
	        locatorName.click();
	    } catch (Exception e) {		        	
	    	js.executeScript("arguments[0].click();", locatorName);
	     //   System.err.println("Exception while clicking the element: " + e.getMessage());
	    }
	 }
	 
	  public void scrollDown() {		      
	        js.executeScript("window.scrollBy(0,200)");
	    }
	  
	   public  void scrollUp() {      
	        js.executeScript("window.scrollTo(0, 0);");
	    }
	    
	 public static void hover(WebElement element) {
	        Actions actions = new Actions(driver);
	        actions.moveToElement(element).perform();
	 }
	 
	 public  Wait<WebDriver> createFluentWait(WebDriver driver) {
	        return new FluentWait<>(driver)
	            .withTimeout(Duration.ofSeconds(30))
	            .pollingEvery(Duration.ofMillis(10))
	            .ignoring(org.openqa.selenium.NoSuchElementException.class);
	     
	}
		
		
		public  void WaitForClickableElement(WebElement e){
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.elementToBeClickable(e));
			
		}
	
		public  void waitAndVisibility(WebElement e) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(e));
		}

		public String fakeData(String username) {
			Faker faker = new Faker();
	        // Generate random username (faker provides realistic usernames)
	         username = faker.name().username().substring(0, 5);
	        // Generate random password with letters, numbers, and special characters
	        String password = faker.internet().password(7, 10, true, true, true);

	        // Print the generated username and password
	        System.out.println("Random Username: " + username);
	        System.out.println("Random Password: " + password);
	        return username;
		}
		
		public void isSuccessBannerDisplayed() {
			WebElement element = driver.findElement(By.id("oxd-toaster_1"));
			waitAndVisibility(element);
			Assert.assertTrue(element.isDisplayed(), "Success banner not displayed");		
		}
}