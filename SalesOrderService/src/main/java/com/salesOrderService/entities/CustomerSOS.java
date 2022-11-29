
 package com.salesOrderService.entities;
  
 import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor; import lombok.Data; 
 import lombok.NoArgsConstructor;
  
 @Entity 
 @Data
 @NoArgsConstructor
 @AllArgsConstructor 
 @Table(name = "customer_sos")
 public class CustomerSOS {
	 @Id 
	 String cust_id; 
	 String cust_first_name; 
	 String cust_last_name; 
	 String cust_email;
	 @OneToMany(cascade = { CascadeType.ALL }, mappedBy = "customer", fetch = FetchType.EAGER)
	 private List<SalesOrder> orders;
	 
	public CustomerSOS(String cust_id, String cust_first_name, String cust_last_name, String cust_email) {
		super();
		this.cust_id = cust_id;
		this.cust_first_name = cust_first_name;
		this.cust_last_name = cust_last_name;
		this.cust_email = cust_email;
	}
	 
 }
 