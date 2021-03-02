package com.wishers.model.dto;

import org.springframework.stereotype.Component;

import com.wishers.model.entity.Wish;

@Component
public class WishDTOConverter {

	public Wish fromWishDTOToWish(WishDTO wishDto) {
		Wish wish = new Wish();
		wish.setCustomers(wishDto.getCustomers());
		wish.setDescription(wishDto.getDescription());
		wish.setTitle(wishDto.getTitle());
		wish.setValue(wishDto.getValue());
		return wish;
	}
	
	public WishDTO fromWishToWishDTO(Wish wish) {
		WishDTO wishDto = new WishDTO();
		wishDto.setCustomers(wish.getCustomers());
		wishDto.setDescription(wish.getDescription());
		wishDto.setTitle(wish.getTitle());
		wishDto.setValue(wish.getValue());
		return wishDto;
	}
}
