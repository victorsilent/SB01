package br.ufc.banco.contas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContaTest {
	
	
	@Before
	public void setUp() throws Exception {
	}
	
	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testCreditarNormal() {
		Conta teste = new Conta("1");
		teste.creditar(100);
		assertEquals(100, teste.obterSaldo() , 0);
		
	}
	
	@Test
	public void testCreditarNegativo() {
		Conta teste = new Conta("1");
		teste.creditar(100);
		teste.creditar(-100);
		assertEquals(100, teste.obterSaldo() , 0);
	}
	

	@Test
	public void testDebitarNormal() {
		Conta teste = new Conta("1");
		teste.creditar(100);
		teste.debitar(50);
		assertEquals(50, teste.obterSaldo(), 0);
	}
	
	@Test
	public void testDebitarNegativo() {
		Conta teste = new Conta("1");
		teste.creditar(100);
		teste.debitar(-50);
		assertEquals(100, teste.obterSaldo(), 0);
	}

	

}
