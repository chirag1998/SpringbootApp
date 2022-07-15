package com.xoriant.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.xoriant.util.JwtUtil;

@Component
public class JWTBlacklist {

	@Autowired
	JwtUtil jwtUtil;

	// Map which keeps the list of blacklisted/invalid tokens
	public static Map<String, String> balcklistedTokensMap = new HashMap<>();

	@Scheduled(cron = "${blacklist.tokens.cleanup.schedule}")
	public void expiredTokenCleanUp() {
		balcklistedTokensMap.entrySet().removeIf(entry -> jwtUtil.isTokenExpired(entry.getKey()));
		System.out.println("BlacklistedTokens cleanup scheduler run !");
	}
}
