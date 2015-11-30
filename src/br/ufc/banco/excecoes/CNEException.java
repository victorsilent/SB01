package br.ufc.banco.excecoes;

public class CNEException extends Exception {

	private static final long serialVersionUID = 1L;

	private String numero;

	public CNEException(String numero) {
		super("Conta Não Existente!");
		this.numero = numero;
	}

	public String getMessage() {
		return "Conta Não Existente: conta = " + numero;
	}

	public String numeroConta() {
		return numero;
	}

}
