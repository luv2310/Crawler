package poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;





public class httpgetrequest {

	public static void main(String[] args) throws IOException 
	
	{
		
		JSONObject uswitchJson = new JSONObject();
		JSONArray uswitchresultsArray = new JSONArray();
		

        uswitchresultsArray.put(0, uswitchresultsArray)		;
		uswitchJson.put("uswitchresults", uswitchresultsArray);
		
		
		new httpgetrequest().sendPostRequestWithParams(null, null);
		
	
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
	

