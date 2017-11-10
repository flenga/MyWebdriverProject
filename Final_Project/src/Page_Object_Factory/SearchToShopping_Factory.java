package Page_Object_Factory;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import Project_Utilities.Base_Project;
import Project_Utilities.Project_CommonFunction;
public class SearchToShopping_Factory extends Base_Project
{
	Project_CommonFunction cf = new Project_CommonFunction();
	private String ValueSendToreport;
	//@FindBy(how = How.CSS,using= "span.ajax_cart_product_txt_s") // 
	@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[1]/span[1]") 
	public WebElement TitleShoppingDiv;  //Blouse
	
	@FindBy(how = How.CSS,using= "span.ajax_block_products_total")  // //*[@id="layer_cart"]/div[1]/div[2]/div[1]/span
	public WebElement TotalProducts; 
	/*
	@FindBy(how = How.CSS,using= "span.ajax_cart_shipping_cost unvisible") 
	public WebElement Totalshipping;
	
	@FindBy(how = How.CSS,using= "span.ajax_block_cart_total") 
	public WebElement TotalIncluds;

	@FindBy(how = How.ID,using= "layer_cart_product_title") 
	public WebElement ProductTitle;
	*/
	@FindBy(how = How.CSS,using= "span.cross[title=’Close window’]") 
	public WebElement CloseDiv;
	
	//@FindBy(how = How.CSS,using= "span.cross[title=’Close window’]") 
		@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[1]/span[1]") 
		public WebElement CloseDiv1;
		
		//@FindBy(how = How.CSS,using= "span.ajax_cart_product_txt_s") // 
		@FindBy(how = How.XPATH,using= "//*[@id='layer_cart']/div[1]/div[2]/span")    //*[@id="layer_cart"]/div[1]/div[2]
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
	
	public void VerifyElementIndiv() throws Exception
	{
	TitleShoppingDiv1.isDisplayed();	
	Assert.assertEquals(TitleShoppingDiv1.getText(), "There is 1 item in your cart.");
	Assert.assertEquals(TotalProducts.getText(), "$24.00");
	Assert.assertEquals(Totalshipping.getText(), "$7.00"); //TotalIncluds
	Assert.assertEquals(TotalIncluds.getText(), "$31.00");
	Assert.assertEquals(ProductTitle.getText(), "Blouse");
	ValueSendToreport=CloseDiv.getAttribute("title");
	cf.ClickOnElement(CloseDiv,ValueSendToreport);
	}


}
