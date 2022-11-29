package com.cts.customerService.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.customerService.entities.Customer;
import com.cts.customerService.repository.CustomerRepository;



@Service
public class UserAuthDetailsService implements UserDetailsService {
	
	@Override
	public UserDetails loadUserByUsername(String uid) {
		return new User("user", "password", new ArrayList<>());
	}
}
