package com.wishers.security.service;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wishers.security.model.dto.UserDTO;
import com.wishers.security.model.dto.UserDTOConverter;
import com.wishers.security.model.entity.User;
import com.wishers.security.repo.UserRepository;
import com.wishers.service.BasePersistenceService;

@Service
public class UserService extends BasePersistenceService<User, Long> implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserDTOConverter dtoConverter;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		
		return user != null ? user : null;
	}
	
	public UserDetails loadUserById(Long idUser) throws AuthenticationException {
		return userRepository.findById(idUser)
				.orElseThrow(()-> new AuthenticationException("Id/username not found"));
	}
	
	public User getUserByUserName(String username) {
		User user = userRepository.findByUsername(username);
		return user != null ? user : null;
	}
	
	public UserDTO register (UserDTO userDto) {
		return dtoConverter.fromUserToUserDTO(userRepository.save(dtoConverter.fromUserDTOToUser(userDto)));
	}
	
	public Boolean checkUserOrEmailExists(String username, String email) {		
		return (userRepository.existsByEmailIgnoreCase(email) || userRepository.existsByUsernameIgnoreCase(username)) ? true : false;
	}

}
