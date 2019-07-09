package com.sp.marketplaceapi.application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Properties;

public class AppPropertyValues {
	Dictionary configProperties = new Hashtable(); 
	InputStream inputStream;
 
	public Dictionary getPropValues() throws IOException {
 		
		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			configProperties.put("dbHostname", prop.getProperty("dbHostname"));
			configProperties.put("dbSchema", prop.getProperty("dbSchema"));
			configProperties.put("dbUser", prop.getProperty("dbUser"));
			configProperties.put("dbPassword", prop.getProperty("dbPassword"));
 
			//result = "Company List = " + company1 + ", " + company2 + ", " + company3;
			//System.out.println(result + "\nProgram Ran on " + time + " by user=" + user);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return configProperties;
	}
}
