package com.rahmat.kwh.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rahmat.kwh.model.general.GeneralResponse;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/app")
public class KWHController {
	@RequestMapping(value = "/welcome", method = RequestMethod.GET, consumes = "application/json")
	public ResponseEntity<GeneralResponse> tokenValidate() throws Exception {
		GeneralResponse res = new GeneralResponse();
		res.setStatusCode("200");
		res.setStatusMessage("OK");
		res.setData("Hello");
		 return ResponseEntity.ok().body(res);
	}
}
