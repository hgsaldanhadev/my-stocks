package dev.hgsaldanha.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hgsaldanha.dto.StockDTO;
import dev.hgsaldanha.exception.MyStocksException;
import dev.hgsaldanha.exception.StockNotFoundException;
import dev.hgsaldanha.model.Stock;
import dev.hgsaldanha.service.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

	@Autowired
	private StockService stockService;
	
	@Autowired
	private ModelMapper mm;

	@GetMapping
	public List<Stock> getStocks() {
		return stockService.getStocks();
	}

	@GetMapping("/{id}")
	public Stock getStock(@PathVariable Integer id) throws StockNotFoundException {
		return stockService.getStock(id);
	}
	
	@PostMapping
	public ResponseEntity<String> createStock(@Valid @RequestBody StockDTO stockDto) throws MyStocksException {
		Stock stock = mm.map(stockDto, Stock.class);
		Integer id = stockService.addStock(stock);
		try {
			return ResponseEntity.created(new URI("/api/stocks/" + id)).build();
		} catch (URISyntaxException e) {
			throw new MyStocksException("Error creating a new Stock.", e);
		}
	}

	@PutMapping("/{id}")
	public void updateStock(@PathVariable Integer id, @Valid @RequestBody StockDTO stockDto) throws StockNotFoundException {
		Stock stock = mm.map(stockDto, Stock.class);
		stock.setId(id);
		stockService.updateStock(stock);
	}

	@DeleteMapping("/{id}")
	public void deleteStock(@PathVariable Integer id) throws StockNotFoundException {
		stockService.deleteStock(id);
	}
}
