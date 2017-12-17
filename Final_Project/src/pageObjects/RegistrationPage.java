package pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import Project_Utilities.BaseFunction;
import Project_Utilities.CommonFunction;

public class RegistrationPage extends BaseFunction
{
	CommonFunction cf = new CommonFunction();
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
		
		cf.SplitTheDate("23/8/1956");
		ValuToreport=FirstName.getAttribute("name");
		cf.SendKeyAction(FirstName,"fab",ValuToreport);
		ValuToreport=LastName.getAttribute("name");
		cf.SendKeyAction(LastName,"fabo",ValuToreport);
		ValuToreport=LastName.getAttribute("name");
		cf.SendKeyAction(Email,"publisherpete4@gmail.com",ValuToreport);
		Thread.sleep(700);
		ValuToreport=LastName.getAttribute("name");
		cf.SendKeyAction(Password,"12345",ValuToreport);
		cf.SelectDropdown(Monthdropdown, cf.Month);
		cf.SelectDropdown(Daysdropdown, cf.DAY);
		cf.SelectDropdown(Yeardropdown, cf.Year);
		ValuToreport=RadioButtonSex.get(1).getAttribute("name");
		cf.ClickOnElement(RadioButtonSex.get(1),ValuToreport);
		ValuToreport=RegisterButton.getAttribute("innerHTML");
		cf.ClickOnElement(RegisterButton,ValuToreport);
		cf.waitToElement(ErrorMessage);  
		cf.verifyElementExist(ErrorMessage);
		Thread.sleep(500);
	}
}

