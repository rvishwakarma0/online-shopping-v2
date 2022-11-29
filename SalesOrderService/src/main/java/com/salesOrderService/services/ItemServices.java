package com.salesOrderService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesOrderService.dtos.ItemDTO;
import com.salesOrderService.dtos.OrderLineItemDTO;
import com.salesOrderService.feign_clients.ItemServiceFeignClient;

@Service
public class ItemServices {

	@Autowired
	ItemServiceFeignClient client;
	

	public boolean checkItems(List<OrderLineItemDTO> items) {	
		for (OrderLineItemDTO item : items) {
			System.out.println("item is ********");
			System.out.println(item);
			ItemDTO existingItem = client.getItem(item.getItem_name());
			System.out.println("existing item is **********");
			System.out.println(existingItem);
			if(existingItem == null)
				return false;					
		}
		
		return true;
	}
	
	public double calculateBillAmount(List<OrderLineItemDTO> items) {	
		double amt = 0;
		for (OrderLineItemDTO item : items) {
			ItemDTO existingItem = client.getItem(item.getItem_name());
			if(existingItem != null)
				amt = amt + (existingItem.getPrice() * item.getItem_quantity());					
		}
		
		return amt;
	}
}
