package com.nexlogica.dashboard.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {
	private String result = "";
	private InputStream inputStream;
	
	public String getLocationProperty() throws IOException {
		
		try {
			Properties properties = new Properties();
			String propertiesFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
			
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertiesFileName + "' not found.");
			}
			
			result = properties.getProperty("location");
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return result;
		
	}
	
	public String getDCTMRestLocation() throws IOException {
		try {
			Properties properties = new Properties();
			String propertiesFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
			
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertiesFileName + "' not found.");
			}
			
			result = properties.getProperty("dctm-rest");
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return result;
	}
	public String getDCTMRepoName() throws IOException {
		try {
			Properties properties = new Properties();
			String propertiesFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
			
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertiesFileName + "' not found.");
			}
			
			result = properties.getProperty("dctm-repo");
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return result;
	}
	public String getDCTMUsername() throws IOException {
		try {
			Properties properties = new Properties();
			String propertiesFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
			
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertiesFileName + "' not found.");
			}
			
			result = properties.getProperty("dctm-username");
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return result;
	}
	
	public String getDCTMPassword() throws IOException {
		try {
			Properties properties = new Properties();
			String propertiesFileName = "config.properties";
			
			inputStream = getClass().getClassLoader().getResourceAsStream(propertiesFileName);
			
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propertiesFileName + "' not found.");
			}
			
			result = properties.getProperty("dctm-password");
			
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return result;
	}
}