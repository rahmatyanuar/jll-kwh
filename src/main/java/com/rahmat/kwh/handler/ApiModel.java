package com.rahmat.kwh.handler;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import com.rahmat.kwh.model.Account;

import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class ApiModel {
	private final String message;
	private final HttpStatus httpStatus;
//	private final Throwable throwable;
	private final LocalDateTime timestamp;
	@Nullable
	private final Map<Integer ,Account> data;
	private final Integer totalData;
}
