package com.wishers.security.model.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.wishers.security.model.entity.User;

@Component
public class UserDTOConverter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User fromUserDTOToUser(UserDTO userDto) {
		User user = new User(userDto.getName(),userDto.getSurname(), userDto.getEdad(),
				userDto.getUsername(), userDto.getEmail(), userDto.getPassword());
	
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		return user;
		
	}
	
	public UserDTO fromUserToUserDTO(User user) {
		UserDTO userDto = new UserDTO();
		userDto.setUsername(user.getUsername());
		userDto.setRoles(user.getRoles());
		return userDto;
	}
	
}
