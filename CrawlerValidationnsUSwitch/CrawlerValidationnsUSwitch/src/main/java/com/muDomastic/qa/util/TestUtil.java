package com.muDomastic.qa.util;

import org.openqa.selenium.WebElement;

import com.muDomastic.qa.base.TestBase;

public class TestUtil extends TestBase {
	public static long PAGE_LOAD_TIME = 20;
	public static long ImplicitWait = 10;

//click 
	/* validate if the object is present and enabled before clicking */
	public static void ClickElement(WebElement webObject) {
		webObject.isDisplayed();

	}

}