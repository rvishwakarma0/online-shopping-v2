package com.salesOrderService.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.salesOrderService.entities.CustomerSOS;

public interface CustomerSOSRepository extends CrudRepository<CustomerSOS, Integer> {

	@Query(value="select * from customer_sos where cust_id like ?1", nativeQuery = true)
	public List<CustomerSOS> findByCust_id(String cust_id);
	
}
