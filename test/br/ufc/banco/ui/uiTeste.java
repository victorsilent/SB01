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
	public void testConnect() throws Exception{
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/","root","");
		}
		catch(Exception e){
			System.out.println("Errrroooo...Driver não encontrado "+e);
		}
	}

}
