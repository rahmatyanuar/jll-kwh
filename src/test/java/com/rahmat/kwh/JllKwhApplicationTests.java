package com.rahmat.kwh;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.rahmat.kwh.service.PropertiesService;

@SpringBootTest
class JllKwhApplicationTests {
	@Value("${auth.token}")
	private String apiHost;
	
	@Autowired
	private PropertiesService propSrv;

	@Test
	public void contextLoads() 
	  throws ClientProtocolException, IOException, JSONException {
	    CloseableHttpClient client = HttpClients.createDefault();
	    System.out.println(propSrv.tokenValidate());
	    HttpGet httpGet = new HttpGet(propSrv.tokenValidate());
	    httpGet.setHeader("Accept", "application/json");
	    httpGet.setHeader("Content-type", "application/json");
	    httpGet.setHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYWhtYXQiLCJleHAiOjE2Mjk2NDg0MTgsImlhdCI6MTYyOTYzMDQxOH0.MnhTjlRe1iOLIVWaDhonmTix7aQcl_Gb11Swv7Kfu1iSc-SK8sz-_RXM00TDVZJQac3Cu1g7To3fCEoJDeqpCw");

	    CloseableHttpResponse response = client.execute(httpGet);
	    int statusCode = response.getStatusLine().getStatusCode();
	    
	    String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
	    JSONObject o = null;
	    o = new JSONObject(responseBody);
	    JSONObject data = o.getJSONObject("data");
	    
	    String username = data.getString("name");
	    JSONArray auth = data.getJSONArray("authorities");
	    System.out.println(data);
	    client.close();
	}

}
