package br.com.mateus.manager.products.exceptions;

public class AtualizarException extends Exception {

	private static final long serialVersionUID = -3746423550179135997L;

	public AtualizarException() {

	}

	public AtualizarException(String message) {
		super(message);
	}

	public AtualizarException(String message, Throwable cause) {
		super(message, cause);
	}

}
