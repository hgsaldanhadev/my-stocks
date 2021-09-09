package dev.hgsaldanha.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.PastOrPresent;

import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Operation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(optional = false)
	private Broker broker;

	@OneToOne(optional = false)
	private Stock stock;
	
	@OneToOne(optional = false)
	private User user;

	@OneToMany(mappedBy = "operation", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OperationHistory> history;

	@PastOrPresent
	private LocalDate startDate;

	@PastOrPresent
	private LocalDate closingDate;

	private BigDecimal startPrice;

	private BigDecimal closingPrice;

	private String notes;

	public boolean isOpen() {
		return closingDate == null ? true : false;
	}

	public Integer getQuantity() {
		return history.stream().map(oper -> oper.getQuantity()).reduce(0, Integer::sum);
	}
	
	public String getAveragePrice() {
		BigDecimal sum = new BigDecimal("0");
		Integer q = 0;
		BigDecimal average = new BigDecimal("0");
		for (OperationHistory oper : history) {
			q += oper.getQuantity();
			if (oper.getQuantity() > 0) {
				sum = sum.add(oper.getPrice().multiply(new BigDecimal(oper.getQuantity())));
				average = sum.divide(new BigDecimal(String.valueOf(q)),2,RoundingMode.HALF_DOWN);
			} else {
				sum = average.multiply(new BigDecimal(String.valueOf(q)));
			}
		}
		return average.toString();
	}

}
