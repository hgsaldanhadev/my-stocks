package dev.hgsaldanha.exception;

public class BrokerNotFoundException extends MyStocksNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -212170163180938915L;
	
	public BrokerNotFoundException() {
		super("Broker not found.");
	}

}
