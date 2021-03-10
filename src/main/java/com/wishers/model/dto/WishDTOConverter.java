package com.wishers.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wishers.model.entity.Comment;
import com.wishers.model.entity.Customer;
import com.wishers.model.entity.Wish;
import com.wishers.model.repo.CommentRepository;
import com.wishers.service.CustomerService;

@Component
public class WishDTOConverter {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CommentRepository commRepo;
	
	public Wish fromWishDTOToWish(WishDTO wishDto) {
		Wish wish = new Wish();
		List<Customer> customers = new ArrayList();
		List<Comment> comments = new ArrayList();
		wishDto.getCustomers().forEach(username -> {
			Customer cus = customerService.findCustomer(username);
			customers.add(cus);
		});
		wishDto.getComments().forEach(comment-> {
			Comment comm = commRepo.findByTitleAndOwner(comment.getTitle(), comment.getOwner());
			comments.add(comm);
		});
		wish.setComments(comments);
		wish.setCustomers(customers);
		wish.setDescription(wishDto.getDescription());
		wish.setTitle(wishDto.getTitle());
		wish.setValue(wishDto.getValue());
		wish.setCompleted(wishDto.getCompleted());
		return wish;
	}
	
	public WishDTO fromWishToWishDTO(Wish wish) {
		WishDTO wishDto = new WishDTO();
		List<String> nombres = new ArrayList();
		wish.getCustomers().forEach(cus -> {
			String nombre = cus.getUsername();
			nombres.add(nombre);
		});
		List<CommentDTO> comments = new ArrayList();
		wish.getComments().forEach(com -> {
			CommentDTO commDto = new CommentDTO();
			commDto.setComment(com.getComment());
			commDto.setOwner(com.getOwner());
			commDto.setTitle(com.getTitle());
			commDto.setLikes(com.getValoration().getLikes());
			commDto.setDislikes(com.getValoration().getDislikes());
			comments.add(commDto);
		});
		wishDto.setComments(comments);
		wishDto.setCustomers(nombres);
		wishDto.setDescription(wish.getDescription());
		wishDto.setTitle(wish.getTitle());
		wishDto.setValue(wish.getValue());
		wishDto.setCompleted(wish.getCompleted());
		wishDto.setLikes(wish.getValoration().getLikes());
		wishDto.setDislikes(wish.getValoration().getDislikes());
		return wishDto;
	}
}
