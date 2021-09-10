package dev.hgsaldanha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StockNotFoundException extends MyStocksException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1639063175598408745L;
	
	public StockNotFoundException() {
		super("Stock not found.");
	}

}
