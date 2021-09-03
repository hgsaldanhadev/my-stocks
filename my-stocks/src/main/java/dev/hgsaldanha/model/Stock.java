package dev.hgsaldanha.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Stock implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 396999447428901716L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String ticker;
	
	private String name;

}
