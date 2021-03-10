package com.wishers.model.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Comment extends BaseEntity{

	private String owner;
	
	private String title;
	private String comment;
	
	private Valoration valoration;
	
	//@ManyToOne
	//	@JoinColumn(name="parent_id")
	//	private Comment parent;
	//
	//	@OneToMany(mappedBy="parent", cascade = CascadeType.ALL)
	//	private List<Comment> response = new ArrayList<Comment>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn( name = "wish_id")
	private Wish wish;

	public Comment() {
		super();
		this.valoration = new Valoration();
	}
	
	public Comment(Wish wish) {
		super();
		this.wish = wish;
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


	public Wish getWish() {
		return wish;
	}

	public void setWish(Wish wish) {
		this.wish = wish;
	}
	
	
}
