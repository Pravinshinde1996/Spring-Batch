package com.pravin.spring.batch.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.Test;
import com.pravin.spring.batch.entity.Customer;

public class SpringBatchConfigTest {

	@Test
	public void testSpringBatchConfig() throws Exception {
		
		CustomerItemProcessor customerItemProcessor=new CustomerItemProcessor();
		Customer customer=new Customer();
		customer.setCid(266L);
		customer.setContactNo("899899");
		customer.setCountry("Afghanistan");
		customer.setDob("10/10/1994");
		customer.setEmail("kbulluck7d@photobucket.com");
		customer.setFname("Kameko");
		customer.setLname("Bulluck");
		customer.setGender("Female");
		
		assertEquals(customer,customerItemProcessor.process(customer));
		
	}
}
