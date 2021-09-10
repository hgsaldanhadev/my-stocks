package dev.hgsaldanha.exception;

import java.net.URISyntaxException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class MyStocksException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3263208108892574089L;

	public MyStocksException(String message) {
		super(message);
	}

	public MyStocksException(String message, URISyntaxException e) {
		super(message, e);
	}

}
