package com.wishers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishers.model.dto.CommentDTO;
import com.wishers.model.dto.WishDTO;
import com.wishers.model.entity.Wish;
import com.wishers.model.repo.CommentRepository;
import com.wishers.model.repo.WishRepository;
import com.wishers.service.CustomerService;
import com.wishers.service.WishService;
import com.wishers.service.error.Errores;

@RestController
@RequestMapping("/wish")
public class WishController {
		@Autowired
		private CustomerService cusService;
		
		@Autowired
		private WishService wishService;
		
		@Autowired
		private CommentRepository comRepo;
		@Autowired
		private WishRepository wishRepo;
		
		
		@PostMapping("/comment")
		public ResponseEntity<?> addComment(@PathVariable String title, @RequestBody CommentDTO commDto) {
			ResponseEntity<?> response;
			if(commDto != null) {
				Wish wish = wishRepo.findByTitle(title);
				WishDTO wishDto = wishService.addComment(title, commDto);
				if(wishDto != null && wish != null) {
					response = ResponseEntity.status(HttpStatus.ACCEPTED).body(wishDto);				
				}else {
					response = ResponseEntity.status(HttpStatus.FOUND).body(Errores.EXISTE_WISH_NAME);
				}
			}else {
				response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Errores.PETICION_INCORRECTA);
			}
			return response;
		}
}
