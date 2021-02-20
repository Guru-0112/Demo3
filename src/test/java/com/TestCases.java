package com;

import java.util.HashMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestCases extends BaseClass{
	@Test(dataProvider="return Data")
	public void login_testcases(HashMap<String, String>hash)
	{
		System.out.println("Results is::"+hash.get("username"));
		extentTest=extentReports.createTest("Login");
		Launch login=new Launch(driver);
		login.login(("username"),hash.get("password"));
	}
	@DataProvider
	public Object[][] returnData() throws Throwable
	{
		return DataDriven.getData("E:\flipkartxcelsheet.xlsx", "Login");
	}
	

}
