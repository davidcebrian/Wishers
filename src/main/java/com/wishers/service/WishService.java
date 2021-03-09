package com.wishers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishers.model.dto.CommentDTO;
import com.wishers.model.dto.CommentDTOConverter;
import com.wishers.model.dto.CustomerDTOConverter;
import com.wishers.model.dto.WishDTO;
import com.wishers.model.dto.WishDTOConverter;
import com.wishers.model.entity.Comment;
import com.wishers.model.entity.Wish;
import com.wishers.model.repo.CommentRepository;
import com.wishers.model.repo.CustomerRepository;
import com.wishers.model.repo.WishRepository;

@Service
public class WishService extends BasePersistenceService<Wish, Long>{
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private CommentRepository comRepo;
	@Autowired
	private CommentDTOConverter comConverter;
	@Autowired
	private WishRepository wishRepo;
	@Autowired
	private CustomerDTOConverter customerDtoConverter;
	@Autowired
	private WishDTOConverter wishDtoConverter;
	
	public WishDTO addComment(String title, CommentDTO commentDto) {
		Comment comment = comConverter.fromCommentDTOToComment(commentDto);
		Wish wish = wishRepo.findByTitle(title);
		wish.addComment(comment);
		return wishDtoConverter.fromWishToWishDTO(wishRepo.save(wish));
	};
	
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
