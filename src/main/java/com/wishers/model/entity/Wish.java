package com.wishers.model.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Entity
public class Wish extends BaseEntity{
	
	@ManyToMany(mappedBy = "wishes")
	private List<Customer> customers;
	
	private String title;
	private String description;
	private Long value;
	private Boolean completed;
	
	@OneToMany(mappedBy = "wish", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Comment> comments;
	
	private Valoration valoration;
	
	public Wish() {
		super();
		this.customers = new ArrayList<Customer>();
		this.comments = new ArrayList<Comment>();
		this.valoration = new Valoration();
	}
	
	public Valoration getValoration() {
		return valoration;
	}
	public void setValoration(Valoration valoration) {
		this.valoration = valoration;
	}
	
	public void Like() {
		this.valoration.setLikes( this.valoration.getLikes() + 1 );
	}
	public void Unlike() {
		this.valoration.setLikes( this.valoration.getLikes() + -1 );
	}
	
	public void Dislike() {
		this.valoration.setLikes( this.valoration.getDislikes() + 1 );
	}
	public void Undislike() {
		this.valoration.setLikes( this.valoration.getDislikes() - 1 );
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		comment.setWish(this);
		this.comments.add(comment);
	}
	
	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}
	
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
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
