package dev.hgsaldanha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import dev.hgsaldanha.exception.StockNotFoundException;
import dev.hgsaldanha.model.Stock;
import dev.hgsaldanha.repository.StockRepository;

@Service
public class StockService {
	
	@Autowired
	private StockRepository stocks;

	public List<Stock> getStocks() {
		return stocks.findAll(Sort.by("ticker"));
	}

	public Stock getStock(Integer id) throws StockNotFoundException {
		return stocks.findById(id).orElseThrow(() -> new StockNotFoundException());
	}

}
