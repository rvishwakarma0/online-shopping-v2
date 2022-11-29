package com.salesOrderService.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.salesOrderService.entities.CustomerSOS;
import com.salesOrderService.entities.SalesOrder;

public interface SalesOrderRepository extends CrudRepository<SalesOrder, String> {

	List<SalesOrder> findByCustomer(CustomerSOS customer); 
}
