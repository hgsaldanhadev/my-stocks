package dev.hgsaldanha.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

@Data
public class SwingTradeOperationDTO {

	private Long id;

	@NotNull
	@Positive
	private Integer brokerId;

	@NotNull
	@Positive
	private Integer stockId;
	
	// TODO precisa?
//	private List<OperationHistory> history;

	@PastOrPresent
	private LocalDate startDate;

	@PastOrPresent
	private LocalDate closingDate;

	@Positive
	private BigDecimal startPrice;

	@PositiveOrZero
	private BigDecimal closingPrice;

	private String notes;

}
