package com;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.iterators.IteratorEnumeration;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Colors;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class BaseClass {
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	public static ExtentColor colour;
	public static WebDriver driver;
	@BeforeSuite
	public void generateHtmlReport()
	{
	htmlReporter=new ExtentHtmlReporter("user,dir"+"/Results"+"/Report.html");
	extentReports=new ExtentReports();
	extentReports.attachReporter(htmlReporter);
	htmlReporter.config().setFilePath("Automation Results");
	htmlReporter.config().setDocumentTitle("Automation Results");
	}
	@BeforeTest
	public  void launchUrl()
	{
		System.setProperty("webdriver.chrome.driver","E:\\libs\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.navigate().to("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	@AfterTest
	public void closeHtmlReports()
	{
		extentReports.flush();
		driver.close();
	}
	@AfterMethod
	public void resultStatus(ITestResult result)throws Throwable
	{
		if (result.getStatus()==ITestResult.FAILURE) {
			extentTest.addScreenCaptureFromPath(screenshot(driver));
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"test case fail", colour.RED));
		}
     	else if (result.getStatus()==ITestResult.SKIP) {
			extentTest.addScreenCaptureFromPath(screenshot(driver));
			extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"test case skip",colour.BLUE));
			
			
		}
		else if(result.getStatus()==ITestResult.SUCCESS) {
			extentTest.addScreenCaptureFromPath(screenshot(driver));
			extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"test case pass", colour.GREEN));
			
			
		}
	}
	private String screenshot(WebDriver driver) throws IOException {
	String date=new SimpleDateFormat("YYYYMMDDhhmmss").format(new Date());
	TakesScreenshot ts=(TakesScreenshot)driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	String destination=System.getProperty("user,dir"+"/failed test cases"+".png");
	File finaldestination=new File(destination);
	FileUtils.copyFile(source, finaldestination);
		return destination;
	}

}
