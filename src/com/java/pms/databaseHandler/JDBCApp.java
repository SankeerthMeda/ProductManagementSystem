package com.java.pms.databaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCApp {
	private static JDBCApp jdbcApp;
	private PreparedStatement ps;
	private Connection connection;

	public JDBCApp() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public PreparedStatement getPs() {
		return ps;
	}

	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public static JDBCApp getInstance() {
		if (jdbcApp != null) {
			jdbcApp = new JDBCApp();
		}
		return jdbcApp;
	}
	
	public void closeConnection(Connection connection) throws SQLException {
		if (connection != null)
			connection.close();
	}
}
