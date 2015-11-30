package br.ufc.banco.auditoria;

import br.ufc.banco.bb.BancoBrasil;
import br.ufc.banco.bb.IBanco;
import br.ufc.banco.dados.IRepositorioContas;
import br.ufc.banco.dados.VectorContas;

public class Auditoria {

	public static void main(String args[]) {
		IRepositorioContas repositorio = new VectorContas();
		IBanco banco = new BancoBrasil(repositorio);
		AuditorBancoGenerico auditor = new AuditorBancoGenerico("Auditor Mor");
		auditor.auditaBanco(banco);
	}
}
