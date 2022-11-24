package com.product.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.entities.Item;
import com.product.repository.ProductRepository;

@Service
public class ProductService  {

	@Autowired
	ProductRepository repo;
	
	public List<Item> getAllItems() {
	  List<Item> items = new ArrayList<>();
	  for(Item item : repo.findAll()) {
		  items.add(item);
	  }
	  return items;
	}
	public Item getItemByName(String name) {
		return repo.findByName(name).get(0);
	}
}
