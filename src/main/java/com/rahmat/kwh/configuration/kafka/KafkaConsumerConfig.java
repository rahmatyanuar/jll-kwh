package com.rahmat.kwh.configuration.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.rahmat.kwh.model.Account;
import com.rahmat.kwh.service.AccountService;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
	Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public Map<String, Object> consumerConfig() {
		logger.info("Loading Consumer Factory");
		Map props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
//		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"io.confluent.kafka.serializers.KafkaAvroDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(JsonDeserializer.TRUSTED_PACKAGES,"*");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "account-logger1");
		return props;
	}
	
	@Bean
	public ConsumerFactory<String, Account> consumerFactory() {
	   return new DefaultKafkaConsumerFactory<>(consumerConfig());
	}
	
//	@Bean
//	public ConsumerFactory<String, Account> consumerFactory() {
//	   return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(),
//		        new JsonDeserializer<>(Account.class));
//	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String,Account> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String,Account> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		return factory;
	}
}
