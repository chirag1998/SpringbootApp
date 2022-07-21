package com.xoriant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.entity.RolesEntity;
import com.xoriant.service.RolesService;

@RestController
@RequestMapping("roles")
@CrossOrigin(origins = "http://localhost:3000")
public class RolesController {

	@Autowired
	private RolesService rolesService;

	@PostMapping("addrole")
	public ResponseEntity<RolesEntity> add(@RequestBody RolesEntity roles) {
		return new ResponseEntity<>(rolesService.addroles(roles), HttpStatus.CREATED);
	}

}
