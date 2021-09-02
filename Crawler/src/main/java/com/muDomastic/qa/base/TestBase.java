package com.muDomastic.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;

import com.muDomastic.qa.util.TestUtil;

public class TestBase {

	public static WebDriver driver = null;
	public static Properties propt;
	public static String path = System.getProperty("user.dir");

	public TestBase() {

		// read the property
		try {
			propt = new Properties();
			FileInputStream ip = new FileInputStream(
					path + "\\src\\main\\java\\com\\muDomastic\\qa\\config\\config.properties");
			propt.load(ip);
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static HashMap<String, String> setValuesforexecution() 
	{
		HashMap<String,String> configValue = new HashMap<String,String>();
		try {	
			configValue.put("runWithoutFetchingData", propt.getProperty("runWithoutFetchingData"));
			configValue.put("specificRequestID", propt.getProperty("specificRequestID"));
			configValue.put("requestID", propt.getProperty("requestID"));
			configValue.put("runWithoutPostingtheData", propt.getProperty("runWithoutPostingtheData"));
			configValue.put("setReAttempt", propt.getProperty("setReAttempt"));
		}catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}		
		return configValue;			
	}

	public static WebDriver initialization(String website) {
		try {
			String browserName = propt.getProperty("browser");
			String url = propt.getProperty(website);
			if (browserName.equals("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(TestUtil.ImplicitWait, TimeUnit.SECONDS);
				driver.get(url);
			}

			if (browserName.equals("opera")) 
			{
				DesiredCapabilities capablities=DesiredCapabilities.opera();
				String opera_profile = propt.getProperty("opera_profile");
				String operaBinary =propt.getProperty("operaBinary");
				OperaOptions operaoptions = new OperaOptions();
				//	ChromeOptions chromeOptions = new ChromeOptions();
				operaoptions.addArguments("--user-data-dir="+opera_profile);
				operaoptions.addArguments("--disable-blink-features=AutomationControlled");
			    if(!website.contains("money"))
			    {
					operaoptions.addArguments("private");
			    }
				operaoptions.setBinary(operaBinary);
				System.setProperty("webdriver.opera.driver",System.getProperty("user.dir")+"\\driver\\operadriver.exe");
				capablities.setCapability(ChromeOptions.CAPABILITY, operaoptions);
				driver = new OperaDriver(capablities);
				driver.manage().window().maximize();
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(TestUtil.ImplicitWait, TimeUnit.SECONDS);
				driver.get(url);

				/*
				 * hack to handle vpn error 
				 * */
				try{
					String errorxpath = "//div[@class=\"error-code\"] | //*[contains(@id,'cf-error-details')]";
					if(	driver.findElement(By.xpath(errorxpath)).isDisplayed()){
						initialization(website);
					}					
				}catch (Exception e) {
					System.out.println("no vpn error occured continue the test");
				}				
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return driver;
	}

}
