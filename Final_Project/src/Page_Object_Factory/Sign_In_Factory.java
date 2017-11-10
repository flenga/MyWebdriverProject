package Page_Object_Factory;

import java.util.ArrayList;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.relevantcodes.extentreports.LogStatus;

import org.apache.poi.ss.usermodel.DataFormatter;
//import DDT.ReadFromExcel;
//import DDT.ReadFromExcel;
import Project_Utilities.Base_Project;
import Project_Utilities.Project_CommonFunction;
import Page_Object_Factory.Header_Factory;
public class Sign_In_Factory  extends Base_Project
{
	
	Project_CommonFunction cf = new Project_CommonFunction();
	public String ValuToreport;
	
	//Header_Factory cc =new Header_Factory();
	public WebDriver driver;
	@FindBy(how = How.LINK_TEXT, using= "Sign in")
	public WebElement SignIn;
	@FindBy(how = How.LINK_TEXT, using= "Sign out")//After signin
	public WebElement SignOut;
	@FindBy(how = How.XPATH, using= "//*[@id='email']")
	//@FindBy(how = How.XPATH, using= "//a[contains(text(),'Developer s')]")
	public WebElement Emailaddress;
	//	
	@FindBy(how = How.XPATH, using= "//*[@id='passwd']")  
	public WebElement Password;

	@FindBy(how = How.ID, using= "SubmitLogin")  
	public WebElement SignInButton;

	@FindBy(how = How.XPATH, using= "//*[@id='center_column']/div[1]")
	public WebElement ErrorForSignIn;

	@FindBy(how = How.XPATH, using= "//*[@id='center_column']/div[1]/p")
	public WebElement ErrorForSignInTitle;

	@FindBy(how = How.XPATH, using= "//*[@id='center_column']/div[1]/ol/li")  //*[@id="center_column"]/div[1]/ol/li
	public WebElement ErrorForSignInSpecific;

	@FindBy(how = How.XPATH, using= "//*[@id='center_column']/p") 
	//@FindBy(how = How.XPATH, using= "//*[@id='center_column']/p/text()") 
	public WebElement SuccessLogin;


	public Sign_In_Factory(WebDriver driver) 
	{
		this.driver = driver;

	}

	public void LoginToPage() throws Exception 
	{
		
		cf.ReadExcelFile();
		int rowcount=cf.wrkbook.getSheet(1).getRows();
		String ExpectedColor;
		String  ExpectedMessage1;
		for(int i=1;i<rowcount;i++)
		{
			try
			{
				ExpectedColor=cf.wrkbook.getSheet(1).getCell(2,i).getContents();
				ExpectedMessage1=cf.wrkbook.getSheet(1).getCell(3,i).getContents();
				ValuToreport = Emailaddress.getAttribute("name");
				cf.SendKeyAction(Emailaddress,cf.wrkbook.getSheet(1).getCell(0,i).getContents(),ValuToreport);	
				ValuToreport=Password.getAttribute("name");
				cf.SendKeyAction(Password,cf.wrkbook.getSheet(1).getCell(1,i).getContents(),ValuToreport);	
				SignInButton.click();
				if("green".equals(ExpectedColor))//
				{
					cf.NoErrorMessageExsit(cf.verifyElementExist(SuccessLogin), ExpectedColor,ExpectedMessage1);
					logger.info("Cradiantial "+ cf.wrkbook.getSheet(1).getCell(0,i).getContents()+", and Password: "+cf.wrkbook.getSheet(1).getCell(1,i).getContents()+"were OK");
					test.log(LogStatus.PASS, "Cradiantial "+ cf.wrkbook.getSheet(1).getCell(0,i).getContents()+", and Password: "+cf.wrkbook.getSheet(1).getCell(1,i).getContents()+"were OK and user login to ");
					
					if(SignOut.isDisplayed())
					{
						SignOut.click();
						logger.info("Clicked on signout");
					}
				}
				else 										
				{
					cf.NoErrorMessageExsit(cf.verifyElementExist(ErrorForSignInSpecific), ExpectedColor, ExpectedMessage1);
					logger.info("Cradiantial "+ cf.wrkbook.getSheet(1).getCell(0,i).getContents()+", and Password: "+cf.wrkbook.getSheet(1).getCell(1,i).getContents()+"were OK - Which FAILED to login");
					test.log(LogStatus.PASS, "Cradiantial "+ cf.wrkbook.getSheet(1).getCell(0,i).getContents()+", and Password: "+cf.wrkbook.getSheet(1).getCell(1,i).getContents()+"were OK - Which FAILED to login");
					ValuToreport=SignIn.getAttribute("title");
					cf.ClickOnElement(SignIn,ValuToreport);
				}
			} 
			catch (Exception e) 
			{
				e.getMessage();
			}
		}


	}
}
