package Project_Utilities;
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
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
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
	public String productPrice = "$24.00";
	public String ShippingPrice = "$7.01";
	public String TotalPrice = "$31.010";
	public String ProductName = "Blouse";
	
	//Loh4j XML file
	public void loadlog4j()
	{
			DOMConfigurator.configure("log4j.xml");
	}

	//Screenshot function
	public  String getscreenshot() throws IOException, ParserConfigurationException, SAXException

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
	public static void StartTest(String OpeNTest)
	{
		logger.info(OpeNTest);
		test.log(LogStatus.PASS, OpeNTest);
	}
	
	//Adding report to log and reports when test case End.
		public static void EndTest(String OpeNTest)
		{
			logger.info(OpeNTest);
			test.log(LogStatus.INFO, OpeNTest);
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
	
	public  String timeStamp()
	{
		String ThetimeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		return ThetimeStamp;	
	}
}