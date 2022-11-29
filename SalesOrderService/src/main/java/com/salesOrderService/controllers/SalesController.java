package com.salesOrderService.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesOrderService.dtos.OrderLineItemDTO;
import com.salesOrderService.dtos.SOResponse;
import com.salesOrderService.dtos.SalesOrderDTO;
import com.salesOrderService.entities.OrderLineItem;
import com.salesOrderService.entities.SalesOrder;
import com.salesOrderService.services.SOSService;

@RestController
@RequestMapping("/orders")
public class SalesController {

	@Autowired
	SOSService service;
	
	@PostMapping("")
	public SOResponse createOrder(@RequestBody SalesOrderDTO salesOrderDTO) throws Exception {
		service.validateSalesOrderDto(salesOrderDTO);
		SalesOrder order = service.createOrder(salesOrderDTO);
		List<OrderLineItem> items = order.getOrderLineItems();
		List<OrderLineItemDTO> itemsDto = new ArrayList<OrderLineItemDTO>();
		for(OrderLineItem i: items) {
			itemsDto.add(new OrderLineItemDTO(i.getItem_name() , i.getItem_quantity()));
		}
		return new SOResponse(order.getId(), order.getOrder_desc(), itemsDto);
	}
	
	@GetMapping("")
	public List<SOResponse> getOrders(@RequestParam String customerId) throws Exception {
		System.out.println("Inside get order");

		List<SalesOrder> orders = service.getOrders(customerId);
		List<SOResponse> soResponses = new ArrayList<>();
		for(SalesOrder so: orders) 
			soResponses.add(new SOResponse(so.getId(), so.getOrder_desc()));
		return soResponses;
	}
	
	@GetMapping("/{orderId}")
	public SOResponse getOrderDetails(@PathVariable String orderId) throws Exception{
		SalesOrder orderDetails = service.getOrderDetails(orderId);
		List<OrderLineItemDTO> itemsDto = new ArrayList<OrderLineItemDTO>();
		for(OrderLineItem i: orderDetails.getOrderLineItems()) {
			itemsDto.add(new OrderLineItemDTO(i.getItem_name() , i.getItem_quantity()));
		}
		SOResponse response = new SOResponse(orderDetails.getId(), orderDetails.getOrder_desc(), itemsDto);
		return response;
		
	}
}
