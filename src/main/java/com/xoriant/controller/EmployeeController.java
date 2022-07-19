package com.xoriant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.entity.EmployeeEntity;
import com.xoriant.service.EmployeeService;
import com.xoriant.service.UserService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping()
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/addemployee")
	public ResponseEntity<EmployeeEntity> addEmployee(@RequestBody EmployeeEntity employee){
		return new ResponseEntity<>(employeeService.adduser(employee), HttpStatus.CREATED);
	}
	
	@GetMapping("/listemployee")
	public ResponseEntity<?> getDataList(){
		return new ResponseEntity<>(employeeService.getUsers(), HttpStatus.OK);	
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		employeeService.deleteById(id);
		return new ResponseEntity<>("Employee Deleted successfully", HttpStatus.ACCEPTED);
		
	}
	
	@PutMapping("update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id,
			@RequestBody EmployeeEntity emp){
		//boolean result = employeeService.updateEmployee(id, emp);
		return new ResponseEntity<>(employeeService.updateEmployee(id,emp), HttpStatus.OK);
	}

}
