package poc;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;


import org.json.JSONArray;
import org.json.JSONObject;



public class httpgetrequest {

	public static void main(String[] args) throws IOException 
	{System.out.println();
		 String file = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"\\data.json")), StandardCharsets.UTF_8);
         JSONObject obj = new JSONObject(file);
         JSONArray arr = new JSONArray(obj.get("response"));
     
         for (Object s : arr)
         {
        	 
         }
	}

}


