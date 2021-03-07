package com.wishers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishers.model.dto.CustomerDTO;
import com.wishers.model.dto.WishDTO;
import com.wishers.model.entity.Customer;
import com.wishers.service.CustomerService;
import com.wishers.service.error.Errores;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("")
	public ResponseEntity<?> getAll() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.allCustomers());
	}
	
	@PostMapping("/{username}/wish")
	public ResponseEntity<?> addWish(@PathVariable String username, @RequestBody WishDTO wishDto) {
		ResponseEntity<?> response;
		if(wishDto != null) {
			Customer customer = customerService.findCustomer(username);
			CustomerDTO cusDto = customerService.addWish(username, wishDto);
			if(cusDto != null && customer != null) {
				response = ResponseEntity.status(HttpStatus.ACCEPTED).body(cusDto);				
			}else {
				response = ResponseEntity.status(HttpStatus.FOUND).body(Errores.EXISTE_WISH_NAME);
			}
		}else {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Errores.PETICION_INCORRECTA);
		}
		return response;
	}
	
	@PostMapping("/{username}/wish/customer")
	public ResponseEntity<?> addWishToCustomer(@PathVariable String username, @RequestBody WishDTO wishDto) {
		ResponseEntity<?> response;
		if(wishDto != null) {
			Customer customer = customerService.findCustomer(username);
			CustomerDTO cusDto = customerService.addWishToCustomer(username, wishDto);
			if(customer != null && cusDto != null) {
				response = ResponseEntity.status(HttpStatus.ACCEPTED).body(cusDto);				
			}else {
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Errores.PETICION_INCORRECTA);
			}
		}else {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Errores.PETICION_INCORRECTA);
		}
		return response;
	}
	
	@GetMapping("/{username}/wish")
	public ResponseEntity<?> allWishesFromUser(@PathVariable String username) {
		ResponseEntity<?> response;
		if(username != null) {
			response = ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.wishesFromCustomer(username));
		}else {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Errores.PETICION_INCORRECTA);
		}
		return response;
	}
	
	@DeleteMapping("/{username}/wish")
	public ResponseEntity<?> deleteWish(@PathVariable String username, @RequestBody WishDTO wishDto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.removeWish(username, wishDto));
	}
	
	@PutMapping("/{username}/wish")
	public ResponseEntity<?> completeWish(@PathVariable String username, @RequestBody WishDTO wishDto) {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(customerService.completeWish(username, wishDto));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> getCustomer(@PathVariable String username){
		return ResponseEntity.status(HttpStatus.OK).body(customerService.findCustomerDto(username));
	}
}
