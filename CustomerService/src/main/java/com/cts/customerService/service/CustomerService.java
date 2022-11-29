package com.cts.customerService.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cts.customerService.entities.Customer;
import com.cts.customerService.repository.CustomerRepository;
import com.kafka.dtos.CustomerDTO;
import com.kafka.dtos.CustomerEvent;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repo;

	 
	@Autowired KafkaTemplate<String, CustomerEvent> kafkaTemplate;
	
	private static final String TOPIC = "customerTopic";
	
	public List<Customer> getAllCustomers() {
		List<Customer> li = new ArrayList<>();
		Iterable<Customer> iterable = repo.findAll();
		for(Customer c: iterable) {
			li.add(c);
		}
		return li;
	}

	public void createCustomers(Customer cs) {
		String generatedId = IdGenerator.generate(cs.getFirst_name());
		cs.setId(generatedId);
		repo.save(cs);
		CustomerDTO dto = new CustomerDTO(cs.getId(), cs.getEmail(), cs.getFirst_name(), cs.getLast_name());
		sendCustomerEvent(dto);
	}
	
	public void sendCustomerEvent(CustomerDTO cs) {
		CustomerEvent cevent = new CustomerEvent("CustomerCreated", "CustomerCreated", cs);
		System.out.println(cevent);
		kafkaTemplate.send(TOPIC, cevent);
	}

	public void validateInputs(Customer cs) throws Exception {
		if(cs.getEmail().isBlank() && cs.getFirst_name().isBlank() && cs.getLast_name().isBlank())
			throw new Exception("invalid inputs");
		if(cs.getEmail().isEmpty() && cs.getFirst_name().isEmpty() && cs.getLast_name().isEmpty())
			throw new Exception("invalid inputs");
	}
}
