package com.xoriant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.entity.EmployeeEntity;
import com.xoriant.entity.UserEntity;
import com.xoriant.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepo;

	public Object getUsers() {
		return employeeRepo.findAll();
	}

	public void deleteById(long id) {
		employeeRepo.deleteById(id);
		
	}

	public EmployeeEntity adduser(EmployeeEntity employee) {
		employeeRepo.save(employee);
		EmployeeEntity result = new EmployeeEntity(employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getDate());
		return result;
	}

	public EmployeeEntity updateEmployee(long id, EmployeeEntity emp) {
		EmployeeEntity employee = employeeRepo.findById(id).get();
		if(emp.getFirstName() != null && !"".equalsIgnoreCase(emp.getFirstName())) {
			employee.setFirstName(emp.getFirstName());
		}
		if(emp.getLastName() != null && !"".equalsIgnoreCase(emp.getLastName())) {
			employee.setLastName(emp.getLastName());
		}
		if(emp.getEmail() != null && !"".equalsIgnoreCase(emp.getEmail())) {
			employee.setEmail(emp.getEmail());
		}
		if(emp.getDate() != null) {
			employee.setDate(emp.getDate());
		}
		
		return employeeRepo.save(employee);
	}

}