package com.cognizant.authorizationService.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.authorizationService.model.UserData;


@SpringBootTest
public class JwUtilTest {

	@Autowired(required = true)
	JwtUtil jwtUtil;
	@Autowired
	AdminDetailsService adminDetailService;

	@Test
	public void contextLoads() {

		assertNotNull(jwtUtil);

	}

	private static String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDEiLCJpYXQiOjE2MjkxOTQxMjd9.MqXpGGoPGO4gF_kGNR81NnnBZglmQOUWuRBT0CjZQoQ";

	@Test
	public void extractUsernameTestSuccess() {

		assertEquals("101", jwtUtil.extractUsername(token));
	}
	@Test
	public void extractUsernameTestFail() {

		assertEquals("101", jwtUtil.extractUsername(token));
	}

	@Test
	public void generateTokenTestSuccess() {
		UserData userData = new UserData( "101", "101", "101","101");
		UserDetails userDetails = adminDetailService.loadUserByUsername(userData.getUserid());
		String exampleToken = jwtUtil.generateToken(userDetails);
		assertNotNull(exampleToken);
	}

	
	@Test
	public void validateTokenTestSuccess() {
		assertTrue(jwtUtil.validateToken(token));
	}

	@Test
	public void validateTokenTestFail() {
		assertFalse(jwtUtil.validateToken("randomToken"));

	}

}
