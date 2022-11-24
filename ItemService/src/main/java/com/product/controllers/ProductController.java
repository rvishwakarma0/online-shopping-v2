package com.product.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entities.Item;
import com.product.service.ProductService;

@RestController
@RequestMapping("/items")
public class ProductController {
	
	@Autowired
	ProductService service; 

	@GetMapping("")
	List<Item> getProducts(){
		return service.getAllItems();
	}
	
	@GetMapping("/{name}")
	Item getProduct(@PathVariable String name){
		return service.getItemByName(name);
	}
	

}
