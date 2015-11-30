package br.ufc.banco.auditoria;

import br.ufc.banco.bb.IBanco;

public class AuditorBancoGenerico {

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public AuditorBancoGenerico(String nome) {
		super();
		this.nome = nome;
	}

	public void auditaBanco(IBanco banco) {
		if ((banco.saldoTotal() / banco.numeroContas()) > 500) {
			System.out.print("Aprovado!");
		} else {
			System.out.print("N�o aprovado!");
		}
	}

}
