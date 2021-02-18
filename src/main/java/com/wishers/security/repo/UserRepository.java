package com.wishers.security.repo;

import org.springframework.stereotype.Repository;

import com.wishers.model.repo.BaseRepository;
import com.wishers.security.model.entity.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long>{

	User findByUsername(String username);
	
	User findByUsernameAndPassword(String username, String password);
	
	Boolean existsByUsernameIgnoreCase(String username);
	
	Boolean existsByEmailIgnoreCase(String email);
}
