package com.rahmat.kwh.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PropertiesService {
	Properties prop = new Properties();
	
	@Value("${auth.token}")
	private String apiHost;
	
	public String tokenValidate() {
		prop.setTokenValidation(apiHost);
		return prop.getTokenValidation();
	}
}
