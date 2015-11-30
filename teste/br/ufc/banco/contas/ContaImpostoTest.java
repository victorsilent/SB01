package br.ufc.banco.contas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ContaImpostoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDebitarNormal() {
		ContaImposto teste = new ContaImposto("1");
		teste.creditar(100);
	
		
	}

}
