package pageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import projectUtilities.BaseFunction;
import projectUtilities.CommonFunction;

public class HeaderAreaPage extends BaseFunction
{
	
	public String ValuToreport;
	@FindBy(how = How.LINK_TEXT, using = "Sign in")
	public WebElement SignInLink;
	
	@FindBy(how = How.LINK_TEXT, using = "Contact us")
	public WebElement ContactUs;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign out")
	public WebElement SignOut;
	
	@FindBy(how = How.CSS, using = "a[title='View my customer account']")
	public WebElement UserName;
	
	@FindBy(how = How.ID, using = "header_logo")
	public WebElement SiteLogo;
	
	
	public  HeaderAreaPage () 
	{
		this.driver = driver;

	}
	
	public void ClickOnSignIn() throws Exception 
	{
		ValuToreport=SignInLink.getText();
		CommonFunction.ClickOnElement(SignInLink,ValuToreport);
	}
	
	public void ClickOnContactUs() throws Exception 
	{
		ValuToreport=ContactUs.getText();
		CommonFunction.ClickOnElement (ContactUs,ValuToreport);
	}
	
	public void ClickOnCustomerName() throws Exception 
	{
		ValuToreport=ContactUs.getText();
		CommonFunction.ClickOnElement (UserName,ValuToreport);
	}
	
	public void ClickOnSignOut() throws Exception 
	{
		ValuToreport=SignOut.getText();
		CommonFunction.ClickOnElement (SignOut,ValuToreport);
	}
	
	public void ClickOnLogoSite() throws Exception 
	{
		ValuToreport=SiteLogo.getText();
		CommonFunction.ClickOnElement(SiteLogo,ValuToreport);
	}
}