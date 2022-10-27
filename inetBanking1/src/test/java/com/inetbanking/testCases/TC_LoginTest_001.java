package com.inetbanking.testCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
	
	@Test
	public void loginTest() throws InterruptedException, IOException {
		
		logger.info("URL is opened");
		Thread.sleep(5000);
		LoginPage login=new LoginPage(driver);
		login.setUserName(username);
		Thread.sleep(5000);
		logger.info("Username is entered");
		login.setPassword(password);
		Thread.sleep(5000);
		logger.info("Password is entered");
		login.clickSubmit();
		
		
		Thread.sleep(3000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(2000);
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			captureScreen(driver, "loginTest"); 
			Assert.assertTrue(false);
			logger.info("Login failed");
		}
	}
	
}
