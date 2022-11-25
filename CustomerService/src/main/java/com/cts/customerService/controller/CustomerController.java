package com.cts.customerService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.customerService.entities.Customer;
import com.cts.customerService.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService service;
	
	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	List<Customer> getAllCustomers(){
		return service.getAllCustomers();
	}
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	String createCustomer(@RequestBody Customer cs){
		service.createCustomers(cs);
		return "customerCreated";
	}
}
