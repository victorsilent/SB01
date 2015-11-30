package br.ufc.banco.contas;

public class Conta extends ContaAbstrata {

	public Conta(String numero) {
		super(numero);
	}

	public void debitar(double valor) {
		this.saldo = this.saldo - valor;
	}
}