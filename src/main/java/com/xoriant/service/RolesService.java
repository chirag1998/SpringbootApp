package com.xoriant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.entity.RolesEntity;
import com.xoriant.repository.RolesRepository;

@Service
public class RolesService {

	@Autowired
	private RolesRepository rolesRepository;

	public RolesEntity addroles(RolesEntity roles) {
		Optional<RolesEntity> roleEntity = rolesRepository.findByRoleName(roles.getRoleName());
		if (roleEntity.isPresent()) {
			return roleEntity.get();
		} else {
			rolesRepository.save(roles);
		}
		return roles;
	}

}
