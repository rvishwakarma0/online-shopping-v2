package com.kafka.dtos;


import javax.persistence.Entity;
import javax.persistence.Id;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	
	@Id
	int id;
	String email;
	String first_name;
	String last_name;
		

}
