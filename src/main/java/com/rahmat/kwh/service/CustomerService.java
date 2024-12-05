package com.rahmat.kwh.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rahmat.kwh.model.Customer;
import com.rahmat.kwh.repository.CustomerRepo;

@Service
public class CustomerService {
	Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	@Autowired
	CustomerRepo cus;
	
	public Customer loadCustomer() {
		return null;
	}
}
