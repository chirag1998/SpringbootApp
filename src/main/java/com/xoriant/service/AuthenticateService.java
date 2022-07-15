package com.xoriant.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xoriant.entity.UserEntity;
import com.xoriant.pojo.AuthenticatePOJO;
import com.xoriant.repository.UserRepository;
import com.xoriant.util.JwtUtil;

@Service
public class AuthenticateService implements UserDetailsService {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userDao;

	public String authenticateUser(AuthenticatePOJO request) throws Exception {
		String userName = request.getUserName();

		UserDetails userDetails = loadUserByUsername(userName);
		String token = jwtUtil.generateToken(userDetails);

		return token;
	}

	public void authenticate(String userName, String userPassword) throws Exception {
		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch (BadCredentialsException | DisabledException e) {
			throw e;
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity user = userDao.findByUserName(username);
		if (user != null) {
			return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
					new ArrayList<>());
		}
		/*
		 * for first time use if (username.equals("xorpay")) { return new
		 * org.springframework.security.core.userdetails.User("xorpay", "xorpay", new
		 * ArrayList<>()); }
		 */ else {
			System.out.println("User not found with username:{} " + username);
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}
