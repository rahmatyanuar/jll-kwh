package com.rahmat.kwh.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahmat.kwh.model.Account;
@Component
public class KafkaConsumer {
	Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	AccountService accountService;
	
	@KafkaListener(topics = "pending-approval", groupId = "account-logger1")
    public void accountConsumer(String account) throws IOException {
        try {
        	logger.info("incoming account object");
        	ObjectMapper objectMapper = new ObjectMapper();
        	Account acct = objectMapper.readValue(account, Account.class);
        	accountService.saveAccObj(acct);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("account listener error || "+e.getMessage());
		}
    }
}
