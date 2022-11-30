package com.cts.customerService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customerService.entities.Customer;
import com.cts.customerService.security.service.AuthService;
import com.cts.customerService.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService service;
	
	@Autowired
	AuthService authService;
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	List<Customer> getAllCustomers(@RequestHeader("Authorization") String token) throws Exception{
		authService.validateToken(token);
		return service.getAllCustomers();
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	String createCustomer(@RequestBody Customer cs, @RequestHeader("Authorization") String token) throws Exception{
		authService.validateToken(token);
		service.validateInputs(cs);
		service.createCustomers(cs);
		return "customerCreated";
	}
}
