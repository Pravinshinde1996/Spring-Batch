package com.pravin.spring.batch.file.step;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.pravin.spring.batch.entity.Customer;
import com.pravin.spring.batch.repository.CustomerRepository;

public class Writer implements ItemWriter<Customer>{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void write(List<? extends Customer> customers) throws Exception {
		
		RepositoryItemWriter<Customer> repositoryItemWriter=new RepositoryItemWriter<Customer>();
		repositoryItemWriter.setRepository(customerRepository);
		repositoryItemWriter.setMethodName("save");
		repositoryItemWriter.write(customers);
		
	}

}
