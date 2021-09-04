package dev.hgsaldanha.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class OperationHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Operation operation;
	
	@NonNull
	private LocalDate date;
	
	@NonNull
	private Integer quantity;
	
	@NonNull
	private BigDecimal price;
	
	//TODO precisa desse enum?
//	@Enumerated(EnumType.STRING)
//	private OperationHistoryType type;
//
//	public enum OperationHistoryType {
//		
//		C("Compra"),V("Venda");
//		
//		private String type;
//		
//		OperationHistoryType(String type) {
//			this.type = type;
//		}
//
//		@Override
//		public String toString() {
//			return this.type;
//		}
//
//	}

}
