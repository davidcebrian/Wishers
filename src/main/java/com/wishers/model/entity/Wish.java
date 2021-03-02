package com.wishers.model.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
@SuppressWarnings("serial")
public class Wish extends BaseEntity{
	
	@ManyToMany(mappedBy = "wishes")
	private List<Customer> customers;
	
	private String title;
	private String description;
	private Long value;
	
	
	public List<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getValue() {
		return value;
	}
	public void setValue(Long value) {
		this.value = value;
	}
	
}
