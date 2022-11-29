package com.cognizant.authorizationService.model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


public class UserDataTest {

	private UserData userData;
	
	@BeforeEach
	void setUp() {
		userData = new UserData();
		userData.setUserid("1005");
		userData.setUname("rajeev");
		userData.setUpassword("password");
		userData.setAuthToken("token");
	}
	
	@Test
	public void allArgumentConstructorTest() {
		UserData user = new UserData(userData.getUserid(), userData.getUpassword(), userData.getUname(), userData.getAuthToken());
		assertEquals(userData.getUserid(), user.getUserid());
	}
}