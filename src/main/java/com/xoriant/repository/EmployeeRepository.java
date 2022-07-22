package com.xoriant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
	EmployeeEntity findByEmail(String email);
	
	
	List<EmployeeEntity> findByFirstName(String fname);

}
