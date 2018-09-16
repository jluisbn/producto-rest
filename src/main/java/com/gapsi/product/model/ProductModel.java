package com.gapsi.product.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gapsi.product.connection.Connection;
import com.gapsi.product.dao.Productos;


public class ProductModel {

	Connection conn = new Connection();
	private static Logger logger = Logger.getAnonymousLogger();
	
	public int addProduct(String nombre, String descripcion, String precio, String modelo, String status) throws SQLException
	{
		logger.log(Level.INFO, "Executing addProduct");
		int added = 0;
		String query = "insert into producto (nombre, descripcion, precio, modelo, status) values (?, ?, ?, ?, ?)";
		java.sql.Connection conn = getConnection();
	    PreparedStatement preparedStmt;
	    
		try 
		{
			preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		    preparedStmt.setString (1, nombre);
		    preparedStmt.setString (2, descripcion);
		    preparedStmt.setString (3, precio);
		    preparedStmt.setString (4, modelo);
		    preparedStmt.setString (5, status);
		    preparedStmt.executeUpdate();
		    ResultSet rs = preparedStmt.getGeneratedKeys();
		    
		    while (rs.next())
		    	added = rs.getInt(1);
		    
		    logger.log(Level.INFO, "Query finalized with no errors, result: " + added);
		    
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Query finalized with errors." + e.getMessage());
			added = -1;
		} finally {
			conn.close();
			logger.log(Level.INFO, "Finally executed");
		}

		return added;
	}
	
	public int updateProduct(int id, String descripcion, String modelo) throws SQLException {
		logger.log(Level.INFO, "Executing updateProduct");
		int updated = 0;
		String query = "update producto set descripcion = ?, modelo = ? where id = ?";
		java.sql.Connection conn = getConnection();
	    PreparedStatement preparedStmt;
	    
		try 
		{
			preparedStmt = conn.prepareStatement(query);
		    preparedStmt.setString(1, descripcion);
		    preparedStmt.setString(2, modelo);
		    preparedStmt.setInt(3, id);
		    preparedStmt.executeUpdate();
		    updated = preparedStmt.getUpdateCount();
		    logger.log(Level.INFO, "Query finalized with no errors, result: " + updated);
		    
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Query finalized with errors." + e.getMessage());
			updated = -1;
		} finally {
			conn.close();
			logger.log(Level.INFO, "Finally executed");
		}
		
		return updated;
	}

	public ArrayList<Productos> getProductos(Integer id) 
	{
		logger.log(Level.INFO, "Executing getProductos id = " + id);
		ArrayList<Productos> productos = new ArrayList<Productos>();
		String query = "SELECT id, status, nombre, descripcion, precio, modelo FROM producto ";
		logger.log(Level.INFO, query);
		try 
		{
			java.sql.Connection conn = getConnection();
			Statement st = conn.createStatement();
			if (!id.equals(0))
				query = query.concat("WHERE id = " + id);
				
			ResultSet rs = st.executeQuery(query);
			
			while (rs.next()) 
			{
				Productos student = new Productos(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				productos.add(student);
			}
			
			logger.log(Level.INFO, "Query finalized with no errors");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			logger.log(Level.INFO, "Finally executed");
		}
		return productos;
	}

	private java.sql.Connection getConnection() 
	{
		java.sql.Connection conn = null;
		try {
			conn = this.conn.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
