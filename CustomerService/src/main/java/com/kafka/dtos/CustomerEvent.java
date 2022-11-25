package com.kafka.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEvent {
	private String message;
	private String status;
	private CustomerDTO customer;
}
