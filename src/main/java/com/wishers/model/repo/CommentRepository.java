package com.wishers.model.repo;

import org.springframework.stereotype.Repository;

import com.wishers.model.entity.Comment;

@Repository
public interface CommentRepository extends BaseRepository<Comment, Long>{
	
	Comment findByTitleAndOwner(String title, String owner);
}
