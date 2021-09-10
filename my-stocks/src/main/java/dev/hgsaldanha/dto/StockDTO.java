package dev.hgsaldanha.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class StockDTO {
	
	private Integer id;
	
	@NotBlank
	private String ticker;
	
	@NotBlank
	private String name;

}
