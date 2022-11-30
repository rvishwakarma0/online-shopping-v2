package com.cts.customerService.security;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cts.customerService.security.entity.AuthResponse;


@FeignClient(name = "authorization-service", url = "${authorization-service-url}")
public interface AuthFeign {
	/**
	 * This method will verify whether the token is valid or expired.
	 * 
	 * @param token
	 * @return An object of type AuthResponse which has fields userid, username and
	 *         isValid.
	 */
	@RequestMapping(value = "/auth-api/validate", method = RequestMethod.GET)
	public AuthResponse getValidity(@RequestHeader("Authorization") String token);

}