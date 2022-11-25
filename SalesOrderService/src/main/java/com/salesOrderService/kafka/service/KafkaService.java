package com.salesOrderService.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.dtos.CustomerEvent;


@Service
public class KafkaService {
 
    // Annotation required to listen
    // the message from Kafka server
    @KafkaListener(topics = "customerTopic",
                   groupId = "id", containerFactory
                                   = "customerListner")
    public void
    publish(CustomerEvent cevent)
    {
    	System.out.println("8********************************8");
        System.out.println("New Entry: "
                           + cevent);
    }
}