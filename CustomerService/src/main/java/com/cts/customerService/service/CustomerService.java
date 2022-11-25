package com.cts.customerService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cts.customerService.entities.Customer;
import com.cts.customerService.entities.CustomerEvent;
import com.cts.customerService.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repo;

	 
	@Autowired KafkaTemplate<String, CustomerEvent> kafkaTemplate;
	
	private static final String TOPIC = "NewTopic";
	
	public List<Customer> getAllCustomers() {
		List<Customer> li = new ArrayList<>();
		Iterable<Customer> iterable = repo.findAll();
		for(Customer c: iterable) {
			li.add(c);
		}
		return li;
	}

	public void createCustomers(Customer customer) {
		customer.setId(IdGenerator.nextValue());
		repo.save(customer);
		sendCustomerEvent(customer);
	}
	
	public void sendCustomerEvent(Customer cs) {
		CustomerEvent cevent = new CustomerEvent("CustomerCreated", "CustomerCreated", cs);
		kafkaTemplate.send(TOPIC, cevent);
	}
}
