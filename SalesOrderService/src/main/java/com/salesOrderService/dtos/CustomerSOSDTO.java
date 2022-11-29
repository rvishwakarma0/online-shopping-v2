
 package com.salesOrderService.dtos;
  
 import java.util.List;

import lombok.AllArgsConstructor; import lombok.Data; 
 import lombok.NoArgsConstructor;
  

 @Data
 @NoArgsConstructor
 @AllArgsConstructor 
 public class CustomerSOSDTO {
	 String cust_id; 
	 String cust_first_name; 
	 String cust_last_name; 
	 String cust_email;
	 private List<SalesOrderDTO> orders;
	 
	public CustomerSOSDTO(String cust_id, String cust_first_name, String cust_last_name, String cust_email) {
		super();
		this.cust_id = cust_id;
		this.cust_first_name = cust_first_name;
		this.cust_last_name = cust_last_name;
		this.cust_email = cust_email;
	}
	 
 }
 