package dev.hgsaldanha;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import dev.hgsaldanha.model.Operation;
import dev.hgsaldanha.model.OperationHistory;

public class OperationTests {

	@Test
	public void givenOperationHistory_thenAveragePriceAndQuantity() {
		Operation operation = new Operation();
		operation.setHistory(Arrays.asList(new OperationHistory(LocalDate.now().minusDays(4), 100, new BigDecimal(2)),
				new OperationHistory(LocalDate.now().minusDays(3), 200, new BigDecimal(3)),
				new OperationHistory(LocalDate.now().minusDays(2), -100, new BigDecimal(4)),
				new OperationHistory(LocalDate.now().minusDays(1), 100, new BigDecimal(5))));
		assertThat(operation.getAveragePrice()).isEqualTo("3.45");
		assertThat(operation.getQuantity()).isEqualTo(300);
	}

}
