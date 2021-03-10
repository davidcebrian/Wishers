package com.wishers.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class WishDTO {
	
	private String title;
	private String description;
	private Long value;
	private List<String> customers;
	private List<CommentDTO> comments;
	private Boolean completed;
	private Long likes;
	private Long Dislikes;
	
	
	public Long getLikes() {
		return likes;
	}
	public void setLikes(Long likes) {
		this.likes = likes;
	}
	public Long getDislikes() {
		return Dislikes;
	}
	public void setDislikes(Long dislikes) {
		Dislikes = dislikes;
	}
	public List<CommentDTO> getComments() {
		return comments;
	}
	public void setComments(List<CommentDTO> comments) {
		this.comments = comments;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	public List<String> getCustomers() {
		return customers;
	}
	public void setCustomers(List<String> customers) {
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
