package com.salesOrderService.services;

import java.util.UUID;

public class UniqueIdGenerator {

	public static String generate() {
		return UUID.randomUUID().toString().substring(24, 34);
	}
	
}
