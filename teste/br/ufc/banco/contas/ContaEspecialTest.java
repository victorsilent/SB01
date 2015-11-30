package br.ufc.banco.contas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContaEspecialTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreditarNormal(){
		ContaEspecial teste = new ContaEspecial("1");
		teste.creditar(100);
		assertEquals(100, teste.obterSaldo(),0);
	}
	
	@Test
	public void testCreditarNegativo(){
		ContaEspecial teste = new ContaEspecial("1");
		teste.creditar(100);
		teste.creditar(-100);
		assertEquals(100, teste.obterSaldo(),0);
	}
	
	@Test
	public void testBonusNormal(){
		ContaEspecial teste = new ContaEspecial("1");
		int valor = 100;
		teste.creditar(valor);
		assertEquals(valor*0.01, teste.obterBonus(),0);
	}
	
	@Test
	public void testBonusNegativo(){
		ContaEspecial teste = new ContaEspecial("1");
		teste.creditar(-100);
		assertEquals(0, teste.obterBonus(),0);
	}
	
	@Test
	public void testRendeBonus(){
		ContaEspecial teste = new ContaEspecial("1");
		teste.creditar(100);
		
		double saldo = teste.obterSaldo();
		double bonus = teste.obterBonus();
		
		teste.rendeBonus();
		
		assertEquals(saldo+bonus,teste.obterSaldo(),0);
	}

}
