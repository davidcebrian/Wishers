package com.wishers.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wishers.model.entity.Customer;
import com.wishers.security.model.dto.UserDTOConverter;

@Component
public class CustomerDTOConverter {
	
	@Autowired
	private UserDTOConverter userDtoConverter;
	@Autowired
	private WishDTOConverter wishConverter;
	
	public CustomerDTO fromCustomerToCustomerDTO(Customer customer) {
		CustomerDTO customerDto = new CustomerDTO();
		List<WishDTO> wishesDto = new ArrayList();
		customerDto.setUser(userDtoConverter.fromUserToUserDTO(customer.getUser()));
		customerDto.setUsername(customer.getUsername());
		customerDto.setPoints(customer.getPoints());
		customer.getWishes().forEach(wish -> {
			wishesDto.add(wishConverter.fromWishToWishDTO(wish));
		});
		customerDto.setWishes(wishesDto);
		return customerDto;
	}
	
}
