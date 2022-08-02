package com.xoriant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.pojo.AuthenticatePOJO;
import com.xoriant.pojo.AuthenticationResponsePOJO;
import com.xoriant.service.AuthenticateService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("authenticate")
@Slf4j
public class AuthenticationController {

	@Autowired
	private AuthenticateService authenticateService;

	@PostMapping
	public ResponseEntity<AuthenticationResponsePOJO> authenticateUser(@RequestBody AuthenticatePOJO request)
			throws Exception {
		log.info("Inside Authenticate User");
		return new ResponseEntity<>(authenticateService.authenticateUser(request), HttpStatus.OK);
	}

}
