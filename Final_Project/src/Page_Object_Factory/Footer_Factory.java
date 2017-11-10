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
	//public WebDriver driver;
	
	
	
	//@FindBy(how = How.XPATH, using= "//*[@id='block_various_links_footer']/ul/li[4]/a") //From The footer   //*[@id='block_various_links_footer']/ul/li[4]
	@FindBy(how = How.LINK_TEXT, using= "Our stores")  //css a[title='Our stores']    
	public WebElement StoreLink;
	
	// Need to set functions to click on The links in the header according to test case
	public  Footer_Factory (WebDriver driver) 
	{
		Footer_Factory.driver = driver;

	}
	
	
	
	public void ClickOnStoreLink() throws Exception 
	{
		cf.ClickOnLinkInFooter(StoreLink);
	}
}
