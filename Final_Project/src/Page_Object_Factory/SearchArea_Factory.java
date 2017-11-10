package Page_Object_Factory;
//import java.util.List;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.relevantcodes.extentreports.LogStatus;

//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import Project_Utilities.Base_Project;
import Project_Utilities.Project_CommonFunction;
//import junit.framework.Assert;
public class SearchArea_Factory extends Base_Project
{
	
	public String ShoppingCartAfter;
	public String ValuToreport;
	//public WebDriver driver;
	Project_CommonFunction cf = new Project_CommonFunction();
	SearchToShopping_Factory St=new SearchToShopping_Factory();
	
	@FindBy(how = How.ID,using= "search_query_top") 
	public WebElement SearchBar;  //Blouse
	
	@FindBy(how = How.NAME,using= "submit_search") 
	public WebElement SubmitButton; 
	
	@FindBy(how = How.CSS,using= "span.label-success") 
	public WebElement InStock;
	
	@FindBy(how = How.LINK_TEXT,using= "Add to cart") 
	public WebElement AddToCartButton;

	@FindBy(how = How.CSS,using= "div.shopping_cart") 
	public WebElement ShoppingCart;
	
	@FindBy(how = How.CSS,using="span.heading-counter")
	public WebElement SearchResult;
	
	@FindBy(how = How.CSS,using="span.price.product-price")
	public WebElement ItemPrice;
	
	@FindBy(how = How.CSS,using="li#list")// #list
	public WebElement ListView;
	
	//@FindBy(how = How.CSS,using= "span.cross[title=’Close window’]") 
	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[1]/span[1]") 
	public WebElement CloseDiv1;
	
	//@FindBy(how = How.CSS,using= "span.ajax_cart_product_txt_s") // 
	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/span") 
	public WebElement TitleShoppingDiv1;  //Blouse
	
	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/div[1]/span") 
	public WebElement TotalProducts1;
	
	//@FindBy(how = How.CSS,using= "span.ajax_cart_shipping_cost unvisible") 
	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/div[2]/span") 
	public WebElement Totalshipping; 
	//*[@id='layer_cart']/div[1]/div[2]/div[3]/span
	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/div[3]/span") 
	//@FindBy(how = How.CSS,using= "span.ajax_block_cart_total") 
	public WebElement TotalIncluds;

	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart_product_title']") 
	//@FindBy(how = How.ID,using= "layer_cart_product_title") 
	public WebElement ProductTitle;
	
	public SearchArea_Factory(WebDriver driver) // 
	{
		SearchArea_Factory.driver = driver;
	}
	
	
	public void SearchForAnItemAndAddToShoppingCart() throws Exception
	{
	 String ShoppingCart1 =ShoppingCart.getText() ;//;.getText());
	 String Price =ItemPrice.getText();
	
	//cf.SearchForAnItem(SearchBar, "Blouse");
	//SearchBar.sendKeys("Blouse");
	ValuToreport=SearchBar.getAttribute("placeholder");
	cf.SendKeyAction(SearchBar, "Blouse",ValuToreport);
	ValuToreport=ShoppingCart.getText();
	cf.ClickOnElement(SubmitButton,ValuToreport);//Need to verify that issue flow search for an item click on the element add to cart verify few elements on page check that shopping cart had changed
	
	
	Thread.sleep(1500);
	cf.SearchResult(SearchResult);
	if(!ListView.isSelected())
	{
		ListView.click();
	}
	if(InStock.isDisplayed())
	{
		
		ListView.click();
		ValuToreport=AddToCartButton.getText();
		cf.ClickOnElement(AddToCartButton,ValuToreport);
		Thread.sleep(1500);
		
			
		try {
			cf.verifyElementExist(TitleShoppingDiv1);
			cf.asserequal( TitleShoppingDiv1.getText(), "There is 1 item in your cart.");
			//cf.asserequal(TotalProducts1.getText(), "$24.01");
			cf.asserequal(TotalProducts1.getText(), "$24.00");
			cf.asserequal(Totalshipping.getText(), "$7.00");
			cf.asserequal(TotalIncluds.getText(), "$31.00");
			//cf.asserequal(ProductTitle.getText(), "1Blouse");
			cf.asserequal(ProductTitle.getText(), "Blouse");
			logger.info("The Elements are displayed on page!!");
			test.log(LogStatus.PASS, "The Elements:  are displayed on page!!");
		    //papos2511
		} catch (Exception e) 
		{
			logger.error("The title: "+TitleShoppingDiv1.getText()+" doesn't exsit on page : "+e.getMessage());
			test.log(LogStatus.FAIL,"The element: "+TitleShoppingDiv1.getText()+" doesn't exsit on page !! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
			e.printStackTrace();
		}
		
	}
	ValuToreport=CloseDiv1.getAttribute("title");
	cf.ClickOnElement(CloseDiv1,ValuToreport);
	Thread.sleep(1000);	
	ShoppingCartAfter=ShoppingCart.getText();
	//System.out.println(ShoppingCartAfter);
	cf.asserNotequal(ShoppingCart1, ShoppingCartAfter);
	}
}
