package br.ufc.banco.dados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class ManipuladorArquivos implements IRepositorioContas {
	
	private ArrayList<ContaAbstrata> contasFromFile;
	private File contas = new File("contas.txt");
	private BufferedReader br;
	
	public ManipuladorArquivos(){
		contasFromFile = new ArrayList<>();
		if (!contas.exists()){
			try {
				new File("contas.txt").createNewFile();
			} catch (IOException e) {
				e.getMessage();
			}	
		} else {
			lerArquivo();
		}
	}	
	
	public void lerArquivo() {
		ContaAbstrata contaAbstrata;
		String textReaded;
		
		try(BufferedReader mBufferedReader = new BufferedReader(new FileReader("contas.txt"))) {
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
		System.out.println(contasFromFile.size());
		
		for (ContaAbstrata contas : contasFromFile) {
			if(contas.obterNumero().equals(numero)) {
				contasFromFile.remove(contasFromFile.indexOf(contas));
			}
		}
		
		System.out.println(contasFromFile.size());
	}

	@Override
	public ContaAbstrata procurar(String numero) {
		String file="contas.txt";
	       try{
	          FileReader input = new FileReader(file);
	          
	          BufferedReader bufferReader = new BufferedReader(input);
	          String line;
	          
	          while ((line = bufferReader.readLine()) != null)   {
	            String lineConta[] = line.split(",");
	            if(lineConta[0].equals(numero)) {
	            	ContaAbstrata conta = null;
	            	if(lineConta[2].equals("Conta")) {
	            		conta = new Conta(lineConta[0]);
	            	}
	            	if(lineConta[2].equals("ContaEspecial")) {
	            		conta = new ContaEspecial(lineConta[0]);
	            	}
	            	if(lineConta[2].equals("ContaImposto")) {
	            		conta = new ContaImposto(lineConta[0]);
	            	}
	            	if(lineConta[2].equals("ContaPoupanca")) {
	            		conta = new ContaPoupanca(lineConta[0]);
	            	}
            		conta.creditar(Double.parseDouble(lineConta[1]));
	            	return conta;
	            }
	          }
	          bufferReader.close();
	       } catch(FileNotFoundException fnfe) { 
				System.out.println(fnfe.getMessage());
	       } catch(IOException ioe) {
				System.out.println(ioe.getMessage());
	       }
		return null;
	}

	@Override
	public ContaAbstrata[] listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numeroContas(){
		int count = 0;
		String file="contas.txt";
	       try{
	          FileReader input = new FileReader(file);
	          
	          BufferedReader bufferReader = new BufferedReader(input);
	          String line;
	          
	          while ((line = bufferReader.readLine()) != null)   {
	            count++;
	          }
	          bufferReader.close();
	          return count;
	       }catch(Exception e){
	          System.out.println("Erro ao ler a linha:" + e.getMessage());                      
	       }
	       return count;
	}
	
	public static void editarArquivo(ArrayList<ContaAbstrata> contas) {
		for (ContaAbstrata conta : contas) {
			try {
				String numero = conta.obterNumero();
				String saldo = String.valueOf(conta.obterSaldo());
				FileWriter fw = new FileWriter("contas.txt",true);
				BufferedWriter writer = new BufferedWriter(fw);
				writer.write(numero+","+saldo+","+conta.getClass().getSimpleName());
				fw.write(System.getProperty("line.separator"));
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<ContaAbstrata> getContasFromFile() {
		return contasFromFile;
	}
	
}
