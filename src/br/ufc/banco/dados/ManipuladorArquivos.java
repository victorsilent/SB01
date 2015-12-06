package br.ufc.banco.dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ManipuladorArquivos implements IRepositorioContas {
	
	private File contas = new File("contas.txt");
	private BufferedReader br;
	
	public ManipuladorArquivos(){
		
		if (!contas.exists()){
			try {
				new File("contas.txt").createNewFile();
			} catch (IOException e) {
				e.getMessage();
			}	
		}
		
	}	
	
	public void lerArquivo(){
		try{
			FileInputStream stream = new FileInputStream("contas.txt");
			InputStreamReader reader = new InputStreamReader(stream);
			br = new BufferedReader(reader);
			String linha = br.readLine();
        
			while(linha != null){
				System.out.println(linha);
				linha = br.readLine();
			}
		}catch(FileNotFoundException fnfe) { 
			System.out.println(fnfe.getMessage());
		} 
		catch(IOException ioe){
			System.out.println(ioe.getMessage());
		}
}
	@Override
	public void inserir(ContaAbstrata conta) throws CEException {
		
	}

	@Override
	public void apagar(String numero) throws CIException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ContaAbstrata procurar(String numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContaAbstrata[] listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numeroContas() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}