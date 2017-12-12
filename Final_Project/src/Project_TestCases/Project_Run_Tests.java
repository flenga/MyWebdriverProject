package Project_TestCases;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;
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
		InitBrowser(getData("BrowserType"));
		
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
	
	/*
	 * This Test case check the Our stores page, by using Sikuli it will verify 
	 * that clicking on a store location will open the correct location dialog.
	*/
	
	@Test
	public void test_1_Storelocator() throws Exception
	{
		initReportTest("First test","Verify:Our Stores");
		StartTest("Store locator - Start test #1");
		Osf.OpenOneOfTheStoresLocationCheckingAddressAndDialog();
	}
	
	/*
	 * This Test case check the Sign in page. entering different credentials 
	 * and verify that correct message appear according to credentials. 
	 * This test case and the next one are using an Excel file with Data.
	 */
	
	@Test
	public void test_2_SignIn() throws Exception 
	{
		initReportTest("Second test","Verify:The login page of The Site ");
		StartTest("Sign in - Start test #2");
		Hef.ClickOnSignIn();
		fsf.LoginToPage();
	}

	/*
	 * This Test case check the Contact us page. 
	 * Verify and check file and sending the Contact us form with different content.
	 */
	
	@Test
	public void test_3_SendContactUsForm() throws Exception 
	{
		initReportTest("Third test","Verify:Contact Us");
		StartTest("Send Contact Us Form - Start test #3");
		Hef.ClickOnContactUs();
		Cuf.SendAmessage();
		Hef.ClickOnLogoSite();
	}

	/*
	 * This Test case check the field Search of the site.
	 * After searching for an item adding the Item to the Shopping cart 
	 * and verify that details are correct!! 
	 */
	
	@Test
	public void test_4_SearchForAnItemAndaddToCart() throws Exception 
	{
		initReportTest("Fourth test","Verify:Search and Shopping cart");
		StartTest("Verify:Search and Shopping cart- Start test #4");
		Saf.SearchForAnItemAndAddToShoppingCart();
	}

	@After
	public void DoAfterTest()
	{
		EndTest("End of Test!!!");
		FinalizedreportTest();

	}
	
	@AfterClass
	public static void setUpAfterClass()
	{
		FinalizeExtentReport();
		driver.quit();

	}


}
