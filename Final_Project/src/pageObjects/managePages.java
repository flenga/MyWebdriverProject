package pageObjects;

import org.openqa.selenium.support.PageFactory;
import projectUtilities.BaseFunction;
public class managePages extends BaseFunction
{
	public static void init()
	{
		fsf = PageFactory.initElements(driver, SignInPage.class);
		Hef = PageFactory.initElements(driver, HeaderAreaPage.class);
		Fef = PageFactory.initElements(driver, FooterAreaPage.class);
		Ref = PageFactory.initElements(driver, RegistrationPage.class);
		Cuf = PageFactory.initElements(driver, ContactUsPage.class);
		Osf = PageFactory.initElements(driver, OurStoresPage.class);
		Saf = PageFactory.initElements(driver, SearchAreaPage.class);
		Ssc = PageFactory.initElements(driver, SearchToShoppingCartPage.class);
	}
}
