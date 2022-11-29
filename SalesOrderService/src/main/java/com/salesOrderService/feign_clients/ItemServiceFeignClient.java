package com.salesOrderService.feign_clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.salesOrderService.dtos.ItemDTO;

@FeignClient(path = "/items",name="item-service",url="${feign.clients.item-service-url}", fallback = ItemServiceFallback.class)
public interface ItemServiceFeignClient {
	
	@GetMapping("/{name}")
	ItemDTO getItem(@PathVariable String name);
	
}
