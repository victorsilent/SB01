package br.ufc.banco.contas;


public class ContaImposto extends ContaAbstrata {

	public ContaImposto(String numero) {
		super(numero);
	}

	public void debitar(double valor) {
		this.saldo = this.saldo - (valor + (valor * 0.001));
	}
}
