package com.wishers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wishers.model.dto.CustomerDTO;
import com.wishers.model.dto.CustomerDTOConverter;
import com.wishers.model.dto.WishDTO;
import com.wishers.model.dto.WishDTOConverter;
import com.wishers.model.entity.Customer;
import com.wishers.model.entity.Wish;
import com.wishers.model.repo.CustomerRepository;
import com.wishers.model.repo.WishRepository;

@Service
public class CustomerService  extends BasePersistenceService<Customer, Long>{

	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private WishRepository wishRepo;
	
	@Autowired
	private CustomerDTOConverter customerDtoConverter;
	
	@Autowired
	private WishDTOConverter wishDtoConverter;
	
	public CustomerDTO addWish(String username, WishDTO wishDto) {
		Customer customer = null;
		Wish wish = null;
		if(username != null || wishDto != null) {
			customer = customerRepo.findByUsername(username);
			wish = wishDtoConverter.fromWishDTOToWish(wishDto);
			customer.addWish(wish);
			return customerDtoConverter.fromCustomerToCustomerDTO(customerRepo.save(customer));
		}
		return null;
	}
	
	public CustomerDTO removeWish(String username, WishDTO wishDto) {
		Customer customer = null;
		if(username != null || wishDto != null) {
			customer = customerRepo.findByUsername(username);
			customer.removeWish(wishRepo.findByTitle(wishDto.getTitle()));
			wishRepo.delete(wishRepo.findByTitle(wishDto.getTitle()));
			return customerDtoConverter.fromCustomerToCustomerDTO(customerRepo.save(customer));
		}
		return null;
	}
	
	public CustomerDTO completeWish(String username, WishDTO wishDto) {
		Customer customer = null;
		if(username != null || wishDto != null) {
			customer = customerRepo.findByUsername(username);
			Wish wish = wishRepo.findByTitle(wishDto.getTitle());
			wish.setCompleted(!wish.getCompleted());
			return customerDtoConverter.fromCustomerToCustomerDTO(customerRepo.save(customer));
		}
		return null;
	}
	
	public List<CustomerDTO> allCustomers(){
		List<Customer> customers = customerRepo.findAll();
		List<CustomerDTO> customersDTO = new ArrayList();
		customers.stream().forEach(cus -> {
			CustomerDTO cusDto = customerDtoConverter.fromCustomerToCustomerDTO(cus);
			customersDTO.add(cusDto);
		});
		return customersDTO;
	}
	
	public List<WishDTO> wishesFromCustomer(String username){
		Customer customer = findCustomer(username);
		List<WishDTO> wishesDto = new ArrayList<>();
		customer.getWishes().stream().forEach(wish -> {
			wishesDto.add(wishDtoConverter.fromWishToWishDTO(wish));
		});
		return wishesDto;
	}
	
	public Customer findCustomer(String username) {
		return customerRepo.findByUsername(username);
	}
	
	public CustomerDTO findCustomerDto(String username) {
		return customerDtoConverter.fromCustomerToCustomerDTO(customerRepo.findByUsername(username));
	}
}
