package dev.hgsaldanha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyStocksNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3263208108892574089L;

	public MyStocksNotFoundException(String message) {
		super(message);
	}

}
