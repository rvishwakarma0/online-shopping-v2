package com.salesOrderService.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesOrderService.dtos.OrderLineItemDTO;
import com.salesOrderService.dtos.SalesOrderDTO;
import com.salesOrderService.entities.CustomerSOS;
import com.salesOrderService.entities.OrderLineItem;
import com.salesOrderService.entities.SalesOrder;
import com.salesOrderService.repositories.CustomerSOSRepository;
import com.salesOrderService.repositories.OrderLineItemRepository;
import com.salesOrderService.repositories.SalesOrderRepository;

@Service
public class SOSService {

	@Autowired
	CustomerSOSRepository crepo;
	
	@Autowired
	OrderLineItemRepository olrepo;
	
	@Autowired
	SalesOrderRepository sorepo;

	@Autowired
	ItemServices itemService;
	
	
	
	public SalesOrder createOrder(SalesOrderDTO salesOrderDTO, String token) throws Exception {
		CustomerSOS customerSOS = null;
		List<OrderLineItem> orderItems = new ArrayList<>();
		SalesOrder so = new SalesOrder();
		so.setId(UniqueIdGenerator.generate());
		//get customer
		customerSOS = getCustomerFromId(salesOrderDTO.getCust_id());
		//check items validity 
		List<OrderLineItemDTO> items = salesOrderDTO.getItems();
		if(itemService.checkItems(items, token)) {
			for (OrderLineItemDTO item : items)
				if(item.getItem_quantity()>0)
					orderItems.add(new OrderLineItem(UniqueIdGenerator.generate(), item.getItem_name(), item.getItem_quantity(), so));
		}
		else
			throw new Exception("Invalid Items in list");
		
		//set all values to so and save
		
		so.setCustomer(customerSOS);
		so.setOrderLineItems(orderItems);
		so.setTotal_price(itemService.calculateBillAmount(items, token));
		so.setOrder_desc(salesOrderDTO.getOrder_desc());
		so.setOrder_date(salesOrderDTO.getOrder_date());
		sorepo.save(so);
		return so;
	}

	public List<SalesOrder> getOrders(String customerId) throws Exception {
		CustomerSOS customerSOS = getCustomerFromId(customerId);
		System.out.println(customerSOS);
		List<SalesOrder> soList = sorepo.findByCustomer(customerSOS);
		System.out.println(soList);
		 return soList;
	}

	private CustomerSOS getCustomerFromId(String cust_id) throws Exception {
		System.out.println(cust_id);
		List<CustomerSOS> cList = crepo.findByCust_id(cust_id);
		if(cList.isEmpty()) 
			throw new Exception("Customer Not Found");
		return cList.get(0);
	}

	public SalesOrder getOrderDetails(String orderId) throws Exception {
		Optional<SalesOrder> findById = sorepo.findById(orderId);
		if(findById.isPresent())
			return findById.get();
		throw new Exception("invalid order id");
	}

	public void validateSalesOrderDto(SalesOrderDTO salesOrderDTO) throws Exception {
		
		if(salesOrderDTO.getOrder_desc().length()==0)
			throw new Exception("Order Description is invalid");
		
		if(salesOrderDTO.getCust_id().length()==0)
			throw new Exception("cutomer id is invalid");
		
		if(salesOrderDTO.getOrder_date()== null)
			throw new Exception("Order date is invalid");
		
		if(salesOrderDTO.getItems().size() == 0)
			throw new Exception("items list is invalid");
		
		List<OrderLineItemDTO> items = salesOrderDTO.getItems();
		for(OrderLineItemDTO  odto: items) {
			if(odto.getItem_name().length() == 0)
				throw new Exception("item name is invalid");
			if(odto.getItem_quantity() == 0)
				throw new Exception("item qty is invalid");
				
		}
	}
	
	
}
