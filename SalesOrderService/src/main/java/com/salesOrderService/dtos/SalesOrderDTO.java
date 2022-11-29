package com.salesOrderService.dtos;

import java.sql.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class SalesOrderDTO {
	String id;
	Date order_date;
	String order_desc;
	Double total_price;
	private String cust_id;
	private List<OrderLineItemDTO> items;
}
