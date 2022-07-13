package com.xoriant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.pojo.LoginPOJO;
import com.xoriant.service.LoginService;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	private LoginService loginservice;

	@PostMapping
	public ResponseEntity<String> login(@RequestBody LoginPOJO login) {
		boolean result = loginservice.loginuser(login);
		if (result) {
			return new ResponseEntity<>("Login Successful", HttpStatus.OK);
		}
		return new ResponseEntity<>("Login Failed Check Emailid and Password", HttpStatus.NOT_FOUND);
	}

}
