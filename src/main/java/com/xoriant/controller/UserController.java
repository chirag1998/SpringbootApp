package com.xoriant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.pojo.UserPOJO;
import com.xoriant.service.UserService;

@RestController
@RequestMapping("registration")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("adduser")
	public ResponseEntity<UserPOJO> add(@RequestBody UserPOJO user) {
		return new ResponseEntity<>(userService.adduser(user), HttpStatus.CREATED);
	}

}
