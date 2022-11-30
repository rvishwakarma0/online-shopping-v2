package com.salesOrderService.feign_clients;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.salesOrderService.dtos.ItemDTO;

@RibbonClient(name = "item-service")
@FeignClient(path = "/items",name="item-service")//,url="${feign.clients.item-service-url}")
public interface ItemServiceFeignClient {
	@GetMapping("/{name}")
	ItemDTO getItem(@PathVariable String name, @RequestHeader("Authorization") String token);
}