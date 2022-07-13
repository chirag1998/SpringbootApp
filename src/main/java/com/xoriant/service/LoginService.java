package com.xoriant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.entity.UserEntity;
import com.xoriant.pojo.LoginPOJO;
import com.xoriant.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository userRepository;

	public boolean loginuser(LoginPOJO loginpojo) {
		UserEntity user = userRepository.findByEmail(loginpojo.getEmailid());
		if (user != null && user.getPassword().equals(loginpojo.getPassword())) {
			return true;
		}
		return false;
	}

}
