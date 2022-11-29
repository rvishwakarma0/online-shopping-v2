package com.kafka.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	String id;
	String email;
	String first_name;
	String last_name;
}
