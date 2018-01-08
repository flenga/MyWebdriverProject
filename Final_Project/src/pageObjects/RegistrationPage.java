package pageObjects;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import projectUtilities.BaseFunction;
import projectUtilities.CommonFunction;

public class RegistrationPage extends BaseFunction
{
	public static WebDriver driver;
	public String ValuToreport;
	@FindBy(how = How.NAME, using = "id_gender")
	public List<WebElement> RadioButtonSex;
	
	@FindBy(how = How.ID, using = "customer_firstname")
	public WebElement FirstName;
	
	@FindBy(how = How.ID, using = "customer_lastname")
	public WebElement LastName;
	
	@FindBy(how = How.ID, using = "email")
	public WebElement Email;
	
	@FindBy(how = How.ID, using = "passwd")
	public WebElement Password;
	
	@FindBy(how = How.NAME,using = "days")
	public WebElement Daysdropdown; 
	
	@FindBy(how = How.NAME,using = "months")
	public WebElement Monthdropdown; 
	
	@FindBy(how = How.NAME,using = "years")
	public WebElement Yeardropdown; 
	
	@FindBy(how = How.NAME,using = "submitAccount")
	public WebElement RegisterButton; 
	
	//Error message
	@FindBy(how = How.XPATH,using = "//*[@id='center_column']/div")
	public WebElement ErrorMessage; 
	
	public RegistrationPage(WebDriver driver) 
	{
		RegistrationPage.driver = driver;
	}
	
	public void FindElementsOnPage() throws Throwable
	{
		CommonFunction.SplitTheDate("23/8/1956");
		ValuToreport=FirstName.getAttribute("name");
		CommonFunction.SendKeyAction(FirstName,"fab",ValuToreport);
		ValuToreport=LastName.getAttribute("name");
		CommonFunction.SendKeyAction(LastName,"fabo",ValuToreport);
		ValuToreport=LastName.getAttribute("name");
		CommonFunction.SendKeyAction(Email,"publisherpete4@gmail.com",ValuToreport);
		Thread.sleep(700);
		ValuToreport=LastName.getAttribute("name");
		CommonFunction.SendKeyAction(Password,"12345",ValuToreport);
		CommonFunction.SelectDropdown(Monthdropdown, CommonFunction.Month);
		CommonFunction.SelectDropdown(Daysdropdown, CommonFunction.DAY);
		CommonFunction.SelectDropdown(Yeardropdown, CommonFunction.Year);
		ValuToreport=RadioButtonSex.get(1).getAttribute("name");
		CommonFunction.ClickOnElement(RadioButtonSex.get(1),ValuToreport);
		ValuToreport=RegisterButton.getAttribute("innerHTML");
		CommonFunction.ClickOnElement(RegisterButton,ValuToreport);
		CommonFunction.waitToElement(ErrorMessage);  
		CommonFunction.verifyElementExist(ErrorMessage);
		Thread.sleep(500);
	}
}