package br.ufc.banco.contas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContaPoupancaTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRendeJuros() {
		ContaPoupanca teste = new ContaPoupanca("1");
		
		teste.creditar(100);
		double saldo = teste.obterSaldo();
		
		teste.rendeJuros(0.01);
		
		assertEquals(saldo+(saldo*0.01), teste.obterSaldo(), 0);
	
	}

}
