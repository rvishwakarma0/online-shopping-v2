package com.salesOrderService.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
	String id;
	String name;
	String description;
	Double price;
	
}
