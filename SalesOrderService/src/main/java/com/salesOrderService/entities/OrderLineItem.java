package com.salesOrderService.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "order_line_item")
public class OrderLineItem {
	@Id
	String id;
	String item_name;
	int item_quantity;
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private SalesOrder salesOrder;
}
