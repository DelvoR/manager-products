package br.com.mateus.manager.products.exceptions;

public class CadastrarException extends Exception {

	private static final long serialVersionUID = -338218038067740068L;

	public CadastrarException() {

	}

	public CadastrarException(String message) {
		super(message);
	}

	public CadastrarException(String message, Throwable cause) {
		super(message, cause);
	}

}