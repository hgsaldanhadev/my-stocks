package dev.hgsaldanha.model;

import java.math.BigDecimal;
import java.time.LocalDate;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	private StockBroker broker;

	@OneToOne(optional = false)
	private Stock stock;
	
	@OneToMany(mappedBy = "operation",cascade = CascadeType.ALL,orphanRemoval = true)
	private OperationHistory history;

	@Temporal(TemporalType.DATE)
	private LocalDate startDate;

	@Temporal(TemporalType.DATE)
	private LocalDate closingDate;

	private BigDecimal startPrice;

	private BigDecimal closingPrice;
	
	private String notes;

	public boolean isOpen() {
		return closingDate == null ? true : false;
	}

}
