package com.salesOrderService.repositories;

import org.springframework.data.repository.CrudRepository;

import com.salesOrderService.entities.OrderLineItem;

public interface OrderLineItemRepository extends CrudRepository<OrderLineItem, String>{

}
