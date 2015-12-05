package br.ufc.banco.dados;

import java.util.ArrayList;

import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ArrayContas implements IRepositorioContas {

	private ArrayList<ContaAbstrata> contas;

	public ArrayContas() {
		this.contas = new ArrayList<>();
	}

	public void apagar(String numero) throws CIException {
		if (this.procurar(numero) != null) {
			for (int i = 0; i < contas.size(); i++) {
				if (contas.get(i) != null && contas.get(i).obterNumero().equals(numero)) {
					contas.remove(i);
				}
			}
		} else {
			throw new CIException(numero);
		}
	}

	public void inserir(ContaAbstrata conta) throws CEException {
		if (this.procurar(conta.obterNumero()) != null) {
			this.contas.add(conta);
		} else {
			throw new CEException(conta.obterNumero());
		}
	}

	public ContaAbstrata[] listar() {
		ContaAbstrata[] lista = new ContaAbstrata[contas.size()];
	
		for (int i = 0; i < contas.size(); i++) {
			lista[i] = this.contas.get(i);
		}
		
		return lista;
	}

	public int numeroContas() {
		return contas.size();
	}

	public ContaAbstrata procurar(String numero) {
		if (this.contas.size() > 0) {
			for (int i = 0; i < contas.size(); i++) {
				if (contas.get(i) != null && contas.get(i).obterNumero().equals(numero)) {
					return contas.get(i);
				}
			}
		}
		return null;
	}
}
