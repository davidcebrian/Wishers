package com.wishers.service;

import java.io.Serializable;
import java.util.List;

import javax.transaction.Transactional;

import com.wishers.model.entity.BaseEntity;
import com.wishers.model.repo.BaseRepository;

@Transactional
public abstract class BasePersistenceService<T extends BaseEntity, ID extends Serializable> {
	
	protected BaseRepository<T, ID> baseRepository;
	

	public T find(ID entityId) {
		T entity = null;
		if(entityId != null) {
			entity = baseRepository.findById(entityId).get();			
		}
		return entity != null ? entity : null;
	}
	
	
	public List<T> findAll() {
		return (List<T>) baseRepository.findAll();
	}

	
	public T save(T entity) {
		return baseRepository.save(entity);
	}

	
	public T update(T entity, ID entityId) {
		// Save and update do the same thing
		return baseRepository.save(entity);
	}


	public void delete(ID entityId) {
		baseRepository.deleteById(entityId);
	}

}
