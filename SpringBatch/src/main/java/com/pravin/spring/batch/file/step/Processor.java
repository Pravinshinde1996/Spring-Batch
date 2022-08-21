package com.pravin.spring.batch.file.step;

import org.springframework.batch.item.ItemProcessor;

import com.pravin.spring.batch.entity.Customer;

public class Processor implements ItemProcessor<Customer, Customer>{

	@Override
	public Customer process(Customer item) throws Exception {
		return item;
	}
	
}
