package br.ufc.banco.ui;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Before;
import org.junit.Test;

public class uiTeste {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testConnect() {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/","root","");
	}

}
