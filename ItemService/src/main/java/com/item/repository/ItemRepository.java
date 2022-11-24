package com.item.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.item.entities.Item;

//public interface ItemRepository extends CrudRepository<Product, Integer> {
public interface ItemRepository extends PagingAndSortingRepository<Item, Integer> {
	
	List<Item> findByName(String name);
	
}
