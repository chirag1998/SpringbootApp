package com.xoriant.service;

import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xoriant.entity.AccessMappingEntity;
import com.xoriant.entity.RolesEntity;
import com.xoriant.entity.UserEntity;
import com.xoriant.pojo.UserPOJO;
import com.xoriant.repository.RolesRepository;
import com.xoriant.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String getEncodedPassword(String password) {
		return passwordEncoder.encode(password);
	}

	public UserPOJO adduser(UserPOJO user) {
		UserEntity userdetails = new UserEntity(user.getDisplayName(), user.getUserName(),
				getEncodedPassword(user.getPassword()), user.getEmail(), user.getMobileNumber());
		if (null != user.getRoles()) {
			for (String role : user.getRoles()) {
				Optional<RolesEntity> roleEntity = rolesRepository.findByRoleName(role);
				if (roleEntity.isPresent()) {
					userdetails.getRoleMapping().add(new AccessMappingEntity(userdetails, roleEntity.get()));
				}
			}
		}
		UserEntity response = userRepository.save(userdetails);
		UserPOJO responseEntity = mapToPojo(response);
		responseEntity.setRoles(getRoles(response.getRoleMapping()));
		responseEntity.setPassword("");
		return responseEntity;
	}

	public String[] getRoles(Set<AccessMappingEntity> role) {
		return role.stream().map(mapper -> mapper.getRole().getRoleName()).toArray(String[]::new);
	}

	private UserPOJO mapToPojo(UserEntity user) {
		return modelMapper.map(user, UserPOJO.class);
	}
}
