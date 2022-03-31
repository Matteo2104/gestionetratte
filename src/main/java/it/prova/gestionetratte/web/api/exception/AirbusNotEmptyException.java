package it.prova.gestionetratte.web.api.exception;

public class AirbusNotEmptyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AirbusNotEmptyException(String message) {
		super(message);
	}
}
