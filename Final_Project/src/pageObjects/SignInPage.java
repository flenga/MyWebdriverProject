package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.relevantcodes.extentreports.LogStatus;
import projectUtilities.BaseFunction;
import projectUtilities.CommonFunction;

public class SignInPage  extends BaseFunction
{
	public String ValuToreport;
	public WebDriver driver;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign in")
	public WebElement SignIn;
	
	@FindBy(how = How.LINK_TEXT, using = "Sign out")//After signin
	public WebElement SignOut;
	
	@FindBy(how = How.ID, using = "email")
	public WebElement Emailaddress;
		
	@FindBy(how = How.ID, using = "passwd") 
	public WebElement Password;

	@FindBy(how = How.ID, using = "SubmitLogin")  
	public WebElement SignInButton;

	@FindBy(how = How.XPATH, using = "1//*[@id='center_column']/div[1]")
	public WebElement ErrorForSignIn;

	@FindBy(how = How.XPATH, using = "//*[@id='center_column']/div[1]/p")
	public WebElement ErrorForSignInTitle;

	@FindBy(how = How.XPATH, using = "//*[@id='center_column']/div[1]/ol/li") 
	public WebElement ErrorForSignInSpecific;

	@FindBy(how = How.XPATH, using = "//*[@id='center_column']/p") 
	public WebElement SuccessLogin;

	public SignInPage(WebDriver driver) 
	{
		this.driver = driver;

	}

	public void LoginToPage() throws Exception 
	{
		/*
		*Reading from Excel file and get the data from this file, wrkbook.getSheet(1) 
		*refer to the second tab in the file, running through a loop
		*to collect the data getCell(4,i) the first number represent the column and the second value represent the row.   
		*/
		CommonFunction.ReadExcelFile();
		int rowcount = CommonFunction.wrkbook.getSheet(1).getRows();
		String ExpectedColor;
		String  ExpectedMessage;
		for(int i=1;i<rowcount;i++)
		{
			try
			{
				ExpectedColor = CommonFunction.wrkbook.getSheet(1).getCell(2,i).getContents();
				ExpectedMessage = CommonFunction.wrkbook.getSheet(1).getCell(3,i).getContents();
				ValuToreport = Emailaddress.getAttribute("name");
				CommonFunction.SendKeyAction(Emailaddress,CommonFunction.wrkbook.getSheet(1).getCell(0,i).getContents(),ValuToreport);	
				ValuToreport = Password.getAttribute("name");
				CommonFunction.SendKeyAction(Password,CommonFunction.wrkbook.getSheet(1).getCell(1,i).getContents(),ValuToreport);	
				SignInButton.click();
				/*
				 * This check if the error is green or red and according to the color 
				 * received continue!!
				 */
				
				if(ExpectedColor.equals("green"))
				{
					CommonFunction.NoErrorMessageExist(SuccessLogin,ExpectedColor,ExpectedMessage);
					logger.info("Cradiantial "+ CommonFunction.wrkbook.getSheet(1).getCell(0,i).getContents() +", and Password: "+ CommonFunction.wrkbook.getSheet(1).getCell(1,i).getContents() +"were OK");
					test.log(LogStatus.PASS, "Cradiantial "+ CommonFunction.wrkbook.getSheet(1).getCell(0,i).getContents() +", and Password: "+ CommonFunction.wrkbook.getSheet(1).getCell(1,i).getContents() +"were OK and user login to ");
					
					if(SignOut.isDisplayed())
					{
						SignOut.click();
						logger.info("Clicked on signout");
					}
				}
				else 										
				{
					CommonFunction.NoErrorMessageExist(ErrorForSignInSpecific,ExpectedColor,ExpectedMessage);
					logger.info("Cradiantial "+ CommonFunction.wrkbook.getSheet(1).getCell(0,i).getContents() +", and Password: "+ CommonFunction.wrkbook.getSheet(1).getCell(1,i).getContents() +"were OK - Which FAILED to login");
					test.log(LogStatus.PASS, "Cradiantial "+ CommonFunction.wrkbook.getSheet(1).getCell(0,i).getContents() +", and Password: "+ CommonFunction.wrkbook.getSheet(1).getCell(1,i).getContents() +"were OK - Which FAILED to login");
					ValuToreport = SignIn.getAttribute("title");
					CommonFunction.ClickOnElement(SignIn,ValuToreport);
				}
			} 
			catch (Exception e) 
			{
				logger.error("There was a problem while trying to Sign in, see error : "+ e.getMessage());
				test.log(LogStatus.FAIL,"There was a problem while trying to Sign in, see error : "+ e.getMessage() +" "+ test.addScreenCapture(getScreenshot()));
			}
		}
	}
}