package br.com.mateus.manager.products.exceptions;

public class TabelaVaziaException extends Exception {

	private static final long serialVersionUID = -7275708503465557701L;

	public TabelaVaziaException() {

	}

	public TabelaVaziaException(String message) {
		super(message);
	}

	public TabelaVaziaException(String message, Throwable cause) {
		super(message, cause);
	}

}
