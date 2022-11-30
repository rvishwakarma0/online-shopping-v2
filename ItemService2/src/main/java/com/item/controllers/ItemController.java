package com.item.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.item.entities.Item;
import com.item.security.service.AuthService;
import com.item.service.ItemService;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	ItemService service; 
	
	@Autowired
	AuthService authService;

	@GetMapping("")
	List<Item> getItems(@RequestHeader("Authorization") String token) throws Exception{
		authService.validateToken(token);
		return service.getAllItems();
	}
	
	@GetMapping("/{name}")
	Item getItem(@PathVariable String name, @RequestHeader("Authorization") String token) throws Exception{
		authService.validateToken(token);
		return service.getItemByName(name);
	}
	

}
