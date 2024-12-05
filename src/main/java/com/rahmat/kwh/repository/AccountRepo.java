package com.rahmat.kwh.repository;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rahmat.kwh.handler.ApiModel;
import com.rahmat.kwh.model.Account;

@Repository
public interface AccountRepo {
	ApiModel getAccount(String username) throws JsonMappingException, JsonProcessingException;

	ApiModel saveAccObj(Account account) throws JsonMappingException, JsonProcessingException;

	ApiModel updateAccObj(Account account) throws JsonMappingException, JsonProcessingException;

	ApiModel delAcc(String username) throws JsonMappingException, JsonProcessingException;

	ApiModel purgeAccounts();
}
