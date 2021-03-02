package com.wishers.model.dto;

import org.springframework.stereotype.Component;

import com.wishers.model.entity.Customer;

@Component
public class CustomerDTOConverter {
	
	public CustomerDTO fromCustomerToCustomerDTO(Customer customer) {
		CustomerDTO customerDto = new CustomerDTO();
		customerDto.setUser(customer.getUser());
		customerDto.setUsername(customer.getUsername());
		customerDto.setPoints(customer.getPoints());
		customerDto.setWishes(customer.getWishes());
		return customerDto;
	}
	
}
