package com.muDomastic.qa.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.muDomastic.qa.base.TestBase;


public class TestUtil extends TestBase {
	public static long PAGE_LOAD_TIME = 30;
	public static long ImplicitWait = 20;




	public void clickElement(WebElement webObject) 
	{		
		try {
			webObject.click();
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public boolean verifyElementPresent(WebElement webObject) 
	{		
		boolean result = false;
		try {			
			result = webObject.isDisplayed();			
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return result;
	}

	public void clickVerifiedElement(WebElement webObject) 
	{		
		try {
			if(webObject.isDisplayed())
			{
				webObject.click();
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void  sendText(WebElement webObject, String data) 
	{		
		try {

			webObject.sendKeys(data);
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void wait(int seconds)
	{		
		try {
			seconds=seconds*1000;
			Thread.sleep(seconds);		
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void selectDropDownByValue(WebElement webObject, String value)
	{		
		try {
			Select dropdown = new Select(webObject);
			dropdown.selectByValue(value);	
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public String selectDropDownByVisibleText(WebElement webObject, String value) 
	{
		String Result = "Fail";	
		try {

			Select dropdown = new Select(webObject);
			dropdown.selectByVisibleText(value);	
			Result = "Pass";
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return Result;	
	}

	public String randomEmailGenerator() 
	{		
		String saltStr = null;
		try {
			String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			StringBuilder salt = new StringBuilder();
			Random rnd = new Random();
			while (salt.length() < 10) 
			{ 
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
			saltStr = salt.toString();

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return saltStr+"@gmail.com";
	}

	public boolean retryingFindClick(By by) {
		boolean result = false;
		int attempts = 1;
		while(attempts < 3) {
			try {
				System.out.println("attempt number for stale exception : "+attempts);
				driver.findElement(by);
				result = true;
				break;
			} catch(StaleElementReferenceException e) {
				attempts++;
			}
		}
		return result;
	}

	public List<WebElement> listext(WebElement webObject) 
	{		
		List<WebElement> webEleList = null;
		try {
			String Result = "Fail";

			// Below will return a list of all elements inside element
			webEleList	= webObject.findElements(By.xpath(".//*"));

			//For above given HTML it will print 5. As there are 5 elements inside div
			System.out.println(webEleList.size());
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return webEleList;	
	}
	public void clickWithAttempt(WebElement webObject) 
	{			
		try {
			int attempt = 5; 
			while(attempt>1) 
			{
				wait(1);
				System.out.println("clicking with attempt method , left attempt  :: " + attempt);
				if(webObject.isEnabled())
				{
					clickElement(webObject);
					break;
				}
				attempt--;
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void  clickWithAttemptActions(WebElement webObject) 
	{		
		try {
			int attempt = 5; 
			while(attempt>1) 
			{
				wait(1);
				System.out.println("clicking with attempt using actions method , left attempt  :: " + attempt);
				if(webObject.isEnabled())
				{
					Actions actions = new Actions(driver);
					actions.moveToElement(webObject).click().perform();	
					break;
				}
				attempt--;
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void clickWithjavascriptattempt(WebElement webObject) 
	{		
		try {
			int attempt = 5; 
			while(attempt>1) 
			{
				wait(1);
				System.out.println("clicking with attempt using actions method , left attempt  :: " + attempt);
				if(webObject.isEnabled())
				{
					JavascriptExecutor jse = (JavascriptExecutor)driver;
					jse.executeScript("arguments[0].click();", webObject);
					break;
				}
				attempt--;
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void killOpera() 
	{		
		try {
			Runtime rt = Runtime.getRuntime();
			rt.exec("taskkill /F /IM opera.exe");
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	/** send get request with timeout param 
	 * 
	 * @param url
	 * @return
	 */
	public HashMap<Object, Object> sendGetRequestWithParams(String url) {
		HashMap<Object, Object> responseMap = new HashMap<>();
		try
		{
			int timeout = 60 * 1000; //sec 
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(timeout)
					.setConnectionRequestTimeout(timeout)
					.setSocketTimeout(timeout)
					.build();
			HttpClient httpClient = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
			HttpResponse httpResponse = null;
			try {
				httpResponse = httpClient.execute(new HttpGet(url));
			}catch (Exception e) {
				System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
				e.printStackTrace();
				responseMap.put("statuscode", 404);
			}
			if(httpResponse != null) {

				BufferedReader rd = null;
				try{
					rd = new BufferedReader( new InputStreamReader(httpResponse.getEntity().getContent()));
				}catch(NullPointerException e){
					System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
					e.printStackTrace();
				}
				StringBuffer response = new StringBuffer();
				if(rd != null){
					String line = "";
					while ((line = rd.readLine()) != null){
						response.append(line);
					}
				}
				responseMap.put("response", response.toString());
				responseMap.put("statuscode", httpResponse.getStatusLine().getStatusCode());
			}

		}catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return responseMap;
	}


}