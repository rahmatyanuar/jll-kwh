package com.rahmat.kwh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahmat.kwh.model.Bergainser;
import com.rahmat.kwh.repository.BergainserRepo;

@Service
public class BergainserService {
	Logger logger = LoggerFactory.getLogger(BergainserService.class);
	
	@Autowired
	BergainserRepo bgr;
	
	public List<Bergainser> loadBergainser() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String updateBergainser() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String deleteBergainser() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
