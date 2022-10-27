package com.inetbanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class readConfig {

	 Properties prop;
	 
	 public readConfig() {
		 
		 File src=new File("./Configuration/config.properties");
		 try {
			 FileInputStream fis=new FileInputStream(src);
			 prop=new Properties();
			 prop.load(fis);
		} catch (Exception e) {
			System.out.println("Error Message"+e.getMessage());
		}
		 
	 }
	 
	 public String getApplicationURL() {
		 return prop.getProperty("baseURL");
	 }
	 
	 public String getUsername() {
		 return prop.getProperty("username");
	 }
	 
	 public String getPassword() {
		 return prop.getProperty("password");
	 }
	 
	 public String getChromepath() {
		 return prop.getProperty("chromepath");
	 }
	 
	 public String getfirefoxpath() {
		 return prop.getProperty("firefoxpath");
	 }
	 
	 public String getInternetExplorer() {
		 return prop.getProperty("iepath");
	 }
}
