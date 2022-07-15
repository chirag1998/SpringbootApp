package com.xoriant.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xoriant.entity.AccessMappingEntity;
import com.xoriant.entity.RolesEntity;
import com.xoriant.entity.UserEntity;
import com.xoriant.pojo.RolesPOJO;
import com.xoriant.repository.RolesRepository;
import com.xoriant.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public UserEntity adduser(UserEntity user) {
		UserEntity userdetails = new UserEntity(user.getDisplayName(), user.getUserName(),
				getEncodedPassword(user.getPassword()), user.getEmail(), user.getMobileNumber());
		userRepository.save(userdetails);
		userdetails.setPassword("");
		return userdetails;
	}

	public RolesPOJO addroles(RolesPOJO roles) {
		UserEntity user = userRepository.findByUserName(roles.getUsername());
		if (user != null) {
			if (null != roles.getRoles()) {
				for (String role : roles.getRoles()) {
					Optional<RolesEntity> roleEntity = rolesRepository.findByRoleName(roles.getUsername());
					if (roleEntity.isPresent()) {
						user.getRoleMapping().add(new AccessMappingEntity(user, roleEntity.get()));
					}
				}
			}
		}
		return null;

	}
}
