package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//Log4j2
import org.apache.logging.log4j.Logger;//Log4j2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
	
	public static WebDriver driver;//from video 52 this is needed make it as a common variable using 'static' keyword.
	//public WebDriver driver;
	public Logger logger;//Log4j2
	public Properties p;
	
	
	@BeforeClass(groups = {"Sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setUp(String os,String br) throws IOException {//execute only once so beforeCLass annotation is used
		
	//loading config.properties file
		
		FileReader file=new FileReader("./src//test//resources//config.properties");
		//FileInputStream file=new FileInputStream("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		logger=LogManager.getLogger(this.getClass());//Log4j2.xml is store in variable 'logger'
	
	
//for selenium grid setup::

//check config.properties file for the below scenarios:
		
  //for execution_env is "remote":::-
	
	/* 	if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
		
			DesiredCapabilities capabilities=new DesiredCapabilities();
		
		//operating system(os)::from xml
			
			if(os.equalsIgnoreCase("windows")) 
			{
				capabilities.setPlatform(Platform.WIN10);
			
			}
			else if(os.equalsIgnoreCase("linux")) 
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			
			else if(os.equalsIgnoreCase("mac")) 
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else 
			{
				System.out.println("No matching os");
				return;
			}
		
		
		//browser section::from xml
			
			switch(br.toLowerCase())
			{
			case "chrome":	capabilities.setBrowserName("chrome"); break;
			case "edge":	capabilities.setBrowserName("MicrosoftEdge"); break;
			case "firefox":	capabilities.setBrowserName("firefox"); break;
			
			default:System.out.println("no matching browser....");return;
		
		     }

			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);//hub URL
			
		}// till here
  
//for execution_env is "local"::::-
	
		/*	if (p.getProperty("execution_env").equalsIgnoreCase("local")) 
			{
				
				switch(br.toLowerCase())
				{
				case "chrome":	driver=new ChromeDriver(); break;
				case "edge":	driver=new EdgeDriver(); break;
				case "firefox":	driver=new FirefoxDriver();break;
				
				default:System.out.println("invalid browser name....");return;
				
			}
				
		} //till here */
			
	//for normal execution::
			
			  switch(br.toLowerCase())
				{
				case "chrome":	driver=new ChromeDriver(); break;
				case "edge":	driver=new EdgeDriver(); break;
				case "firefox":	driver=new FirefoxDriver();break;
				
				default:System.out.println("invalid browser name....");return;
			
				}//till here 
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//dri+ver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
		//driver.get("https://demo.opencart.com/en-gb?route=account/register");
		driver.get(p.getProperty("appURL"));

	}

	@AfterClass(groups = {"Sanity","Regression","Master","Datadriven"})
	public void tearDown() {//execute only once

		if (!(driver==null)) {
		driver.quit();
			
		}
	}
	
	//For multiple registration,we need different email and others things.
		//this we can do by generating random string using java-method and append that string with other details
		
		//using method 'randomAlphabetic(no of chars needed in string)' from class 'RandomStringUtils' from 'commons-library'. 
		
		public String randomString() {
			
			String generatedString = RandomStringUtils.randomAlphabetic(5);
			return generatedString;
			
		} 
		
		public String randomNumber() {

			String generatedNumber = RandomStringUtils.randomNumeric(10);
			return generatedNumber;
		}
		
		public String randomAlphaNumeric() {
			String generateString=RandomStringUtils.randomAlphabetic(5); 
		    String generateNumber = RandomStringUtils.randomNumeric(5);
			String alphaNumericSting=generateString+"@"+generateNumber;
			return alphaNumericSting;
			
		}
		
		public String captureScreenshot(String tname) { //name:name of screnshot=methodName presult in result-captured with timestamp
			 String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			 
			 TakesScreenshot  takescreenShot=(TakesScreenshot) driver;//one more driver will be created other then setup() driver.
			                                                          //conflict
			                                                          //make thr global webdriver as 'static'
			 File sourceFile = takescreenShot.getScreenshotAs(OutputType.FILE);
			 
			 String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
			 File targetFile=new File(targetFilePath);
			 
			 sourceFile.renameTo(targetFile);
			
			 return targetFilePath;//screenshot path is returned

			
		}
		

}
