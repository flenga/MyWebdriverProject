package Page_Object_Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import Project_Utilities.Base_Project;
import Project_Utilities.Project_CommonFunction;

public class Header_Factory extends Base_Project
{
Project_CommonFunction comfunc = new Project_CommonFunction();
	public WebDriver driver;
	
	@FindBy(how = How.LINK_TEXT, using= "Sign in")
	public WebElement SignInLink;
	
	@FindBy(how = How.LINK_TEXT, using= "Contact us")
	public WebElement ContactUs;
	
	@FindBy(how = How.LINK_TEXT, using= "Sign out")//After signin
	public WebElement SignOut;
	
	@FindBy(how = How.CSS, using= "a[title='View my customer account']")//After signin replace the link-'SignIn'
	public WebElement UserName;
	
	@FindBy(how = How.ID, using= "header_logo")//After signin replace the link-'SignIn'
	public WebElement SiteLogo;
	
	
		
	// Need to set functions to click on The links in the header according to test case
	public  Header_Factory () 
	{
		this.driver = driver;

	}
	
	public void ClickOnSignIn() throws Exception 
	{
		Project_CommonFunction.ClickOnLinkInHeader(SignInLink);
	}
	
	public void ClickOnContactUs() throws Exception 
	{
		Project_CommonFunction.ClickOnLinkInHeader(ContactUs);
	}
	
	public void ClickOnCustomerName() throws Exception 
	{
		Project_CommonFunction.ClickOnLinkInHeader(UserName);
	}
	
	public void ClickOnSignOut() throws Exception 
	{
		Project_CommonFunction.ClickOnLinkInHeader(SignOut);
	}
	
	public void ClickOnLogoSite() throws Exception 
	{
		Project_CommonFunction.ClickOnLinkInHeader(SiteLogo);
	}
}
