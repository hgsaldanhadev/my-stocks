package dev.hgsaldanha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	//TODO testar retorno em caso de entity not found exception
	@GetMapping("/{id}")
	public Stock getStock(Long id) {
		return stockService.getStock(id);
	}
}
