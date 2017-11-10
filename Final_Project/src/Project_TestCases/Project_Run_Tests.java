package Project_TestCases;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;

//import org.sikuli.script.*;
	//import org.testng.annotations.DataProvider;
//import org.xml.sax.SAXException;

import Page_Object_Factory.ContactUs_Factory;
import Page_Object_Factory.Footer_Factory;
import Page_Object_Factory.Header_Factory;
import Page_Object_Factory.OurStores_Factory;
import Page_Object_Factory.Registration_Factory;
import Page_Object_Factory.SearchArea_Factory;
import Page_Object_Factory.SearchToShopping_Factory;
import Page_Object_Factory.Sign_In_Factory;
import Project_Utilities.Base_Project;
		
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 

public class Project_Run_Tests extends Base_Project
{
	static Sign_In_Factory fsf;
	static Header_Factory Hef;
	static Footer_Factory Fef;
	static Registration_Factory Ref;
	static ContactUs_Factory  Cuf;
	static OurStores_Factory Osf;
	static SearchArea_Factory Saf;
	static SearchToShopping_Factory Ssc;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
//	System.setProperty(getData("driverChrome"),getData("ChromeDriverPath"));
//	driver =new ChromeDriver();
//	driver.manage().window().maximize();
//	driver.get(getData("URL"));
//	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//	screen = new Screen();
	BrowserSwitch(getData("BrowserType"));
	InstanceReports();
	fsf = PageFactory.initElements(driver, Sign_In_Factory.class);
	Hef = PageFactory.initElements(driver, Header_Factory.class);
	Fef = PageFactory.initElements(driver, Footer_Factory.class);
	Ref = PageFactory.initElements(driver, Registration_Factory.class);
	Cuf = PageFactory.initElements(driver, ContactUs_Factory.class);
	Osf = PageFactory.initElements(driver, OurStores_Factory.class);
	Saf = PageFactory.initElements(driver, SearchArea_Factory.class);
	Ssc = PageFactory.initElements(driver, SearchToShopping_Factory.class);
	}
	@Before
	public void setUp() throws Exception 
	{
		
	}
	
	@Test
	public void test_1_Storelocator() throws Exception
	{
		
		initReportTest("First test","Verify:Our Stores");
		logger.info("Store locator - First test");
		Osf.ClickOnOneOfTheStores() ;
	}

	@Test
	
	public void test_2_SignIn() throws Exception 
	{
	 	initReportTest("Second test","Verify:The login page of The Site ");
	 	logger.info("Sign in -second test");
		Hef.ClickOnSignIn();
		fsf.LoginToPage();
	}
	
	@Test
	public void test_3_SendContactUsForm() throws Exception 
	{
		initReportTest("Third test","Verify:Contact Us");
		logger.info("Send Contact Us Form -Third test");
		Hef.ClickOnContactUs();
		Cuf.SendAmessage();
		Hef.ClickOnLogoSite();
	}
	
	@Test
	public void test_4_SearchForItem() throws Exception 
	{
		initReportTest("Fourth test","Verify:Search and Shopping cart");
		logger.info("Search and Shopping cart- Fourth test");
		Saf.SearchForAnItemAndAddToShoppingCart();
	}
	
	@After
	public void doAfterTest()
	{
		FinalizedreportTest();
	
	}
	@AfterClass
	
	public static void setUpAfterClass()
	{
		FinalizeExtentReport();
		driver.quit();
		
	}
	

}
