package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import org.testng.annotations.Parameters;

import com.github.javafaker.Faker; 

public class BaseClass {
	
	  public static WebDriver driver ;
	  public Logger logger;
	  public  Properties p;
	  
	  public static Faker faker =new Faker();
	  public static String userName=faker.name().username().substring(0, 5);
	 // public static String empName= "Orange Test";
	  

	  @BeforeTest
	  @Parameters({"os","browser"})
	  public  void setUp(String os,String br) throws IOException {
		  	
		  FileReader file = new FileReader("./src//test//resources//config.properties");
		  p=new Properties();	
		  p.load(file);
		  
		//LogManager.getLogger(this.getClass());	
		// multiple browsers 
		  
		switch(br.toLowerCase()) 
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge"   : driver=new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver();break;
		default : System.out.println("Invalid browser name..."); return; // it will return from the driver no other further process will continue
		}
		
	    driver.get(p.getProperty("url"));
		driver.manage().window().maximize();
	    }  		

	 @AfterTest
	 public void tearDown() {
	  // Close the browser
		 if (driver != null) {
			//   driver.quit();
			}
	}
	 public String captureScreen(ITestResult result) throws IOException {
	        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	        
	        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	        String fileName = result.getMethod().getMethodName() + "_" + timeStamp + ".jpg";
	        File destination = new File("./Screenshots/" + fileName);
	        
	        FileUtils.copyFile(sourceFile, destination);
	        return destination.getAbsolutePath();
	        
	    }
}
