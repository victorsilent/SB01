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
	public void testeStat() throws Exception{
		
		Class.forName("org.postgresql.Driver").newInstance(); 
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bdvendas?currentSchema=trabalho","postgres","");
		
		Statement stmt = null;
		stmt = connection.createStatement();
		String query = "CREATE TABLE meubanco " +
                "(id INTEGER not NULL, " +
                " age INTEGER, " + 
                " PRIMARY KEY ( id ))"; 
		
		stmt.executeUpdate(query);
	}
}
