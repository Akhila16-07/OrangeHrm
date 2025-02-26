package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Multipart;
import javax.mail.BodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	    public ExtentSparkReporter sparkReporter;
	    public ExtentReports extent;
	    public ExtentTest test;

	    String repName;

	    public void onStart(ITestContext testContext) {
	        /*
	        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
	        Date dt = new Date();
	        String currentdatetimestamp = df.format(dt);
	        */
	    	System.out.println("ONstart");
	        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // time stamp
	        repName = "Test-Report-" + timeStamp + ".html";
	        sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName); // specify location of the report
	        System.out.println(repName);

	        sparkReporter.config().setDocumentTitle("Orange Hrm Automation Report"); // Title of report
	        sparkReporter.config().setReportName("Orange Hrm Functional Testing"); // Name of the report
	        sparkReporter.config().setTheme(Theme.DARK);

	        extent = new ExtentReports();
	        extent.attachReporter(sparkReporter);
	        extent.setSystemInfo("Application", "Orange Hrm");
	        extent.setSystemInfo("Module", "Admin");
	        extent.setSystemInfo("Sub Module", "Customers");
	        extent.setSystemInfo("User Name", System.getProperty("user.name"));
	        extent.setSystemInfo("Environment", "QA");

	        String os = testContext.getCurrentXmlTest().getParameter("os");
	        extent.setSystemInfo("Operating System", os);

	        String browser = testContext.getCurrentXmlTest().getParameter("browser");
	        extent.setSystemInfo("Browser", browser);

	        List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	        if(!includedGroups.isEmpty()) {
	            extent.setSystemInfo("Groups", includedGroups.toString());
	        }

	    }

	    public void onTestSuccess (ITestResult result) {
	    	System.out.println("test sucess");
	    	test=extent.createTest (result.getMethod().getMethodName());
			test.assignCategory (result.getMethod().getMethodName()); 
			test.log(Status.PASS, result.getName()+" got successfully executed");
	    }
	    
	    public void onTestFailure(ITestResult result) {
	    	test=extent.createTest (result.getMethod().getMethodName());
	        test.assignCategory(result.getMethod().getGroups());      
	        test.log(Status.FAIL, result.getName() + " got failed");
	        test.log(Status.INFO, result.getThrowable().getMessage());

	        try {
	            BaseClass base = new BaseClass(); 
	            if(base.driver!=null){
	            	  String screenshotPath = base.captureScreen(result);  // Calling captureScreen method
	            	  test.fail("Screenshot", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	            }
	          
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	   
		public void onTestSkipped (ITestResult result) {
			
			test=extent.createTest (result.getMethod().getMethodName());
			test.assignCategory (result.getMethod().getGroups());
			test.log(Status.SKIP, result.getName()+" got skipped"); 
			test.log(Status.INFO, result.getThrowable().getMessage());
			
		}
	
		
		public void onFinish(ITestContext testContext) {
				extent.flush();
				System.out.println("flush");
				
				String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName; 
				File extentReport = new File(pathOfExtentReport);
				
				try {
					Desktop.getDesktop().browse (extentReport.toURI());
				} catch (IOException e) {
					e.printStackTrace();
				}
			//to send email
				/*
				 try { 
					 System.out.println("Email is starting to send");
					    final String username = "akhireddy9642@gmail.com";
					    final String password = "Xog32763";
					    String smtpHost = "smtp.office365.com";
					    int smtpPort = 587;
					    
					    //normal mail
					    
					    //upendrareddy1212@gmail.com
					    //hqpolukazazrvlbi 
					    //smtp.gmail.com
					    
					    //pro
					    //upendra.y@etg.digital
					    //Xog32763
					    //smtp.office365.com
					    
					
						//new version	 URL url = new URI("file://"+System.getProperty("user.dir")+"\\reports\\"+repName).toURL();
						File file= new File("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);

								
					    File reportFilePath =file;
				        String subject1 = "Providio Automation Test Report And Screenshot";
				        String body1 = "Please find the attached Automation Test Report of Providio Project.";

					  //  String reportScreenshot = TD.ScreenShot;
					    //String subject2 = "";
					    //String body2 = "Please find the attached screenshot Automation Test Report of Provido Project";

					   //String[] recipients = { "krishnarjun.c@etg.digital", "akhila.m@etg.digital", "shankar.challa@etg.digital", "akhila.v@etg.digital","pushpa.s@etg.digital","bhavya.a@etg.digital","bhaskarrao.s@etg.digital","niveditha.d@etg.digital","salma.s@etg.digital","f8eecde2.etggs.com@apac.teams.ms","akhila.m@etg.digital"};
					   String[] recipients = { "akhila.m@etg.digital"};
					    //

					    sendEmail(username, password, smtpHost, smtpPort, recipients,subject1, body1, reportFilePath);

					    System.out.println("Email sent successfully.");
				
				
				}
				catch (Exception e)
				 { 
				 	e.printStackTrace();
				 }*/
			
		}
			
			private static void sendEmail(final String username, final String password, String smtpHost, int smtpPort, String[] recipients,
		            String subject1, String body1, File reportFilePath) {
				

			    Properties props = new Properties();
			    props.put("mail.smtp.auth", "true");
			    props.put("mail.smtp.starttls.enable", "true");
			    props.put("mail.smtp.host", smtpHost);
			    props.put("mail.smtp.port", smtpPort);
			    props.put("mail.smtp.ssl.trust", "smtp.office365.com");
			    props.put("mail.smtp.debug", "true");

			    Session session = Session.getInstance(props, new Authenticator() {
			        protected PasswordAuthentication getPasswordAuthentication() {
			            return new PasswordAuthentication(username, password);
			        }
			    });

			    try {
			        Message message = new MimeMessage(session);
			        message.setFrom(new InternetAddress(username));

			        InternetAddress[] recipientAddresses = new InternetAddress[recipients.length];
			        for (int i = 0; i < recipients.length; i++) {
			            recipientAddresses[i] = new InternetAddress(recipients[i]);
			        }
			        message.setRecipients(Message.RecipientType.TO, recipientAddresses);
			        
			        message.setSubject(subject1);
			       // message.setSubject(subject2);
			        
			     // Create the message part for the body
		            BodyPart messageBodyPart1 = new MimeBodyPart();
		            messageBodyPart1.setText(body1);
		            
			        //BodyPart messageBodyPart2 = new MimeBodyPart();
			       // messageBodyPart2.setText(body2);
		            
		         // Create the message part for the attachment
		            MimeBodyPart attachmentPart1 = new MimeBodyPart();
		            attachmentPart1.attachFile(reportFilePath);

			        //MimeBodyPart attachmentPart2 = new MimeBodyPart();
			        //attachmentPart2.attachFile(reportScreenshot);

			        // Create a multipart message and set its parts
		            Multipart multipart = new MimeMultipart();
		            multipart.addBodyPart(messageBodyPart1);
		            //multipart.addBodyPart(messageBodyPart2);
				    multipart.addBodyPart(attachmentPart1);
				  //  multipart.addBodyPart(attachmentPart2);

				    // Set the multipart message as the content of the email
				    message.setContent(multipart);
				    message.setSubject(subject1);
				    // Send the email
				    Transport.send(message);

				    System.out.println("Extent report sent successfully.");
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
}
