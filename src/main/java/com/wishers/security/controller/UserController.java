package com.wishers.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishers.security.model.dto.UserDTO;
import com.wishers.security.model.entity.User;
import com.wishers.security.service.UserService;
import com.wishers.service.error.Errores;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserDTO userDto) {
			ResponseEntity<?> response;
			if(userDto.getUsername().isBlank() || userDto.getPassword().isBlank() || userDto.getEmail().isBlank()) {
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Errores.PETICION_INCORRECTA);
			}else if(userService.checkUserOrEmailExists(userDto.getUsername(), userDto.getPassword())) {
				response = ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Errores.EXISTE_NICK_EMAIL);
			}else {
				response = ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userDto));
			}
					return response;
		
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO userDto){
		// Created only to retrieve the Bearer token once authenticated
		ResponseEntity<?> response;
		if(userDto.getUsername().isBlank() || userDto.getPassword().isBlank()) {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Errores.PETICION_INCORRECTA);
		}else {
			User user = userService.getUserByUserName(userDto.getUsername());
			response = user != null ? ResponseEntity.status(HttpStatus.ACCEPTED).body(userDto) : ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(Errores.USUARIO_PASS_INCORRECTA);
		}
		return response;
	}
}

