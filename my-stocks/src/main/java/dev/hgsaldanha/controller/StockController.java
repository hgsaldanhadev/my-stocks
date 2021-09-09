package dev.hgsaldanha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.hgsaldanha.exception.StockNotFoundException;
import dev.hgsaldanha.model.Stock;
import dev.hgsaldanha.service.StockService;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping
	public List<Stock> getStocks() {
		return stockService.getStocks();
	}

	@GetMapping("/{id}")
	public Stock getStock(Integer id) throws StockNotFoundException {
		return stockService.getStock(id);
	}
}
