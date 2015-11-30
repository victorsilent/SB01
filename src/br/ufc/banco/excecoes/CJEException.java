package br.ufc.banco.excecoes;

public class CJEException extends Exception {

	private static final long serialVersionUID = 1L;

	private String numero;

	public CJEException(String numero) {
		super("Conta Já Existente!");
		this.numero = numero;
	}

	public String numeroConta() {
		return numero;
	}

}
