package Page_Object_Factory;

//import java.util.ArrayList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.relevantcodes.extentreports.LogStatus;
import Project_Utilities.Base_Project;
import Project_Utilities.Project_CommonFunction;
import Page_Object_Factory.Header_Factory;

public class ContactUs_Factory  extends Base_Project
{
	Project_CommonFunction cf= new Project_CommonFunction();
	Header_Factory haa=new Header_Factory();
	public String ValuToreport;
	public WebDriver driver;

	@FindBy(how = How.NAME,using= "id_contact")
	public WebElement SubjectDopdown; 

	@FindBy(how = How.ID, using= "email")
	public WebElement email;

	@FindBy(how = How.ID, using= "id_order")//In case user is sign in and has orders it become selection
	public WebElement OrderRef;

	@FindBy(how = How.XPATH, using= "//*[@id='fileUpload']")//Upload file button fileUpload 
	public WebElement fileUploadButton;

	@FindBy(how = How.ID, using= "message")
	public WebElement message;

	@FindBy(how = How.ID, using= "submitMessage") //submitMessage
	public WebElement SubmitMessage;

	@FindBy(how = How.XPATH, using= " //*[@id='center_column']/div/ol/li") 
	public WebElement ErrorDiv;

	@FindBy(how = How.XPATH, using= "//*[@id='center_column']/p")
	public WebElement SuccessDiv;

	@FindBy(how = How.LINK_TEXT, using= "Contact us")
	public WebElement ContactUs;

	@FindBy(how = How.CSS, using= "span.filename")
	public WebElement Filename;

	public ContactUs_Factory(WebDriver driver)


	{
		this.driver = driver;
//commit another change
	//change 3	
	}
	// Need to set functions to click on The links in the header according to test case  submitMessage
	public void SendAmessage() throws Exception 
	{
		cf.ReadExcelFile();
		int rowcount=cf.wrkbook.getSheet(1 ).getRows();
		for(int i=1;i<=rowcount;i++)
		{
			try 
			{
				String ExpectedColor1=cf.wrkbook.getSheet(0).getCell(4,i).getContents();
				String  ExpectedMessage1=cf.wrkbook.getSheet(0).getCell(5,i).getContents();
				cf.SelectDropdown(SubjectDopdown, cf.wrkbook.getSheet(0).getCell(1,i).getContents());
				ValuToreport=email.getAttribute("id");
				cf.SendKeyAction(email,cf.wrkbook.getSheet(0).getCell(0,i).getContents(),ValuToreport);
				ValuToreport=OrderRef.getAttribute("id");
				cf.SendKeyAction(OrderRef,"123456",ValuToreport);
				ValuToreport=fileUploadButton.getAttribute("name");
				cf.ClickOnElement(fileUploadButton,ValuToreport);
				Thread.sleep(300);	
				cf.setClipboardData(cf.wrkbook.getSheet(0).getCell(2,i).getContents()+"Picture016.jpg");
				ValuToreport=message.getAttribute("name");
				cf.SendKeyAction(message,cf.wrkbook.getSheet(0).getCell(3,i).getContents(),ValuToreport);
				ValuToreport=SubmitMessage.getAttribute("name");
				cf.ClickOnElement(SubmitMessage,ValuToreport);
				Thread.sleep(3000);
				logger.info("ContactUs form submitted!");
				test.log(LogStatus.PASS, "ContactUs form submitted!");
				if(ExpectedColor1.equals("red"))
				{
					cf.NoErrorMessageExsit(cf.verifyElementExist(ErrorDiv), ExpectedColor1,ExpectedMessage1);//"Invalid email address.");   message:"The message cannot be blank."  this should be change to ddt mechanism need to create 
				}	
				else
				{
					cf.NoErrorMessageExsit(cf.verifyElementExist(SuccessDiv), ExpectedColor1,ExpectedMessage1);
				}
				if(i<rowcount)
				{
					ContactUs.click();
				}
			} 
			catch (Exception e) 
			{
				logger.error("There was a problem while trying to send Contact us form see error : "+e.getMessage());
				test.log(LogStatus.FAIL,"There was a problem while trying to send Contact us form see error : "+e.getMessage()+" "+test.addScreenCapture(getscreenshot()));
			}
		}
	}
}
