package com.xoriant.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import io.jsonwebtoken.ExpiredJwtException;

@Component
@CrossOrigin(origins = "http://localhost:3000")
public class RbacAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException)
			throws ExpiredJwtException, IOException, ServletException, BadCredentialsException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}

}
