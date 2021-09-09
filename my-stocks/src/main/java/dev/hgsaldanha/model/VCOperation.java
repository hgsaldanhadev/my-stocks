package dev.hgsaldanha.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("VC")
public class VCOperation extends Operation {
	
	private String option;
	
	private LocalDate dueDate;
	
	private BigDecimal strike;
	
	@Enumerated(EnumType.STRING)
	private VCType optionType;

	public enum VCType {
		P("Venda Coberta de Put"), C("Venda Coberta de Call");

		private String description;

		VCType(String description) {
			this.description = description;
		}
		
		public String toString() {
			return description;
		}

	}
	
	//TODO Use corretoras diferentes e você não precisa se preocupar com preço de compra para VCC. Ver isso na interface?
	
}
