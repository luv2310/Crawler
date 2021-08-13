package com.muDomastic.qa.TestCases;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import com.muDomastic.qa.base.TestBase;
import com.muDomastic.qa.base.apiFetchedData;
import com.muDomastic.qa.base.dataDump;
import com.muDomastic.qa.page.CompareTheElementPage;
import com.muDomastic.qa.page.USwitchPage;
import com.muDomastic.qa.util.TestUtil;


public class USwitchGenericScenario extends TestBase {
	JSONArray testCasesArray = null;
	static int attempt;
	HashMap<String,HashMap<String, Map>> super_getallthedetails = new HashMap<>();
	int count =1;
	TestUtil testUtil = new TestUtil();
	String postCode,partialAddress,supplierName,paymentMethod = null,plan = null,gasusage,eleusage,nightPercent,
			gasSpendFrequency,electricitySpendFrequency,requestId;

	boolean hasGas,isDualFuel,hasElectricity,isEconomy7;

	public  USwitchGenericScenario() {
		//call the base class constructor to initilize the propt
		super();
	}

	@BeforeMethod
	public void setup() {
		try {
			String file = null;		

			//Kill any open Opera browser
			testUtil.killOpera();

			//fetch test cases data and create a data.json file.
			new apiFetchedData().fetchData();

			//create test cases array
			file = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\data.json")), StandardCharsets.UTF_8);
			JSONObject obj = new JSONObject(file);
			testCasesArray = new JSONArray(obj.get("response").toString());			
		} catch (Exception e) {
			System.out.println(":: Exception Occured in the setup method of  USwitchGenericScenario's ::");
			e.printStackTrace();
		}
	}

	@Test
	public void executevalue() 
	{
		try 
		{	
			// iterating the test cases array one by one and running the journey with data.json scenario's
			for (Object testcase : testCasesArray)
			{
				System.out.println("runnning test case number : "+ count + " out of " +
						testCasesArray.length() + " Test Cases"); 

				JSONObject testCaseJsonObj = new JSONObject(testcase.toString());
				postCode = testCaseJsonObj.get("postCode").toString();
				partialAddress = testCaseJsonObj.get("partialAddress").toString();
				hasGas = (boolean) testCaseJsonObj.get("hasGas");
				isDualFuel = (boolean) testCaseJsonObj.get("isDualFuel");
				isEconomy7 = (boolean) testCaseJsonObj.get("isEconomy7");
				hasElectricity= (boolean) testCaseJsonObj.get("hasElectricity");
				gasSpendFrequency=testCaseJsonObj.get("gasSpendFrequency").toString();
				gasSpendFrequency = gasSpendFrequency.trim().replaceAll(" +", " ");
				electricitySpendFrequency=testCaseJsonObj.get("electricitySpendFrequency").toString();
				electricitySpendFrequency = electricitySpendFrequency.trim().replaceAll(" +", " ");
				gasusage = testCaseJsonObj.get("usageForGas").toString();
				eleusage = testCaseJsonObj.get("usageForElectricity").toString();
				nightPercent = testCaseJsonObj.get("electricityNightPercentage").toString();
				requestId =  testCaseJsonObj.get("requestId").toString();

				//set supplier name
				if(isDualFuel)
				{
					supplierName = testCaseJsonObj.get("dualFuelSuppliers").toString();	
					paymentMethod = testCaseJsonObj.get("dualFuelPaymentMethod").toString();	
					plan = testCaseJsonObj.get("dualFuelPlan").toString();
				}
				else
				{
					if(hasGas)
					{
						supplierName = testCaseJsonObj.get("gasSupplier").toString();	
						paymentMethod = testCaseJsonObj.get("gasPaymentMethod").toString();	
						plan = testCaseJsonObj.get("gasPlan").toString();
					}
					if(hasElectricity)
					{
						supplierName = testCaseJsonObj.get("electricitySuppliers").toString();	
						paymentMethod = testCaseJsonObj.get("electricityPaymentMethod").toString();	
						plan = testCaseJsonObj.get("electricityPlan").toString();
					}
					else
					{
						supplierName="Not Known";
					}
				}

				//attempt given for crawler rerun in case of failure 
				attempt = 3;

				//runcrawler now
				runCrawler();

				count++;
			}
		}
		catch (Exception e) 
		{
			System.out.println(":: Exception occured in Class uSwitchGenericScenario, method name  execute value :: " );
			e.printStackTrace();
		}
	}


	@AfterMethod
	public void closeTest()
	{
		try {		 

			//saving data in yaml file before using post request 
			System.out.println("Fetched data is at: "+System.getProperty("user.dir")+"\\USwitch_data.yaml");
			Yaml yaml = new Yaml();	
			OutputStreamWriter writer = null;
			writer	= new OutputStreamWriter(new FileOutputStream(System.getProperty("user.dir")+"\\USwitch_data.yaml"), StandardCharsets.UTF_8);
			yaml.dump(super_getallthedetails, writer);

			//sending data to db using post request	
			new dataDump().jsonVariables();	

		}catch (Exception e) {
			System.out.println(":: Exception occured while running the closeTest method of class USwitchGenericScenario's ::");
			e.printStackTrace();
		}
	}

	public void runCrawler() {
		try{

			//running the crawler for uswitch website
			USwitchPage UswitchPageObj=new USwitchPage();
			UswitchPageObj.uSwitchJourney(postCode,
					partialAddress,
					supplierName,
					paymentMethod,
					plan,
					gasusage,
					eleusage,
					gasSpendFrequency,
					electricitySpendFrequency,
					nightPercent,
					requestId,
					hasGas,
					isDualFuel,
					isEconomy7);

			// storing the fetched data for u switch crawler 
			HashMap<String, Map> sectionData = UswitchPageObj.storedataNew(hasGas,paymentMethod);	
			driverClose();
			if(sectionData.isEmpty())
			{
				crawlerReAttempt();
			}		
			super_getallthedetails.put("Execution-"+requestId, sectionData);
		}catch (Exception e) {
			System.out.println(":: Exception occured in runCrawler method of uSwitchGenericScenario  :: "); 
			e.printStackTrace();
		}
	}

	public void crawlerReAttempt() 
	{
		try {
			while(attempt>1) 
			{ 
				attempt--;			
				System.out.println("!!!!!! Failed During First Exceution, running again  !!!!!!!!, left attempt :: " + attempt);
				runCrawler();			
			}
		}
		catch (Exception e) {
			System.out.println(":: Exception occured in crawlerReAttempt method of uSwitchGenericScenario  :: "); 
			e.printStackTrace();
		}	
	}

	public void driverClose() 
	{
		try {
			driver.close();
			driver.quit();
			Thread.sleep(10000);
			if (!driver.toString().toLowerCase().contains("null"))
			{
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+driver.toString());
				Thread.sleep(20000);
				driver.quit();
			}
		} catch (Exception e) {	System.out.println(":: Exception occured in driverClose method of uSwitchGenericScenario  :: "); 
		e.printStackTrace();
		}
	}

}




