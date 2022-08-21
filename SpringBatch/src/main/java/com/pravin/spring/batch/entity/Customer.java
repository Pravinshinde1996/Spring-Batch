package com.pravin.spring.batch.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	
	
	@Id
	@Column(name="cid")
	private Long cid;
	
	@Column(name="cust_fname")
	private String fname;
	
	@Column(name="cust_lname")
	private String lname;
	
	@Column(name="cust_email")
	private String email;
	
	@Column(name="cust_gender")
	private String gender;
	
	@Column(name="cust_mobileno")
	private String contactNo;
	
	@Column(name="cust_country")
	private String country;
	
	@Column(name="cust_dob")
	private String dob;
	
}
