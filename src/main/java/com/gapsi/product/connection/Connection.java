package com.gapsi.product.connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connection {

	private String userName = "root";
	private String password = "Ing.Bell0Nieves";
	private String serverName = "localhost";
	private String dataBase = "tienda";
	private String portNumber = "3306";

	public java.sql.Connection getConnection() throws SQLException {

		String connectionString = "jdbc:mysql://" + this.serverName + ":" + this.portNumber + "/" + dataBase;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", this.userName);
	    connectionProps.put("password", this.password);

	    java.sql.Connection con =
                //DriverManager.getConnection(connectionString, connectionProps);
	    		//DriverManager.getConnection(connectionString, this.userName, this.password);
	    		DriverManager.getConnection(connectionString + "?user=" + this.userName + "&password=" + this.password + "&useSSL=false");
	    System.out.println("Connected to database");
	    return con;
	}
}
