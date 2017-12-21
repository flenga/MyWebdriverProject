package Project_TestCases;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.support.PageFactory;

import pageObjects.ContactUsPage;
import pageObjects.FooterAreaPage;
import pageObjects.HeaderAreaPage;
import pageObjects.OurStoresPage;
import pageObjects.RegistrationPage;
import pageObjects.SearchAreaPage;
import pageObjects.SearchToShoppingCartPage;
import pageObjects.SignInPage;
import projectUtilities.BaseFunction;
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 

public class ProjectRunTests extends BaseFunction
{
	static SignInPage fsf;
	static HeaderAreaPage Hef;
	static FooterAreaPage Fef;
	static RegistrationPage Ref;
	static ContactUsPage  Cuf;
	static OurStoresPage Osf;
	static SearchAreaPage Saf;
	static SearchToShoppingCartPage Ssc;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		InitBrowser(getData("BrowserType"));
		
		InstanceReports();
		fsf = PageFactory.initElements(driver, SignInPage.class);
		Hef = PageFactory.initElements(driver, HeaderAreaPage.class);
		Fef = PageFactory.initElements(driver, FooterAreaPage.class);
		Ref = PageFactory.initElements(driver, RegistrationPage.class);
		Cuf = PageFactory.initElements(driver, ContactUsPage.class);
		Osf = PageFactory.initElements(driver, OurStoresPage.class);
		Saf = PageFactory.initElements(driver, SearchAreaPage.class);
		Ssc = PageFactory.initElements(driver, SearchToShoppingCartPage.class);
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
		Cuf.SendContactFormAmessage();
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