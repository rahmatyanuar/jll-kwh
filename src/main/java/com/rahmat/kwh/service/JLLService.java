package com.rahmat.kwh.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rahmat.kwh.model.Bergainser;
import com.rahmat.kwh.model.Customer;
import com.rahmat.kwh.model.Location;
import com.rahmat.kwh.model.Raiser;
import com.rahmat.kwh.model.Tenant;
import com.rahmat.kwh.repository.AccountRepo;
import com.rahmat.kwh.repository.BergainserRepo;
import com.rahmat.kwh.repository.CustomerRepo;

@Component
@Service
public class JLLService {
	Logger logger = LoggerFactory.getLogger(JLLService.class);
	
	@Autowired
	private AccountRepo acc;
	
	@Autowired
	private BergainserRepo brg;
	
	@Autowired
	private CustomerRepo cus;
	
	public Customer loadCustomer() {
		return null;
	}
	
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
	
	public List<Location> loadLocation(){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String updateLocation() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	
	public String deleteLocation() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	
	public List<Raiser> loadRaiser(){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String updateRaiser() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	
	public String deleteRaiser() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	
	public List<Tenant> loadTenant(){
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	public String updateTenant() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	
	public String deleteTenant() {
		try {
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
}
