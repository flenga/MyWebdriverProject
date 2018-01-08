package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.relevantcodes.extentreports.LogStatus;

import pageObjects.HeaderAreaPage;
import projectUtilities.BaseFunction;
import projectUtilities.CommonFunction;

public class ContactUsPage  extends BaseFunction
{
	HeaderAreaPage haa = new HeaderAreaPage();
	public String ValuToreport;
	public WebDriver driver;

	@FindBy(how = How.NAME,using = "id_contact")
	public WebElement SubjectDopdown; 

	@FindBy(how = How.ID, using = "email")
	public WebElement email;
	//In case user is signed in and has orders it becomes selected.
	@FindBy(how = How.ID, using = "id_order")
	public WebElement OrderRef;

	@FindBy(how = How.ID, using = "fileUpload")
	public WebElement fileUploadButton;

	@FindBy(how = How.ID, using = "message")
	public WebElement message;

	@FindBy(how = How.ID, using = "submitMessage")
	public WebElement SubmitMessage;

	@FindBy(how = How.XPATH, using = " //*[@id='center_column']/div/ol/li") 
	public WebElement ErrorDiv;

	@FindBy(how = How.XPATH, using = "//*[@id='center_column']/p")
	public WebElement SuccessDiv;

	@FindBy(how = How.LINK_TEXT, using = "Contact us")
	public WebElement ContactUs;

	@FindBy(how = How.CSS, using = "span.filename")
	public WebElement Filename;

	public ContactUsPage(WebDriver driver)
	{
		this.driver = driver;
	}

	public void SendContactFormAmessage() throws Exception 
	{
		/*
		 Reading from Excel file and get the data from this file, wrkbook.getSheet(0) 
		 refer to the first tab in the file, running through a loop
		 to collect the data getCell(4,i) the first number represent the column
		 and the second value represent the row. 
		 */
		CommonFunction.ReadExcelFile();
		int rowcount = CommonFunction.wrkbook.getSheet(1).getRows();
		for(int i=1;i<=rowcount;i++)
		{
			try 
			{
				String ExpectedColor = CommonFunction.wrkbook.getSheet(0).getCell(4,i).getContents();
				String  ExpectedMessage1 = CommonFunction.wrkbook.getSheet(0).getCell(5,i).getContents();
				CommonFunction.SelectDropdown(SubjectDopdown, CommonFunction.wrkbook.getSheet(0).getCell(1,i).getContents());
				ValuToreport = email.getAttribute("id");
				CommonFunction.SendKeyAction(email,CommonFunction.wrkbook.getSheet(0).getCell(0,i).getContents(),ValuToreport);
				ValuToreport = OrderRef.getAttribute("id");
				CommonFunction.SendKeyAction(OrderRef,"123456",ValuToreport);
				ValuToreport = fileUploadButton.getAttribute("name");
				CommonFunction.ClickOnElement(fileUploadButton,ValuToreport);
				Thread.sleep(300);	
				CommonFunction.setClipboardData(CommonFunction.wrkbook.getSheet(0).getCell(2,i).getContents()+"Picture016.jpg");
				ValuToreport = message.getAttribute("name");
				CommonFunction.SendKeyAction(message,CommonFunction.wrkbook.getSheet(0).getCell(3,i).getContents(),ValuToreport);
				ValuToreport = SubmitMessage.getAttribute("name");
				CommonFunction.ClickOnElement(SubmitMessage,ValuToreport);
				logger.info("ContactUs Form submitted!");
				test.log(LogStatus.PASS, "ContactUs form submitted!");
				/*
				 * This check if the error is green or red and according to the color 
				 * received continue!!
				 */
				if(ExpectedColor.equals("red"))
				{
					CommonFunction.NoErrorMessageExist(ErrorDiv, ExpectedColor,ExpectedMessage1);
				}	
				else
				{
					CommonFunction.NoErrorMessageExist(SuccessDiv, ExpectedColor,ExpectedMessage1);
				}
				if(i<rowcount)
				{
					ContactUs.click();
				}
			} 
			catch (Exception e) 
			{
				logger.error("There was a problem while trying to send Contact us form see error : " + e.getMessage());
				test.log(LogStatus.FAIL,"There was a problem while trying to send Contact us form see error : "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
			}
		}
	}
}