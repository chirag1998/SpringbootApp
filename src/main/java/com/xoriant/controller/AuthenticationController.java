package com.xoriant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.pojo.AuthenticatePOJO;
import com.xoriant.pojo.AuthenticationResponsePOJO;
import com.xoriant.service.AuthenticateService;

@RestController
@RequestMapping("authenticate")
@CrossOrigin
public class AuthenticationController {

	@Autowired
	private AuthenticateService authenticateService;

	@PostMapping
	public ResponseEntity<AuthenticationResponsePOJO> authenticateUser(@RequestBody AuthenticatePOJO request)
			throws Exception {
		return new ResponseEntity<>(authenticateService.authenticateUser(request), HttpStatus.OK);
	}

}
