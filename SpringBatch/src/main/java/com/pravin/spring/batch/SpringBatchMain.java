package com.pravin.spring.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.pravin.spring.batch"})
public class SpringBatchMain {

	public static void main(String[] args) {
		SpringApplication.run(SpringBatchMain.class, args);

	}

}
