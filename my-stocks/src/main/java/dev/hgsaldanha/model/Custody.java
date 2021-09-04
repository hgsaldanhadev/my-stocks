package dev.hgsaldanha.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Custody implements Serializable { //TODO precisa dessa classe?
	/**
	 * 
	 */
	private static final long serialVersionUID = -865476411158229535L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Stock stock;

	private LocalDate startDate;

	private LocalDate closingDate;

	private Long quantity;

	private BigDecimal price;

}
