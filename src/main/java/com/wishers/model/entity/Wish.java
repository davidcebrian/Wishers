package com.wishers.model.entity;


import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
@SuppressWarnings("serial")
public class Wish extends BaseEntity{
	
	@ManyToMany(mappedBy = "wishes")
	private List<Customer> customers;
	
	private String title;
	private String description;
	private Long value;
	private Boolean completed;
	
	@OneToMany(mappedBy = "wish")
	private List<Comment> comments;
	
	//TODO PERSINSTENCE
	private Valoration valoration;
	
	
	
	
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	public void addComment(Comment comment) {
		this.comments.add(comment);
	}
	
	public void removeComment(Comment comment) {
		this.comments.remove(comment);
	}
	
	public Valoration getValoration() {
		return valoration;
	}
	public void setValoration(Valoration valoration) {
		this.valoration = valoration;
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
