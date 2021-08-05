package com.muDomastic.qa.TestCases;

import java.io.FileWriter;
import java.io.IOException;
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
import com.muDomastic.qa.page.USwitchLandingPage;


public class USwitchGenericScenario extends TestBase {
	JSONArray arr = null;
	HashMap<String,HashMap<String, Map>> super_getallthedetails = new HashMap<>();
	int count =1;

	public  USwitchGenericScenario() {
		//call the base class constructor to initilize the propt
		super();
	}

	@BeforeMethod
	public void setup() {
		String file = null;
		try {
			file = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\data.json")), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject(file);
		arr = new JSONArray(obj.get("response").toString());
	}


	@Test
	public void executevalue() throws IOException, InterruptedException {

		String postCode,partialAddress,supplierName,paymentMethod = null,plan = null,gasusage,eleusage,nightPercent,
				gasSpendFrequency,electricitySpendFrequency,requestId;
		boolean hasGas,isDualFuel,hasElectricity,isEconomy7;

		for (Object a :arr)
		{
			System.out.println("runnning test case number : "+ count + " out of " +
					arr.length() + " Test Cases"); 

			JSONObject jsonObj = new JSONObject(a.toString());

			postCode = jsonObj.get("postCode").toString();
			partialAddress = jsonObj.get("partialAddress").toString();
			hasGas = (boolean) jsonObj.get("hasGas");
			isDualFuel = (boolean) jsonObj.get("isDualFuel");
			isEconomy7 = (boolean) jsonObj.get("isEconomy7");
			hasElectricity= (boolean) jsonObj.get("hasElectricity");
			gasSpendFrequency=jsonObj.get("gasSpendFrequency").toString();
			gasSpendFrequency = gasSpendFrequency.trim().replaceAll(" +", " ");
			electricitySpendFrequency=jsonObj.get("electricitySpendFrequency").toString();
			electricitySpendFrequency = electricitySpendFrequency.trim().replaceAll(" +", " ");
			gasusage = jsonObj.get("usageForGas").toString();
			eleusage = jsonObj.get("usageForElectricity").toString();
			nightPercent = jsonObj.get("electricityNightPercentage").toString();
			requestId =  jsonObj.get("requestId").toString();
			

			//set supplier name
			if(isDualFuel)
			{
				supplierName = jsonObj.get("dualFuelSuppliers").toString();	
				paymentMethod = jsonObj.get("dualFuelPaymentMethod").toString();	
				plan = jsonObj.get("dualFuelPlan").toString();
			}
			else
			{
				if(hasGas)
				{
					supplierName = jsonObj.get("gasSupplier").toString();	
					paymentMethod = jsonObj.get("gasPaymentMethod").toString();	
					plan = jsonObj.get("gasPlan").toString();
				}
				if(hasElectricity)
				{
					supplierName = jsonObj.get("electricitySuppliers").toString();	
					paymentMethod = jsonObj.get("electricityPaymentMethod").toString();	
					plan = jsonObj.get("electricityPlan").toString();
				}
				else
				{
					supplierName="Not Known";
				}
			}

			USwitchLandingPage UswitchPageObj=new USwitchLandingPage();
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

			HashMap<String, Map> sectionData = UswitchPageObj.storedataNew(hasGas);	
			super_getallthedetails.put("Execution-"+requestId, sectionData);
			driver.close();
			driver.quit();
			Thread.sleep(10000);
			count++;
			if (!driver.toString().toLowerCase().contains("null"))
			{
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+driver.toString());
				Thread.sleep(20000);
				driver.quit();
			}

		}



	}


	@AfterMethod
	public void closeTest() {      
		System.out.println("Fetched data is at: "+System.getProperty("user.dir")+"\\CrawlerValidationnsUSwitch_data.yaml");
		Yaml yaml = new Yaml();
		FileWriter writer = null;
		try {
			writer = new FileWriter(System.getProperty("user.dir")+"\\CrawlerValidationnsUSwitch_data.yaml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		yaml.dump(super_getallthedetails, writer);

	}


}


