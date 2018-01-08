package projectUtilities;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.sikuli.script.Screen;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import pageObjects.ContactUsPage;
import pageObjects.FooterAreaPage;
import pageObjects.HeaderAreaPage;
import pageObjects.OurStoresPage;
import pageObjects.RegistrationPage;
import pageObjects.SearchAreaPage;
import pageObjects.SearchToShoppingCartPage;
import pageObjects.SignInPage;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import java.time.Instant;

public class BaseFunction
{
	public final static DateFormat Calendar = null;
	public static WebDriver driver=null;
	public static ExtentReports extent;
	public static ExtentTest test;
	static Instant instant = Instant.now();
	public static long localDate = System.currentTimeMillis()/1000;
	public static String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());	
	public static Screen screen;
	public static final Logger logger = Logger.getLogger(BaseFunction.class.getName());
	public static String WhichBrowserType;
	//These variables can be replace with different data source and are used in SearchToShopping_Factory class and SearchArea_Factory class.
	public String TitleInShoppingDiv = "There is 1 item in your cart.";
	public String ProductPrice = "$27.00";
	public String ShippingPrice = "$2.00";
	public String TotalPrice = "$29.00";
	public String ProductName = "Blouse";
	public String TermSearch = "Blouse";
	
	public static SignInPage fsf;
	public static HeaderAreaPage Hef;
	public static FooterAreaPage Fef;
	public static RegistrationPage Ref;
	public static ContactUsPage  Cuf;
	public static OurStoresPage Osf;
	public static SearchAreaPage Saf;
	public static SearchToShoppingCartPage Ssc;
	
	
	
	//Loh4j XML file
	public void loadlog4j()
	{
			DOMConfigurator.configure("log4j.xml");
	}

	//Taking Screenshot function
	public static  String getScreenshot() throws IOException, ParserConfigurationException, SAXException
	{
		String SsPath = getData("ScreenshotsReportFilePath") + timeStamp() + localDate +".png";	
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(SsPath));
		return SsPath;
	}
	
	//Reading and Connection to XML file
	public static String getData (String nodeName) throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = new File("External_Files/XML/Project_Conf.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile); 
		doc.getDocumentElement().normalize();
		return doc.getElementsByTagName(nodeName).item(0).getTextContent();
	}
	
	//************************Reports function*********************************
	public static  void  InstanceReports() throws ParserConfigurationException, SAXException, IOException
	{
		extent= new ExtentReports(getData("ReportFilePath") + timeStamp +"-"+ localDate+getData("Reporfilename"),true); //Report file name. 
	}
	public static void initReportTest(String testName,String testDescription)
	{
		test = extent.startTest(testName, testDescription);
	}
	public static void FinalizedreportTest()
	{
		extent.endTest(test);
	}
	public static void FinalizeExtentReport()
	{
		extent.flush();
		extent.close();
	}
	//*********************end Report function******************************* 

	//Adding report to log and reports when test case start.
	public static void StartTest(String OpenTest)
	{
		logger.info(OpenTest);
		test.log(LogStatus.PASS, OpenTest);
	}
	
	//Adding report to log and reports when test case End.
		public static void EndTest(String CloseTest)
		{
			logger.info(CloseTest);
			test.log(LogStatus.INFO, CloseTest);
		}
	
	//Switch Browser
	public static void InitBrowser(String BrowserType) throws ParserConfigurationException, SAXException, IOException
	{
		switch(BrowserType.toLowerCase())
		{
		case "chrome":
			driver = ChromeDriver();
			break;
		case "firefox":
			driver =  initFFDriver();
			break;
		case "ie":
			driver = initIEDriver();
			break;
		}
		
		logger.info("Open brwoser :"+BrowserType);
		driver.manage().window().maximize();
		driver.get(getData("URL"));
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		screen = new Screen();
		WhichBrowserType = getData("BrowserType");
	}	
	
	public static WebDriver ChromeDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.chrome.driver",getData("ChromeDriverPath"));
		WebDriver driverChrome = new ChromeDriver();
		return driverChrome;
	}
	
	public static WebDriver initFFDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.gecko.driver", getData("FFDriverPath")); 
		WebDriver driverFF = new FirefoxDriver();
		return driverFF;
	}
	
	public static WebDriver initIEDriver() throws ParserConfigurationException, SAXException, IOException
	{
		System.setProperty("webdriver.ie.driver",getData("IEDriverPath"));
			WebDriver driverIE = new InternetExplorerDriver();
		return driverIE;
	}
	
	public static String timeStamp()
	{
		String ThetimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return ThetimeStamp;	
	}

	//*********************Annotations from Test class******************************* 
	@Rule public TestName name =new TestName();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		InitBrowser(getData("BrowserType"));
		InstanceReports();
		pageObjects.managePages.init();
	}
	
	@After
	public void DoAfterTest()
	{
		EndTest("End of Test!!!");
		FinalizedreportTest();
	}
	
	@Before
	public void DoBeforeTest()
	{
		initReportTest(name.getMethodName().split("_")[0],CommonFunction.SeperateString(name.getMethodName().split("_")[1]));
		
		
	}
	
	@AfterClass
	public static void setUpAfterClass()
	{
		FinalizeExtentReport();
		driver.quit();
	}
}