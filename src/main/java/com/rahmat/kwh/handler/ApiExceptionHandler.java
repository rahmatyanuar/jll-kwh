package com.rahmat.kwh.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler(value = {ApiRequestException.class})
	public ResponseEntity<Object> handleApiRequestException(ApiRequestException e){
		HttpStatus badRequest = HttpStatus.BAD_REQUEST;
		ApiModel apiException = new ApiModel(e.getMessage(), badRequest, LocalDateTime.now(), null, 0);
		return new ResponseEntity<>(apiException, badRequest);
	}
}
