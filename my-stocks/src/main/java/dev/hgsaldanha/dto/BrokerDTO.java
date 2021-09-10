package dev.hgsaldanha.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class BrokerDTO {
	
	private Integer id;
	
	@NotBlank
	private String name;
	
	@NotNull
	@PositiveOrZero
	private Double commissionPerTrade;
	
	@NotNull
	@PositiveOrZero
	private Double commissionPerExercise;

}
