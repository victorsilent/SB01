package br.ufc.banco.ui;

import static org.junit.Assert.*;
import java.sql.*;

import org.junit.Before;
import org.junit.Test;

public class uiTeste {

	@Before
	public void setUp() throws Exception {
		
	}

	@Test(expected=Exception.class)
	public void testeStat() throws Exception{
		try{
			Class.forName("org.postgresql.Driver").newInstance(); 
			Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/teste2","postgres","admin123");
			connection.createStatement().execute("alter session set current_schema=trabalho");
			Statement stmt = null;
			stmt = connection.createStatement();
			String query = "CREATE TABLE meubanco " +
		        "(id INTEGER not NULL, " +
		        " age INTEGER, " + 
		        " PRIMARY KEY ( id ))"; 
		
			stmt.executeUpdate(query);
		}catch(Exception e) {
            		throw new Exception(e);
		}
		
	}
}
