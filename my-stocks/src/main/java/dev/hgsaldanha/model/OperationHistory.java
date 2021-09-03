package dev.hgsaldanha.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class OperationHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Operation operation;
	
	private LocalDate date;
	
	private Long quantity;
	
	@Enumerated(EnumType.STRING)
	private OperationHistoryType type;

	public enum OperationHistoryType {
		
		C("Compra"),V("Venda");
		
		private String type;
		
		OperationHistoryType(String type) {
			this.type = type;
		}

		@Override
		public String toString() {
			return this.type;
		}

	}

}
