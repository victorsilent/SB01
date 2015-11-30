package br.ufc.banco.excecoes;

public class TNRException extends Exception {

	private static final long serialVersionUID = 1L;

	private Exception causa;

	public TNRException(Exception exception) {
		super("Transação não Realizada!");
		this.causa = exception;
	}

	public String getMessage() {
		return "Transferencia não Realizada! Causa: " + causa.getMessage();
	}

	public Exception getCausa() {
		return causa;
	}

}
