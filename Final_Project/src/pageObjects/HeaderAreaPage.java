package pageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import projectUtilities.BaseFunction;
import projectUtilities.CommonFunction;

public class HeaderAreaPage extends BaseFunction
{
CommonFunction cf = new CommonFunction();
	
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
		cf.ClickOnElement(SignInLink,ValuToreport);
	}
	
	public void ClickOnContactUs() throws Exception 
	{
		ValuToreport=ContactUs.getText();
		cf.ClickOnElement (ContactUs,ValuToreport);
	}
	
	public void ClickOnCustomerName() throws Exception 
	{
		ValuToreport=ContactUs.getText();
		cf.ClickOnElement (UserName,ValuToreport);
	}
	
	public void ClickOnSignOut() throws Exception 
	{
		ValuToreport=SignOut.getText();
		cf.ClickOnElement (SignOut,ValuToreport);
	}
	
	public void ClickOnLogoSite() throws Exception 
	{
		ValuToreport=SiteLogo.getText();
		cf.ClickOnElement(SiteLogo,ValuToreport);
	}
}