package com.product.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.product.entities.Item;

//public interface ProductRepository extends CrudRepository<Product, Integer> {
public interface ProductRepository extends PagingAndSortingRepository<Item, Integer> {
	
	List<Item> findByName(String name);
	
}
