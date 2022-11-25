package com.cts.customerService.service;

import java.util.concurrent.atomic.AtomicInteger;

public class IdGenerator {
	
	  private static final AtomicInteger counter = new AtomicInteger(5);

	  public static int nextValue() {
	    return counter.getAndIncrement();
	  }
		
}
