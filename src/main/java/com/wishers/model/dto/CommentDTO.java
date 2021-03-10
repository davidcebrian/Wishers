package com.wishers.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CommentDTO {
	
	private String owner;
	
	private String title;
	private String comment;
	
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
