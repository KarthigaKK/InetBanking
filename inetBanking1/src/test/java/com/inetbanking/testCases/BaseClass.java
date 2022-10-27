package com.inetbanking.testCases;




import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.readConfig;




public class BaseClass {
	
	readConfig readconfig= new readConfig();
	public String baseurl=readconfig.getApplicationURL();
	public String username=readconfig.getUsername();
	public String password=readconfig.getPassword();
	public static WebDriver driver;
	public static Logger logger;
	
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) {
		
		//Application name
		logger =Logger.getLogger("ebanking");
		PropertyConfigurator.configure("log4j.properties");
				
		if(br.equals("chrome"))
		{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromepath());
		driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
		System.setProperty("webdriver.gecko.driver",readconfig.getfirefoxpath());
		driver=new FirefoxDriver();
		}
		else if(br.equals("ie"))
		{
		System.setProperty("webdriver.ie.driver",readconfig.getInternetExplorer());
		driver=new InternetExplorerDriver();
		}	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(baseurl);
		driver.manage().window().maximize();
	}
		
	
	@AfterClass
	public void teardown() {
		//driver.quit();
	}
	
	public void captureScreen(WebDriver driver,String tname) throws IOException {
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source=ts.getScreenshotAs(OutputType.FILE);  
		File target=new File(System.getProperty("user.dir")+"/Screenshots/"+tname+".png");
		FileUtils.copyFile(source, target);
		logger.info("Screenshot Taken");
	}
	
	public String randomString() {
		String generatedemail=RandomStringUtils.randomAlphabetic(8);
		return(generatedemail);
		
	}
	
	public String randomNUmberic() {
		String generatedNumber=RandomStringUtils.randomNumeric(4);
		return(generatedNumber);
	}

}
