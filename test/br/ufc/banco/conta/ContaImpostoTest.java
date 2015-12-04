package br.ufc.banco.conta;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.ufc.banco.conta.excecoes.SIException;

public class ContaImpostoTest {
	ContaImposto conta;

	@Before
	public void setUp() throws Exception {
		conta = new ContaImposto("123");
	}

	@Test
	public void testDebitarNormal() throws SIException {
		conta.creditar(1000);
		conta.debitar(100);
		assertEquals(1000 - (100 + (100*0.001)), conta.obterSaldo(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDebitarNegativo() throws SIException {
		conta.debitar(-100);
	}

}
