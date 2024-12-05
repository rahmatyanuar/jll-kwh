package com.rahmat.kwh;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;

import com.rahmat.kwh.service.AccountService;

@SpringBootApplication
@ComponentScan(basePackages={"com.rahmat.kwh"})
public class JllKwhApplication {

	public static void main(String[] args) {
		SpringApplication.run(JllKwhApplication.class, args);
	}
	
	
	Logger logger = LoggerFactory.getLogger(AccountService.class);
	
	@KafkaListener(topics = "account-registration-event1", groupId = "account-logger1")
    public void consume(String message) throws IOException {
        logger.info(String.format("#### -> Consumed message -> %s", message));
    }

}
