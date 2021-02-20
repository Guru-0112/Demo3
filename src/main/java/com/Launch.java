package com;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.appium.java_client.functions.ExpectedCondition;

public class Launch extends BaseClass{
	@FindBy(xpath="//input[@classname='_2IX_2- VJZDxU']")
	public WebElement username;
	@FindBy(xpath="//input[@classname='_2IX_2- _3mctLh VJZDxU']")
	public WebElement password;
	@FindBy(xpath="//input[@classname='_2KpZ6l _2HKlqd _3AWRsL']")
	public WebElement click;
	@FindBy(xpath="//span[contains(text(),'Your username or password is incorrect')]")
	public WebElement loginError;
	@FindBy(xpath="//input[@classname='xtXmba']")
	public WebElement home;
	public Launch(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public void login(String username,String password)
	{
		WebDriverWait wait=new WebDriverWait(driver, 10);
		extentTest.log(Status.INFO, MarkupHelper.createLabel("Test fields are Entering", colour.GREY));
		
		this.username.sendKeys(username);
		extentTest.log(Status.INFO, MarkupHelper.createLabel("Enter The Username::"+username, colour.BLUE));
		this.password.sendKeys(password);
		extentTest.log(Status.INFO, MarkupHelper.createLabel("Enter The Password::"+password, colour.BLUE));
		this.click.click();
		extentTest.log(Status.INFO, MarkupHelper.createLabel("Click the Login::", colour.BROWN));
		try {
			String error=loginError.getText();
			if (error.equalsIgnoreCase("username and password do not match")) {
				extentTest.log(Status.PASS, MarkupHelper.createLabel("expected is not matched with actual:"+error,colour.RED));
			}
			this.username.clear();
			this.password.clear();
			
		} catch (Exception e) {
			String homePage=this.home.getText();
			if (homePage.equalsIgnoreCase("Home")) {
				extentTest.log(Status.PASS, MarkupHelper.createLabel("expected is matched with actual", colour.GREEN));
				
			}
			else {
				System.out.println("Fail");
			
		}
			
			
		}
	}
	
	
	

}
