package com.wishers.model.repo;

import org.springframework.stereotype.Repository;

import com.wishers.model.entity.Wish;

@Repository
public interface WishRepository extends BaseRepository<Wish, Long>{

	Wish findByTitle(String title);
}
