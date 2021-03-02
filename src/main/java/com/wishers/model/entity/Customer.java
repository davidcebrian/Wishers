package com.wishers.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.wishers.security.model.entity.User;

@SuppressWarnings("serial")
@Entity
public class Customer extends BaseEntity{
	
	
	private static final Long START_POINTS = (long) 0;
	
	private String username;
	
	@OneToOne(mappedBy = "customer")
	private User user;

	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "customer_wish",
	joinColumns = @JoinColumn(name="customer_id", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="wish_id", referencedColumnName="id"))
	private List<Wish> wishes;
	
	private Long points;


	public Customer(User user) {
		super();
		this.username = user.getUsername();
		this.user = user;
		this.wishes = new ArrayList<>();
		this.points = START_POINTS;
	}

	public List<Wish> getWishes() {
		return wishes;
	}
	
	
	public void setWishes(List<Wish> wish) {
		this.wishes = wish;
	}
	
	public void addWish(Wish wish) {
		this.wishes.add(wish);
	}
	
	public void removeWish(Wish wish) {
		this.wishes.remove(wish);
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
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
