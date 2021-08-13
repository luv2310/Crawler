package com.muDomastic.qa.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.muDomastic.qa.util.TestUtil;

import poc.httpgetrequest;

public class dataDump {

	String  
	supplierName = "static value entered" ,
	tariffName = "static value entered",
	contractTerm = "static value entered", 
	earlyExitFee = "static value entered", 
	savePerYear = "static value entered", 
	isGreen = "static value entered", 
	extras = "static value entered", 
	isSaving = "static value entered", 
	personalProjection = "static value entered", 
	contractType = "static value entered", 
	rank = "static value entered", 
	paymentMethod = "static value entered", 
	comparisonSiteExclusive = "static value entered",

	//electricity variables
	electricity_supplierName = "static value entered",
	electricity_tariffName = "static value entered",
	electricity_tariffType = "static value entered", 
	electricity_paymentMethod = "static value entered", 
	electricity_unitRate = "static value entered", 
	electricity_nightUnitRate = "static value entered", 
	electricity_standingCharge = "static value entered",
	electricity_tariffEndson = "static value entered", 
	electricity_priceGuaranteedUntil = "static value entered", 
	electricity_exitfees = "static value entered", 
	electricity_additionalCharges = "static value entered",
	electricity_additionalproductsorservices = "static value entered",
	electricity_isGreen = "static value entered",

	//gas variables
	gas_supplierName = "static value entered",
	gas_tariffName = "static value entered",
	gas_tariffType = "static value entered",
	gas_paymentMethod = "static value entered",
	gas_unitRate = "static value entered",
	gas_nightUnitRate = "static value entered",
	gas_standingCharge = "static value entered",
	gas_tariffEndson = "static value entered",
	gas_priceGuaranteedUntil = "static value entered",
	gas_exitfees = "static value entered",
	gas_additionalCharges = "static value entered",
	gas_additionalproductsorservices = "static value entered",
	gas_isGreen = "static value entered",
	execution_ID="0000";
	JSONObject firstJsonOnject ;
	JSONArray rankJsonArray ;


	public static void main(String[] args) throws IOException 

	{
		new dataDump().jsonVariables();

	}	

	public void jsonVariables()
	{
		String filePath = System.getProperty("user.dir")+"\\USwitch_data.yaml";
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.findAndRegisterModules();
		try {
			LinkedHashMap file = (LinkedHashMap) mapper.readValue(new File(filePath), Map.class);	
			Set fileKeySet = file.keySet();
			for(Object fileKeyValue : fileKeySet)
			{    
				firstJsonOnject = new JSONObject();
				rankJsonArray= new JSONArray();
				String[] executionidarr =fileKeyValue.toString().split("-");
				execution_ID=executionidarr[1];
				LinkedHashMap executions = (LinkedHashMap) file.get(fileKeyValue.toString());
				Set executionKeySet = executions.keySet();	
				for (Object ranksKeyValue : executionKeySet)
				{
					LinkedHashMap ranks = setMainElements(ranksKeyValue,executions);
					if(comparisonSiteExclusive.toLowerCase().contains("t"))
					{
						setComparisonElements(ranks);
					}
					else
					{
						setNoValuecomparison();
					}
					createJsonBody();
				}
				firstJsonOnject.put("uswitchresults", rankJsonArray);
				firstJsonOnject.put("source", "USwitch");
				System.out.println(firstJsonOnject.toString());
				String url = "https://api-home-staging.myutilitygenius.co.uk/request/compare/uswitch/request/"+execution_ID;
				postMethd(url,  firstJsonOnject.toString());
			}

			//	HashMap<Object, Object> result = sendPostRequestWithParams(url, firstJsonOnject.toString());
			//	System.out.println(result.get("statuscode"));
			System.out.println("Done");

		}
		catch (Exception e) 
		{
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}


	public LinkedHashMap setMainElements(Object ranksKeyValue,LinkedHashMap executions)
	{
		LinkedHashMap ranks =  (LinkedHashMap) executions.get(ranksKeyValue.toString());
		try{
			supplierName = ranks.get("supplierName").toString();
			rank = ranks.get("rank").toString();
			paymentMethod = ranks.get("paymentMethod").toString();
			contractTerm = ranks.get("contractTerm").toString();
			contractType = ranks.get("contractType").toString();
			personalProjection = ranks.get("personalProjection").toString();
			extras = ranks.get("Extras").toString();
			isGreen = ranks.get("isGreen").toString();
			savePerYear = ranks.get("savePerYear").toString();
			tariffName = ranks.get("tariffName").toString();
			earlyExitFee = ranks.get("earlyExitFee").toString();
			isSaving = ranks.get("isSaving").toString();
			comparisonSiteExclusive = ranks.get("comparisonSiteExclusive").toString();

		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
		return ranks;
	}

	public void setComparisonElements(LinkedHashMap ranks) 
	{
		try {
			if(ranks.containsKey("electricity")) 
			{
				LinkedHashMap electricity = (LinkedHashMap) ranks.get("electricity");
				electricity_supplierName = electricity.get("supplierName").toString();
				electricity_tariffName = electricity.get("tariffName").toString();
				electricity_tariffType = electricity.get("tariffType").toString();
				electricity_paymentMethod = electricity.get("paymentMethod").toString();  
				electricity_unitRate = electricity.get("unitRate").toString(); 
				electricity_nightUnitRate = electricity.get("unitRate").toString();  
				electricity_standingCharge = electricity.get("standingCharge").toString(); 
				electricity_tariffEndson = electricity.get("tariffEndson").toString();  
				electricity_priceGuaranteedUntil = electricity.get("priceGuaranteedUntil").toString(); 
				electricity_exitfees = electricity.get("exitfees").toString();  
				electricity_additionalCharges = electricity.get("discounts").toString(); 
				electricity_additionalproductsorservices = electricity.get("additionalproductsorservices").toString(); 		
				electricity_isGreen = electricity.get("isGreen").toString(); 	
			}
			else {
				System.out.println("there is no electricity key in rank");
			}

			if(ranks.containsKey("gas"))
			{

				LinkedHashMap gas = (LinkedHashMap) ranks.get("gas");
				gas_supplierName = gas.get("supplierName").toString();
				gas_tariffName = gas.get("tariffName").toString();
				gas_tariffType = gas.get("tariffType").toString();
				gas_paymentMethod = gas.get("paymentMethod").toString();  
				gas_unitRate = gas.get("unitRate").toString(); 
				gas_nightUnitRate = gas.get("unitRate").toString();  
				gas_standingCharge = gas.get("standingCharge").toString(); 
				gas_tariffEndson = gas.get("tariffEndson").toString();  
				gas_priceGuaranteedUntil = gas.get("priceGuaranteedUntil").toString(); 
				gas_exitfees = gas.get("exitfees").toString();  
				gas_additionalCharges = gas.get("discounts").toString(); 
				gas_additionalproductsorservices = gas.get("additionalproductsorservices").toString(); 		
				gas_isGreen = gas.get("isGreen").toString(); 	
			}
			else {
				System.out.println("there is no gas key in rank");
			}
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();

		}

	}

	public void setNoValuecomparison()
	{
		//electricity variables
		electricity_supplierName = null;
		electricity_tariffName = null;
		electricity_tariffType = null; 
		electricity_paymentMethod = null; 
		electricity_unitRate = null; 
		electricity_nightUnitRate = null; 
		electricity_standingCharge = null;
		electricity_tariffEndson = null; 
		electricity_priceGuaranteedUntil = null; 
		electricity_exitfees = null; 
		electricity_additionalCharges = null;
		electricity_additionalproductsorservices = null;
		electricity_isGreen = null;

		//gas variables
		gas_supplierName = null;
		gas_tariffName = null;
		gas_tariffType = null;
		gas_paymentMethod = null;
		gas_unitRate = null;
		gas_nightUnitRate = null;
		gas_standingCharge = null;
		gas_tariffEndson = null;
		gas_priceGuaranteedUntil = null;
		gas_exitfees = null;
		gas_additionalCharges = null;
		gas_additionalproductsorservices = null ;
		gas_isGreen = null;
	}

	public void createJsonBody() 

	{
		try {
			HashMap<String, Object> rankData =new  HashMap<String,Object>();
			HashMap<String, String> electricityinsideData = new HashMap<String, String>();
			HashMap<String, String> gasinsideData =new  HashMap<String, String>();

			rankData.put("supplierName", supplierName);
			rankData.put("tariffName", tariffName);
			rankData.put("contractTerm", contractTerm);
			rankData.put("earlyExitFee", earlyExitFee);
			rankData.put("savePerYear", savePerYear);
			rankData.put("isGreen", isGreen);
			rankData.put("extras", extras);
			rankData.put("comparisonSiteExclusive", comparisonSiteExclusive);
			rankData.put("isSaving", isSaving);
			rankData.put("personalProjection", personalProjection);
			rankData.put("contractType", contractType);
			rankData.put("paymentMethod", paymentMethod);
			rankData.put("rank", rank);

			//electricity data
			electricityinsideData.put("supplierName", electricity_supplierName);
			electricityinsideData.put("tariffName", electricity_tariffName);
			electricityinsideData.put("tariffType", electricity_tariffType);
			electricityinsideData.put("paymentMethod", electricity_paymentMethod);
			electricityinsideData.put("unitRate", electricity_unitRate);
			electricityinsideData.put("nightUnitRate", electricity_nightUnitRate);
			electricityinsideData.put("standingCharge", electricity_standingCharge);
			electricityinsideData.put("tariffEndson", electricity_tariffEndson);
			electricityinsideData.put("priceGuaranteedUntil", electricity_priceGuaranteedUntil);
			electricityinsideData.put("exitfees", electricity_exitfees);
			electricityinsideData.put("discounts", electricity_additionalCharges);
			electricityinsideData.put("additionalproductsorservices", electricity_additionalproductsorservices);
			electricityinsideData.put("isGreen", electricity_isGreen);

			rankData.put("electricity", electricityinsideData);
			// gasinsideData data
			gasinsideData.put("supplierName", gas_supplierName);
			gasinsideData.put("tariffName", gas_tariffName);
			gasinsideData.put("tariffType", gas_tariffType);
			gasinsideData.put("paymentMethod", gas_paymentMethod);
			gasinsideData.put("unitRate", gas_unitRate);
			gasinsideData.put("nightUnitRate", gas_nightUnitRate);
			gasinsideData.put("standingCharge", gas_standingCharge);
			gasinsideData.put("tariffEndson", gas_tariffEndson);
			gasinsideData.put("priceGuaranteedUntil", gas_priceGuaranteedUntil);
			gasinsideData.put("exitfees", gas_exitfees);
			gasinsideData.put("discounts", gas_additionalCharges);
			gasinsideData.put("additionalproductsorservices", gas_additionalproductsorservices);
			gasinsideData.put("isGreen", gas_isGreen);

			rankData.put("gas", gasinsideData);

			rankJsonArray.put(rankData);
		}
		catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

	public void postMethd(String passurl, String jsonInputString) 
	{
		try {
			URL url = new URL (passurl);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);

			try(OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);			
			}
			try(BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				System.out.println(response.toString());
			}
		} catch (Exception e) {
			System.out.println(" :: Exception Occured in class TestUtils Page, method name "+new Object(){}.getClass().getEnclosingMethod().getName()+" ::");
			e.printStackTrace();
		}
	}

}
