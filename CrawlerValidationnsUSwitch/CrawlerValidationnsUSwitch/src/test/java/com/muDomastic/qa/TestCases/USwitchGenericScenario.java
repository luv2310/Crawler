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
	public JSONArray arr;
	USwitchLandingPage UswitchPageObj=new USwitchLandingPage();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject(file);
		arr = new JSONArray(obj.get("response"));
	}


	@Test
	public void executevalue() throws IOException, InterruptedException {

		String postCode,partialAddress,supplierName,paymentMethod;
		boolean hasGas,isDualFuel,hasElectricity,isEconomy7;
		for (Object a :arr)
		{
			count++;
			JSONObject jsonObj = new JSONObject(a);
			postCode = jsonObj.get("postCode").toString();
			partialAddress = jsonObj.get("partialAddress").toString();
			hasGas = (boolean) jsonObj.get("hasGas");
			isDualFuel = (boolean) jsonObj.get("isDualFuel");
			isEconomy7 = (boolean) jsonObj.get("isEconomy7");
			hasElectricity= (boolean) jsonObj.get("hasElectricity");

			//set supplier name
			if(isDualFuel)
			{
				supplierName = jsonObj.get("dualFuelSuppliers").toString();	
			}
			else
			{
				if(hasGas)
				{
					supplierName = jsonObj.get("gasSupplier").toString();	
				}
				if(hasElectricity)
				{
					supplierName = jsonObj.get("electricitySuppliers").toString();	
				}
				else
				{
					supplierName="Not Known";
				}
			}

			//set payment method
			if(isDualFuel)
			{
				paymentMethod = jsonObj.get("dualFuelPaymentMethod").toString();	
			}
			else
			{
				if(hasGas)
				{
					paymentMethod = jsonObj.get("gasPaymentMethod").toString();	
				}
				if(hasElectricity)
				{
					paymentMethod = jsonObj.get("electricityPaymentMethod").toString();	
				}
				else
				{
					paymentMethod="Not Known";
				}
			}

			initialization();		
			UswitchPageObj.uSwitchJourney(postCode,partialAddress,supplierName,paymentMethod,hasGas,isDualFuel,isEconomy7);
			HashMap<String, Map> sectionData = UswitchPageObj.storedata();	
			super_getallthedetails.put(count+"_execution", sectionData);
			driver.quit();
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


