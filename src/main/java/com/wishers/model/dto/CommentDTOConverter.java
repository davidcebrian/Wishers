package com.wishers.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wishers.model.entity.Comment;
import com.wishers.model.repo.WishRepository;

@Component
public class CommentDTOConverter {

	@Autowired
	private WishRepository wishRepo;
	
	public Comment fromCommentDTOToComment(CommentDTO commentDto) {
		Comment comment = new Comment(wishRepo.findByTitle(commentDto.getTitle()));
		comment.setOwner(commentDto.getOwner());
		comment.setComment(commentDto.getComment());
		comment.setTitle(commentDto.getTitle());
		return comment;
	}
	
	public CommentDTO fromCommentToCommentDTO(Comment comment) {
		CommentDTO commentDto = new CommentDTO();
		commentDto.setComment(comment.getComment());
		commentDto.setOwner(comment.getOwner());
		commentDto.setTitle(comment.getTitle());
		commentDto.setLikes(comment.getValoration().getLikes());
		commentDto.setDislikes(comment.getValoration().getDislikes());
		return commentDto;
	}
}
