package com.cognizant.authorizationService.model;




import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AuthResponseTest {
	
private AuthResponse authResponse;
	
	@BeforeEach
	void setUp() {
		authResponse = new AuthResponse();
		authResponse.setName("rajeev");
		authResponse.setUid("1005");
		authResponse.setValid(true);
	}
	
	@Test
	public void allArgumentConstructorTest() {
		AuthResponse response = new AuthResponse(authResponse.getUid(),authResponse.getName(),authResponse.isValid());
		assertEquals(response.getUid(), authResponse.getUid());
	}
}
