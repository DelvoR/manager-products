package br.com.mateus.manager.products.exceptions;

public class BuscarException extends Exception {

	private static final long serialVersionUID = -583898971044499061L;

	public BuscarException() {

	}

	public BuscarException(String message) {
		super(message);
	}

	public BuscarException(String message, Throwable cause) {
		super(message, cause);
	}

}
