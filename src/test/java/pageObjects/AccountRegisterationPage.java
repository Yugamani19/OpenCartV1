package pageObjects;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegisterationPage extends BasePage {

	public AccountRegisterationPage(WebDriver driver) {
		super(driver);
		
	}
	
	@FindBy(id ="input-firstname")
	WebElement firstNametxt;
	
	@FindBy(id ="input-lastname")
	WebElement lastNametxt;
	
	@FindBy(id ="input-email")
	WebElement emailtxt;
	
	@FindBy(id ="input-telephone")
	WebElement phoneNotxt;
	
	@FindBy(id ="input-password")
	WebElement passwordtxt;
	
	@FindBy(id ="input-confirm")
	WebElement cnfrmPwdtxt;
	
	@FindBy(name ="agree")
	WebElement agreeButton;
	
	@FindBy(xpath ="//input[@value='Continue']")
	WebElement continueButton;
	
	@FindBy(xpath ="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmMsg;
	
	public void setFirstName(String fName)
	{
		firstNametxt.sendKeys(fName);
	}
	
	public void setLastName(String lName)
	{
		lastNametxt.sendKeys(lName);
	}
	
	public void setEmail(String email)
	{
		emailtxt.sendKeys(email);
	}
	
	public void setPhNo(String phno)
	{
		phoneNotxt.sendKeys(phno);
	}
	
	public void setPassword(String password)
	{
		passwordtxt.sendKeys(password);
	}
	
	public void setConfirmPassword(String password)
	{
		cnfrmPwdtxt.sendKeys(password);
	}
	
	public void clickAgree()
	{
		agreeButton.click();
	}
	
	public void clickContinue()
	{
		//sol1
		continueButton.click();
		
		//sol2
		//continueButton.submit();
		
		//sol3
		//Actions act = new Actions(driver);
		//act.moveToElement(continueButton).click().perform();
		
		//sol4
		//JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].click();", continueButton);
		
		//sol5
		//continueButton.sendKeys(Keys.ENTER);
		
		//sol6
		//WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
	}
	
	public String getConfirmationMsg()
	{
		try
		{
			return (confirmMsg.getText());
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	
	

}
