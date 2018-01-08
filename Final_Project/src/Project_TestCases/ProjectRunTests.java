package Project_TestCases;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import projectUtilities.BaseFunction;
//import projectUtilities.CommonFunction;
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 

public class ProjectRunTests extends BaseFunction
{
	
	/*
	 * This Test case check the Our stores page, by using Sikuli it will verify 
	 * that clicking on a store location will open the correct location dialog.
	*/
	@Test
	public void test01_StoreLocator() throws Exception
	{
		Osf.OpenOneOfTheStoresLocationCheckingAddressAndDialog();
	}
	
	/*
	 * This Test case check the Sign in page. entering different credentials 
	 * and verify that correct message appear according to credentials. 
	 * This test case and the next one are using an Excel file with Data.
	 */
	@Test
	public void test02_SignIn() throws Exception 
	{
		Hef.ClickOnSignIn();
		fsf.LoginToPage();
	}

	/*
	 * This Test case check the Contact us page. 
	 * Verify and check file and sending the Contact us form with different content.
	 */
	@Test
	public void test03_SendContactUsForm() throws Exception 
	{
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
	public void test04_SearchForAnItemAndAddToCart() throws Exception 
	{
		Saf.SearchForAnItemAndAddToShoppingCart();
	}

	
}