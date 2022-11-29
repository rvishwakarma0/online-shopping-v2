package com.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.item.entities.Item;
import com.item.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	ItemService service; 

	@GetMapping("")
	List<Item> getItems(){
		return service.getAllItems();
	}
	
	@GetMapping("/{name}")
	Item getItem(@PathVariable String name){
		return service.getItemByName(name);
	}
	

}
