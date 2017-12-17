package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import Project_Utilities.BaseFunction;
import Project_Utilities.CommonFunction;

public class FooterAreaPage extends BaseFunction
{
	CommonFunction cf = new CommonFunction();
	public String ValuToreport;
	
	@FindBy(how = How.LINK_TEXT, using = "Our stores")   
	public WebElement StoreLink;
	
	public  FooterAreaPage (WebDriver driver) 
	{
		FooterAreaPage.driver = driver;
	}
	
	public void ClickOnStoreLink() throws Exception 
	{
		ValuToreport=StoreLink.getText();
		cf.ClickOnElement(StoreLink,ValuToreport );
	}
}
