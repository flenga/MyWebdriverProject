package pageObjects;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.relevantcodes.extentreports.LogStatus;

import projectUtilities.BaseFunction;
import projectUtilities.CommonFunction;

public class SearchToShoppingCartPage extends BaseFunction
{
	
	private String ValueSendToreport;
	public String ShoppingCartAfter;

	@FindBy(how = How.XPATH,using = "//*[@id='layer_cart']/div[1]")
	public WebElement ContainarDiv; 

	@FindBy(how = How.XPATH,using = "//*[@id='layer_cart']/div[1]/div[1]/span[1]") 
	public WebElement TitleShoppingDiv;
	
	@FindBy(how = How.XPATH,using = "//*[@id='layer_cart']/div[1]/div[2]/div[1]/span") 
	public WebElement TotalProductsDiv; 

	@FindBy(how = How.CSS,using = "span.cross[title=’Close window’]") 
	public WebElement CloseDiv;

	@FindBy(how = How.XPATH,using = "//*[@id='layer_cart']/div[1]/div[1]/span[1]") 
	public WebElement CloseDiv1;

	@FindBy(how = How.XPATH,using = "//*[@id='layer_cart']/div[1]/div[2]/span/span[2]")   
	public WebElement TitleShoppingDiv1; 

	@FindBy(how = How.XPATH,using = "//*[@id='layer_cart']/div[1]/div[2]/div[1]/span") 
	public WebElement TotalProducts1;

	@FindBy(how = How.XPATH,using = "//*[@id='layer_cart']/div[1]/div[2]/div[2]/span") 
	public WebElement TotalshippingDiv; 

	@FindBy(how = How.XPATH,using = "//*[@id='layer_cart']/div[1]/div[2]/div[3]/span") 
	public WebElement TotalIncludsDiv;

	@FindBy(how = How.XPATH,using = "//*[@id='layer_cart_product_title']") 
	public WebElement ProductTitleDiv;

	@FindBy(how = How.CSS,using = "div.shopping_cart") 
	public WebElement ShoppingCart;
	
	public void VerifyElementIndiv() throws Exception
	{
		ValueSendToreport = CloseDiv.getAttribute("title");
		CommonFunction.ClickOnElement(CloseDiv,ValueSendToreport);
		try 
		{
			ShoppingCartAfter = ShoppingCart.getText();
			logger.info("The Element: "+ ShoppingCartAfter +" appear!!");
			test.log(LogStatus.PASS, "The Element :"+ ShoppingCartAfter +" appear!!");
		}
		catch(Exception e) 
		{
			logger.error("Failed to get the text from "+ ShoppingCart +" : "+ e.getMessage());
			test.log(LogStatus.FAIL,"Failed to get text from "+ ShoppingCart +" : see screenshot: "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
		}
	}
}