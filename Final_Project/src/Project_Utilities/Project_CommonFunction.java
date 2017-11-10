package Project_Utilities;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import jxl.Sheet;
import jxl.Workbook;
import com.relevantcodes.extentreports.LogStatus;
import jdk.internal.org.xml.sax.SAXException;
//Important Need to add log4j jar
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.awt.AWTException;
//import org.apache.poi.ss.usermodel.Row;
//import org.openqa.selenium.By;
//import org.sikuli.script.FindFailed;
//import jxl.read.biff.BiffException;
//import static org.junit.Assert.fail;
//import org.openqa.selenium.NoSuchElementException;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.sikuli.script.*;

public class Project_CommonFunction extends Base_Project
{

	static Logger logger=Logger.getLogger(Project_CommonFunction.class);
	public String Month;
	public String DAY;
	public String Year;
	//public String ExcelSheetTab;
	HSSFWorkbook wb;
	HSSFSheet sheet;
	static Robot robot;
	public  Sheet wrksheet;
	public  Workbook wrkbook = null;
	public static Hashtable<String, Integer> dict = new Hashtable<String, Integer>();

	//
	public void ReadExcelFile() throws IOException
	{
		try 
		{
			File src =new File("E:\\Users\\lenga\\fabi\\Fabi\\WebDriver\\Excels\\testData1.xls");
			wrkbook =Workbook.getWorkbook(src);
			logger.info("succeed to read from Excel ");
			test.log(LogStatus.PASS, "Action succeed.");
		} 
		catch (Exception e) 
		{
			logger.error("Something went wrong with this action!!!");
			test.log(LogStatus.FAIL, "Action Failed  , see Screen Shot: " + e.getMessage());

			e.getMessage();
		}

	}	

	//File-Up loader function to use to upload computers file. 
	public void setClipboardData(String string) throws Exception 
	{
		robot=new Robot();
		//robot.setAutoDelay(1000);
		try {
			StringSelection stringSelection= new StringSelection(string);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
			robot.setAutoDelay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			logger.info("This Action succeed.");
			test.log(LogStatus.PASS, "Action succeed.");
		} 
		catch (HeadlessException e) 
		{
			logger.error("Something went wrong with this action!!!");
			test.log(LogStatus.FAIL, "Action Failed  , see Screen Shot: " + e.getMessage() +" "+test.addScreenCapture(getscreenshot()));


			e.printStackTrace();
		}

	}


	// Drop down 
	public void SelectDropdown(WebElement selection,String ValueForSelection) throws Exception
	{

		try {
			Select MyValue =  new Select(selection);
			MyValue.selectByValue(ValueForSelection);
			logger.info(" Element selected with success ");
			test.log(LogStatus.PASS, "Element Selected");
		}
		catch (Exception e) 
		{
			logger.error("Something went wrong with the drop down!!!");
			test.log(LogStatus.FAIL, "Element NOT Not Selected , see Screen Shot: " + e.getMessage() +" "+test.addScreenCapture(getscreenshot()));
			e.printStackTrace();
		}	

	}

	//Find date elements
	public void SplitTheDate(String FullDate)
	{
		String[] temDate=FullDate.split("/");
		DAY=temDate[0];
		Month=temDate[1];
		Year=temDate[2];
	}

	//Click on one of the link in the header
	public static void ClickOnLinkInHeader(WebElement Headerlink) throws Exception
	{
		try
		{
			Headerlink.click();
			Thread.sleep(500);
			logger.info("Clicked on the Element: "+ Headerlink.getText() +" with success ");
			test.log(LogStatus.PASS, "Clicked on the Element: "+ Headerlink.getText() +" with success ");
		}
		catch(Exception e)
		{
			logger.error("Something went wrong while trying to log in");
			test.log(LogStatus.FAIL,"Failed to Click on link !! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}
	}
	//Click on one of the links in the footer
	public void ClickOnLinkInFooter(WebElement Footerlink) throws Exception
	{
		try
		{
			Footerlink.click();
			Thread.sleep(500);
			logger.info("Clicked on the Element: "+ Footerlink.getText() +" with success ");
			test.log(LogStatus.PASS, "Clicked on the Element: "+ Footerlink.getText() +" with success ");
		}
		catch(Exception e)
		{
			logger.error("Failed to Click on link !! "+e.getMessage());
			test.log(LogStatus.FAIL,"Failed to Click on link !! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}
	}

	//Scroll down
	public void test_Scroll_Page_Down() throws Exception 
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight);"); 

	}

	//Click on Web Element 
	public  void ClickOnElement(WebElement ElementToClick,String stringToAddtoReport) throws Exception
	{
		try
		{
			ElementToClick.click();
			Thread.sleep(500);
			logger.info("Clicked on the Element: \""+stringToAddtoReport+"\" with success ");
			test.log(LogStatus.PASS, "Clicked on the Element: \""+stringToAddtoReport+"\" with success ");
		}
		catch(Exception e)
		{
			logger.error("Failed to click on the element: \""+stringToAddtoReport+"\"  !! see screenshot: "+e.getMessage() );
			test.log(LogStatus.FAIL,"Failed to click on the element: \""+stringToAddtoReport+"\" !! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));

		}


	}

	// Send key function from input field
	public void SendKeyAction(WebElement Element,String ValueToSend,String ValueToreport) throws Exception //Fill field function
	{
		try
		{
			Element.sendKeys(ValueToSend);
			logger.info("Succeeded to send from field :\""+ ValueToreport+"\" the value: \"" +ValueToSend+"\"");
			test.log(LogStatus.PASS, "Succeeded to send in field :\""+ ValueToreport+"\" the value: \"" +ValueToSend+"\"");
		}
		catch(Exception e)
		{
			logger.error("Failed to Send value : \"" +ValueToSend+"\" from field \"" +ValueToreport+"\"!! "+e.getMessage() );
			test.log(LogStatus.FAIL,"Failed to Send value : \"" +ValueToSend+"\" from field \"" +ValueToreport+"\" !! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}

	}
	//waitToElement
	public void waitToElement(WebElement ElementToWait) throws Exception
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver, 20);
			wait.until(ExpectedConditions.visibilityOf(ElementToWait));
			logger.info("Element name: "+ElementToWait.getText()+", succeeded to loaded!!!");
		} 
		catch (Exception e) 
		{
			logger.error("Failed to loaded element: "+ElementToWait.getText()+"!! "+e.getMessage() );
			test.log(LogStatus.FAIL,"Failed to loaded element: "+ElementToWait.getText()+"!!!! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
			e.printStackTrace();
		} 
	}	

	//Assertion equal
	public void asserequal( String Expected,String Actual) throws Exception
	{
		try
		{
			Assert.assertEquals(Expected, Actual);
			logger.info("Assertion  was succed ");
			test.log(LogStatus.PASS, "Assertion  was succed");

		}
		catch(AssertionError e)
		{
			logger.error("Something went wrong while trying to Assert the two values: "+Expected+" and second one is: "+Actual);
			test.log(LogStatus.FAIL,"assertion failed the two values: "+Expected+" and second one is: "+Actual+": "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}
	}

	//asserNotequal
	public void asserNotequal(String Expected,String Actual) throws Exception
	{
		try
		{
			Assert.assertNotEquals(Expected, Actual);
			logger.info("Assertion  was succed ");
			test.log(LogStatus.PASS, "Assertion  was succed");

		}
		catch(AssertionError e)
		{
			logger.error("Something went wrong while trying to Assert the first value"+ Expected+ "And the second value is "+Actual );
			test.log(LogStatus.FAIL,"assertion failed: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}
	}

	//Not relevant
	/*
	public void printAllFooterLinks(List<WebElement> FooterLink)
	{
		for (WebElement link : FooterLink) 
		{
			System.out.println(link.getText());
		}
	}
	 */

	// Function that verify results using DDT Currently using in 2 test (logIn test and Contact us test) 
	public void NoErrorMessageExsit(WebElement Message, String expectedColor1,String string) throws Exception
	{
		String message=Message.getText();
		try
		{
			//System.out.println("The message: "+message);
			Message.isDisplayed();
			Assert.assertEquals(message,string);
			logger.info("The message that appear was :"+message);
			test.log(LogStatus.PASS,"Message shown after action is:" +message);
		}
		catch(Exception  e)
		{
			logger.error("There was an error while sending the form see error message: "+message +" "+e.getMessage());
			test.log(LogStatus.FAIL, "There was an error while sending the form see error message: "+message +" "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}
	}

	// Verify if element exist in page
	public WebElement verifyElementExist(WebElement elementExist) throws Exception  
	{
		String VerifyElement=elementExist.getText();
		try
		{
			elementExist.isDisplayed();
			Thread.sleep(500);
			logger.info("The Element "+VerifyElement+" is displayed on page!!");
			test.log(LogStatus.PASS, "The Element: "+VerifyElement+"  is displayed on page!!");
		}
		catch(Exception e)
		{
			logger.error("The Element: "+VerifyElement+" is not displayed on page!!  "+e.getMessage());
			test.log(LogStatus.FAIL,"The Element: "+VerifyElement+"  is not displayed on page!! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}
		return elementExist;
	}


	//Check if element contain text
	public void ElementExistInPage( WebElement elementExistInPage ,String StrToFind) throws Exception 
	{
		String ElementExistInPage=elementExistInPage.getText();	
		try
		{
			elementExistInPage.getText().contains(StrToFind);
			logger.info("The Elements are in page!!");
			test.log(LogStatus.PASS, "The Elements are in page!!");
		}
		catch(Exception e)
		{
			logger.error("The element: "+ElementExistInPage+" doesn't exsit on page : "+e.getMessage());
			test.log(LogStatus.FAIL,"The element: "+ElementExistInPage+" doesn't exsit on page !! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}


	}

	//Clicking on image using Sikuli
	public void clickOnImage(String imageName) throws Exception
	{
		String imageNameToClick=imageName;
		try
		{
			screen.click(getData("ImagePath")+imageName);
			logger.info("Clicked on Image:  "+imageNameToClick+" !!");
			test.log(LogStatus.PASS, "Clicked on Image "+imageNameToClick+" !!");
		}
		catch(Exception e)
		{
			logger.error("Couldn't find image : "+imageNameToClick+" , "+e.getMessage());
			test.log(LogStatus.FAIL, "Couldn't find the image  ! , see Screen Shot: "+e.getMessage()+" " + test.addScreenCapture(getscreenshot()));
		}
	}

	//Verify if image exist on page!
	public void verifyImageExists(String imageName)  throws IOException, ParserConfigurationException, SAXException, Exception

	{
		String imageNameToVerify=imageName;	
		try
		{
			screen.find(getData("ImagePath")+imageName);
			logger.info("The image we looked for exist!!");
			test.log(LogStatus.PASS, "Element "+imageNameToVerify+"Exists !");

		}
		catch (Exception e)
		{
			logger.error("The image : "+imageNameToVerify+" doesn't exsit on page : "+e.getMessage());
			test.log(LogStatus.FAIL, "Element NOT Exists ! , see Screen Shot: "+e.getMessage()+" " + test.addScreenCapture(getscreenshot()));

		}

	}

	//Search for an Item 
	public void SearchForAnItem(WebElement SearchField,String SearchItem) throws Exception
	{
		try {
			SearchField.sendKeys(SearchItem);
			logger.info("Search for : "+SearchItem+" !!");
			test.log(LogStatus.PASS, "Search for :  "+SearchItem+"  !");
		} catch (Exception e) 
		{
			logger.error("The search for : "+SearchItem+" Failed : "+e.getMessage());
			test.log(LogStatus.FAIL, "The search for : "+SearchItem+" Failed, see Screen Shot: "+e.getMessage()+" " + test.addScreenCapture(getscreenshot()));
			e.printStackTrace();
		}
	}

	public void SearchResult(WebElement Message) throws Exception
	{
		String message=Message.getText();
		String Expectedstring="0 results have been found.";
		try
		{
			Message.isDisplayed();
			Assert.assertNotEquals(message, Expectedstring);
			logger.info("The message that appear was :"+message);
			test.log(LogStatus.PASS,"Message shown after action is :" +message);
		}
		catch(Exception  e)
		{
			logger.error("There was an error while sending the form see error message :"+message +" "+e.getMessage());
			test.log(LogStatus.FAIL, "There was an error while sending the form see error message :"+message +" "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}
	}

	//Login To site function
	public void LoginToPage(String Username,String pass,WebElement Button,WebElement EmailField,WebElement PasswordField) throws Exception
	{
		try
		{
			EmailField.sendKeys(Username);  // 
			logger.info("Enter the User name "+Username);
			test.log(LogStatus.PASS, "Enter User name: "+ Username +" with success ");
			PasswordField.sendKeys(pass);   // 
			logger.info("Enter the Password "+ pass);
			test.log(LogStatus.PASS, "Enter Password: "+ pass +" with success ");
			Button.click();
			logger.info("button was clicked");
			test.log(LogStatus.PASS, "Clicked on the submit button");
		}
		catch(Exception e)
		{
			logger.error("Something went wrong while trying to log in");
			test.log(LogStatus.FAIL,"Failed to login to site !! see screenshot: "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
		}
	}


}



