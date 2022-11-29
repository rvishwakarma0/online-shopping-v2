package com.salesOrderService.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

 
@Data
@NoArgsConstructor
@AllArgsConstructor 
public class OrderLineItemDTO {
	String item_name;
	int item_quantity;
}
