package com.wishers.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.wishers.security.model.dto.UserDTO;

@JsonInclude(Include.NON_NULL)
public class CustomerDTO {
	
	private String username;

	private List<WishDTO> wishes;
	
	private Long points;
	
	private UserDTO user;

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public List<WishDTO> getWishes() {
		return wishes;
	}

	public void setWishes(List<WishDTO> wishes) {
		this.wishes = wishes;
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}
	
	
}
