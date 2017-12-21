package projectUtilities;

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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import jxl.Sheet;
import jxl.Workbook;
import com.relevantcodes.extentreports.LogStatus;
import jdk.internal.org.xml.sax.SAXException;
import static org.junit.Assert.fail;

public class CommonFunction extends BaseFunction
{
	static Logger logger=Logger.getLogger(CommonFunction.class);
	public String Month;
	public String DAY;
	public String Year;
	HSSFWorkbook wb;
	HSSFSheet sheet;
	static Robot robot;
	public  Sheet wrksheet;
	public  Workbook wrkbook = null;
	public static Hashtable<String, Integer> dict = new Hashtable<String, Integer>();

	//Read from Excel file currently is used in two test cases(test_2_Siginin and test_3_SendContactUsForm)
	public void ReadExcelFile() throws IOException
	{
		try 
		{
			File src = new File(getData("ExcelFilePath") +"/testData1.xls"); // Excels
			wrkbook = Workbook.getWorkbook(src);
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
			test.log(LogStatus.FAIL, "Action Failed   " + e.getMessage() +". /n see Screen Shot: "+test.addScreenCapture(getScreenshot()));
			e.printStackTrace();
		}
	}

	// Drop down 
	public void SelectDropdown(WebElement selection,String ValueForSelection) throws Exception
	{
		try 
		{
			Select MyValue =  new Select(selection);
			MyValue.selectByValue(ValueForSelection);
			logger.info(" Element selected with success ");
			test.log(LogStatus.PASS, "Element Selected");
		}
		catch (Exception e) 
		{
			logger.error("Something went wrong with the drop down!!!");
			test.log(LogStatus.FAIL, "Element NOT Not Selected , see Screen Shot: " + e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
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
			logger.info("Clicked on the Element: \""+ stringToAddtoReport +"\" with success ");
			test.log(LogStatus.PASS, "Clicked on the Element: \""+ stringToAddtoReport +"\" with success ");
		}
		catch(Exception e)
		{
			logger.error("Failed to click on the element: \""+ stringToAddtoReport +"\"  !! see screenshot: "+ e.getMessage() );
			test.log(LogStatus.FAIL,"Failed to click on the element: \""+ stringToAddtoReport +"\" !! see screenshot: "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
		}
	}

	// Send key function from input field
	public void SendKeyAction(WebElement Element,String ValueToSend,String ValueToreport) throws Exception //Fill field function
	{
		try
		{
			Element.sendKeys(ValueToSend);
			logger.info("Succeeded to send from field :\""+ ValueToreport +"\" the value: \"" + ValueToSend +"\"");
			test.log(LogStatus.PASS, "Succeeded to send in field :\""+ ValueToreport +"\" the value: \"" + ValueToSend +"\"");
		}
		catch(Exception e)
		{
			logger.error("Failed to Send value : \"" + ValueToSend +"\" from field \"" + ValueToreport +"\"!! "+ e.getMessage() );
			test.log(LogStatus.FAIL,"Failed to Send value : \"" + ValueToSend +"\" from field \"" + ValueToreport +"\" !! see screenshot: "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
		}
	}

	//waitToElement
	public void waitToElement(WebElement ElementToWait) throws Exception
	{
		try 
		{
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOf(ElementToWait));
			logger.info("The element, succeeded to loaded!!!");
			test.log(LogStatus.PASS,"The element, succeeded to loaded!!!!");
		} 
		
		catch(Exception e)
		{
			e.getMessage();
			logger.error("Failed to loaded element!! "+ e.getMessage() );
			test.log(LogStatus.FAIL,"Failed to loaded element!! see screenshot: "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
			fail("Element NOT Exists !");
		}
		
	}	

	//Assertion equal
	public void asserequal(String Expected,String Actual) throws Exception
	{
		try
		{
			Assert.assertEquals(Expected, Actual);
			logger.info("Assertion was succed ");
			test.log(LogStatus.PASS, "Assertion was succed");

		}
		catch(AssertionError e)
		{
			
			test.log(LogStatus.FAIL,"assertion failed the two values: "+ Expected +" and second one is: "+Actual+": "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
			logger.error("Something went wrong while trying to Assert the two values: "+ Expected +" and second one is: "+Actual);
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
			logger.error("Something went wrong while trying to Assert the first value"+ Expected + "And the second value is "+Actual );
			test.log(LogStatus.FAIL,"assertion failed: "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
		}
	}

	// Function that verify results using DDT Currently using in 2 test (logIn test and Contact us test) 
	public void NoErrorMessageExist(WebElement Message, String expectedColor,String ExpectedString) throws Exception
	{
		String message=Message.getText();
		try
		{
			Message.isDisplayed();
			Assert.assertEquals(message,ExpectedString);
			logger.info("The message that appear was :"+ message);
			test.log(LogStatus.PASS,"Message shown after action is:" + message);
		}
		catch(Exception e)
		{
			logger.error("There was an error while sending the form see error message: "+ message +" "+ e.getMessage());
			test.log(LogStatus.FAIL, "There was an error while sending the form see error message: "+ message +" "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
		}
		catch(AssertionError e)
		{
			logger.error("The messages are not equal: "+ message + "And the second value is "+ ExpectedString);
			test.log(LogStatus.FAIL,"assertion failed: "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
		}
	}

	// Verify if element exist in page
	public boolean verifyElementExist(WebElement elementExist) throws Exception, IOException, ParserConfigurationException, SAXException
	{
		String VerifyElement="";
		try
		{
			VerifyElement=elementExist.getText();
			logger.info("The Element "+ VerifyElement +" is displayed on page!!");
			test.log(LogStatus.PASS, "The Element: "+ VerifyElement +" is displayed on page!!");
			return true;
		}
		catch(Exception e)
		{
			logger.error("The Element: "+ elementExist +" is not displayed on page!!  "+ e.getMessage());
			test.log(LogStatus.FAIL,"The Element: "+ elementExist +"  is not displayed on page!! see screenshot: "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
			fail("Element NOT Exists !");
			return false;
		}
	}
		
	//Check if element contain text
	public void verifyTextInElement( WebElement elementExistInPage ,String StringToFind) throws Exception 
	{
		String ElementExistInPage=elementExistInPage.getText();	
		try
		{
			elementExistInPage.getText().contains(StringToFind);
			logger.info("The Elements are in page!!");
			test.log(LogStatus.PASS, "The Elements are in page!!");
		}
		catch(Exception e)
		{
			logger.error("The element: "+ ElementExistInPage +" doesn't exist on page : "+ e.getMessage());
			test.log(LogStatus.FAIL,"The element: "+ ElementExistInPage +" doesn't exist on page !! see screenshot: "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
			fail("Text NOT Exists !");
		}
	}

	//Clicking on image using Sikuli
	public void clickOnImage(String imageName) throws Exception
	{
		String imageNameToClick=imageName;
		try
		{
			screen.click(getData("ImagePath") +imageName);
			Thread.sleep(500);
			logger.info("Clicked on Image: "+ imageNameToClick +"!!");
			test.log(LogStatus.PASS, "Clicked on Image "+ imageNameToClick +"!!");
		}
		catch(Exception e)
		{
			logger.error("Couldn't find image: "+ imageNameToClick +", "+ e.getMessage());
			test.log(LogStatus.FAIL, "Couldn't find the image!, see Screen Shot: "+ e.getMessage() +" " + test.addScreenCapture(getScreenshot()));
			fail("Image NOT Exists !");
		}
	}

	//Verify if image exist on page!
	public boolean verifyImageExists(String imageName)  throws IOException, ParserConfigurationException, SAXException, Exception

	{
		String imageNameToVerify = imageName;	
		try
		{
			screen.find(getData("ImagePath")+imageName);
			Thread.sleep(500);
			logger.info("The image "+ imageNameToVerify +" exist!!");
			test.log(LogStatus.PASS, "The image "+ imageNameToVerify +" Exists!");
			return true;
		}
		catch (Exception e)
		{
			logger.error("The image: "+ imageNameToVerify +" doesn't exist on page: "+ e.getMessage());
			test.log(LogStatus.FAIL, "Element NOT Exists ! , see Screen Shot: "+e.getMessage() +" " + test.addScreenCapture(getScreenshot()));
			fail("Image NOT Exists !");
			return false;
		}
	}
	
	public void SearchResult(WebElement Message) throws Exception
	{
		String message=Message.getText();
		String ExpectedMessage = "0 results have been found.";
		try
		{
			Message.isDisplayed();
			Assert.assertNotEquals(message, ExpectedMessage);
			logger.info("The message that appear was:"+ message);
			test.log(LogStatus.PASS,"Message shown after action is:" + message);
		}
		catch(Exception e)
		{
			logger.error("There was an error while sending the form see error message :"+ message +" "+ e.getMessage());
			test.log(LogStatus.FAIL, "There was an error while sending the form see error message :"+ message +" "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
		}
		catch(AssertionError e)
		{
			logger.error("The messages are equal for before and after "+ message + "And the second value is "+ExpectedMessage );
			test.log(LogStatus.FAIL,"assertion failed: "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
		}
	}
}