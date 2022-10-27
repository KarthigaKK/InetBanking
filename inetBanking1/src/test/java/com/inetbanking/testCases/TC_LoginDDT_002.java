package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass{
	
	@Test(dataProvider = "LoginData")
	public void loginDDT(String usrnm,String pwd) throws InterruptedException, IOException {
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(usrnm);
		logger.info("Entered Username");
		lp.setPassword(pwd);
		logger.info("Entered password");
		lp.clickSubmit();
		Thread.sleep(3000);
		if(isAlertPresent()==true)
		{
		driver.switchTo().alert().accept();
		driver.switchTo().defaultContent();
		Assert.assertTrue(false);
		captureScreen(driver, "loginDDL");
		logger.warn("Login failed");
		
	}
		else
		{
			Assert.assertTrue(true);
			logger.info("login passed");
			lp.clickLogout();
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			
		}
	}
	//User defined method to handle alert
	public boolean isAlertPresent() {
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
	
	}
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rowcount=XLUtils.getRowCount(path, "sheet1");
		int colcount=XLUtils.getCellCount(path, "sheet1", 1);
		String logindata[][]=new String[rowcount][colcount];
		
		
		for (int i=1;i<=rowcount;i++)
		{
			for (int j=0;j<colcount;j++)
			{
				 logindata[i-1][j]=XLUtils.getCellData(path, "sheet1", i, j);
			}
		}
		
	return logindata;	
	}

}
