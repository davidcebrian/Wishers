package com.wishers.model.entity;


@SuppressWarnings("serial")

public class Valoration extends BaseEntity{
	private static final long INITIAL_VALORS = 0;
	
	private Long likes;
	
	private Long dislikes;
	
	
	
	public Valoration() {
		super();
		this.likes = INITIAL_VALORS;
		this.dislikes = INITIAL_VALORS;
	}
	
	public Long getLikes() {
		return likes;
	}
	public void setLikes(Long likes) {
		this.likes = likes;
	}
	public Long getDislikes() {
		return dislikes;
	}
	public void setDislikes(Long dislikes) {
		this.dislikes = dislikes;
	}
	
	
}
