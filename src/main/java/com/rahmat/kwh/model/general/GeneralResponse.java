package com.rahmat.kwh.model.general;

import lombok.Data;

@Data
public class GeneralResponse {
	private String StatusCode;
	private String StatusMessage;
	private Object Data;
}
