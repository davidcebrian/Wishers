package com.wishers.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wishers.model.entity.Comment;

@Component
public class CommentDTOConverter {

	@Autowired
	private WishDTOConverter wishConverter;

	
	public Comment fromCommentDTOToComment(CommentDTO commentDto) {
		Comment comment = new Comment();
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
		return commentDto;
	}
}
