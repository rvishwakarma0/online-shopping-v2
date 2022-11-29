package com.salesOrderService.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SOResponse {
	public SOResponse(String id2, String order_desc2) {
		this.id = id2;
		this.order_desc = order_desc2;
	}
	String id;
	String order_desc;
	private List<OrderLineItemDTO> items;
}
