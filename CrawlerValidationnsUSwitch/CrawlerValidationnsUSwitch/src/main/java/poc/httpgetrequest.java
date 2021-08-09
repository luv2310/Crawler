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

	public static void main(String[] args) throws IOException 

	{

		new httpgetrequest().jsonVariables();


	}	

	public void jsonVariables()
	{

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
					System.out.println(ranks.get("rank"));
				}
				


			}
					}
		catch (Exception e) 
		{
			e.printStackTrace();
		}



		JSONObject firstJsonOnject = new  JSONObject();
		JSONArray firstJsonArray = new JSONArray();
		HashMap<String, Object> insideData =new  HashMap<String,Object>();
		HashMap<String, String> electricityinsideData = new HashMap<String, String>();
		HashMap<String, String> gasinsideData =new  HashMap<String, String>();

		insideData.put("supplierName", supplierName);
		insideData.put("tariffName", tariffName);
		insideData.put("contractTerm", contractTerm);
		insideData.put("earlyExitFee", earlyExitFee);
		insideData.put("savePerYear", savePerYear);
		insideData.put("isGreen", isGreen);
		insideData.put("extras", extras);
		insideData.put("comparisonSiteExclusive", comparisonSiteExclusive);
		insideData.put("isSaving", isSaving);
		insideData.put("personalProjection", personalProjection);
		insideData.put("contractType", contractType);
		insideData.put("paymentMethod", paymentMethod);
		insideData.put("rank", rank);

		//electricity data
		electricityinsideData.put("electricity_supplierName", electricity_supplierName);
		electricityinsideData.put("electricity_tariffName", electricity_tariffName);
		electricityinsideData.put("electricity_tariffType", electricity_tariffType);
		electricityinsideData.put("electricity_paymentMethod", electricity_paymentMethod);
		electricityinsideData.put("electricity_unitRate", electricity_unitRate);
		electricityinsideData.put("electricity_nightUnitRate", electricity_nightUnitRate);
		electricityinsideData.put("electricity_standingCharge", electricity_standingCharge);
		electricityinsideData.put("electricity_tariffEndson", electricity_tariffEndson);
		electricityinsideData.put("electricity_priceGuaranteedUntil", electricity_priceGuaranteedUntil);
		electricityinsideData.put("electricity_exitfees", electricity_exitfees);
		electricityinsideData.put("electricity_additionalCharges", electricity_additionalCharges);
		electricityinsideData.put("electricity_additionalproductsorservices", electricity_additionalproductsorservices);
		insideData.put("electricity", electricityinsideData);

		// gasinsideData data
		gasinsideData.put("gas_supplierName", gas_supplierName);
		gasinsideData.put("gas_tariffName", gas_tariffName);
		gasinsideData.put("gas_tariffType", gas_tariffType);
		gasinsideData.put("gas_paymentMethod", gas_paymentMethod);
		gasinsideData.put("gas_unitRate", gas_unitRate);
		gasinsideData.put("gas_nightUnitRate", gas_nightUnitRate);
		gasinsideData.put("gas_standingCharge", gas_standingCharge);
		gasinsideData.put("gas_tariffEndson", gas_tariffEndson);
		gasinsideData.put("gas_priceGuaranteedUntil", gas_priceGuaranteedUntil);
		gasinsideData.put("gas_exitfees", gas_exitfees);
		gasinsideData.put("gas_additionalCharges", gas_additionalCharges);
		gasinsideData.put("gas_additionalproductsorservices", gas_additionalproductsorservices);
		insideData.put("gas", gasinsideData);

		firstJsonArray.put(insideData);

		firstJsonOnject.put("uswitchresults", firstJsonArray);
		firstJsonOnject.put("source", "USwitch");

		System.out.println(firstJsonOnject.toString());
		System.out.println("Done");
	}

	public void createJsonBody() 

	{


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


