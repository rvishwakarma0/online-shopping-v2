package com.salesOrderService.entities;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor 
@Table(name = "sales_order")
public class SalesOrder {
	@Id
	String id;
	Date order_date;
	String order_desc;
	Double total_price;
	
	@OneToMany(cascade = { CascadeType.ALL }, mappedBy = "salesOrder", fetch = FetchType.EAGER)
	private List<OrderLineItem> orderLineItems;
	
	@ManyToOne
	@JoinColumn(name = "cust_id", referencedColumnName = "cust_id")
	private CustomerSOS customer;
}
