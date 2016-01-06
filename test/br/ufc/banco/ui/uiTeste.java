package br.ufc.banco.ui;

import static org.junit.Assert.*;
import java.sql.*;

import org.junit.Before;
import org.junit.Test;

public class uiTeste {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void testConnect() throws SQLException{
		Class.forName("com.mysql.jdbc.Driver"); 
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/","root","");
	}

}
