package br.ufc.banco.conta;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContaEspecialTest {
	ContaEspecial conta;

	@Before
	public void setUp() throws Exception {
		conta = new ContaEspecial("123");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testeCreditar() {
		conta.creditar(10);
		assertEquals(10, conta.obterSaldo(), 0);
	}
	
	@Test
	public void testCreditarNegativo(){
		conta.creditar(-10);
		assertEquals(0,conta.obterSaldo(),0);
	}
	
	@Test
	public void testBonusNormal(){
		conta.creditar(10);
		double valor = (10*0.01);
		assertEquals(valor,conta.obterBonus(),0);
	}
	
	@Test
	public void testBonusNegativo(){
		conta.creditar(-10);
		assertEquals(0,conta.obterBonus(),0);
	}
	
	@Test
	public void testRenderBonus(){
		conta.creditar(10);
		assertEquals(0.1,conta.obterBonus(),0);
		conta.rendeBonus();
		assertEquals(10.1,conta.obterSaldo(),0);
	}

}
