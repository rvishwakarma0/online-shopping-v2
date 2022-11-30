package com.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.item.entities.Item;
import com.item.repository.ItemRepository;

@Service
public class ItemService  {

	@Autowired
	ItemRepository repo;
	
	public List<Item> getAllItems() {
	  List<Item> items = new ArrayList<>();
	  for(Item item : repo.findAll()) {
		  items.add(item);
	  }
	  return items;
	}
	public Item getItemByName(String name) {
		List<Item> list = repo.findByName(name);
		if(list.isEmpty())
			return null;
		else
			return repo.findByName(name).get(0);
	}
}
