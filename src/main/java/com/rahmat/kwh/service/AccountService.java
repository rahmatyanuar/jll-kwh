package com.rahmat.kwh.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.rahmat.kwh.data.DataProcessor;
import com.rahmat.kwh.handler.ApiModel;
import com.rahmat.kwh.handler.ApiRequestException;
import com.rahmat.kwh.model.Account;
import com.rahmat.kwh.repository.AccountRepo;

@Component
@Service
public class AccountService implements AccountRepo {

	Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	public KafkaTemplate<String, Account> kfkTmpl;

	@Autowired
	private DataProcessor dp;

	private static final String SUCCESS_MESSAGE = "SUCCESS";
	private static final String DATA_NOT_AVAILABLE = "DATA NOT AVAILABLE";
	private static final String REDIS_KEY_ACCOUNTS = "JLLAccount";

	@Override
	public ApiModel getAccount(String username) throws JsonMappingException, JsonProcessingException {
		logger.info("Get Account");
		List<Account> list = dp.retriveList(REDIS_KEY_ACCOUNTS);
		Map<Integer, Account> resMap = new HashMap<Integer, Account>();
		if (list.stream().filter(o -> o.username().equals(username)).findFirst().isPresent()) {
			int i = 0;
			for (Account accountls : list) {
				if (accountls.username.equals(username)) {
					resMap.put(i, accountls);
				}
				i++;
			}
			return new ApiModel(SUCCESS_MESSAGE, HttpStatus.OK, LocalDateTime.now(), resMap, resMap.size());
		}
		throw new ApiRequestException(DATA_NOT_AVAILABLE + "FOR PARAM {" + username + "}");
	}

	@Override
	public ApiModel saveAccObj(Account account) throws JsonMappingException, JsonProcessingException, NullPointerException {
		logger.info("saveOrUpdateListObj");
		List<Account> list = dp.retriveList(REDIS_KEY_ACCOUNTS);
		Boolean isExisting = false;
		for (Account accountls : list) {
			isExisting = (accountls.username.equals(account.username))?true:false;
		}
		if(isExisting) {
//			throw new ApiRequestException(DUPLICATE_MESSAGE + "FOR PARAM {" + account.username + "}"); 
//			return new ApiModel(DUPLICATE_MESSAGE, HttpStatus.BAD_REQUEST, LocalDateTime.now(), null, list.size());
			return this.updateAccObj(account);
		}else {
			this.approval(account);
			list.add(account);
			dp.commitList(list,REDIS_KEY_ACCOUNTS);
			kfkTmpl.send("jll-approved-account", account);
			return new ApiModel(SUCCESS_MESSAGE, HttpStatus.OK, LocalDateTime.now(), null, list.size());
		}
	}

	@Override
	public ApiModel updateAccObj(Account account) throws JsonMappingException, JsonProcessingException {
		logger.info("saveOrUpdateListObj");
		List<Account> list = dp.retriveList(REDIS_KEY_ACCOUNTS);
		int i = 0;
		for (Account accountls : list) {
			if(accountls.username.equals(account.username)) {
				this.approval(account);
				list.set(i, account);
			}
			i++;
		}
		dp.commitList(list,REDIS_KEY_ACCOUNTS);
		kfkTmpl.send("jll-approved-account", account);
		return new ApiModel(SUCCESS_MESSAGE, HttpStatus.OK, LocalDateTime.now(), null, 1);
	}
	
	@Override
	public ApiModel delAcc(String username) throws JsonMappingException, JsonProcessingException {
		List<Account> list = dp.retriveList(REDIS_KEY_ACCOUNTS);
		if (!(this.getAccount(username)== null)) {
			int i = 0;
			int totalData = 0;
			for (Account accountls : list) {
				if (accountls.username.equals(username)) {
					totalData++;
					list.remove(i);
					dp.commitList(list,REDIS_KEY_ACCOUNTS);
					return new ApiModel(SUCCESS_MESSAGE, HttpStatus.OK, LocalDateTime.now(), null, totalData);
				}
				i++;
			}
		}
		throw new ApiRequestException(DATA_NOT_AVAILABLE + "FOR PARAM {" + username + "}");
	}
	
	@Override
	public ApiModel purgeAccounts() {
		Map<Integer, Account> resMap = new HashMap<Integer, Account>();
		dp.purgeData(REDIS_KEY_ACCOUNTS);
		return new ApiModel(SUCCESS_MESSAGE+" PURGE ALL DATA", null, null, resMap, null);
	}
	
	private Account approval(Account account) {
		try {
			account.approvalDateTime = LocalDateTime.now().toString();
			account.approved = true;
			account.approverUsername = "auto";
			account.updateDateTime = LocalDateTime.now().toString();
			return account;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("approval error || "+e.getMessage());
		}
		return null;
	}
}
