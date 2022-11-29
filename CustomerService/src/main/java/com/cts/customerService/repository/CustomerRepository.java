package com.cts.customerService.repository;

import org.springframework.data.repository.CrudRepository;

import com.cts.customerService.entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

}
