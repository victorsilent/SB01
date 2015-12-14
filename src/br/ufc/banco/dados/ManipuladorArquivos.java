package br.ufc.banco.dados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ManipuladorArquivos implements IRepositorioContas {
	private static final String arquivo = "contas.txt";
	private List<ContaAbstrata> contasFromFile;
	private File contas;
	private BufferedReader br;
	
	public ManipuladorArquivos(){
		contasFromFile = new ArrayList<ContaAbstrata>();
		contas = new File(arquivo);
		if (!contas.exists()){
			try {
				new File(arquivo).createNewFile();
			} catch (IOException e) {
				e.getMessage();
			}	
		} else {
			lerArquivo();
		}
	}	
	
	public void editarArquivo(double novoSaldo, int index) {
		BufferedReader mBufferedReader;
		String textReaded;
		String targetLine = "";
		String textUpdated = "";
		
		try {
			mBufferedReader = new BufferedReader(new FileReader("contas.txt"));
			while((textReaded = mBufferedReader.readLine()) != null) {
				String lineConta[] = textReaded.split(",");
				
				if(lineConta[0].equals(contasFromFile.get(index).obterNumero())) {
					targetLine = textReaded;
				}
				
				textUpdated += textReaded + System.lineSeparator();
			}
		} catch(IOException e) {			
			System.err.println("There was something wrong... " + e.getMessage());
		}
		
		int lastIndexOfPointAtClassName = contasFromFile.get(index).getClass().toString().lastIndexOf('.');
		String accountType = contasFromFile.get(index).getClass().toString().substring(lastIndexOfPointAtClassName + 1);
		
		textUpdated = textUpdated.replace(targetLine, contasFromFile.get(index).obterNumero() + "," + novoSaldo + "," + accountType);
		
		try(PrintStream mPrintStream = new PrintStream("contas.txt")) {
			mPrintStream.write(textUpdated.getBytes());
		} catch(IOException e) {
			System.err.println("There was something wrong here..." + e.getMessage());
		}
	}
	
	public void lerArquivo() {
		ContaAbstrata contaAbstrata;
		String textReaded;
		
		try(BufferedReader mBufferedReader = new BufferedReader(new FileReader(arquivo))) {
			while((textReaded = mBufferedReader.readLine()) != null) {
				String lineConta[] = textReaded.split(",");
				contaAbstrata = null;
				
				switch(lineConta[2]) {
					case "Conta":
						contaAbstrata = new Conta(lineConta[0]);
						break;
						
					case "ContaEspecial":
						contaAbstrata = new ContaEspecial(lineConta[0]);
						break;
						
					case "ContaPoupanca":
						contaAbstrata = new ContaPoupanca(lineConta[0]);
						break;
						
					case "ContaImposto":
						contaAbstrata = new ContaImposto(lineConta[0]);
						break;
				}
				
				contaAbstrata.creditar(Double.valueOf(lineConta[1]));
				contasFromFile.add(contaAbstrata);
				
			}
		} catch(IOException e) {			
			System.err.println("There was something wrong... " + e.getMessage());
		}
	}
	@Override
	public void inserir(ContaAbstrata conta) throws CEException {
		contasFromFile.add(conta);
	}

	@Override
	public void apagar(String numero) {		
		for (ContaAbstrata contas : contasFromFile) {
			if(contas.obterNumero().equals(numero)) {
				contasFromFile.remove(contasFromFile.indexOf(contas));
				return;
			}
		}
	}

	@Override
	public ContaAbstrata procurar(String numero) {

		for (ContaAbstrata c : contasFromFile){
			if(c.obterNumero().equals(numero)){
				return c;
			}
		}
		return null;
	}

	@Override
	public ContaAbstrata[] listar() {
		ContaAbstrata contas[] = new ContaAbstrata[contasFromFile.size()];
		contas = contasFromFile.toArray(contas);
		
		for(int i = 0; i < contas.length; i++) {
			System.out.println(contas[i].obterNumero());
		}
		
		return contas;
	}

	@Override
	public int numeroContas(){
		return contasFromFile.size();
	}
	
	public List<ContaAbstrata> getContasFromFile() {
		return contasFromFile;
	}
	
	public void setContasFromFile(List<ContaAbstrata> contas) {
		this.contasFromFile = contas;
	}
	
}
