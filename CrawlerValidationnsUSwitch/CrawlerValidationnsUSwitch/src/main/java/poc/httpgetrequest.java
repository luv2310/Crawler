package poc;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
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
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.muDomastic.qa.base.apiFetchedData;





public class httpgetrequest {

	String  
	supplierName = null ,
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
	gas_additionalproductsorservices = "static value entered" ;
	static JSONObject firstJsonOnject = new  JSONObject();
	static JSONArray rankJsonArray = new JSONArray();


	public static void main(String[] args) throws IOException 

	{

		new httpgetrequest().jsonVariables();

		firstJsonOnject.put("uswitchresults", rankJsonArray);
		firstJsonOnject.put("source", "USwitch");

		System.out.println(firstJsonOnject.toString());
		System.out.println("Done");

	}	

	public void jsonVariables()
	{


		String filePath = "C:\\Users\\Luv\\Desktop\\CrawlerValidationnsUSwitch_data.yaml";
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		mapper.findAndRegisterModules();
		try {

			LinkedHashMap file = (LinkedHashMap) mapper.readValue(new File(filePath), Map.class);	
			Set fileKeySet = file.keySet();
			for(Object fileKeyValue : fileKeySet)
			{

				LinkedHashMap executions = (LinkedHashMap) file.get(fileKeyValue.toString());
				Set executionKeySet = executions.keySet();	
				for (Object ranksKeyValue : executionKeySet)
				{
					LinkedHashMap ranks =  (LinkedHashMap) executions.get(ranksKeyValue.toString());
					supplierName = ranks.get("supplierName").toString();
					rank = ranks.get("rank").toString();
					paymentMethod = ranks.get("paymentMethod").toString();
					contractTerm = ranks.get("contractTerm").toString();
					//contractType = ranks.get("contractType").toString();
					personalProjection = ranks.get("personalProjection").toString();
					extras = ranks.get("Extras").toString();
					isGreen = ranks.get("isGreen").toString();
					savePerYear = ranks.get("savePerYear").toString();
					tariffName = ranks.get("tariffName").toString();
					earlyExitFee = ranks.get("earlyExitFee").toString();
					isSaving = ranks.get("isSaving").toString();
					comparisonSiteExclusive = ranks.get("comparisonSiteExclusive").toString();
					if(comparisonSiteExclusive.toLowerCase().contains("T"))
					{
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

						}
						else {
							System.out.println("there is no gas key in rank");
						}
					}


					createJsonBody();
				}


			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}



	}

	public void createJsonBody() 

	{

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
		electricityinsideData.put("additionalCharges", electricity_additionalCharges);
		electricityinsideData.put("additionalproductsorservices", electricity_additionalproductsorservices);
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
		gasinsideData.put("additionalCharges", gas_additionalCharges);
		gasinsideData.put("additionalproductsorservices", gas_additionalproductsorservices);
		rankData.put("gas", gasinsideData);

		rankJsonArray.put(rankData);


	}

	public HashMap<Object, Object> sendPostRequestWithParams(String url, String postData)
	{
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
			HttpPost httppost = new HttpPost(url);
			httppost.setEntity(new StringEntity(postData));
			HttpResponse httpResponse = null;
			try {
				httpResponse = httpClient.execute(httppost);
			}catch (Exception e) {
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
}


