package com.pravin.spring.batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pravin.spring.batch.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
