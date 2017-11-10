package Page_Object_Factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import Project_Utilities.Base_Project;
import Project_Utilities.Project_CommonFunction;

public class OurStores_Factory extends Base_Project
{
	Project_CommonFunction cf = new Project_CommonFunction();
	//
	public String ValueSendToreport;
	@FindBy(how = How.LINK_TEXT, using= "Our stores")
	public WebElement StoreLink;
	
	@FindBy(how = How.XPATH,using="//*[@id='map']/div/div")
	public WebElement MapDiv;
	// Need to set functions to click on The links in the header according to test case
	public OurStores_Factory (WebDriver driver) 
	{
		Base_Project.driver = driver;
	}
	
	public void ClickOnOneOfTheStores() throws Exception 
	{
		ValueSendToreport=StoreLink.getText();
		
		cf.test_Scroll_Page_Down();
		cf.ClickOnElement(StoreLink,ValueSendToreport);
		cf.waitToElement(MapDiv);
		cf.verifyElementExist(MapDiv);
		cf.verifyImageExists("StoreToPNG.png");
		cf.clickOnImage("StoreToPNG.png");
		cf.verifyImageExists("StoreDirectionsMiami.png");
		cf.verifyImageExists("ClosePopup.png");
		cf.clickOnImage("ClosePopup.png");
	}
}
