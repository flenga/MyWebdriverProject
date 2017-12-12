package Page_Object_Factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import Project_Utilities.Base_Project;
import Project_Utilities.Project_CommonFunction;

public class Footer_Factory extends Base_Project
{
	Project_CommonFunction cf = new Project_CommonFunction();
	public String ValuToreport;
	
	@FindBy(how = How.LINK_TEXT, using= "Our stores")   
	public WebElement StoreLink;
	
	public  Footer_Factory (WebDriver driver) 
	{
		Footer_Factory.driver = driver;
	}
	
	public void ClickOnStoreLink() throws Exception 
	{
		ValuToreport=StoreLink.getText();
		cf.ClickOnElement(StoreLink,ValuToreport );
	}
}
