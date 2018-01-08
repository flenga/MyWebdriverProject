package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import projectUtilities.BaseFunction;


public class SignInErrorsPage extends BaseFunction
{
	public WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//*[@id='center_column']/div[1]")
	public WebElement ErrorForSignIn;
	
	@FindBy(how = How.XPATH, using = "//*[@id='center_column']/div[1]/p")
	public WebElement ErrorForSignInTitle;
	
	@FindBy(how = How.XPATH, using = "//*[@id='center_column']/div[1]/ol/li")
	public WebElement ErrorForSignInSpecific;
	
	@FindBy(how = How.XPATH, using = "//*[@id='create_account_error']/ol/li")
	public WebElement ErrorForCreateAccount;
}