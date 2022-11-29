package com.cognizant.authorizationService.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@SpringBootTest

public class AdminDetailsServiceTest {

	@Autowired(required = true)
	AdminDetailsService adminDetailsService;

	@Test
	public void contextLoads() {

		assertNotNull(adminDetailsService);

	}

	@Test
	public void loadUserByUsernameTestSuccess() {

		
		assertEquals("101", adminDetailsService.loadUserByUsername("101").getUsername());
	}

	@Test
	public void loadUserByUsernameTestFail() {
		Exception thrown = Assertions.assertThrows(UsernameNotFoundException.class,
				() ->adminDetailsService.loadUserByUsername("randomUser"));
		
		assertEquals("UsernameNotFoundException", thrown.getMessage());
	}
	
	@Test
	public void getUserDataById() {
		assertEquals("1005", adminDetailsService.getUserDataById("1005").getUserid());
	}
	
}
