package br.com.mateus.manager.products.exceptions;

public class ExcluirException extends Exception {

	private static final long serialVersionUID = 5245676312358533336L;

	public ExcluirException() {

	}

	public ExcluirException(String message) {
		super(message);
	}

	public ExcluirException(String message, Throwable cause) {
		super(message, cause);
	}

}
