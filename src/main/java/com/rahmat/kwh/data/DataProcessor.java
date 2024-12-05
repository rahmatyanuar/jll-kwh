package com.rahmat.kwh.data;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rahmat.kwh.handler.ApiRequestException;
import com.rahmat.kwh.model.Account;
import com.rahmat.kwh.service.AccountService;

import io.lettuce.core.RedisException;

@Component
@Service
public class DataProcessor {
	ObjectMapper mapper = new ObjectMapper();
	String json = "";
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	private ValueOperations<String, String> getValueOperations() {
		return redisTemplate.opsForValue();
	}
	
	public String commitList(List<Account> list, String key) {
		try {
			json = mapper.writeValueAsString(list);
			getValueOperations().set(key, json);
			logger.info("Save Account To Redis", json);
			return json;
		} catch (JsonProcessingException e) {
			throw new ApiRequestException(e.getMessage());
		} catch (RedisException e) {
			throw new ApiRequestException(e.getMessage());
		}
	}
	
	public List<Account> retriveList(String key) {
		String accountJson = getValueOperations().get(key);
		TypeReference<List<Account>> listType = new TypeReference<List<Account>>() {};
		if(accountJson == null) {
			return new ArrayList<>();
		}
		try {
			logger.info("Load Account", accountJson);
			return mapper.readValue(accountJson, listType);
		} catch (JsonMappingException e) {
			throw new ApiRequestException(e.getMessage());
		} catch (JsonProcessingException e) {
			throw new ApiRequestException(e.getMessage());
		} catch (NullPointerException e) {
			throw new ApiRequestException(e.getMessage());
		}
	}
	
	public void purgeData(String key) {
		redisTemplate.delete(key);
	}
}
