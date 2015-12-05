package br.ufc.banco.conta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ContaPoupancaTest {
	
	private ContaPoupanca teste;
	
	@Before
	public void setUp() throws Exception {
		teste = new ContaPoupanca("1");
	}

	@Test
	public void testRendeJuros() {
		teste.creditar(50);
		teste.rendeJuros(0.01);
		assertEquals(50+(50*0.01), teste.obterSaldo(), 0);
	}

}
