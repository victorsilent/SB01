package br.ufc.banco.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				
				String[] tipos = {"Comum","Especial","Poupança","Imposto"};
				int tipoConta = JOptionPane.showOptionDialog(null, "Escolha o tipo de conta", "Tipo de Conta", 
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, tipos, 0);
				if(tipoConta >= 0) {
					String numeroConta = JOptionPane.showInputDialog("Digite o número da conta");
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
							JOptionPane.showMessageDialog(null, "Valor inválido");
						}
					}
				}
			}
		});
		painelPrincipal.add(botaoCadastrar);
	}
	
	public void preparaBotaoDeposito() {
		JButton botaoDeposito = new JButton("Fazer Depósito");
		botaoDeposito.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroConta = JOptionPane.showInputDialog("Digite o número da conta");
				if(numeroConta != null) {
					String valor = JOptionPane.showInputDialog("Digite o valor a ser creditado");
					if(valor != null) {
						try {
							double valorDouble = Double.parseDouble(valor);
							banco.creditar(numeroConta, valorDouble);
							JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!" + valorDouble);
						} catch (NumberFormatException nfex) {
							JOptionPane.showMessageDialog(null, "Valor Inválido");
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
				String numeroConta = JOptionPane.showInputDialog("Digite o número da conta");
				if(numeroConta != null) {
					String valor = JOptionPane.showInputDialog("Digite o valor a ser debitado");
					if(valor != null) {
						try {
							double valorDouble = Double.parseDouble(valor);
							banco.debitar(numeroConta, valorDouble);
							JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!");
						} catch(NumberFormatException nfex) {
							JOptionPane.showMessageDialog(null, "Valor inválido.");
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
		JButton botaoTransf = new JButton("Transferência");
		botaoTransf.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroContaOrig = JOptionPane.showInputDialog("Digite o número da conta de origem");
				if(numeroContaOrig != null) {
					String numeroContaDest = JOptionPane.showInputDialog("Digite o número da conta de destino");
					if(numeroContaDest != null) {
						String valor = JOptionPane.showInputDialog("Digite o valor a ser transferido");
						if(valor != null) {
							try {
								double valorDouble = Double.parseDouble(valor);
								banco.transferir(numeroContaOrig, numeroContaDest, valorDouble);
								JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!");
							} catch(NumberFormatException nfex) {
								JOptionPane.showMessageDialog(null, "Valor inválido");
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
				String numeroConta = JOptionPane.showInputDialog("Digite o número da conta");
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
				String numeroConta = JOptionPane.showInputDialog("Digite o número da conta");
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
				String numeroConta = JOptionPane.showInputDialog("Digite o número da conta");
				if(numeroConta != null) {
					try {
						banco.renderJuros(numeroConta);
						JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
					} catch(TNRException tnrex) {
						JOptionPane.showMessageDialog(null, tnrex.getMessage());
					}
				}
			}
		});
		painelPrincipal.add(botaoJuros);
	}
	
	public void preparaBotaoBonus() {	
		JButton botaoBonus = new JButton("Render Bônus");
		botaoBonus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String numeroConta = JOptionPane.showInputDialog("Digite o número da conta");
				if(numeroConta != null) {
					try {
						banco.renderBonus(numeroConta);
						JOptionPane.showMessageDialog(null, "Operação realizada com sucesso!");
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
		new BBUI(new BancoBrasil(new ManipuladorArquivos())).montaTela();
	}

}
