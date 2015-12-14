package br.ufc.banco.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.ufc.banco.bb.BancoBrasil;
import br.ufc.banco.bb.excecoes.TNRException;
import br.ufc.banco.conta.Conta;
import br.ufc.banco.conta.ContaAbstrata;
import br.ufc.banco.conta.ContaEspecial;
import br.ufc.banco.conta.ContaImposto;
import br.ufc.banco.conta.ContaPoupanca;
import br.ufc.banco.dados.ManipuladorArquivos;
import br.ufc.banco.dados.excecoes.CEException;
import br.ufc.banco.dados.excecoes.CIException;

public class BBUI {
	
	private BancoBrasil banco;
	static ManipuladorArquivos manipulador = new ManipuladorArquivos();
	
	private JFrame janela;
	private JPanel painelPrincipal;
	
	public BBUI(BancoBrasil banco) {
		this.banco = banco;
	}
	
	public void montaTela() {
		preparaJanela();
		preparaPainelPrincipal();
		preparaBotaoCadastrar();
		preparaBotaoDeposito();
		preparaBotaoSaque();
		preparaBotaoTransf();
		preparaBotaoSaldo();
		preparaBotaoRemover();
		preparaBotaoJuros();
		preparaBotaoBonus();
		mostraJanela();
	}
	
	public void preparaJanela() {
		janela = new JFrame("Banco do Brasil");
		janela.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				manipulador.atualizarArquivo();
				e.getWindow().dispose();
			}
		});
	}
	
	public void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new GridLayout(0, 1));
		janela.add(painelPrincipal);
	}
	
	public void preparaBotaoCadastrar() {
		JButton botaoCadastrar = new JButton("Cadastrar Conta");
		botaoCadastrar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ContaAbstrata conta = null;
				
				String[] tipos = {"Comum","Especial","Poupan�a","Imposto"};
				int tipoConta = JOptionPane.showOptionDialog(null, "Escolha o tipo de conta", "Tipo de Conta", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipos, 0);
				if(tipoConta >= 0) {
					String numeroConta = JOptionPane.showInputDialog("Digite o n�mero da conta");
					if(numeroConta != null) {
						try {
							Integer.parseInt(numeroConta);
							
							switch (tipoConta) {
							case 0:
								conta = new Conta(numeroConta);
								break;
							case 1:
								conta = new ContaEspecial(numeroConta);
								break;
							case 2:
								conta = new ContaPoupanca(numeroConta);
								break;
							case 3:
								conta = new ContaImposto(numeroConta);
								break;
							default:
								break;
							}
							
							try {
								banco.cadastrar(conta);
								JOptionPane.showMessageDialog(null, "Conta criada com sucesso!");
							} catch (CEException ceex) {
								JOptionPane.showMessageDialog(null, ceex.getMessage());
							}
						
						} catch(NumberFormatException nfex) {
							JOptionPane.showMessageDialog(null, "Valor inv�lido");
						}
					}
				}
			}
		});
		painelPrincipal.add(botaoCadastrar);
	}
	
	public void preparaBotaoDeposito() {
		JButton botaoDeposito = new JButton("Fazer Dep�sito");
		botaoDeposito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroConta = JOptionPane.showInputDialog("Digite o numero da conta");
				if(numeroConta != null) {
					String valor = JOptionPane.showInputDialog("Digite o valor a ser creditado");
					if(valor != null) {
						try {
							double valorDouble = Double.parseDouble(valor);
							banco.creditar(numeroConta, valorDouble);
							JOptionPane.showMessageDialog(null, "Dep�sito realizado com sucesso!" + valorDouble);
						} catch (NumberFormatException nfex) {
							JOptionPane.showMessageDialog(null, "Valor Inv�lido");
						} catch (TNRException tnrex) {
							JOptionPane.showMessageDialog(null, tnrex.getMessage());
						}
					}
				}
			}
		});
		painelPrincipal.add(botaoDeposito);
	}	
	
	public void preparaBotaoSaque() {
		JButton botaoSaque = new JButton("Realizar Saque");
		botaoSaque.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroConta = JOptionPane.showInputDialog("Digite o n�mero da conta");
				if(numeroConta != null) {
					String valor = JOptionPane.showInputDialog("Digite o valor a ser debitado");
					if(valor != null) {
						try {
							double valorDouble = Double.parseDouble(valor);
							banco.debitar(numeroConta, valorDouble);
							JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
						} catch(NumberFormatException nfex) {
							JOptionPane.showMessageDialog(null, "Valor inv�lido.");
						} catch (TNRException tnrex) {
							JOptionPane.showMessageDialog(null, tnrex.getMessage());
						}
					}
				}
			}
		});
		painelPrincipal.add(botaoSaque);
	}
	
	public void preparaBotaoTransf() {	
		JButton botaoTransf = new JButton("Transfer�ncia");
		botaoTransf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroContaOrig = JOptionPane.showInputDialog("Digite o n�mero da conta de origem");
				if(numeroContaOrig != null) {
					String numeroContaDest = JOptionPane.showInputDialog("Digite o n�mero da conta de destino");
					if(numeroContaDest != null) {
						String valor = JOptionPane.showInputDialog("Digite o valor a ser transferido");
						if(valor != null) {
							try {
								double valorDouble = Double.parseDouble(valor);
								banco.transferir(numeroContaOrig, numeroContaDest, valorDouble);
								JOptionPane.showMessageDialog(null, "Transfer�ncia realizada com sucesso!");
							} catch(NumberFormatException nfex) {
								JOptionPane.showMessageDialog(null, "Valor inv�lido");
							} catch (TNRException tnrex) {
								JOptionPane.showMessageDialog(null, tnrex.getMessage());
							}
						}
					}
				}
			}
		});
		painelPrincipal.add(botaoTransf);
	}
	
	public void preparaBotaoSaldo() {	
		JButton botaoVerSaldo = new JButton("Ver Saldo");
		botaoVerSaldo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroConta = JOptionPane.showInputDialog("Digite o n�mero da conta");
				if(numeroConta != null) {
					try {
						double saldo = banco.saldo(numeroConta);
						JOptionPane.showMessageDialog(null, "Saldo: " + saldo);
					} catch (TNRException tnrex) {
						JOptionPane.showMessageDialog(null, tnrex.getMessage());
					}
				}
			}
		});
		painelPrincipal.add(botaoVerSaldo);
	}	
		
	public void preparaBotaoRemover() {
		JButton botaoRemover = new JButton("Remover Conta");
		botaoRemover.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroConta = JOptionPane.showInputDialog("Digite o n�mero da conta");
				if(numeroConta != null) {
					try {
						banco.remover(numeroConta);
						JOptionPane.showMessageDialog(null, "Conta removida com sucesso!");
					} catch(CIException ciex) {
						JOptionPane.showMessageDialog(null, ciex.getMessage());
					}
				}
			}
		});
		painelPrincipal.add(botaoRemover);
	}
	
	public void preparaBotaoJuros() {	
		JButton botaoJuros = new JButton("Render Juros");
		botaoJuros.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroConta = JOptionPane.showInputDialog("Digite o n�mero da conta");
				if(numeroConta != null) {
					try {
						banco.renderJuros(numeroConta);
						JOptionPane.showMessageDialog(null, "Opera��o realizada com sucesso!");
					} catch(TNRException tnrex) {
						JOptionPane.showMessageDialog(null, tnrex.getMessage());
					}
				}
			}
		});
		painelPrincipal.add(botaoJuros);
	}
	
	public void preparaBotaoBonus() {	
		JButton botaoBonus = new JButton("Render B�nus");
		botaoBonus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroConta = JOptionPane.showInputDialog("Digite o n�mero da conta");
				if(numeroConta != null) {
					try {
						banco.renderBonus(numeroConta);
						JOptionPane.showMessageDialog(null, "Opera��o realizada com sucesso!");
					} catch(TNRException tnrex) {
						JOptionPane.showMessageDialog(null, tnrex.getMessage());
					}
				}
			}
		});
		painelPrincipal.add(botaoBonus);
	}
	
	public void mostraJanela() {
		janela.pack();
		janela.setSize(480, 480);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}
	
	public static void main(String[] args) {
		new BBUI(new BancoBrasil(manipulador)).montaTela();
		
	}
}
