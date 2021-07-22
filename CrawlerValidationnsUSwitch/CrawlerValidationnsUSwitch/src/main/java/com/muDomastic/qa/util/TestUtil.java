package com.muDomastic.qa.util;

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

	public  void verifyElementPresent(WebElement webObject) 
	{
		try {


			webObject.isDisplayed();
		}
		catch (Exception e) {
			System.out.println("error occured" + e);
		}
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
			System.out.println("error occured" + e);
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
	public  void selectDropDownByVisibleText(WebElement webObject, String value) 
	{
		try {
			Select dropdown = new Select(webObject);
			dropdown.selectByVisibleText(value);		
		}
		catch (Exception e) {
			System.out.println("error occured" + e);
		}	
	}
}