package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import net.bytebuddy.agent.builder.AgentBuilder.DescriptionStrategy;
import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter; // UI of the report - look and feel
	public ExtentReports extent; // common info - OS browser, Who
	public ExtentTest test; // status, screenshots
	
	String repName;
	
	public void onStart(ITestContext testContext) {
		
		/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp = df.format(dt);*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		repName = "Test-Report-" + timeStamp + ".html";  
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/Reports/" +repName ); //location
		
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); // title
		sparkReporter.config().setReportName("OpenCart Functional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK );
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "Open Cart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
		
	  }
	
	public void onTestSuccess(ITestResult result) {
	   test = extent.createTest(result.getTestClass().getName()); //create new entry
	   test.assignCategory(result.getMethod().getGroups()); // to display groups in testcase
	   test.log(Status.PASS, result.getName() + " successfully executed"  ); // update status
	   
	  }
	
	public  void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName()); 
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() +" TestCase Failed" ); 
		test.log(Status.INFO, result.getThrowable().getMessage()); 
		
		try
		{
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	  }
	
	public  void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getClass().getName()); 
		test.assignCategory(result.getMethod().getGroups()); 
		test.log(Status.SKIP, result.getName() + " got skipped"); 
		test.log(Status.INFO, result.getThrowable().getMessage());
	  }
	
	public void onFinish(ITestContext context) {
	    extent.flush();
	    
	    String pathOfExtentReport = System.getProperty("user.dir") + "/reports/" + repName;
	    File extentReport = new File(pathOfExtentReport);
	    
	    try
	    {
	    	Desktop.getDesktop().browse(extentReport.toURI());
	    } catch(IOException e)
	    {
	    	e.printStackTrace();
	    }
	    
	   /* try { 
	    	URL url = new URL("file: ///" + System.getProperty("user.dir")+"\\reports\\" + repName);
	    
	    // create the email maessage
	    ImageHtmlEmail email = new ImageHtmlEmail();
	    email.setDataSourceResolver(new DataSourceUrlResolver(url));
	    email.setHostName("smtp.googlemail.com");
	    email.setSmtpPort(465);
	    email.setAuthenticator(new DefaultAuthenticator("yugamani.s.k.s@gmail.com","yugamani@19"));
	    email.setSSLOnConnect(true);
	    email.setFrom("yugamani.s.k.s@gmail.com"); //sender
	    email.setSubject("Test Results");
	    email.setMsg("Please find the attached report.");
	    email.addTo("yugakavivel@gmail.com");
	    email.attach(url,"extent report", "please check report");
	    email.send();
	     
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    } */
	  }

}