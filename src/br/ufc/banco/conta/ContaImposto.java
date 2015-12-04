package br.ufc.banco.conta;

import br.ufc.banco.conta.excecoes.SIException;

public class ContaImposto extends ContaAbstrata {

	public ContaImposto(String numero) {
		super(numero);
	}

	public void debitar(double valor) throws SIException {
		double imposto = valor * 0.001;
		if(valor < 0)
			throw new IllegalArgumentException();
		else if(this.saldo >= valor + imposto)
			this.saldo = this.saldo - (valor + imposto);
		else
			throw new SIException(this.numero, this.saldo);
	}
}
