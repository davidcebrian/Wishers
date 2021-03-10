package com.wishers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishers.model.dto.CommentDTO;
import com.wishers.model.dto.CommentDTOConverter;
import com.wishers.model.dto.WishDTO;
import com.wishers.model.dto.WishDTOConverter;
import com.wishers.model.entity.Comment;
import com.wishers.model.entity.Wish;
import com.wishers.model.repo.CommentRepository;
import com.wishers.model.repo.WishRepository;

@Service
public class WishService extends BasePersistenceService<Wish, Long>{
	@Autowired
	private CommentRepository comRepo;
	@Autowired
	private CommentDTOConverter comConverter;
	@Autowired
	private WishRepository wishRepo;

	@Autowired
	private WishDTOConverter wishDtoConverter;
	
	public WishDTO addComment(String title, CommentDTO commentDto) {
		Wish wish = wishRepo.findByTitle(title);
		Comment comment = comConverter.fromCommentDTOToComment(commentDto);
		wish.addComment(comment);
		return wishDtoConverter.fromWishToWishDTO(wishRepo.save(wish));
	};
	
	public WishDTO Like(String title) {
		Wish wish = wishRepo.findByTitle(title);
		wish.Like();
		return wishDtoConverter.fromWishToWishDTO(wishRepo.save(wish));
	}
	
	public WishDTO Dislike(String title) {
		Wish wish = wishRepo.findByTitle(title);
		wish.Dislike();
		return wishDtoConverter.fromWishToWishDTO(wishRepo.save(wish));
	}
	
	public WishDTO LikeToComment(String title, CommentDTO comentDto) {
		Wish wish = wishRepo.findByTitle(title);
		wish.getComments().forEach(com -> {
			if(com.getTitle().equals(comentDto.getTitle())) {
				com.Like();
			}
		});
		return wishDtoConverter.fromWishToWishDTO(wishRepo.save(wish));
	}
	
	public WishDTO DislikeToComment(String title, CommentDTO comentDto) {
		Wish wish = wishRepo.findByTitle(title);
		wish.getComments().forEach(com -> {
			if(com.getTitle().equals(comentDto.getTitle())) {
				com.Dislike();
			}
		});
		return wishDtoConverter.fromWishToWishDTO(wishRepo.save(wish));
	}
	
	public WishDTO removeComment(String username, String title, CommentDTO commentDto) {
		Wish wish = wishRepo.findByTitle(title);
		if(username.equals(commentDto.getOwner())) {
			wish.removeComment(comRepo.findByTitleAndOwner(commentDto.getTitle(), commentDto.getOwner()));
			comRepo.delete(comRepo.findByTitleAndOwner(commentDto.getTitle(), commentDto.getOwner()));
		}else{
			return null;
		}
		return wishDtoConverter.fromWishToWishDTO(wishRepo.save(wish));
	}
	
	public List<CommentDTO> commentsFromWish(WishDTO wishDto) {
		Wish wish = wishRepo.findByTitle(wishDto.getTitle());
		List<CommentDTO> comments = new ArrayList<>();
		wish.getComments().forEach(comment -> {
			CommentDTO commentDto = comConverter.fromCommentToCommentDTO(comment);
			comments.add(commentDto);
		});
		return comments;
	}
}
