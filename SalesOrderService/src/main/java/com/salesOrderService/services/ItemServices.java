package com.salesOrderService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.salesOrderService.dtos.ItemDTO;
import com.salesOrderService.dtos.OrderLineItemDTO;
import com.salesOrderService.feign_clients.ItemServiceFeignClient;

@Service
public class ItemServices {

	@Autowired
	ItemServiceFeignClient client;
	
	@HystrixCommand(fallbackMethod = "defaultcheckItems")
	public boolean checkItems(List<OrderLineItemDTO> items, String token) {	
		for (OrderLineItemDTO item : items) {
			System.out.println("item is ********");
			System.out.println(item);
			ItemDTO existingItem = client.getItem(item.getItem_name(), token);
			System.out.println("existing item is **********");
			System.out.println(existingItem);
			if(existingItem == null)
				return false;					
		}
		
		return true;
	}
	
	public boolean defaultcheckItems(List<OrderLineItemDTO> items, String token) {
		System.out.println("inside breaker");
		return false;
	}
	
	@HystrixCommand(fallbackMethod = "deafultCalculateBillAmount")
	public double calculateBillAmount(List<OrderLineItemDTO> items, String token) {	
		double amt = 0;
		for (OrderLineItemDTO item : items) {
			ItemDTO existingItem = client.getItem(item.getItem_name(), token);
			if(existingItem != null)
				amt = amt + (existingItem.getPrice() * item.getItem_quantity());					
		}
		
		return amt;
	}
	
	public double deafultCalculateBillAmount(List<OrderLineItemDTO> items, String token) {
		return 0;
	}
	
}
