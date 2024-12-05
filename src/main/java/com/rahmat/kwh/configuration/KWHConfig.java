package com.rahmat.kwh.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.stereotype.Component;

import com.rahmat.kwh.model.Account;

@Component
@Configuration
@EnableRedisRepositories
public class KWHConfig {
	Logger logger = LoggerFactory.getLogger(KWHConfig.class);
	
	@Value(value = "${redis.hostname}")
	private String redisHostname;

	@Value(value = "${redis.port}")
	private int redisPort;
	
	@Bean
	public LettuceConnectionFactory redisConnFactory() {
		logger.info("Debug Info =====> [Redis Connection]"+redisHostname+":"+redisPort);
		return new LettuceConnectionFactory(redisHostname, redisPort);
	}

	@Bean
	public RedisTemplate<String, Account> redisTemplate() {
		RedisTemplate<String, Account> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnFactory());
		return template;
	}
}
