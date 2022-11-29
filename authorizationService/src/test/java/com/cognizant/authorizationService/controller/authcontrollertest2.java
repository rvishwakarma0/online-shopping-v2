package com.cognizant.authorizationService.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import com.cognizant.authorizationService.model.AuthResponse;
import com.cognizant.authorizationService.model.UserData;
import com.cognizant.authorizationService.service.AdminDetailsService;
import com.cognizant.authorizationService.service.JwtUtil;

@SpringBootTest
public class authcontrollertest2 {
	@Mock
	private AuthenticationManager authenticationManager;

	@Mock
	private JwtUtil jwtTokenUtil;

	@Mock
	private AdminDetailsService adminDetailsService;


	
	@InjectMocks
	private AuthController authenticationController;

	@Test
	void testValidLogin(){
		
		UserData userData = new UserData("1005", "password", "rajeev", "dummy-token");
		UserDetails userDetails = new User(userData.getUname(), userData.getUpassword(),
				new ArrayList<>());

		when(adminDetailsService.loadUserByUsername("1005")).thenReturn(userDetails);
		when(jwtTokenUtil.generateToken(userDetails)).thenReturn("dummy-token");

		ResponseEntity<?> authenticationResponse = authenticationController.login(userData);
		assertEquals(HttpStatus.OK, authenticationResponse.getStatusCode());
	}

	@Test
	void testInvalidLogin(){
		UserData userData = new UserData("1005", "password2", "rajeev", "dummy-token");
		UserDetails userDetails = new User(userData.getUname(), "password",
				new ArrayList<>());

		when(adminDetailsService.loadUserByUsername("1005")).thenReturn(userDetails);
		//when(jwtTokenUtil.generateToken(userDetails)).thenReturn("dummy-token");

		ResponseEntity<?> authenticationResponse = authenticationController.login(userData);
		assertEquals(HttpStatus.FORBIDDEN, authenticationResponse.getStatusCode());
		
	}
	
	@Test
	void testValidToken(){
		UserData userData = new UserData("1005", "password", "rajeev", "dummy-token");
		String token="dummy-token";
		AuthResponse res = new AuthResponse("1005","rajeev",true);
		String token1 = token.substring(7);
		
		when(jwtTokenUtil.validateToken(token1)).thenReturn(true);
		when(jwtTokenUtil.extractUsername(token1)).thenReturn("1005");
		when(adminDetailsService.getUserDataById("1005")).thenReturn(userData);
		
		ResponseEntity<?> validity = authenticationController.getValidity(token);
		assertEquals(HttpStatus.OK,validity.getStatusCode());
	}
	
	@Test
	void testNullToken(){
		String token=null;
		ResponseEntity<?> validity = authenticationController.getValidity(token);
		assertEquals(HttpStatus.FORBIDDEN,validity.getStatusCode());
	}
	@Test
	void testTokenErrorHandling(){
		String token="";
		ResponseEntity<?> validity = authenticationController.getValidity(token);
		assertEquals(HttpStatus.OK,validity.getStatusCode());
	}
	@Test
	void testInvalidToken(){
		UserData userData = new UserData("1005", "password", "rajeev", "dummy-token");
		String token="dummy-token";
		AuthResponse res = new AuthResponse("1005","rajeev",true);
		String token1 = token.substring(7);
		
		when(jwtTokenUtil.validateToken(token1)).thenReturn(false);
		when(jwtTokenUtil.extractUsername(token1)).thenReturn("1005");
		when(adminDetailsService.getUserDataById("1005")).thenReturn(userData);
		
		ResponseEntity<?> validity = authenticationController.getValidity(token);
		assertEquals(HttpStatus.FORBIDDEN,validity.getStatusCode());
	}

}
