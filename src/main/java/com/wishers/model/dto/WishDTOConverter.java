package com.wishers.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wishers.model.entity.Customer;
import com.wishers.model.entity.Wish;
import com.wishers.service.CustomerService;

@Component
public class WishDTOConverter {

	@Autowired
	private CustomerService customerService;
	
	public Wish fromWishDTOToWish(WishDTO wishDto) {
		Wish wish = new Wish();
		List<Customer> customers = new ArrayList();
		wishDto.getCustomers().forEach(username -> {
			Customer cus = customerService.findCustomer(username);
			customers.add(cus);
		});
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
		wishDto.setCustomers(nombres);
		wishDto.setDescription(wish.getDescription());
		wishDto.setTitle(wish.getTitle());
		wishDto.setValue(wish.getValue());
		wishDto.setCompleted(wish.getCompleted());
		return wishDto;
	}
}
