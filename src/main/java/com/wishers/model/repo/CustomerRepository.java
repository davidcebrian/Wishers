package com.wishers.model.repo;

import org.springframework.stereotype.Repository;

import com.wishers.model.entity.Customer;
import com.wishers.security.model.entity.User;

@Repository
public interface CustomerRepository extends BaseRepository<Customer, Long>{

	User findByUsername(String username);
	
}
