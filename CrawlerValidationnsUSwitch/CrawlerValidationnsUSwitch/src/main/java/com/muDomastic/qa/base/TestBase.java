package com.muDomastic.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

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
		} catch (FileNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void initialization() {
		String browserName = propt.getProperty("browser");
		if (browserName.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\driver\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.ImplicitWait, TimeUnit.SECONDS);
			driver.get(propt.getProperty("url"));
		}

		if (browserName.equals("opera")) 
		{
			DesiredCapabilities capablities=DesiredCapabilities.opera();
			String opera_profile = "C:\\Users\\luv.mendiratta\\AppData\\Roaming\\Opera Software\\Opera Stable";
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--user-data-dir="+opera_profile);
			chromeOptions.addArguments("private");
			chromeOptions.setBinary("C:\\Users\\luv.mendiratta\\AppData\\Local\\Programs\\Opera\\77.0.4054.256\\opera.exe");
			System.setProperty("webdriver.opera.driver",System.getProperty("user.dir")+"\\driver\\operadriver.exe");
			capablities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			driver = new OperaDriver(capablities);
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.ImplicitWait, TimeUnit.SECONDS);
			driver.get(propt.getProperty("url"));
		}
	}

}
