package br.ufc.banco.conta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.excecoes.SIException;

public class ContaTest {
	
	private Conta teste;
	
	@Before
	public void setUp() throws Exception {
		teste = new Conta("1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreditarNormal() {
		teste.creditar(10);
		assertEquals(10, teste.obterSaldo(), 0);;
	}
	
	@Test
	public void testCreditarNegativo() {
		teste.creditar(-10);
		assertEquals(10, teste.obterSaldo(), 0);
	}
	
	@Test
	public void testDebitarNormal() throws SIException {
		teste.creditar(10);
		teste.debitar(5);
		assertEquals(5, teste.obterSaldo(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDebitarNegativo() throws SIException {
		teste.creditar(10);
		teste.debitar(-5);
	}
	
	@Test(expected = SIException.class)
	public void testDebitarSaldoInsuficiente() throws SIException{
		teste.creditar(10);
		teste.debitar(100);
	}
	

}
