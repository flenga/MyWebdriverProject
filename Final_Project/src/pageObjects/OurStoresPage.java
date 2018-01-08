package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import projectUtilities.BaseFunction;
import projectUtilities.CommonFunction;

public class OurStoresPage extends BaseFunction
{
	private String StoreImage;
	private String StoreDirectionsMiami;
	private String ClosePopup;
	public String ValueSendToreport;
	
	@FindBy(how = How.LINK_TEXT, using = "Our stores")
	public WebElement StoreLink;
	
	@FindBy(how = How.XPATH,using = "//*[@id='map']/div/div")
	public WebElement MapDiv;
	
	public OurStoresPage (WebDriver driver) 
	{
		BaseFunction.driver = driver;
	}
	/*
	 * This function check the functionality of the store map clicking on one of the stores verify that 
	 * address dialog open and correct Store direction and validate it exist.
	 * In this test I used Sikuli to verify if element exists in page.
	 */
	public void OpenOneOfTheStoresLocationCheckingAddressAndDialog() throws Exception
	{
		/*
		 * In case We are testing IE browser We will check the Sikuli image. 
		 * There is an option to change in the future to switch case function for more browsers. 
		 * For now I leaving that as it is.
		 */
		if(WhichBrowserType.equals("IE"))
		{
			 StoreImage = "StoreToPNG_IE.png";
			 StoreDirectionsMiami = "StoreDirectionsMiami_IE.png";
			 ClosePopup = "ClosePopup_IE.png";
		}
		else
		{
			 StoreImage = "StoreToPNG.png";
			 StoreDirectionsMiami = "StoreDirectionsMiami.png";
			 ClosePopup = "ClosePopup.png";
		}
		ValueSendToreport = StoreLink.getText();
		CommonFunction.test_Scroll_Page_Down();
		CommonFunction.ClickOnElement(StoreLink,ValueSendToreport);
		CommonFunction.verifyElementExist(MapDiv);
		CommonFunction.verifyImageExists(StoreImage);
		CommonFunction.clickOnImage(StoreImage);
		CommonFunction.verifyImageExists(StoreDirectionsMiami);
		CommonFunction.verifyImageExists(ClosePopup);
		CommonFunction.clickOnImage(ClosePopup);
	}
}