package com.rahmat.kwh.configuration.jwt;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.rahmat.kwh.service.PropertiesService;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {
//	@Autowired
//	private UserService jwtUserDetailsService;
	
	@Autowired
	private PropertiesService propSrv;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	throws ServletException, IOException, ClientProtocolException {
		 CloseableHttpClient client = HttpClients.createDefault();
		    System.out.println(propSrv.tokenValidate());
		    HttpGet httpGet = new HttpGet(propSrv.tokenValidate());
		    httpGet.setHeader("Accept", "application/json");
		    httpGet.setHeader("Content-type", "application/json");
		    System.out.println(request.getHeader("Authorization"));
		    httpGet.setHeader("Authorization", request.getHeader("Authorization"));

		    CloseableHttpResponse httpResponse = client.execute(httpGet);
		    int statusCode = httpResponse.getStatusLine().getStatusCode();
		    
		    String responseBody = EntityUtils.toString(httpResponse.getEntity(), StandardCharsets.UTF_8);
		    JSONObject o = null;
		    String username = null;
		    if(statusCode == 200) {
		    	try {
					o = new JSONObject(responseBody);
					JSONObject data = o.getJSONObject("data");
				    
				    username = data.getString("name");
				    JSONArray auth = data.getJSONArray("authorities");
				   
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	response.setStatus(HttpServletResponse.SC_OK);
		    	SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
		    } else {
		    	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		    }
		    
		    client.close();
		    chain.doFilter(request, response);
	}
}