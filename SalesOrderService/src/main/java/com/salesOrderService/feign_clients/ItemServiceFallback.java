package com.salesOrderService.feign_clients;

import org.springframework.stereotype.Component;

import com.salesOrderService.dtos.ItemDTO;

@Component
public class ItemServiceFallback implements ItemServiceFeignClient{

	@Override
	public ItemDTO getItem(String name) {
		System.out.println("inside fall back method");
		return null;
	}

}
