package br.ufc.banco.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BBUI {
	
	private JFrame janela;
	private JPanel painelPrincipal;
	
	public static void main(String[] args) {
		new BBUI().montaTela();
	}
	
	public void montaTela() {
		preparaJanela();
		preparaPainelPrincipal();
		preparaBotoes();
		mostraJanela();
	}
	
	public void preparaJanela() {
		janela = new JFrame("Banco do Brasil");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void preparaPainelPrincipal() {
		painelPrincipal = new JPanel();
		//Definir layout
		janela.add(painelPrincipal);
	}
	
	public void preparaBotoes() {
		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//String numeroConta = JOptionPane.showInputDialog("Digite o número da conta");
				//Lógica de cadastro
			}
		});
		painelPrincipal.add(botaoCadastrar);
		
		JButton botaoDeposito = new JButton("Depósito");
		botaoDeposito.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//String numeroConta = JOptionPane.showInputDialog("Digite o número da conta");
				//Lógica de depósito
			}
		});
		painelPrincipal.add(botaoDeposito);
		
		//Outros botões...
	}
	
	public void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
	}

}
