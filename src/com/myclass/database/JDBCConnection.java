package com.myclass.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
	private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	private final static String URL = "jdbc:mysql://localhost:3306/crm?useSSL=false";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "hautrumne";
	
	public static Connection getConnection() {
		
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}catch (ClassNotFoundException e) {
			System.out.println("Not found DRIVER!");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("Not found Database!");
			e.printStackTrace();
		}
		return null;
	}
}
