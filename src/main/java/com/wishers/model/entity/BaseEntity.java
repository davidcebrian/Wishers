package com.wishers.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
	
	/*
	 * Entidad padre de todas las demas entidades, en esta entidad genero el id para cada otra entidad
	 * 
	 * Ademas de las fechas de creacion, modificacion y eliminacion "logica".
	 */
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	
	
	/*
	 * Constructor
	 */
	public BaseEntity() {
		super();
		
	}

	/*
	 * Getters y Setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
