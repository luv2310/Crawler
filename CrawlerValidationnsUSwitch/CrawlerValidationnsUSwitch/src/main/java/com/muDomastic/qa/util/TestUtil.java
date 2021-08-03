package com.muDomastic.qa.util;

import java.io.BufferedReader;
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
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.muDomastic.qa.base.TestBase;


public class TestUtil extends TestBase {
	public static long PAGE_LOAD_TIME = 30;
	public static long ImplicitWait = 20;


	public  void clickElement(WebElement webObject) 
	{
		try {

			webObject.click();
		}
		catch (Exception e) {
			System.out.println("error occured" + e);
		}
	}

	public  boolean verifyElementPresent(WebElement webObject) 
	{
		boolean result = false;
		try {
			result = webObject.isDisplayed();
		}
		catch (Exception e) {
			System.out.println("error occured" + e);
		}
		return result;
	}

	public  void clickVerifiedElement(WebElement webObject) 
	{
		try {


			if(webObject.isDisplayed())
			{
				webObject.click();
			}
		}
		catch (Exception e) {
			System.out.println("error occured : " + e);
		}
	}
	public void sendText(WebElement webObject, String data) 
	{
		try {

			webObject.sendKeys(data);

		}
		catch (Exception e) {
			System.out.println("error occured" + e);
		}
	}

	public  void wait(int seconds) 
	{
		try {
			seconds=seconds*1000;
			Thread.sleep(seconds);		
		}
		catch (Exception e) {
			System.out.println("error occured" + e);
		}
	}

	public  void selectDropDownByValue(WebElement webObject, String value) 
	{
		try {
			Select dropdown = new Select(webObject);
			dropdown.selectByValue(value);		
		}
		catch (Exception e) {
			System.out.println("error occured" + e);
		}
	}
	public  String selectDropDownByVisibleText(WebElement webObject, String value) 
	{
		String Result = "Fail";
		try {
			Select dropdown = new Select(webObject);
			dropdown.selectByVisibleText(value);	
			Result = "Pass";
		}
		catch (Exception e) {
			System.out.println("error occured" + e);
			Result = "Fail";
		}
		return Result;	
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

				/** in case server is not up .. putting status 404 */
				responseMap.put("statuscode", 404);
			}

			if(httpResponse != null) {

				BufferedReader rd = null;
				try{

					rd = new BufferedReader( new InputStreamReader(httpResponse.getEntity().getContent()));
				}catch(NullPointerException e){
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
		}

		return responseMap;
	}

	public  String randomEmailGenerator() 
	{  
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) 
		{ 
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr+"@gmail.com";
	}
	
	public boolean retryingFindClick(By by) {
	    boolean result = false;
	    int attempts = 0;
	    while(attempts < 2) {
	        try {
	            driver.findElement(by);
	            result = true;
	            break;
	        } catch(StaleElementReferenceException e) {
	        }
	        attempts++;
	    }
	    return result;
	}

	
}