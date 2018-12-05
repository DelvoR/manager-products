package br.com.mateus.manager.products.exceptions;

public class LinhaNaoSelecionadaException extends Exception {

	private static final long serialVersionUID = -7038297147447932719L;

	public LinhaNaoSelecionadaException() {

	}

	public LinhaNaoSelecionadaException(String message) {
		super(message);
	}

	public LinhaNaoSelecionadaException(String message, Throwable cause) {
		super(message, cause);
	}

}
