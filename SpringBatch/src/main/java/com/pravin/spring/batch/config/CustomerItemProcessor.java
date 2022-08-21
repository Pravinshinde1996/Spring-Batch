package com.pravin.spring.batch.config;

import org.springframework.batch.item.ItemProcessor;

import com.pravin.spring.batch.entity.Customer;

public class CustomerItemProcessor implements ItemProcessor<Customer, Customer>{

	public Customer process(Customer customer) throws Exception {
		
		Customer customers=null;
		if(customer.getCountry().equalsIgnoreCase("Afghanistan")) {
			customers=new Customer();
			customers=customer;
		}
		return customers;
		/*return customer;*/
	}

}
