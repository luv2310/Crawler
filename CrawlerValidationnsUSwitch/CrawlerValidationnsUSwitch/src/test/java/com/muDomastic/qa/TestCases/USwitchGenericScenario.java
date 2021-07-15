package com.muDomastic.qa.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.muDomastic.qa.base.TestBase;
import com.muDomastic.qa.page.USwitchLandingPage;


public class USwitchGenericScenario extends TestBase {
	
	
	public  USwitchGenericScenario() {
		//call the base class constructor to initilize the propt
		super();
	}
	
	@BeforeMethod
	public void setup() {
		//initialize from baseclass
		initialization();		
	}
	
	@Test
	public void executevalue() {
		//create page class objects 
		USwitchLandingPage UswitchPageObj=new USwitchLandingPage();
		//call the functions/actions to be performed  
		UswitchPageObj.providePostcode();
		UswitchPageObj.storedata();
	}
	
	
	@AfterMethod
	public void closeTest() {      
	driver.quit();
		
	}
	
	
}


