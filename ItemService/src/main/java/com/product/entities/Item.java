package com.product.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
	@Id
	int id;
	String name;
	String description;
	BigDecimal price;
	
}
