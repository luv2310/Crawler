package com.muDomastic.qa.TestCases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import com.muDomastic.qa.base.TestBase;
import com.muDomastic.qa.base.apiFetchedData;
import com.muDomastic.qa.base.dataDump;
import com.muDomastic.qa.page.CompareTheMarketPage;
import com.muDomastic.qa.util.TestUtil;


public class CompareTheMarketGenericScenario extends TestBase {

	TestBase testbase = new TestBase();
	JSONArray testCasesArray = null;
	HashMap<String,HashMap<String, Map>> super_getallthedetails = new HashMap<>();
	int count =1;
	static int attempt ;
	TestUtil testUtil = new TestUtil();

	//..................setting the value to run test as per config file ...................................


	String runWithoutFetchingData = testbase.setValuesforexecution().get("runWithoutFetchingData");
	String specificRequestID = testbase.setValuesforexecution().get("specificRequestID");
	String requestID = testbase.setValuesforexecution().get("requestID");
	String runWithoutPostingtheData = testbase.setValuesforexecution().get("runWithoutPostingtheData");
	int setReAttempt = Integer.parseInt(testbase.setValuesforexecution().get("setReAttempt"));

	//..........................................................................................................

	String postCode,partialAddress,supplierName,paymentMethod = null,plan = null,gasusage,eleusage,nightPercent,
			gasSpendFrequency,electricitySpendFrequency,requestId,electricitySuppliers,gasSupplier;

	boolean hasGas,isDualFuel,hasElectricity,isEconomy7;
	//...........................................................................................................

	


	
	public  CompareTheMarketGenericScenario() {
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
			if(runWithoutFetchingData.toLowerCase().contains("false"))
			{
				new apiFetchedData().fetchData();
			}


			//create test cases array
			file = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\data.json")), StandardCharsets.UTF_8);
			JSONObject obj = new JSONObject(file);
			testCasesArray = new JSONArray(obj.get("response").toString());			

			//modify the created test array with the specific request id
			if(specificRequestID.toLowerCase().contains("true"))
			{ 
				List<String> requestIDlist = new ArrayList<String>();
				int compareCount = 0 ;
				JSONArray testCasesArraytemp =new JSONArray();
				if(requestID.contains("-"))
				{
					String[] requestIDs = requestID.split("-");
					requestIDlist = Arrays.asList(requestIDs);
				}	
				else {
					requestIDlist.add(requestID);
				}

				for (Object testcase : testCasesArray)
				{	
					JSONObject testCaseJsonObj = new JSONObject(testcase.toString()); 
					if(requestIDlist.contains(testCaseJsonObj.get("requestId").toString()))
					{							
						testCasesArraytemp.put(compareCount, testcase);		
						compareCount++;
					}
				}
				testCasesArray = testCasesArraytemp;			
			}


		} catch (Exception e) {
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
					electricitySuppliers= testCaseJsonObj.get("electricitySuppliers").toString();	
					gasSupplier= testCaseJsonObj.get("gasSupplier").toString();	
				}
				else
				{
					if(hasGas)
					{
						supplierName = testCaseJsonObj.get("gasSupplier").toString();	
						paymentMethod = testCaseJsonObj.get("gasPaymentMethod").toString();	
						plan = testCaseJsonObj.get("gasPlan").toString();
						gasSupplier= testCaseJsonObj.get("gasSupplier").toString();	

					}
					if(hasElectricity)
					{
						supplierName = testCaseJsonObj.get("electricitySuppliers").toString();	
						paymentMethod = testCaseJsonObj.get("electricityPaymentMethod").toString();	
						plan = testCaseJsonObj.get("electricityPlan").toString();
						electricitySuppliers= testCaseJsonObj.get("electricitySuppliers").toString();	
					}
					else
					{
						supplierName="Not Known";
					}
				}

				//attempt given for crawler rerun in case of failure 
				try {
					attempt =setReAttempt;
				}							
				catch (Exception e) {
					attempt = 3;		
				}


				//runcrawler now
				runCrawler();

				count++;
			}
		}
		catch (Exception e) 
		{
			System.out.println(":: Exception occured in Class compareTheMarketGenericScenario, method name  execute value :: " );
			e.printStackTrace();
		}
	}


	@AfterMethod
	public void closeTest()
	{
		try {		 
			//saving data in yaml file before using post request 

			String filepath = System.getProperty("user.dir")+"\\CTM_data.yaml";
			System.out.println("Fetched data is at: "+filepath);
			Yaml yaml = new Yaml();	
			OutputStreamWriter writer = null;
			writer	= new OutputStreamWriter(new FileOutputStream(filepath), StandardCharsets.UTF_8);
			yaml.dump(super_getallthedetails, writer);

			//sending data to db using post request	
			if(runWithoutPostingtheData.contains("false"))
			{
				new dataDump().jsonVariables(filepath);	

			}

		}catch (Exception e) {
			System.out.println("Exception occured while running the closeTest method of class CompareTheElementGenericScenario ");
		}
	}

	public void runCrawler() {
		try{
			//running the crawler for compareTheMarket website
			CompareTheMarketPage CompareTheMarketPageObj=new CompareTheMarketPage();
			CompareTheMarketPageObj.CompareTheMarketJourney(
					postCode,
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
					electricitySuppliers,
					gasSupplier,
					hasGas,
					isDualFuel,
					isEconomy7);

			// storing the fetched data for u switch crawler 
			HashMap<String, Map> sectionData = CompareTheMarketPageObj.storedataNew(hasGas,paymentMethod);	

			if(sectionData.isEmpty())
			{
				String time  = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

				//taking screenshot of the failed error
				TakesScreenshot scrShot =((TakesScreenshot) driver);
				File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
				File DestFile=new File(System.getProperty("user.dir")+"\\screenShotResults\\CTM_"+requestId+"_requestID_"+attempt+"_attemptLeft_"+time+".png");
				FileUtils.copyFile(SrcFile, DestFile);		        

				//reattempt
				crawlerReAttempt();
			}		

			//close driver and add the execution in particluar field
			driverClose();

			super_getallthedetails.put("Execution-"+requestId, sectionData);

		}catch (Exception e) {
			System.out.println("Exception occured in runCrawler method of CompareTheElementGenericScenario  :: "+ e);
		}
	}
	public void crawlerReAttempt() 
	{
		try {
			driverClose();
			while(attempt>1) 
			{ 
				attempt--;			
				System.out.println("!!!!!! Failed During First Exceution, running again  !!!!!!!!, left attempt :: " + attempt);
				runCrawler();			
			}
		}
		catch (Exception e) {
			System.out.println(":: Exception occured in crawlerReAttempt method of compareTheMarketGenericScenario  :: "); 
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
		} catch (Exception e) {	System.out.println(":: Exception occured in driverClose method of compareTheMarketGenericScenario  :: "); 
		e.printStackTrace();
		}
	}

}


