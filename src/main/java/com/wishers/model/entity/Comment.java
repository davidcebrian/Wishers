package com.wishers.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Comment extends BaseEntity{

	private String owner;
	
	private String title;
	private String comment;
	
	//TODO
	private Valoration valoration;
	
	@ManyToOne
	@JoinColumn( name = " wish_id ", nullable = false)
	private Wish wish;

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

	public Valoration getValoration() {
		return valoration;
	}

	public void setValoration(Valoration valoration) {
		this.valoration = valoration;
	}

	public Wish getWish() {
		return wish;
	}

	public void setWish(Wish wish) {
		this.wish = wish;
	}
	
	
}
