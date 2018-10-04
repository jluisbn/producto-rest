package com.gapsi.product.model;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gapsi.product.connection.Connection;

public class TestModel {

	static Connection connection = new Connection();
	private static Logger logger = Logger.getAnonymousLogger();
	
	public static String testDatabase() 
	{
		logger.log(Level.INFO, "Executing testDatabase");
		java.sql.Connection conn = getConnection();
		String status = conn.toString();
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return status;
	}
	
	private static java.sql.Connection getConnection() 
	{
		java.sql.Connection conn = null;
		try {
			conn = connection.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
