package com.salesOrderService.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesOrderService.security.AuthFeign;
import com.salesOrderService.security.entity.AuthResponse;

@Service
public class AuthService {
	
	@Autowired
	AuthFeign authClient;
	
	public Boolean isSessionValid(String token) {
		try {
			AuthResponse authResponse = authClient.getValidity(token);
			if(authResponse.isValid()) return true;
			else return false;
		} catch (Exception e) {
			return false;
		} 
	}
	
	public void validateToken(String token) throws Exception {
		if(!isSessionValid(token))
			throw new Exception("invalid token");
	}

}
