package utilities;

import java.awt.Desktop;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


//add this report to all the xml-file using <listeners> tag before <test> tag
public class ExtentReportManager implements ITestListener {
	
	public ExtentSparkReporter sparkreporter;
	public  ExtentReports extentReport;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testcontext) {
		
	String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stamp to get new report everytime
	//get the reportname using timestamp
	
	repName="Test-Report"+timeStamp+".html";
	
	//to make extentReport UI:::::use above reportname and location as parameter to  ExtentSparkReporter object
	
	sparkreporter=new ExtentSparkReporter(".\\reports\\" + repName); //specify the location of the report to make Report-UI
	
	//set diiferent features on extentReport
	
	sparkreporter.config().setDocumentTitle("Opencart Automation Report");//report title
	sparkreporter.config().setReportName("Opencart Functional Testing"); //report name
	sparkreporter.config().setTheme(Theme.DARK);
	
	
	//create extentReport-object to populate common info in the report by Attachhing it to sparkReporter object-ref.
	//things below we get during runtime only Dynamically.
	
		extentReport=new ExtentReports();
		extentReport.attachReporter(sparkreporter);
		
		extentReport.setSystemInfo("Application", "Opencart");
		extentReport.setSystemInfo("Module", "Admin");
		extentReport.setSystemInfo("Sub-module", "Customers");
		extentReport.setSystemInfo("UserName", System.getProperty("user.name"));
		extentReport.setSystemInfo("Environment", "QA");
		
		//when the onStart() method is executed its execution detail will be stored in 'testContext' variable
		
		//get the os from XML file:: using the above 'testContext' varible and store it in extentReport variable using setSystemInfo()
		
		String os = testcontext.getCurrentXmlTest().getParameter("os");
		extentReport.setSystemInfo("Operating System", os);
		
		String browser=testcontext.getCurrentXmlTest().getParameter("browser");
		extentReport.setSystemInfo("Browser", browser);
		
		//get and set the groupname of the Test-method inside extentReport
		
		List<String> includeGroups=testcontext.getCurrentXmlTest().getIncludedGroups();
		if (!includeGroups.isEmpty()) {
			extentReport.setSystemInfo("Groups", includeGroups.toString());
			
		}	
		
	}
	
	//when TC/test-method is passes,this method will be triggered.
		//in this method,we are creating a 'test'(new-entry) in the report,
	    //-->using extentReport-object ref with createTest(param) method
		                                            //param=getName() of result we get from Test-Method execution.
		//-->using new 'test' created in above step,display the groupname in the report
		//-->using log() method::set the log result inside the report on ExtentTest obj-ref with above param name
		
	//category==group
	
	public void onTestSuccess(ITestResult result) {//result will capture all the test o/p result in this variable
		
		test=extentReport.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());//to diplay group-name  associated with test-methodname in report
		test.log(Status.PASS, result.getName()+" has successfully created");//classname is o/p of getName()
		
		
	}
	
	public void onTestFailure(ITestResult result) {
		
		test=extentReport.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {

			BaseClass baseclassObj = new BaseClass();
			String imgPath = baseclassObj.captureScreenshot(result.getName());// o/p:::path/location of screenshot is returned

			test.addScreenCaptureFromPath(imgPath);// method to add/attach this img to the report.

		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	public void onTestSkipped(ITestResult result) {
		test=extentReport.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.SKIP,result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		}
	
	public void onFinish(ITestContext testContext) {
    
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport=new File(pathOfExtentReport);//capture the extentReport file which we want to open
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	
		

}

