package br.com.erp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class ConnectionFactory {
	protected Connection connection;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected String SQL;
	
	public ConnectionFactory() {
		getConnection();
	}
	
	private Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost/SGP", "postgres", "muce41");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection() throws SQLException {
		connection.close();
	}
}
