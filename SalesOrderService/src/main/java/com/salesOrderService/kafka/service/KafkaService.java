package com.salesOrderService.kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.dtos.CustomerDTO;
import com.kafka.dtos.CustomerEvent;
import com.salesOrderService.entities.CustomerSOS;
import com.salesOrderService.repositories.CustomerSOSRepository;


@Service
public class KafkaService {
 
	@Autowired
	CustomerSOSRepository repo;
	
    @KafkaListener(topics = "customerTopic",groupId = "id", containerFactory = "customerListner")
    public void publish(CustomerEvent cevent)
    {
    	CustomerDTO customerDTO = cevent.getCustomer();
    	CustomerSOS customer = new CustomerSOS(customerDTO.getId(),
    			customerDTO.getFirst_name(), 
    			customerDTO.getLast_name(), 
    			customerDTO.getEmail());
    	repo.save(customer);
    	System.out.println("A customer is created with id:"+customer.getCust_id());
    }
}