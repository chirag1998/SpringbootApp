package com.xoriant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.entity.UserEntity;
import com.xoriant.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public UserEntity adduser(UserEntity user) {
		userRepository.save(user);
		UserEntity result = new UserEntity(user.getFirstName(), user.getLastName(), user.getEmail());
		return result;
	}
}
