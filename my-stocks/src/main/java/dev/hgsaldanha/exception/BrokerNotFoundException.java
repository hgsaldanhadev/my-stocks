package dev.hgsaldanha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BrokerNotFoundException extends MyStocksException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -212170163180938915L;
	
	public BrokerNotFoundException() {
		super("Broker not found.");
	}

}
