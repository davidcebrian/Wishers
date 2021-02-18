package com.wishers.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.wishers.security.model.entity.User;

@SuppressWarnings("serial")
@Entity
public class Customer extends BaseEntity{
	
	private String username;
	
	@OneToOne(mappedBy = "customer")
	private User user;

	
	//@ManyToMany
	//private List<Wish> wish;
	
	
	public Customer(User user) {
		super();
		this.username = user.getUsername();
		this.user = user;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public User getUser() {
		return user;
	}
	

}
